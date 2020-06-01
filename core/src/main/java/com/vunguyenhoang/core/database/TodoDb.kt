package com.vunguyenhoang.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vunguyenhoang.core.converter.TaskTypeConverter
import com.vunguyenhoang.core.database.dao.TaskDao
import com.vunguyenhoang.core.database.dao.TaskTypeDao
import com.vunguyenhoang.core.model.Task
import com.vunguyenhoang.core.model.TaskListType

@Database(entities = [Task::class, TaskListType::class], version = 2, exportSchema = false)
@TypeConverters(TaskTypeConverter::class)
abstract class TodoDb : RoomDatabase() {

    companion object {
        fun getDb(context: Context) =
            Room.databaseBuilder(context, TodoDb::class.java, TodoDb::class.java.simpleName)
                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION1_2)
                .build()

        private val MIGRATION1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'task' ADD COLUMN 'uri' STRING")
            }

        }

    }

    abstract fun taskDao(): TaskDao
    abstract fun taskTypeDao(): TaskTypeDao
}