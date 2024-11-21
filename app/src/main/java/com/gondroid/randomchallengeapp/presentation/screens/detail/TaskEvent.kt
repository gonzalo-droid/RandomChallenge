package com.gondroid.randomchallengeapp.presentation.screens.detail

sealed interface TaskEvent{
    data object TaskCreated: TaskEvent
}