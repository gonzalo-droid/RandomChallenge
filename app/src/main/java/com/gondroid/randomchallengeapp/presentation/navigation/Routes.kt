package com.gondroid.randomchallengeapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute
@Serializable
object NotesScreenRoute
@Serializable
data class TaskScreenRoute(val taskId: String? = null)
