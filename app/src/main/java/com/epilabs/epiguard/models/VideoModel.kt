// VideoModel.kt
package com.epilabs.epiguard.models

data class VideoModel(
    val id: Int,
    val userId: Int,
    val name: String,
    val localPath: String,
    val uploadDate: String,
    val status: String,
)