package com.epilabs.epiguard.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    var profileImage = mutableStateOf<String?>(null)
        private set

    fun updateProfileImage(path: String?) {
        profileImage.value = path
    }
}