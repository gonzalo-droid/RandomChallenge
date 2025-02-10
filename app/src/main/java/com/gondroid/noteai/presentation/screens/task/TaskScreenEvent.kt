package com.gondroid.noteai.presentation.screens.task

sealed interface TaskScreenEvent {
    data object UpdatedTask : TaskScreenEvent
    data object DeletedTask : TaskScreenEvent
    data object AllTaskDeleted : TaskScreenEvent
}