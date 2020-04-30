package com.vunguyenhoang.core.database.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.vunguyenhoang.core.DbResult
import com.vunguyenhoang.core.model.Task

interface
TaskSource {
    fun getAllTasks(): LiveData<DbResult<List<Task>>>

    fun addTask(task: Task): Long

    fun loadPagedList(): LiveData<PagedList<Task>>

    fun deleteTask(task: Task)

    fun deleteListTask(listTask: List<Task>)
}