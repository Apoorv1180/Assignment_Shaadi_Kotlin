package com.example.assignment_shaadi_kotlin.ui.matchprofile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_shaadi_kotlin.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfilesViewModel @ViewModelInject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    val profiles = repository.getProfilesFromRepository()
}
