package com.vunguyenhoang.core.repository

import androidx.paging.PagingData
import com.vunguyenhoang.core.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun addTask(task: Task): Long

    fun loadPagedList(): Flow<PagingData<Task>>

    fun loadPagedListFilter(timeInMillis: Long): Flow<PagingData<Task>>

    fun deleteTask(task: Task)

    fun deleteListTask(listTask: List<Task>)
}