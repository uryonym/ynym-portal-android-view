package com.uryonym.ynymportal.data.source

import androidx.lifecycle.LiveData
import com.uryonym.ynymportal.data.Task

interface TaskDataSource {

    fun getTasks(): LiveData<List<Task>>

    suspend fun getTask(taskId: String): Task

    suspend fun saveTask(task: Task)

}