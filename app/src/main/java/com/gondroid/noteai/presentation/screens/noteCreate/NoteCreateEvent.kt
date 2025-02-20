package com.gondroid.noteai.presentation.screens.noteCreate

sealed interface NoteCreateEvent{
    data object NoteCreated: NoteCreateEvent
    data object SaveVoiceRecorder: NoteCreateEvent
}