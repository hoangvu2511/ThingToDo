package com.vunguyenhoang.core.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
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
    suspend fun addTask(task: Task): Long

    @Query("select * from task WHERE time = :timeInMillis")
    fun loadPagedTask(timeInMillis: Long): PagingSource<Int, Task>

    @Query("select * from task")
    fun loadPagedTask(): PagingSource<Int, Task>

    @Query("select * from task")
    fun loadTasks(): List<Task>

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteListTask(task: List<Task>)
}