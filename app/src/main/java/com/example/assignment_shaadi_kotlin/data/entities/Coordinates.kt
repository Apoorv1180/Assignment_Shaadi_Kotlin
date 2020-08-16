package com.example.rickandmorty.data.entities


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Coordinates(
    @ColumnInfo(defaultValue = " ")
    @SerializedName("latitude")
    val latitude: String,
    @ColumnInfo(defaultValue = " ")
    @SerializedName("longitude")
    val longitude: String
)