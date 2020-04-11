package com.example.thingtodo.viewmodel

import androidx.lifecycle.*
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repo: TaskRepository): ViewModel() {

    private val loadMoreTask = MutableLiveData<Boolean>()

    val items = Transformations.switchMap(loadMoreTask){
        repo.getTasks()
    }!!

    fun loadMore(){
        loadMoreTask.value = true
    }

}