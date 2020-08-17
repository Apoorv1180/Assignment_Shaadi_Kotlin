package com.example.assignment_shaadi_kotlin.data.entities


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "match_result")
data class Result(
    @SerializedName("email")
    @PrimaryKey
    @NonNull
    val email: String,
    @SerializedName("picture")
    @Embedded(prefix = "result_picture_")
    val picture: Picture,
    @ColumnInfo(name = "isAccepted", defaultValue = "Normal")
    var isAccepted: String,
    @Embedded(prefix = "result_dob_")
    @SerializedName("dob")
    val dob: Dob,
    @Embedded(prefix = "result_name_")
    @SerializedName("name")
    val name: Name,
    @SerializedName("gender")
    val gender: String
)