package com.example.thingtodo.ext

import android.app.DatePickerDialog
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import java.util.*

fun Long?.toDate() =
    Calendar.getInstance().also {
        it.timeInMillis = this ?: it.timeInMillis
    }.let {
        "${it.get(Calendar.DATE)}/${it.get(Calendar.MONTH)}/${it.get(Calendar.YEAR)}"
    }


fun Context.showDatePicker(calendar: Calendar, isMinDate: Boolean = true) =
    DatePickerDialog(
        this,
        { _, year, month, dayOfMonth ->
            when (calendar) {
                is CustomCalendar -> calendar.customSet(year, month, dayOfMonth)
                else -> calendar.set(year, month, dayOfMonth)
            }
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DATE)
    ).apply {
        if (isMinDate) {
            datePicker.minDate = System.currentTimeMillis()
        }
        show()
    }

@BindingAdapter("app:url", requireAll = false)
fun loadingImageView(v: ImageView, url: String?) {
    v.load(url)
}