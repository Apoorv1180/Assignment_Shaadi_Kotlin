package com.example.rickandmorty.data.entities


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "match_result")
data class Result(
    @SerializedName("email")
    @PrimaryKey
    @NonNull
    val email: String,
    @SerializedName("picture")
    @Embedded(prefix = "result_picture_")
    val picture: Picture
)