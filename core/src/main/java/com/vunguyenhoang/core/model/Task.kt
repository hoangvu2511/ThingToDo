package com.vunguyenhoang.core.model

import androidx.databinding.ObservableField
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task")
data class Task (
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String? = "",
    val description: String? = "",
    val time: Long? = 0,
    val isFinished: Boolean = false,
    val type: TypeTask? = null
) {
    @Ignore
    val isShowCheck = ObservableField(false)

    @Ignore
    val isCheck = ObservableField(false)
}

