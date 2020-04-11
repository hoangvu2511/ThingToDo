package com.example.thingtodo.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.repository.taskType.TaskTypeRepo
import kotlinx.coroutines.launch

class TaskTypeViewModel(private val repo: TaskTypeRepo) : ViewModel(){

    val taskListType = MediatorLiveData<List<TaskListType>>()

    fun getTaskListType(){
        viewModelScope.launch {
            taskListType.addSource(repo.getAllTaskType()){
                when(it){
                    is Result.Success -> {
                        taskListType.value = it.data
                    }
                    is Result.Error -> {

                    }
                    is Result.Loading -> {

                    }
                }
            }
        }
    }

    fun addTaskListType(taskListType: TaskListType){
        viewModelScope.launch {
            repo.addTaskType(taskListType)
        }
    }



}