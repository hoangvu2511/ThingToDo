package com.vunguyenhoang.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.vunguyenhoang.core.model.Task

interface TaskRepository {
    fun addTask(task: Task): Long

    fun loadPagedList(): LiveData<PagingData<Task>>

    fun loadPagedListFilter(timeInMillis: Long): LiveData<PagingData<Task>>

    fun deleteTask(task: Task)

    fun deleteListTask(listTask: List<Task>)
}