package com.example.thingtodo.feature.task

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.thingtodo.R
import com.example.thingtodo.base.BaseFragment
import com.example.thingtodo.databinding.FragmentMyTasksBinding
import com.example.thingtodo.ext.KEY_ARGUMENT
import com.example.thingtodo.viewmodel.TaskViewModel
import com.vunguyenhoang.core.model.TypeTask
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyTasksFragment : BaseFragment<FragmentMyTasksBinding>() {

    override var layoutIdRes: Int = R.layout.fragment_my_tasks
    private var type: TypeTask? = null
    private val taskViewModel: TaskViewModel by viewModel()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.get(KEY_ARGUMENT) as? TypeTask
        taskViewModel.loadPagedList()
    }

    override fun init() {
        adapter = TaskAdapter(taskViewModel)
        binding.rvTasks.adapter = this.adapter
    }

    override fun setUpHeader() {
        mainActivityViewModel.updateHeader(type?.value ?: TypeTask.ALL.value)
    }

    override fun setUpViewModelOnce() {
        taskViewModel.pagedList.observe(this@MyTasksFragment, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

}