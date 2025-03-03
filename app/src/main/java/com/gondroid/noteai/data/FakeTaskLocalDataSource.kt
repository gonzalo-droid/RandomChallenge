package com.gondroid.noteai.data

import com.gondroid.noteai.domain.Task
import com.gondroid.noteai.domain.repository.TaskLocalDataSource
import com.gondroid.noteai.presentation.screens.task.providers.completedTask
import com.gondroid.noteai.presentation.screens.task.providers.pendingTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object FakeTaskLocalDataSource : TaskLocalDataSource {
    private val _tasksFlow = MutableStateFlow<List<Task>>(emptyList())

    init {
        _tasksFlow.value = completedTask + pendingTask
    }

    override val tasksFlow: Flow<List<Task>>
        get() = _tasksFlow

    override suspend fun addTask(task: Task) {
        val tasks = _tasksFlow.value.toMutableList()
        tasks.add(task)
        delay(1000L)
        _tasksFlow.value = tasks
    }

    override suspend fun updateTask(updatedTask: Task) {
        val tasks = _tasksFlow.value.toMutableList()
        val taskIndex = tasks.indexOfFirst { it.id == updatedTask.id }
        if (taskIndex != -1) {
            tasks[taskIndex] = updatedTask
            delay(1000L)
            _tasksFlow.value = tasks
        }
    }

    override suspend fun removeTask(task: Task) {
        val tasks = _tasksFlow.value.toMutableList()
        tasks.remove(task)
        delay(1000L)
        _tasksFlow.value = tasks
    }

    override suspend fun deleteAllTasks() {
        delay(1000L)
        _tasksFlow.value = emptyList()
    }

    override suspend fun getTaskById(taskId: String): Task? {
        delay(1000L)
        return _tasksFlow.value.find { it.id == taskId }
    }
}
