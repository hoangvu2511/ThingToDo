package com.vunguyenhoang.core.converter

import androidx.room.TypeConverter
import com.vunguyenhoang.core.model.TypeTask

class TaskTypeConverter {
    @TypeConverter
    fun fromType(value: TypeTask?) = value?.value

    @TypeConverter
    fun toType(value: String?) = when (value) {
        TypeTask.ALL.value -> TypeTask.ALL
        TypeTask.PERSONAL.value -> TypeTask.PERSONAL
        TypeTask.WORK.value -> TypeTask.WORK
        else -> TypeTask.ADD_NEW
    }
}