package com.example.assignment_shaadi_kotlin.data.entities


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Id(
    @ColumnInfo(defaultValue = " ")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(defaultValue = " ")
    @SerializedName("value")
    val value: String
)