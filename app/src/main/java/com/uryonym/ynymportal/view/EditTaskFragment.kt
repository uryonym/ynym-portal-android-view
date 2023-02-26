package com.uryonym.ynymportal.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.uryonym.ynymportal.R
import com.uryonym.ynymportal.databinding.EditTaskFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private val editTaskViewModel by viewModels<EditTaskViewModel>()
    private lateinit var viewDataBinding: EditTaskFragmentBinding
    private val args: EditTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.edit_task_fragment, container, false)
        viewDataBinding = EditTaskFragmentBinding.bind(root).apply {
            viewModel = editTaskViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewDataBinding.taskTitle.requestFocus()

        editTaskViewModel.getTask(args.taskId)

        editTaskViewModel.navigateTasks.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // キーボードを隠す
                val system =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                system.hideSoftInputFromWindow(viewDataBinding.taskTitle.windowToken, 0)

                this.findNavController()
                    .navigate(EditTaskFragmentDirections.actionEditTaskFragmentToTasksFragment())
                editTaskViewModel.doneNavigateTasks()
            }
        })

        return viewDataBinding.root
    }

}