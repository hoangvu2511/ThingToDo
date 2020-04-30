package com.vunguyenhoang.core.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vunguyenhoang.core.model.Task

@Dao
interface TaskDao {

    @Query("Select * from task")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun addTask(task: Task): Long

    @Query("select * from task")
    fun loadPagedTask(): DataSource.Factory<Int, Task>

    @Delete
    fun deleteTask(task: Task)

    @Delete
    fun deleteListTask(task: List<Task>)
}