package com.example.assignment_shaadi_kotlin.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.assignment_shaadi_kotlin.data.entities.Profiles
import com.example.assignment_shaadi_kotlin.data.entities.Result
import com.example.assignment_shaadi_kotlin.data.locale.ProfileDao
import com.example.assignment_shaadi_kotlin.data.remote.ProfileRemoteDataSource
import com.example.assignment_shaadi_kotlin.utils.Resource
import com.example.assignment_shaadi_kotlin.utils.performGetOperation
import com.example.assignment_shaadi_kotlin.utils.performGetOperationOffline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource,
    private val localDataSource: ProfileDao
) {

    fun getProfilesFromRepository() = performGetOperation(
        databaseQuery = { localDataSource.getAllProfiles() },
        networkCall = { remoteDataSource.getAllProfilesDataSource() },
        saveCallResult = { databaseResults, profiles ->
            var dataToInsert: MutableList<Result> = ArrayList();
            var networkResults = profiles.results
            var dataBaseProfiles = databaseResults
            for (res in networkResults) {
                if (!dataBaseProfiles.isNullOrEmpty()) {
                    val profile = dataBaseProfiles.find { profile -> profile.email == res.email }
                    if (profile == null) {
                        res.isAccepted = "Normal"
                        dataToInsert.add(res)
                    }
                } else {
                    res.isAccepted = "Normal"
                    dataToInsert.add(res)
                }
            }
            runBlocking { localDataSource.insertAllProfiles(dataToInsert) }
        }
    )

    fun updateMyProfile(result: Result): LiveData<Int> {
        var data: MutableLiveData<Int> = MutableLiveData()
        var resultValue: Int = 0
        GlobalScope.launch {
            resultValue = localDataSource.updateProfile(result)
        }
        data.value = resultValue
        return data
    }

    fun getProfilesFromRepositoryOffline() = performGetOperationOffline(
        databaseQuery = { localDataSource.getAllProfiles() },
        saveCallResult = { databaseResults ->
            var dataBaseProfiles = databaseResults
        }
    )
}