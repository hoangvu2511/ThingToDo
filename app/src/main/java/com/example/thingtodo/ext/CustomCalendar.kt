package com.example.thingtodo.ext

import androidx.lifecycle.Observer
import com.vunguyenhoang.core.model.DateChange
import java.util.*


class CustomCalendar : GregorianCalendar() {
    private var observerDateChane: Observer<DateChange>? = null
    private val date = Calendar.DATE
    private val month = Calendar.MONTH
    private val year = Calendar.YEAR

    fun setObserver(observable: Observer<DateChange>) {
        this.observerDateChane = observable
    }

    init {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    fun customSet(year: Int, month: Int, date: Int) {
        set(year, month, date, 0, 0, 0)
        set(Calendar.MILLISECOND, 0)
        observerDateChane?.onChanged(DateChange(year, month, date))
    }

    fun checkTime(): String {
        val today = Calendar.getInstance()
        return when {
            get(year) == today[year] && get(month) == today[month] && get(date) - today[date] == 0 -> "Today"
            get(year) == today[year] && get(month) == today[month] && get(date) - today[date] == 1 -> "Tomorrow"
            else -> "${get(date)}/${get(month)}/${get(year)}"
        }
    }

}