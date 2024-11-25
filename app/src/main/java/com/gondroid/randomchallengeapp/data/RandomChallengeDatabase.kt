package com.gondroid.randomchallengeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1
)

abstract class RandomChallengeDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
}