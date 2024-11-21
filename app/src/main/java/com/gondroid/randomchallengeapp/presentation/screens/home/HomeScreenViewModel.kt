package com.gondroid.randomchallengeapp.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gondroid.randomchallengeapp.data.FakeTaskLocalDataSource
import com.gondroid.randomchallengeapp.presentation.screens.home.HomeScreenAction.OnDeleteAllTasks
import com.gondroid.randomchallengeapp.presentation.screens.home.HomeScreenAction.OnDeleteTask
import com.gondroid.randomchallengeapp.presentation.screens.home.HomeScreenAction.OnToggleTask
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class HomeScreenViewModel() : ViewModel() {

    private val taskLocalDataSource = FakeTaskLocalDataSource

    var state by mutableStateOf(HomeDataState())
        private set

    private val eventChannel = Channel<HomeScreenEvent>() // just send one time
    val events = eventChannel.receiveAsFlow()

    init {

        state = state.copy(
            date = LocalDate.now().let {
                DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy").format(it)
            }
        )

        taskLocalDataSource.tasksFlow.onEach { tasks ->

            val completedTasks = tasks
                .filter { task -> task.isCompleted }
                .sortedByDescending { task ->
                    task.date
                }
            val pendingTasks = tasks
                .filter { task ->
                    !task.isCompleted
                }.sortedByDescending { task ->
                    task.date
                }

            state = state.copy(
                summary = pendingTasks.size.toString(),
                completedTask = completedTasks,
                pendingTask = pendingTasks
            )
        }.launchIn(viewModelScope)

    }


    fun onAction(action: HomeScreenAction) {
        viewModelScope.launch {
            when (action) {
                is OnDeleteTask -> {
                    taskLocalDataSource.removeTask(action.task)
                    eventChannel.send(HomeScreenEvent.DeletedTask)
                }

                is OnToggleTask -> {
                    val updatedTask = action.task.copy(isCompleted = !action.task.isCompleted)
                    taskLocalDataSource.updateTask(updatedTask)
                    eventChannel.send(HomeScreenEvent.UpdatedTask)
                }

                OnDeleteAllTasks -> {
                    taskLocalDataSource.removeAllTasks()
                    eventChannel.send(HomeScreenEvent.AllTaskDeleted)
                }

                else -> Unit
            }
        }
    }
}