package com.gondroid.noteai.presentation.screens.noteCreate

import com.gondroid.noteai.domain.Category

sealed interface ActionNoteCreate {
    data object SaveNote : ActionNoteCreate
    data object Back : ActionNoteCreate
    data class ChangeNoteCategory(val category: Category?) : ActionNoteCreate
    data object VoiceRecorder : ActionNoteCreate
    data object MyTask : ActionNoteCreate
}