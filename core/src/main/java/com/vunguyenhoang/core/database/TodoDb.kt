package com.vunguyenhoang.core.database

import android.content.Context
import androidx.room.*
import com.vunguyenhoang.core.converter.TaskTypeConverter
import com.vunguyenhoang.core.database.dao.TaskDao
import com.vunguyenhoang.core.database.dao.TaskTypeDao
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TaskListType

@Database(entities = [Task::class, TaskListType::class], version = 1, exportSchema = false)
@TypeConverters(TaskTypeConverter::class)
abstract class TodoDb : RoomDatabase() {

    companion object {
        fun getDb(context: Context) =
            Room.databaseBuilder(context, TodoDb::class.java, TodoDb::class.java.simpleName)
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun taskDao(): TaskDao
    abstract fun taskTypeDao(): TaskTypeDao
}