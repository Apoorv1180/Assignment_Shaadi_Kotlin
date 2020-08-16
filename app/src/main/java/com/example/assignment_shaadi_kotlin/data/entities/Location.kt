package com.example.rickandmorty.data.entities


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("city")
    val city: String,
    @Embedded(prefix = "result_dob_coor_")
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String,
    @SerializedName("postcode")
    val postcode: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    @Embedded(prefix = "result_dob_street_")
    val street: Street,
    @Embedded(prefix = "result_dob_coor_")
    @SerializedName("timezone")
    val timezone: Timezone
)