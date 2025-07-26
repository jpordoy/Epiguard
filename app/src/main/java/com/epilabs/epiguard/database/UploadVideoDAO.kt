// UploadVideoDAO.kt
package com.epilabs.epiguard.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.epilabs.epiguard.models.VideoModel

object UploadVideoDAO {
    const val TABLE_NAME = "videos"
    const val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            userId INTEGER,
            name TEXT,
            localPath TEXT,
            uploadDate TEXT,
            status TEXT,
            FOREIGN KEY(userId) REFERENCES tblUsers(userID)
        )
    """

    fun insertVideo(db: SQLiteDatabase, video: VideoModel): Long {
        Log.d("UploadVideoDAO", "Inserting video for userId=${video.userId}")
        val values = ContentValues().apply {
            put("userId", video.userId)
            put("name", video.name)
            put("localPath", video.localPath)
            put("uploadDate", video.uploadDate)
            put("status", video.status)
        }
        return try {
            val result = db.insertOrThrow(TABLE_NAME, null, values)
            Log.d("UploadVideoDAO", "Video inserted: id=$result")
            result
        } catch (e: Exception) {
            Log.e("UploadVideoDAO", "Insert failed: ${e.message}")
            -1
        }
    }

    fun getAllVideos(db: SQLiteDatabase, userId: Int): List<VideoModel> {
        Log.d("UploadVideoDAO", "Getting videos for userId=$userId")
        val list = mutableListOf<VideoModel>()
        val cursor = db.query(
            TABLE_NAME,
            null,
            "userId = ?",
            arrayOf(userId.toString()),
            null,
            null,
            "uploadDate DESC"
        )
        while (cursor.moveToNext()) {
            list.add(
                VideoModel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow("userId")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")) ?: "",
                    localPath = cursor.getString(cursor.getColumnIndexOrThrow("localPath")) ?: "",
                    uploadDate = cursor.getString(cursor.getColumnIndexOrThrow("uploadDate")) ?: "",
                    status = cursor.getString(cursor.getColumnIndexOrThrow("status")) ?: "",
                )
            )
        }
        cursor.close()
        return list
    }

    fun getOldestVideo(db: SQLiteDatabase, userId: Int): VideoModel? {
        Log.d("UploadVideoDAO", "Getting oldest video for userId=$userId")
        val cursor = db.query(
            TABLE_NAME,
            null,
            "userId = ?",
            arrayOf(userId.toString()),
            null,
            null,
            "uploadDate ASC",
            "1"
        )
        return if (cursor.moveToFirst()) {
            VideoModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("userId")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")) ?: "",
                localPath = cursor.getString(cursor.getColumnIndexOrThrow("localPath")) ?: "",
                uploadDate = cursor.getString(cursor.getColumnIndexOrThrow("uploadDate")) ?: "",
                status = cursor.getString(cursor.getColumnIndexOrThrow("status")) ?: "",
            )
        } else {
            null
        }.also { cursor.close() }
    }

    fun deleteVideo(db: SQLiteDatabase, id: Int): Int {
        Log.d("UploadVideoDAO", "Deleting video with id=$id")
        return try {
            db.delete(TABLE_NAME, "id = ?", arrayOf(id.toString()))
        } catch (e: Exception) {
            Log.e("UploadVideoDAO", "Delete failed: ${e.message}")
            0
        }
    }

    fun getTotalVideoSize(db: SQLiteDatabase, userId: Int): Long {
        Log.d("UploadVideoDAO", "Getting total video size for userId=$userId")
        val cursor = db.query(
            TABLE_NAME,
            arrayOf("localPath"),
            "userId = ?",
            arrayOf(userId.toString()),
            null,
            null,
            null
        )
        var totalSize = 0L
        while (cursor.moveToNext()) {
            val path = cursor.getString(cursor.getColumnIndexOrThrow("localPath"))
            totalSize += java.io.File(path).length()
        }
        cursor.close()
        return totalSize
    }
}