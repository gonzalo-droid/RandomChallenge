package com.gondroid.randomchallengeapp.presentation.screens.detail

import com.gondroid.randomchallengeapp.domain.Category

sealed interface ActionTask {
    data object SaveTask : ActionTask
    data object Back : ActionTask
    data class ChangeTaskCategory(val category: Category?) : ActionTask
    data class ChangeTaskDone(val isTaskDone: Boolean) : ActionTask
}