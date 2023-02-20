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
class AddTaskBottomSheetDialogViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val title = MutableLiveData<String>()

    private val _navigateTasks = MutableLiveData<Boolean?>()
    val navigateTasks: LiveData<Boolean?>
        get() = _navigateTasks

    fun onCreateTask() {
        val taskTitle = title.value

        if (taskTitle == null) {
            Log.i("AddTaskBottomSheetDialogViewModel:onCreateTask", "タスクのタイトルが空です。")
            return
        }

        createTask(Task(title = taskTitle))
        _navigateTasks.value = true
    }

    fun doneNavigateTasks() {
        _navigateTasks.value = null
    }

    private fun createTask(newTask: Task) {
        viewModelScope.launch {
            taskRepository.addTask(newTask)
        }
    }

}