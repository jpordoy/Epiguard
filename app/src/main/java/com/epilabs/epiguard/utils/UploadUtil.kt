package com.epilabs.epiguard.utils

import android.content.Context
import android.net.Uri
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.database.UploadVideoDAO
import com.epilabs.epiguard.models.VideoModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UploadUtil(private val context: Context, private val userId: Int) {
    private val dbHandler = DatabaseConnector(context)

    fun uploadVideo(uri: Uri, onResult: (VideoModel?, String?) -> Unit) {
        try {
            val videoName = getFileName(uri)
            val timeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val videoModel = VideoModel(
                id = 0,
                userId = userId,
                name = videoName,
                localPath = uri.toString(),
                uploadDate = timeStamp,
                status = "Uploaded"
            )
            val db = dbHandler.writableDatabase
            // Check for duplicates by name
            val existingVideos = UploadVideoDAO.getAllVideos(db, userId)
            if (existingVideos.any { it.name == videoModel.name }) {
                println("DEBUG: UploadUtil skipped insertion, video with name $videoName already exists")
                onResult(null, "Video already exists")
                return
            }
            val resultId = UploadVideoDAO.insertVideo(db, videoModel)
            if (resultId != -1L) {
                val insertedVideo = UploadVideoDAO.getAllVideos(db, userId).find { it.name == videoName }
                println("DEBUG: UploadUtil inserted video: $insertedVideo")
                onResult(insertedVideo, null)
            } else {
                onResult(null, "Upload failed: Database error")
            }
        } catch (e: Exception) {
            onResult(null, "Upload failed: ${e.message}")
        }
    }

    fun saveResultFile(fileName: String, filePath: String, timestamp: String) {
        val resultModel = VideoModel(
            id = 0,
            userId = userId,
            name = fileName,
            localPath = filePath,
            uploadDate = timestamp,
            status = "Result"
        )
        val db = dbHandler.writableDatabase
        UploadVideoDAO.insertVideo(db, resultModel)
    }

    private fun getFileName(uri: Uri): String {
        var fileName = "video_${System.currentTimeMillis()}.mp4"
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst() && nameIndex != -1) {
                fileName = cursor.getString(nameIndex)
            }
        }
        return fileName
    }
}