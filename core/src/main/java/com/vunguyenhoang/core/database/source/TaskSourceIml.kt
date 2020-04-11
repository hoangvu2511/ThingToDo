package com.vunguyenhoang.core.database.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vunguyenhoang.core.R
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.database.dao.TaskDao
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.model.TypeTask

class TaskSourceIml(private val taskDao: TaskDao) : TaskSource {
    override fun getAllTasks(): LiveData<Result<List<Task>>> {
        return Transformations.map(taskDao.getAllTasks()) {
            Result.Success(it)
        }
    }
}