package com.gondroid.noteai.presentation.screens.notes

sealed interface NoteScreenEvent {
    data object UpdatedNote : NoteScreenEvent
}
