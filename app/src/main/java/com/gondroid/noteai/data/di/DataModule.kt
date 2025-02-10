package com.gondroid.noteai.data.di

import android.content.Context
import androidx.room.Room
import com.gondroid.noteai.data.NotesDatabase
import com.gondroid.noteai.data.local.note.NoteDao
import com.gondroid.noteai.data.local.note.RoomNoteLocalDataSource
import com.gondroid.noteai.data.local.task.RoomTaskLocalDataSource
import com.gondroid.noteai.data.local.task.TaskDao
import com.gondroid.noteai.domain.repository.NoteLocalDataSource
import com.gondroid.noteai.domain.repository.TaskLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext
        context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(
        database: NotesDatabase
    ): TaskDao {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideNoteDao(
        database: NotesDatabase
    ): NoteDao {
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideTaskLocalDataSource(
        taskDao: TaskDao,
        @Named("dispatcherIO")
        dispatcherIO: CoroutineDispatcher
    ): TaskLocalDataSource {
        return RoomTaskLocalDataSource(taskDao, dispatcherIO)
    }

    @Provides
    @Singleton
    fun provideNoteLocalDataSource(
        taskDao: NoteDao,
        @Named("dispatcherIO")
        dispatcherIO: CoroutineDispatcher
    ): NoteLocalDataSource {
        return RoomNoteLocalDataSource(taskDao, dispatcherIO)
    }

}