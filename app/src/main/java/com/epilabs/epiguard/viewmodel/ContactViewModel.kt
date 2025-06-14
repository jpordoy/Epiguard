package com.epilabs.epiguard.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    var ContactImage = mutableStateOf<String?>(null)
        private set

    fun updateContactImage(path: String?) {
        ContactImage.value = path
    }
}