package com.vunguyenhoang.core.repository.taskType

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.Result

interface TaskTypeRepo {
    suspend fun getAllTaskType(): LiveData<Result<List<TaskListType>>>

    suspend fun addTaskType(taskListType: TaskListType)
}