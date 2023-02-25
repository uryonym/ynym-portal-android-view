package com.uryonym.ynymportal.view

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

    private val _taskId = MutableLiveData<String?>()
    val taskId: LiveData<String?>
        get() = _taskId

    fun onNavigateAddTask() {
        _navigateAddTask.value = true
    }

    fun doneNavigateAddTask() {
        _navigateAddTask.value = null
    }

    fun openTask(taskId: String) {
        _taskId.value = taskId
    }

    fun doneOpenTask() {
        _taskId.value = null
    }

}