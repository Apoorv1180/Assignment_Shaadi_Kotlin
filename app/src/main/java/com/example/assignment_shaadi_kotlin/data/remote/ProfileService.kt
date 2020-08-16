package com.example.assignment_shaadi_kotlin.data.remote

import android.provider.ContactsContract
import com.example.rickandmorty.data.entities.Profiles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileService {
    @GET("api")
    suspend fun getAllProfiles(@Query("results") id: Int) : Response<Profiles>

}