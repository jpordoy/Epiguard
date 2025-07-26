// VideoPlayerScreen.kt
package com.epilabs.epiguard.components.testlab_component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.epilabs.epiguard.viewmodel.VideoViewModel

@Composable
fun VideoPlayerScreen(
    navController: NavController,
    videoId: Int,
    viewModel: VideoViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val video = viewModel.getVideoById(videoId)

    if (video == null) {
        Toast.makeText(context, "Video not found", Toast.LENGTH_SHORT).show()
        navController.popBackStack()
        return
    }

    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(video.localPath))
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.playWhenReady = false
                Lifecycle.Event.ON_RESUME -> exoPlayer.playWhenReady = true
                Lifecycle.Event.ON_STOP -> exoPlayer.release()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)) // Blurred background effect
    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false // No controls for simple playback
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}