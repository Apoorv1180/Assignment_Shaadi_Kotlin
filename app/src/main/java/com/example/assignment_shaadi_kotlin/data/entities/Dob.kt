package com.example.assignment_shaadi_kotlin.data.entities


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Dob(
    @SerializedName("age")
    val age: Int,
    @ColumnInfo(defaultValue = " ")
    @SerializedName("date")
    val date: String
)