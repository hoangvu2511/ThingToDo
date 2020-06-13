package com.vunguyenhoang.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.vunguyenhoang.core.database.dao.TaskDao
import com.vunguyenhoang.core.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class TaskRepositoryIml(private val taskDao: TaskDao) : TaskRepository {

    override fun addTask(task: Task): Long = runBlocking(Dispatchers.IO) {
        return@runBlocking taskDao.addTask(task)
    }

    override fun loadPagedList(): LiveData<PagingData<Task>> {
        val config = PagingConfig(20)
        val pagingSource = { taskDao.loadPagedTask() }
        return Pager(
            config = config,
            pagingSourceFactory = pagingSource
        ).liveData
    }

    override fun loadPagedListFilter(timeInMillis: Long): LiveData<PagingData<Task>> {
        val pagingSource = { taskDao.loadPagedTask(timeInMillis) }
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = pagingSource
        ).liveData
    }


    override fun deleteTask(task: Task) = runBlocking(Dispatchers.IO) {
        taskDao.deleteTask(task)
    }

    override fun deleteListTask(listTask: List<Task>) = runBlocking(Dispatchers.IO) {
        taskDao.deleteListTask(listTask)
    }
}