package com.uryonym.ynymportal.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uryonym.ynymportal.data.source.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.getTasks()

    private val _navigateAddTask = MutableLiveData<Boolean?>()
    val navigateAddTask: LiveData<Boolean?>
        get() = _navigateAddTask

    fun onNavigateAddTask() {
        Log.i("TasksViewModel:onNavigateAddTask", "新しいタスクを作成する")
        _navigateAddTask.value = true
    }

    fun doneNavigateAddTask() {
        _navigateAddTask.value = null
    }

}