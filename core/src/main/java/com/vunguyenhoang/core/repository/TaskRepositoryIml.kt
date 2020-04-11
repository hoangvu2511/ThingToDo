package com.vunguyenhoang.core.repository

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.database.source.TaskSource
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TaskListType

class TaskRepositoryIml(private val source: TaskSource) : TaskRepository {

    override fun getTasks(): LiveData<Result<List<Task>>> {
        return source.getAllTasks()
    }

}