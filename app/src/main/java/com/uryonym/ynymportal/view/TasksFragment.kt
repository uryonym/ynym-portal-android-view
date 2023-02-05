package com.uryonym.ynymportal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.uryonym.ynymportal.R
import com.uryonym.ynymportal.databinding.TasksFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: TasksFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.tasks_fragment, container, false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = TasksViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(TasksViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}