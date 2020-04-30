package com.vunguyenhoang.core.database.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vunguyenhoang.core.DbResult
import com.vunguyenhoang.core.database.dao.TaskDao
import com.vunguyenhoang.core.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class TaskSourceIml(private val taskDao: TaskDao) : TaskSource {
    override fun getAllTasks(): LiveData<DbResult<List<Task>>> {
        return Transformations.map(taskDao.getAllTasks()) {
            DbResult.Success(it)
        }
    }

    override fun addTask(task: Task): Long = runBlocking(Dispatchers.IO) {
        taskDao.addTask(task)
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