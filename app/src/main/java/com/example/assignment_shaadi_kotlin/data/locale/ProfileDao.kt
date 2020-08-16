package com.example.assignment_shaadi_kotlin.data.locale

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.entities.Result

@Dao
interface ProfileDao {

    @Query("SELECT * FROM match_result")
    fun getAllProfiles() : LiveData<List<Result>>


//    @Query("SELECT * FROM result WHERE email = :email")
//    fun getProfile(email: String): LiveData<com.example.shaadi_assignment_ktln.data.entities.Result>

    @Insert
    suspend fun insertAllProfiles(results: List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(result: Result)

}