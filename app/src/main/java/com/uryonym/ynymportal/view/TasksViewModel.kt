package com.uryonym.ynymportal.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : ViewModel() {
    val title = MutableLiveData<String>()

    fun onCreateTask() {
        viewModelScope.launch {
            Log.i("onCreateTask", "タスク生成をクリック")
        }
    }
}