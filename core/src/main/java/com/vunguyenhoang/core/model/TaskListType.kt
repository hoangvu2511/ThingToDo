package com.vunguyenhoang.core.model

import androidx.annotation.IdRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vunguyenhoang.core.R

@Entity(tableName = "taskListType")
data class TaskListType(
    @PrimaryKey val title: TypeTask = TypeTask.ADD_NEW,
    val itemCount: Int? = -1
) {
    fun getCounts() = when (itemCount) {
        -1 -> null
        0 -> "No"
        else -> "$itemCount"
    }

    fun getDrawable() = when(title){
        else -> R.drawable.ic_add_list
    }

}
