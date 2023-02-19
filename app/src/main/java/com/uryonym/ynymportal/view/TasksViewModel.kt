package com.uryonym.ynymportal.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uryonym.ynymportal.data.Task
import com.uryonym.ynymportal.data.source.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.getTasks()

    val title = MutableLiveData<String>()

    private val _navigateAddTask = MutableLiveData<Boolean?>()
    val navigateAddTask: LiveData<Boolean?>
        get() = _navigateAddTask

    fun onCreateTask() {
        val taskTitle = title.value

        if (taskTitle == null) {
            Log.i("TasksViewModel:onCreateTask", "タスクのタイトルが空です。")
            return
        }

        createTask(Task(title = taskTitle))
    }

    fun onNavigateAddTask() {
        Log.i("TasksViewModel:onNavigateAddTask","新しいタスクを作成する")
        _navigateAddTask.value = true
    }

    fun doneNavigateAddTask() {
        _navigateAddTask.value = null
    }

    private fun createTask(newTask: Task) {
        viewModelScope.launch {
            taskRepository.addTask(newTask)
        }
    }
}