package com.gondroid.noteai.presentation.screens.detail

sealed interface TaskEvent{
    data object TaskCreated: TaskEvent
}