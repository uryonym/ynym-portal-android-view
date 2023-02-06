package com.uryonym.ynymportal.data.source.local

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

    override fun getTask(taskId: String): LiveData<Task> {
        return taskDatabaseDao.getTask(taskId)
    }

    override suspend fun addTask(task: Task) = withContext(ioDispatcher) {
        taskDatabaseDao.insert(task)
    }

}