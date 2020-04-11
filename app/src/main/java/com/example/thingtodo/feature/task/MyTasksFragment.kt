package com.example.thingtodo.feature.task

import android.os.Bundle
import com.example.thingtodo.R
import com.example.thingtodo.base.BaseFragment
import com.example.thingtodo.databinding.FragmentMyTasksBinding
import com.example.thingtodo.ext.KEY_ARGUMENT
import com.vunguyenhoang.core.model.TypeTask

class MyTasksFragment : BaseFragment<FragmentMyTasksBinding>() {

    override var layoutIdRes: Int = R.layout.fragment_my_tasks
    private var type: TypeTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.get(KEY_ARGUMENT) as? TypeTask
    }

    override fun setUpHeader() {
        mainActivityViewModel.updateHeader(type?.value ?: TypeTask.ALL.value)
    }


}