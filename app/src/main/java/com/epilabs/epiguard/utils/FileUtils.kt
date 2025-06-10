package com.epilabs.epiguard.utils

import android.content.Context
import android.net.Uri
import java.io.File

fun saveImageToInternalStorage(context: Context, uri: Uri): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.filesDir, "profile_${System.currentTimeMillis()}.jpg")
    inputStream?.use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
    return file.absolutePath
}