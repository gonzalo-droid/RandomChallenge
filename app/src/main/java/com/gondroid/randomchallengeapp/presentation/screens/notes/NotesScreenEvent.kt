package com.gondroid.randomchallengeapp.presentation.screens.notes

sealed interface NotesScreenEvent {
    data object UpdatedTask : NotesScreenEvent
}