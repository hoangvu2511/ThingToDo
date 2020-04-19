package com.vunguyenhoang.core.repository

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.database.source.TaskSource
import com.vunguyenhoang.core.model.Task

class TaskRepositoryIml(private val source: TaskSource) : TaskRepository {

    override fun getTasks(): LiveData<Result<List<Task>>> {
        return source.getAllTasks()
    }

    override fun addTask(task: Task): Long {
        return source.addTask(task)
    }
}