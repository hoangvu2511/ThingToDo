package com.example.thingtodo.feature.calendar

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
    private lateinit var adapter: TaskAdapter

    override fun onStart() {
        super.onStart()
        calendarViewModel.setDateSelected(today.time)
    }

    override fun setUpViewModel() {
        calendarViewModel.pagedList.observe(this, Observer {
            adapter.submitData(lifecycle, it)
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
            .defaultSelectedDate(calendarViewModel.dateSelected)
            .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) {
                mainActivityViewModel.toMonth(date ?: return)
                calendarViewModel.setDateSelected(date = date.time)
            }
        }

        adapter = TaskAdapter()
        binding.rvTasks.adapter = adapter
    }

}