package com.example.thingtodo.feature.task

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.example.thingtodo.R
import com.example.thingtodo.base.BaseFragment
import com.example.thingtodo.databinding.FragmentTasksBinding
import com.example.thingtodo.ext.KEY_ARGUMENT
import com.example.thingtodo.ext.navigateWithAnim
import com.example.thingtodo.viewmodel.TaskTypeViewModel
import com.vunguyenhoang.core.model.TypeTask
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksFragment : BaseFragment<FragmentTasksBinding>() {
    override var layoutIdRes: Int = R.layout.fragment_tasks
    private lateinit var adapter: TypeTaskAdapter
    private val taskViewModel: TaskTypeViewModel by viewModel()

    override fun init() {
        adapter = TypeTaskAdapter {
            Toast.makeText(context, it.value, Toast.LENGTH_SHORT).show()
            when (it) {
                TypeTask.ADD_NEW -> {
                }
                else -> navController.navigateWithAnim(
                    R.id.action_tasksFragment_to_myTasksFragent, bundleOf(
                        KEY_ARGUMENT to it
                    )
                )
            }
        }
        binding.rvItemList.adapter = adapter
        taskViewModel.getTaskListType()
    }

    override fun setUpViewModel() {
        taskViewModel.taskListType.observe(this, Observer {
            adapter.updateList(it)
        })
    }
}