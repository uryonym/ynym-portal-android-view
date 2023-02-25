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

    fun getTask(taskId: String) {
        viewModelScope.launch {
            taskRepository.getTask(taskId).let {task ->
                title.value = task.title
            }
        }
    }

    fun onEditTask() {
        val taskTitle = title.value

        if (taskTitle == null) {
            Log.i("EditTaskViewModel:onEditTask", "タスクのタイトルが空です。")
            return
        }

        editTask(Task(title = taskTitle))
    }

    private fun editTask(task: Task) {
        viewModelScope.launch {
            taskRepository.saveTask(task)
        }
    }

}