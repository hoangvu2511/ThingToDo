package com.vunguyenhoang.core.database.source

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.model.TypeTask

interface TaskSource {
    fun getAllTasks(): LiveData<Result<List<Task>>>
}