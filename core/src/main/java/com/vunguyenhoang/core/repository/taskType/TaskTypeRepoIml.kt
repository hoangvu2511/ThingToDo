package com.vunguyenhoang.core.repository.taskType

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vunguyenhoang.core.database.dao.TaskTypeDao
import com.vunguyenhoang.core.model.TaskListType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.vunguyenhoang.core.Result
import com.vunguyenhoang.core.model.TypeTask


class TaskTypeRepoIml(private val taskTypeDao: TaskTypeDao) : TaskTypeRepo {

    override suspend fun getAllTaskType(): LiveData<Result<List<TaskListType>>> =
        Transformations.map(taskTypeDao.getAllTaskType()) {
            val result = arrayListOf<TaskListType>()
            result.addAll(it)
            result.apply {
                addAll(it)
                add(
                    TaskListType(
                    title = TypeTask.ALL,
                        itemCount = 0
                ))
                add(
                    TaskListType(
                    title = TypeTask.PERSONAL,
                        itemCount = 0
                ))
                add(
                    TaskListType(
                    title = TypeTask.WORK,
                        itemCount = 0
                ))
                add(TaskListType())
            }
            return@map Result.Success(result.toList())
        }

    override suspend fun addTaskType(taskListType: TaskListType)  = withContext(Dispatchers.IO){
        taskTypeDao.addTaskType(taskListType)
        return@withContext
    }
}