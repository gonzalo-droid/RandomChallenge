package com.gondroid.randomchallengeapp.presentation.screens.home

import com.gondroid.randomchallengeapp.domain.Task

data class HomeDataState(
    val date: String = "",
    val summary: String = "",
    val completedTask: List<Task> = emptyList(),
    val pendingTask: List<Task> = emptyList(),
)