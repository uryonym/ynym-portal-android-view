package com.uryonym.ynymportal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

        editTaskViewModel.getTask(args.taskId)

        return viewDataBinding.root
    }

}