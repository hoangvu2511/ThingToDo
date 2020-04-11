package com.example.thingtodo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.thingtodo.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<ViewBinding: ViewDataBinding> : Fragment() {
    abstract var layoutIdRes: Int
    protected lateinit var binding: ViewBinding
    protected lateinit var navController: NavController
    protected val mainActivityViewModel: MainActivityViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModelOnce()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutIdRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setUpHeader()
        init()
        setUpViewModel()
    }

    open fun setUpHeader() {
        mainActivityViewModel.updateHeader(this::class.qualifiedName ?: return)
    }

    open fun init() {}

    open fun setUpViewModel() {}

    open fun setUpViewModelOnce() {}
}