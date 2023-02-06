package com.uryonym.ynymportal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.uryonym.ynymportal.R
import com.uryonym.ynymportal.databinding.TasksFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class TasksFragment : Fragment() {

    private val taskViewModel by viewModels<TasksViewModel>()
    private lateinit var viewDataBinding: TasksFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.tasks_fragment, container, false)
        viewDataBinding = TasksFragmentBinding.bind(root).apply {
            viewModel = taskViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }

}