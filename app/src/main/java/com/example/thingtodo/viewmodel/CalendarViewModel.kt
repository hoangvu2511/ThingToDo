package com.example.thingtodo.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.vunguyenhoang.core.repository.TaskRepository
import java.util.*

class CalendarViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _dateSelected = MutableLiveData(Calendar.getInstance().time)

    val dateSelected: Calendar
        get() = Calendar.getInstance().apply {
            time = _dateSelected.value!!
        }

    val pagedList = Transformations.switchMap(_dateSelected) {
        repository.loadPagedListFilter(it.time).cachedIn(viewModelScope).asLiveData()
    }

    fun setDateSelected(date: Date) {
        _dateSelected.value = date
    }

}