package com.gondroid.randomchallengeapp.data.di

import android.content.Context
import androidx.room.Room
import com.gondroid.randomchallengeapp.data.RandomChallengeDatabase
import com.gondroid.randomchallengeapp.data.RoomTaskLocalDataSource
import com.gondroid.randomchallengeapp.data.TaskDao
import com.gondroid.randomchallengeapp.domain.TaskLocalDataSource
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
    ): RandomChallengeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RandomChallengeDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(
        database: RandomChallengeDatabase
    ): TaskDao {
        return database.taskDao()
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

}