package com.example.thingtodo.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TypeTask
import com.vunguyenhoang.core.repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(private val repo: TaskRepository) : ViewModel() {

    val pagedList = MediatorLiveData<PagedList<Task>>()

    fun loadPagedList() {
        viewModelScope.launch {
            pagedList.addSource(repo.loadPagedList()) {
                pagedList.value = it
            }
        }
    }


    val titleTask = ObservableField("")
    val descTask = ObservableField("")

    fun createTask(task: Task? = null) {
        val newTask = task ?: Task(
            title = titleTask.get(),
            description = descTask.get(),
            time = Calendar.getInstance().timeInMillis,
            type = TypeTask.ALL
        )
        repo.addTask(newTask)
        titleTask.set("")
        descTask.set("")
    }

    val needToShowDeleteAll = ObservableField(false)

    fun deleteTask(task: Task?) {
        viewModelScope.launch {
            repo.deleteTask(task ?: return@launch)
        }
    }

    fun deleteListTask() {
        viewModelScope.launch {
            val list = pagedList.value?.toList()
                ?.filter { it.isCheck.get() == true }
            repo.deleteListTask(list ?: return@launch)
            needToShowDeleteAll.set(false)
        }
    }
}