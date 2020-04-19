package com.vunguyenhoang.core.repository

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.Task

interface TaskRepository {
    fun getTasks(): LiveData<Result<List<Task>>>

    fun addTask(task: Task): Long
}