package com.example.thingtodo.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TypeTask
import com.vunguyenhoang.core.repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(private val repo: TaskRepository): ViewModel() {

    private val loadMoreTask = MutableLiveData<Boolean>()

    val items = MediatorLiveData<List<Task>>()
    val error = MutableLiveData<Exception>()

    fun loadTasks() {
        loadMoreTask.value = true
        items.addSource(repo.getTasks()) {
            when (it) {
                is Result.Success -> items.value = it.data
                is Result.Error -> error.value = it.exception
            }
        }
    }

    val titleTask = ObservableField("")
    val descTask = ObservableField("")

    fun createTask(task: Task? = null) {
        Log.d("ABCD", "create task")
        val newTask = task ?: Task(
            title = titleTask.get(),
            description = descTask.get(),
            time = Calendar.getInstance().timeInMillis,
            type = TypeTask.ALL
        )
        viewModelScope.launch {
            repo.addTask(newTask)
            titleTask.set("")
            descTask.set("")
        }
    }

}