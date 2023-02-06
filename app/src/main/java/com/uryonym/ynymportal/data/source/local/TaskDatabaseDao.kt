package com.uryonym.ynymportal.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uryonym.ynymportal.data.Task

@Dao
interface TaskDatabaseDao {

    @Query("select * from tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("select * from tasks where id = :taskId")
    fun getTask(taskId: String): LiveData<Task>

    @Insert
    suspend fun insert(task: Task)

}