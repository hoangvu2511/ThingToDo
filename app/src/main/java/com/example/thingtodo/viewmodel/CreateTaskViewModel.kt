package com.example.thingtodo.viewmodel

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.thingtodo.ext.CustomCalendar
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TypeTask
import com.vunguyenhoang.core.repository.TaskRepository

class CreateTaskViewModel(private val repository: TaskRepository) : ViewModel() {

    var titleTask = ObservableField("")

    val descTask = ObservableField("")

    val needNotification = ObservableField(false)

    val textTime = ObservableField("Today")

    val calendar: CustomCalendar = CustomCalendar()

    private val mutableSetUri = mutableSetOf<Uri>()

    init {
        calendar.setObserver(Observer {
            textTime.set(calendar.checkTime())
        })
    }

    private var time: Long? = null

    fun addUri(uri: Uri) {
        mutableSetUri.add(uri)
    }

    fun createTask(task: Task? = null) {
        val newTask = task ?: Task(
            title = titleTask.get(),
            description = descTask.get(),
            time = calendar.timeInMillis,
            type = TypeTask.ALL,
            uri = mutableSetUri.firstOrNull()?.toString()
        )
        repository.addTask(newTask)
        reset()
    }

    private fun reset() {
        titleTask.set("")
        descTask.set("")
        time = null
        needNotification.set(false)
        mutableSetUri.clear()
    }
}