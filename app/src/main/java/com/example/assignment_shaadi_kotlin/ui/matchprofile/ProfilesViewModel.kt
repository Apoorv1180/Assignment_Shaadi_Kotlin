package com.example.assignment_shaadi_kotlin.ui.matchprofile

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.assignment_shaadi_kotlin.data.entities.Result
import com.example.assignment_shaadi_kotlin.data.repository.ProfileRepository
import com.example.assignment_shaadi_kotlin.utils.Resource
import com.example.assignment_shaadi_kotlin.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfilesViewModel @ViewModelInject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    val profiles = repository.getProfilesFromRepository()

    fun updateProfile(result: Result): LiveData<Int> {
        return repository.updateMyProfile(result)
    }

}
