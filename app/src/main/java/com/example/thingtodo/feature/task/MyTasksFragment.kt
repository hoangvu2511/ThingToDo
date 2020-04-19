package com.example.thingtodo.feature.task

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.thingtodo.R
import com.example.thingtodo.base.BaseFragment
import com.example.thingtodo.databinding.FragmentMyTasksBinding
import com.example.thingtodo.ext.KEY_ARGUMENT
import com.example.thingtodo.viewmodel.TaskViewModel
import com.google.gson.Gson
import com.vunguyenhoang.core.model.TypeTask
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyTasksFragment : BaseFragment<FragmentMyTasksBinding>() {

    override var layoutIdRes: Int = R.layout.fragment_my_tasks
    private var type: TypeTask? = null
    private val taskViewModel: TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.get(KEY_ARGUMENT) as? TypeTask
        taskViewModel.loadTasks()
    }

    override fun setUpHeader() {
        mainActivityViewModel.updateHeader(type?.value ?: TypeTask.ALL.value)
    }

    override fun setUpViewModel() {
        taskViewModel.items.observe(this, Observer {
            Log.d("ABCD", Gson().toJson(it))
        })
    }

}