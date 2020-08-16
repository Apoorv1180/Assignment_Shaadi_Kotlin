package com.example.assignment_shaadi_kotlin.data.remote

import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val profileService: ProfileService
): BaseDataSource() {

    suspend fun getAllProfilesDataSource() = getResult { profileService.getAllProfiles(10) }
}