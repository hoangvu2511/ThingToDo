package com.vunguyenhoang.core.repository.taskType

import androidx.lifecycle.LiveData
import com.vunguyenhoang.core.DbResult
import com.vunguyenhoang.core.model.TaskListType

interface TaskTypeRepo {
    suspend fun getAllTaskType(): LiveData<DbResult<List<TaskListType>>>

    suspend fun addTaskType(taskListType: TaskListType)
}