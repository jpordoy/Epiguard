package com.epilabs.epiguard.models

data class UserProfileWithDetails(
    val userId: Int,
    val email: String,
    val username: String,
    val password: String,
    val profileId: Int,
    val fullName: String?,
    val phone: String?,
    val dateOfBirth: String?,
    val profileImage: String?,
    val bio: String?
)