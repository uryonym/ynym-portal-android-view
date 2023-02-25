package com.uryonym.ynymportal.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.uryonym.ynymportal.data.Task
import com.uryonym.ynymportal.data.source.TaskDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskLocalDataSource internal constructor(
    private val taskDatabaseDao: TaskDatabaseDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TaskDataSource {

    override fun getTasks(): LiveData<List<Task>> {
        return taskDatabaseDao.getTasks()
    }

    override suspend fun getTask(taskId: String): Task = withContext(ioDispatcher) {
        val task = taskDatabaseDao.getTaskById(taskId)
        return@withContext task!!
    }

    override suspend fun saveTask(task: Task) = withContext(ioDispatcher) {
        taskDatabaseDao.insert(task)
    }

}