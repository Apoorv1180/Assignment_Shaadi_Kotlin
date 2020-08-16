package com.example.assignment_shaadi_kotlin.data.entities


import com.google.gson.annotations.SerializedName

data class Profiles(
    @SerializedName("results")
    val results: List<Result>
)