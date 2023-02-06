package com.uryonym.ynymportal.data.source

import androidx.lifecycle.LiveData
import com.uryonym.ynymportal.data.Task

interface TaskRepository {

    fun getTasks(): LiveData<List<Task>>
    fun getTask(taskId: String): LiveData<Task>
    suspend fun addTask(task: Task)

}