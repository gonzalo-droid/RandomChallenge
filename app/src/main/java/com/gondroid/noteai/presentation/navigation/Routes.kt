package com.gondroid.noteai.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute
@Serializable
object NotesScreenRoute
@Serializable
object TestScreenRoute
@Serializable
data class TaskScreenRoute(val taskId: String? = null)
