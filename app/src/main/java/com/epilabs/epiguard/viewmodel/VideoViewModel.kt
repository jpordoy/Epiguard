package com.epilabs.epiguard.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.database.UploadVideoDAO
import com.epilabs.epiguard.models.VideoModel
import com.epilabs.epiguard.utils.UploadUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class VideoViewModel(
    private val context: Context,
    private val userId: Int
) : ViewModel() {
    private val dbHandler = DatabaseConnector(context)
    private val uploadUtil = UploadUtil(context, userId)
    private val _videos = MutableStateFlow<List<VideoModel>>(emptyList())
    val videos: StateFlow<List<VideoModel>> = _videos.asStateFlow()
    private val videoDir = File(context.filesDir, "videos").apply { mkdirs() }
    private val maxStorageBytes = 500 * 1024 * 1024 // 500MB
    private val maxVideos = 3

    init {
        viewModelScope.launch {
            loadVideos()
        }
    }

    private fun loadVideos() {
        val db = dbHandler.readableDatabase
        _videos.value = UploadVideoDAO.getAllVideos(db, userId)
        println("DEBUG: Loaded videos: ${_videos.value}")
    }

    @SuppressLint("SimpleDateFormat")
    fun uploadVideo(uri: Uri, context: Context, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            try {
                println("DEBUG: uploadVideo called with URI: $uri")
                // Check storage cap
                val totalSize = UploadVideoDAO.getTotalVideoSize(dbHandler.readableDatabase, userId)
                val newFileSize = getFileSize(uri, context)
                if (totalSize + newFileSize > maxStorageBytes || _videos.value.size >= maxVideos) {
                    val oldest = UploadVideoDAO.getOldestVideo(dbHandler.readableDatabase, userId)
                    if (oldest != null) {
                        File(oldest.localPath).delete()
                        UploadVideoDAO.deleteVideo(dbHandler.writableDatabase, oldest.id)
                    } else {
                        onResult("Storage limit reached")
                        return@launch
                    }
                }

                // Call UploadUtil
                uploadUtil.uploadVideo(uri) { videoModel, error ->
                    if (videoModel != null) {
                        // Copy file to internal storage
                        val fileName = getFileName(uri, context) ?: "video_${System.currentTimeMillis()}.mp4"
                        val destFile = File(videoDir, fileName)
                        context.contentResolver.openInputStream(uri)?.use { input ->
                            FileOutputStream(destFile).use { output ->
                                input.copyTo(output)
                            }
                        } ?: run {
                            onResult("Failed to copy video file")
                            return@uploadVideo
                        }

                        // Update VideoModel with FileProvider URI
                        val updatedVideo = videoModel.copy(
                            localPath = FileProvider.getUriForFile(context, "com.epilabs.epiguard.fileprovider", destFile).toString()
                        )
                        // Since UploadVideoDAO lacks update, delete and re-insert
                        UploadVideoDAO.deleteVideo(dbHandler.writableDatabase, videoModel.id)
                        val newId = UploadVideoDAO.insertVideo(dbHandler.writableDatabase, updatedVideo)
                        if (newId != -1L) {
                            loadVideos()
                            onResult(null)
                        } else {
                            onResult("Failed to update video path")
                        }
                    } else {
                        onResult(error)
                    }
                }
            } catch (e: Exception) {
                onResult("Upload failed: ${e.message}")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun saveVideo(userId: Int, fileName: String, filePath: String, timestamp: String) {
        viewModelScope.launch {
            try {
                // Check storage cap
                val totalSize = UploadVideoDAO.getTotalVideoSize(dbHandler.readableDatabase, userId)
                val newFileSize = File(filePath).length()
                if (totalSize + newFileSize > maxStorageBytes || _videos.value.size >= maxVideos) {
                    val oldest = UploadVideoDAO.getOldestVideo(dbHandler.readableDatabase, userId)
                    if (oldest != null) {
                        File(oldest.localPath).delete()
                        UploadVideoDAO.deleteVideo(dbHandler.writableDatabase, oldest.id)
                    } else {
                        return@launch // Silently skip if storage limit reached
                    }
                }

                val destFile = File(videoDir, fileName)
                // Copy file to internal storage
                context.contentResolver.openInputStream(Uri.parse(filePath))?.use { input ->
                    FileOutputStream(destFile).use { output ->
                        input.copyTo(output)
                    }
                } ?: throw IllegalStateException("Cannot open input stream for $filePath")

                val video = VideoModel(
                    id = 0,
                    userId = userId,
                    name = fileName,
                    localPath = FileProvider.getUriForFile(context, "com.epilabs.epiguard.fileprovider", destFile).toString(),
                    uploadDate = timestamp,
                    status = "Uploaded"
                )
                UploadVideoDAO.insertVideo(dbHandler.writableDatabase, video)
                loadVideos()
            } catch (e: Exception) {
                println("Failed to save video: ${e.message}")
            }
        }
    }

    fun saveResultFile(
        userId: Int,
        fileName: String,
        filePath: String,
        timestamp: String
    ) {
        viewModelScope.launch {
            try {
                // Store PDF metadata
                val video = VideoModel(
                    id = 0,
                    userId = userId,
                    name = fileName,
                    localPath = filePath, // Already a FileProvider URI
                    uploadDate = timestamp,
                    status = "Result"
                )
                UploadVideoDAO.insertVideo(dbHandler.writableDatabase, video)
                loadVideos()
            } catch (e: Exception) {
                println("Failed to save result file: ${e.message}")
            }
        }
    }

    fun deleteVideo(videoId: Int, context: Context) {
        viewModelScope.launch {
            val video = _videos.value.find { it.id == videoId }
            if (video != null) {
                File(video.localPath).delete()
                UploadVideoDAO.deleteVideo(dbHandler.writableDatabase, videoId)
                loadVideos()
            }
        }
    }

    fun getVideoById(videoId: Int): VideoModel? {
        return _videos.value.find { it.id == videoId }
    }

    private fun getFileSize(uri: Uri, context: Context): Long {
        return context.contentResolver.openFileDescriptor(uri, "r")?.use { it.statSize } ?: 0
    }

    private fun getFileName(uri: Uri, context: Context): String? {
        return context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(nameIndex)
        }
    }

    class Factory(
        private val context: Context,
        private val userId: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoViewModel(context, userId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}