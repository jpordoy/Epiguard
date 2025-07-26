// FilePickerLauncher.kt
package com.epilabs.epiguard.viewmodel

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class FilePickerLauncher(
    private val launcher: ManagedActivityResultLauncher<String, Uri?>,
    private val onUriSelected: (Uri) -> Unit,
    private val onError: (String) -> Unit
) {
    fun launchPicker() {
        try {
            launcher.launch("video/*")
        } catch (e: Exception) {
            onError("Failed to launch file picker: ${e.message}")
        }
    }

    companion object {
        @Composable
        fun create(
            onUriSelected: (Uri) -> Unit,
            onError: (String) -> Unit
        ): FilePickerLauncher {
            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri ->
                if (uri != null) {
                    onUriSelected(uri)
                } else {
                    onError("No video selected")
                }
            }
            return FilePickerLauncher(launcher, onUriSelected, onError)
        }
    }
}