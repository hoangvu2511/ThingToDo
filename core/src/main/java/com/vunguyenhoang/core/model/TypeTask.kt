package com.vunguyenhoang.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class TypeTask(val value: String) : Parcelable {
    WORK("Work"),
    PERSONAL("Personal"),
    ALL("All Tasks"),
    ADD_NEW("Add List");
}