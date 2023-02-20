package com.uryonym.ynymportal.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uryonym.ynymportal.R
import com.uryonym.ynymportal.databinding.AddTaskBottomSheetDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val addTaskBottomSheetDialogViewModel by viewModels<AddTaskBottomSheetDialogViewModel>()
    private lateinit var viewDataBinding: AddTaskBottomSheetDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.bottom_sheet_dialog_style)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.add_task_bottom_sheet_dialog_fragment, container, false)
        viewDataBinding = AddTaskBottomSheetDialogFragmentBinding.bind(root).apply {
            viewModel = addTaskBottomSheetDialogViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        // タスクタイトルにフォーカス
        viewDataBinding.taskTitle.requestFocus()

        addTaskBottomSheetDialogViewModel.navigateTasks.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // キーボードを隠す
                val system =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                system.hideSoftInputFromWindow(viewDataBinding.taskTitle.windowToken, 0)

                this.findNavController()
                    .navigate(AddTaskBottomSheetDialogFragmentDirections.actionAddTaskBottomSheetDialogFragmentToTasksFragment())
                addTaskBottomSheetDialogViewModel.doneNavigateTasks()
            }
        })

        return viewDataBinding.root
    }
}