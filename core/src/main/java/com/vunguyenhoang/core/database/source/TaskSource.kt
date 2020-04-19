package com.vunguyenhoang.core.database.source

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.Task

interface TaskSource {
    fun getAllTasks(): LiveData<Result<List<Task>>>

    fun addTask(task: Task): Long
}