package com.gondroid.noteai.presentation.screens.taskCreate

sealed interface TaskCreateEvent{
    data object TaskCreated: TaskCreateEvent
}