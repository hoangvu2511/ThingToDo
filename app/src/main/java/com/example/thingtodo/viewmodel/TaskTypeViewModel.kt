package com.example.thingtodo.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunguyenhoang.core.DbResult
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.repository.taskType.TaskTypeRepo
import kotlinx.coroutines.launch

class TaskTypeViewModel(private val repo: TaskTypeRepo) : ViewModel(){

    val taskListType = MediatorLiveData<List<TaskListType>>()

    fun getTaskListType(){
        viewModelScope.launch {
            taskListType.addSource(repo.getAllTaskType()){
                when(it){
                    is DbResult.Success -> {
                        taskListType.value = it.data
                    }
                    is DbResult.Error -> {

                    }
                    is DbResult.Loading -> {

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