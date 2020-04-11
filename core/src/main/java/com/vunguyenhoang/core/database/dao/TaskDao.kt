package com.vunguyenhoang.core.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.vunguyenhoang.core.model.Task

@Dao
interface TaskDao {

    @Query("Select * from task")
    fun getAllTasks(): LiveData<List<Task>>

}