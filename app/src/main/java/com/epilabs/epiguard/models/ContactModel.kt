package com.epilabs.epiguard.models

data class ContactModel(
    val contactID: Int = 0,
    val userID: Int,
    val firstname: String,
    val lastname: String,
    val contact: String,
    val profileImage: String? = null, // Can be file path, base64, or URL
    val alertType: String,
    val medicalExperience: String,
    val status: String,
    val primaryCarer: String,
    val timestamp: String,

    )
