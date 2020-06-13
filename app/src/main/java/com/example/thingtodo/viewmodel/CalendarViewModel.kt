package com.example.thingtodo.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.repository.TaskRepository
import kotlinx.coroutines.launch

class CalendarViewModel(private val repository: TaskRepository) : ViewModel() {

    val pagedList = MediatorLiveData<PagingData<Task>>()

    fun getItems(timeInMillis: Long) {
        viewModelScope.launch {
            pagedList.addSource(repository.loadPagedListFilter(timeInMillis)) {
                pagedList.value = it
            }
        }
    }

}