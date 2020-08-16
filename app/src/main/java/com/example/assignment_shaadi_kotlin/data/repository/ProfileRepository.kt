package com.example.assignment_shaadi_kotlin.data.repository

import android.util.Log
import com.example.assignment_shaadi_kotlin.data.locale.ProfileDao
import com.example.assignment_shaadi_kotlin.data.remote.ProfileRemoteDataSource
import com.example.assignment_shaadi_kotlin.utils.performGetOperation
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource,
    private val localDataSource: ProfileDao
) {

    fun getProfilesFromRepository() = performGetOperation(
        databaseQuery = { localDataSource.getAllProfiles() },
        networkCall = { remoteDataSource.getAllProfilesDataSource() },
        saveCallResult = {localDataSource.insertAllProfiles(it.results)
        }
    )


}