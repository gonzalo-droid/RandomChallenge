package com.gondroid.noteai.domain

import java.time.LocalDateTime

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val category: Category? = null,
    val date: LocalDateTime = LocalDateTime.now()
)
