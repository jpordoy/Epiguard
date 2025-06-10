package com.epilabs.epiguard.models

data class UserProfileModel(
    val profileId: Int = 0,
    val userId: Int,
    val fullName: String,
    val phone: String,
    val dateOfBirth: String,
    val profileImage: String? = null, // Can be file path, base64, or URL
    val bio: String
)