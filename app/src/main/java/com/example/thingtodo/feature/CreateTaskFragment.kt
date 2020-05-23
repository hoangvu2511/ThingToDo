package com.example.thingtodo.feature

import android.app.DatePickerDialog
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
import java.util.*

class CreateTaskFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var binding: FragmentCreateTaskBinding
    private val taskViewModel: TaskViewModel by viewModel()
    private lateinit var calendar: Calendar

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
                DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        calendar.set(year, month, dayOfMonth)
                        binding.itemDataTime.tvDate.text =
                            getString(R.string.date_create_task, dayOfMonth, month, year)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DATE)
                ).apply {
                    datePicker.minDate = System.currentTimeMillis()
                    show()
                }
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

        binding.itemDataTime.root.setOnClickListener(this)
        binding.itemPriority.setOnClickListener(this)
    }

    private fun init() {
        binding.viewModel = taskViewModel
        calendar = Calendar.getInstance()
    }
}