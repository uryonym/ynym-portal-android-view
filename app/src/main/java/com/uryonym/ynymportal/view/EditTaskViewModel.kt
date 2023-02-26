package com.uryonym.ynymportal.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uryonym.ynymportal.data.Task
import com.uryonym.ynymportal.data.source.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val title = MutableLiveData<String>()

    private val _navigateTasks = MutableLiveData<Boolean?>()
    val navigateTasks: LiveData<Boolean?>
        get() = _navigateTasks

    private var taskId: String? = null

    fun getTask(taskId: String) {
        this.taskId = taskId

        viewModelScope.launch {
            taskRepository.getTask(taskId).let { task ->
                title.value = task.title
            }
        }
    }

    fun onSaveTask() {
        val taskTitle = title.value

        if (taskTitle == null) {
            Log.i("EditTaskViewModel:onSaveTask", "タスクのタイトルが空です。")
            return
        }

        saveTask(Task(id = taskId!!, title = taskTitle))
        _navigateTasks.value = true
    }

    fun doneNavigateTasks() {
        _navigateTasks.value = null
    }

    private fun saveTask(task: Task) {
        viewModelScope.launch {
            taskRepository.saveTask(task)
        }
    }

}