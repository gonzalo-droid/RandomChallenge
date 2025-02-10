package com.gondroid.noteai.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gondroid.noteai.data.local.note.NoteDao
import com.gondroid.noteai.data.local.note.NoteEntity
import com.gondroid.noteai.data.local.task.TaskDao
import com.gondroid.noteai.data.local.task.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        NoteEntity::class
    ],
    version = 1
)

abstract class NotesDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun noteDao(): NoteDao
}