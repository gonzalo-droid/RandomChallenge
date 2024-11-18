package com.gondroid.randomchallengeapp.presentation.screens.home.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gondroid.randomchallengeapp.domain.Category
import com.gondroid.randomchallengeapp.domain.Task

class TaskItemPreviewProvider : PreviewParameterProvider<Task> {
    override val values: Sequence<Task>
        get() = sequenceOf(
            Task(
                id = "1",
                title = "Task 1",
                isCompleted = false,
                description = "Description 1",
                category = Category.WORK,
            ),

            Task(
                id = "2",
                title = "Task 2",
                isCompleted = true,
                description = "Description 2",
                category = Category.OTHER,
            ),
        )
}