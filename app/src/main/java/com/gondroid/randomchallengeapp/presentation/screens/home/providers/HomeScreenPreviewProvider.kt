package com.gondroid.randomchallengeapp.presentation.screens.home.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gondroid.randomchallengeapp.domain.Category
import com.gondroid.randomchallengeapp.domain.Task
import com.gondroid.randomchallengeapp.presentation.screens.home.HomeDataState

class HomeScreenPreviewProvider : PreviewParameterProvider<HomeDataState> {
    override val values: Sequence<HomeDataState>
        get() = sequenceOf(
            HomeDataState(
                date = "March 29, 2024",
                summary = "5 incomplete, 5 completed",
                completedTask = completedTask,
                pendingTask = pendingTask
            )
        )
}

val completedTask = mutableListOf<Task>()
    .apply {
        repeat(20) {
            add(
                Task(
                    id = it.toString(),
                    title = "Task $it",
                    description = "Description $it",
                    category = Category.WORK,
                    isCompleted = true
                )
            )
        }
    }

val pendingTask = mutableListOf<Task>()
    .apply {
        repeat(20) {
            add(
                Task(
                    id = (it + 30).toString(),
                    title = "Task $it",
                    description = "Description $it",
                    category = Category.OTHER,
                    isCompleted = false
                )
            )
        }
    }