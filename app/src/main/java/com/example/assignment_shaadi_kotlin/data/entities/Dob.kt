package com.example.rickandmorty.data.entities


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Dob(
    @SerializedName("age")
    val age: Int,
    @ColumnInfo(defaultValue = " ")
    @SerializedName("date")
    val date: String
)