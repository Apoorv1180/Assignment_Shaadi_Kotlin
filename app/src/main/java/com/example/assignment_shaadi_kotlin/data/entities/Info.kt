package com.example.assignment_shaadi_kotlin.data.entities


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: Int,
    @SerializedName("seed")
    val seed: String,
    @SerializedName("version")
    val version: String
)