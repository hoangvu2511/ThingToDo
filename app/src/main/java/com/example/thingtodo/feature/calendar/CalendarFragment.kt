package com.example.thingtodo.feature.calendar

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.thingtodo.R
import com.example.thingtodo.base.BaseFragment
import com.example.thingtodo.databinding.FragmentCalendarBinding
import com.example.thingtodo.feature.task.TaskAdapter
import com.example.thingtodo.viewmodel.CalendarViewModel
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CalendarFragment : BaseFragment<FragmentCalendarBinding>() {

    override var layoutIdRes: Int = R.layout.fragment_calendar

    private val calendarViewModel by viewModel<CalendarViewModel>()
    private val adpater = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendarViewModel.getItems(today.timeInMillis)
    }

    override fun setUpViewModel() {
        calendarViewModel.pagedList.observe(viewLifecycleOwner, Observer {
            adpater.submitData(lifecycle, it)
        })
    }

    override fun init() {
        val startDate: Calendar = Calendar.getInstance()
        startDate.add(Calendar.YEAR, -20)
        /* ends after 1 month from now */
        val endDate: Calendar = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 20)
        val horizontalCalendar = HorizontalCalendar.Builder(binding.root, R.id.calendarView)
            .datesNumberOnScreen(7)
            .range(startDate, endDate)
            .configure()
            .showTopText(false)
            .end()
            .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) {
                mainActivityViewModel.toMonth(date ?: return)
                calendarViewModel.getItems(date.timeInMillis)
            }
        }

        binding.rvTasks.adapter = adpater

    }

}