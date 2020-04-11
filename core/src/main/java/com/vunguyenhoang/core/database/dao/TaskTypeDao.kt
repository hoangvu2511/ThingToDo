package com.vunguyenhoang.core.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vunguyenhoang.core.model.TaskListType

@Dao
interface TaskTypeDao {

    @Query("select * from taskListType")
    fun getAllTaskType(): LiveData<List<TaskListType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTaskType(type: TaskListType): Long
}