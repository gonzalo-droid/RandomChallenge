package com.gondroid.noteai.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1
)

abstract class NotesDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
}