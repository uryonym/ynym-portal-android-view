package com.uryonym.ynymportal.di

import android.content.Context
import androidx.room.Room
import com.uryonym.ynymportal.data.source.DefaultTaskRepository
import com.uryonym.ynymportal.data.source.TaskDataSource
import com.uryonym.ynymportal.data.source.TaskRepository
import com.uryonym.ynymportal.data.source.local.TaskDatabase
import com.uryonym.ynymportal.data.source.local.TaskDatabaseDao
import com.uryonym.ynymportal.data.source.local.TaskLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalTaskDataSource

    @Singleton
    @LocalTaskDataSource
    @Provides
    fun provideTaskLocalDataSource(
        database: TaskDatabase, ioDispatcher: CoroutineDispatcher
    ): TaskDataSource {
        return TaskLocalDataSource(
            database.taskDatabaseDao(), ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context.applicationContext, TaskDatabase::class.java, "Tasks.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}

@Module
@InstallIn(SingletonComponent::class)
object TaskRepositoryModule {

    @Singleton
    @Provides
    fun provideTaskRepository(
        @AppModule.LocalTaskDataSource localTaskDataSource: TaskDataSource,
        ioDispatcher: CoroutineDispatcher
    ): TaskRepository {
        return DefaultTaskRepository(
            localTaskDataSource, ioDispatcher
        )
    }

}