package com.example.thingtodo.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.thingtodo.feature.calendar.CalendarFragment
import com.example.thingtodo.feature.settings.SettingsFragment
import com.example.thingtodo.feature.task.TasksFragment
import java.text.SimpleDateFormat
import java.util.*


class MainActivityViewModel: ViewModel() {

    val header = ObservableField("")

    fun updateHeader(fragmentName: String){
        val name = when(fragmentName){
            TasksFragment::class.qualifiedName -> "My Lists"
            CalendarFragment::class.qualifiedName -> {
                val data = Calendar.getInstance().time
                val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
                formatter.format(data)
            }
            SettingsFragment::class.qualifiedName -> "Settings"
            else -> fragmentName
        }
        header.set(name)
    }
}