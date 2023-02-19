package com.uryonym.ynymportal.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.uryonym.ynymportal.R
import com.uryonym.ynymportal.databinding.AddTaskFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private val addTaskViewModel by viewModels<AddTaskViewModel>()
    private lateinit var viewDataBinding: AddTaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_task_fragment, container, false)
        viewDataBinding = AddTaskFragmentBinding.bind(root).apply {
            viewModel = addTaskViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        // アプリバーの戻るアイコンを閉じるアイコンに変更
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_24dp)

        addTaskViewModel.navigateTasks.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // キーボードを隠す
                val system = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                system.hideSoftInputFromWindow(viewDataBinding.taskTitle.windowToken, 0)

                this.findNavController()
                    .navigate(AddTaskFragmentDirections.actionAddTaskFragmentToTasksFragment())
                addTaskViewModel.doneNavigateTasks()
            }
        })

        return viewDataBinding.root
    }

}