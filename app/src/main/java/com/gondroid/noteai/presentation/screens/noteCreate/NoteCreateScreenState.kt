package com.gondroid.noteai.presentation.screens.noteCreate

import androidx.compose.foundation.text.input.TextFieldState

data class NoteCreateScreenState(
    val title: TextFieldState = TextFieldState(),
    val content: TextFieldState = TextFieldState(),
    val category: String? = null,
    val canSaveNote: Boolean = false
)