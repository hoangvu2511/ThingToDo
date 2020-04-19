package com.example.thingtodo.feature


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import com.example.thingtodo.R
import com.example.thingtodo.databinding.FragmentCreateTaskBinding
import com.example.thingtodo.viewmodel.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.jetbrains.anko.find
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateTaskFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var binding: FragmentCreateTaskBinding
    val taskViewModel: TaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_task, container, false)
        setUpListener()
        init()
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.item_data_time -> {
            }
            R.id.item_notification -> {
            }
            R.id.item_priority -> {
            }
        }
    }

    private fun setUpListener() {
        dialog?.setOnShowListener {
            val root =
                dialog?.find<CoordinatorLayout>(R.id.rootCoordinator) ?: return@setOnShowListener
            val bottomSheetInternal = root.find<ConstraintLayout>(R.id.rootBottomSheet)

            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal)
            bottomSheetBehavior.isHideable = false
            BottomSheetBehavior.from(root.parent as View).peekHeight = root.height
            bottomSheetBehavior.peekHeight = root.height
            root.parent.requestLayout()
        }

        binding.itemDataTime.setOnClickListener(this)
        binding.itemNotification.setOnClickListener(this)
        binding.itemPriority.setOnClickListener(this)
    }

    private fun init() {
        binding.viewModel = taskViewModel
    }
}