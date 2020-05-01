package com.vunguyenhoang.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vunguyenhoang.core.database.dao.TaskDao
import com.vunguyenhoang.core.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class TaskRepositoryIml(private val taskDao: TaskDao) : TaskRepository {

    override fun addTask(task: Task): Long = runBlocking(Dispatchers.IO) {
        return@runBlocking taskDao.addTask(task)
    }

    override fun loadPagedList(): LiveData<PagedList<Task>> {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(
            taskDao.loadPagedTask(),
            config
        ).build()
    }

    override fun deleteTask(task: Task) = runBlocking(Dispatchers.IO) {
        taskDao.deleteTask(task)
    }

    override fun deleteListTask(listTask: List<Task>) = runBlocking(Dispatchers.IO) {
        taskDao.deleteListTask(listTask)
    }
}