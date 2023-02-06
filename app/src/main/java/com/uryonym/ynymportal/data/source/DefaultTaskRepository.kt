package com.uryonym.ynymportal.data.source

import androidx.lifecycle.LiveData
import com.uryonym.ynymportal.data.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DefaultTaskRepository(
    private val taskLocalDataSource: TaskDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TaskRepository {

    override fun getTasks(): LiveData<List<Task>> {
        return taskLocalDataSource.getTasks()
    }

    override fun getTask(taskId: String): LiveData<Task> {
        return taskLocalDataSource.getTask(taskId)
    }

    override suspend fun addTask(task: Task) {
        coroutineScope {
            launch {
                taskLocalDataSource.addTask(task)
            }
        }
    }
}