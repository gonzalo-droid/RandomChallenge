package com.gondroid.noteai.presentation.screens.notes

sealed interface NotesScreenEvent {
    data object UpdatedTask : NotesScreenEvent
}