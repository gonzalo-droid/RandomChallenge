package com.gondroid.noteai.domain.repository

import com.gondroid.noteai.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteLocalDataSource {
    val notesFlow: Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun updateNote(updatedNote: Note)
    suspend fun removeNote(note: Note)
    suspend fun deleteAllNotes()
    suspend fun getNoteById(noteId: String): Note?
}