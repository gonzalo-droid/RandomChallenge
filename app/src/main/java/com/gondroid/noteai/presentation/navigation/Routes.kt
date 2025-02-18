package com.gondroid.noteai.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskScreenRoute

@Serializable
object NoteScreenRoute

@Serializable
data class TaskCreateScreenRoute(val taskId: String? = null)

@Serializable
data class NoteCreateScreenRoute(val noteId: String? = null)

@Serializable
object VoiceRecorderRoute
