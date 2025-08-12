package com.epilabs.epiguard.components.seizure_component

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.utils.TFLiteHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.atomic.AtomicReference

data class PredictionEntry(val timestampRange: String, val label: String, val confidence: Float)

@Composable
fun IpMjpegDetector(
    modifier: Modifier = Modifier,
    initialUrl: String = "http://192.168.0.128:8080/video",
    userId: Int = 0
) {
    val context = LocalContext.current
    val tfliteHelper = remember { TFLiteHelper(context) }

    var streamUrl by remember { mutableStateOf(initialUrl) }
    var connectedUrl by remember { mutableStateOf<String?>(null) }

    // UI state
    var latestBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var streaming by remember { mutableStateOf(false) }
    val predictions = remember { mutableStateListOf<PredictionEntry>() }

    // Jobs for streaming and inference
    val streamJob = remember { mutableStateOf<Job?>(null) }
    val inferenceJob = remember { mutableStateOf<Job?>(null) }

    // Atomic reference for latest frame
    val latestFrameRef = remember { AtomicReference<Bitmap?>(null) }

    // Inference settings
    val inferenceIntervalMs = 5000L // 5 seconds
    val maxRetries = 3 // Retry attempts for stream connection

    // Start stream function
    fun startStream(url: String) {
        if (streaming) return
        streaming = true
        connectedUrl = url
        Log.d("IpMjpegDetector", "Starting stream: $url")

        streamJob.value = CoroutineScope(Dispatchers.IO).launch {
            var connection: HttpURLConnection? = null
            var input: InputStream? = null
            var retryCount = 0

            while (isActive && streaming && retryCount < maxRetries) {
                try {
                    val u = URL(url)
                    connection = (u.openConnection() as HttpURLConnection).apply {
                        readTimeout = 15_000
                        connectTimeout = 10_000
                        requestMethod = "GET"
                        doInput = true
                        connect()
                    }

                    input = connection.inputStream
                    val mjpegReader = MjpegStreamReader(input)
                    var lastFrameTime = System.currentTimeMillis()
                    var frameCount = 0

                    while (isActive && streaming) {
                        val frameBitmap = mjpegReader.readFrameBitmap() ?: continue
                        frameCount++
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastFrameTime >= 1000) {
                            Log.d("IpMjpegDetector", "Frame rate: ${(frameCount * 1000.0 / (currentTime - lastFrameTime)).toInt()} FPS")
                            frameCount = 0
                            lastFrameTime = currentTime
                        }

                        // Create separate copies for UI and inference
                        val uiBitmap = frameBitmap.copy(Bitmap.Config.ARGB_8888, false)
                        val inferenceBitmap = frameBitmap.copy(Bitmap.Config.ARGB_8888, false)
                        frameBitmap.recycle() // Recycle original immediately

                        withContext(Dispatchers.Main) {
                            latestBitmap?.recycle() // Recycle old UI bitmap
                            latestBitmap = uiBitmap
                        }
                        latestFrameRef.set(inferenceBitmap)
                        retryCount = 0 // Reset retries on successful frame
                    }
                } catch (e: Exception) {
                    Log.e("IpMjpegDetector", "Stream error: ${e.message}", e)
                    retryCount++
                    if (retryCount < maxRetries) {
                        Log.d("IpMjpegDetector", "Retrying stream connection ($retryCount/$maxRetries)")
                        input?.close()
                        connection?.disconnect()
                        delay(1000L) // Wait before retry
                        continue
                    } else {
                        withContext(Dispatchers.Main) {
                            streaming = false
                            connectedUrl = null
                            predictions.add(PredictionEntry(
                                timestampRange = "${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis())} - Error",
                                label = "Stream Disconnected",
                                confidence = 0f
                            ))
                        }
                    }
                } finally {
                    input?.close()
                    connection?.disconnect()
                }
            }
        }

        // Inference coroutine with fixed 5-second schedule
        inferenceJob.value = CoroutineScope(Dispatchers.Default).launch {
            var nextInferenceTime = System.currentTimeMillis()
            while (isActive && streaming) {
                val startTime = System.currentTimeMillis()
                Log.d("IpMjpegDetector", "Starting inference cycle at $startTime")

                try {
                    val frame = latestFrameRef.getAndSet(null) // Clear reference after use
                    if (frame != null && !frame.isRecycled) {
                        val result = try {
                            tfliteHelper.addFrameAndPredict(frame, userId)
                        } catch (e: Exception) {
                            Log.e("IpMjpegDetector", "Inference error: ${e.message}", e)
                            null
                        } finally {
                            if (!frame.isRecycled) frame.recycle() // Recycle after inference
                        }

                        if (result != null) {
                            val seizureProb = result[0]
                            val notSeizureProb = result[1]
                            val predictedIndex = if (seizureProb > notSeizureProb) 0 else 1
                            val label = if (predictedIndex == 0) "Seizure" else "Not Seizure"
                            val confidence = if (predictedIndex == 0) seizureProb else notSeizureProb

                            val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                            val timestampRange = "${formatter.format(startTime - inferenceIntervalMs)} - ${formatter.format(startTime)}"

                            withContext(Dispatchers.Main) {
                                predictions.add(PredictionEntry(timestampRange, label, confidence))
                                Log.d("IpMjpegDetector", "Prediction: $timestampRange, $label, Confidence: $confidence")
                            }
                        } else {
                            Log.d("IpMjpegDetector", "No valid inference result")
                        }
                    } else {
                        Log.d("IpMjpegDetector", "No frame available for inference or frame recycled")
                    }
                } catch (e: Exception) {
                    Log.e("IpMjpegDetector", "Inference loop error: ${e.message}", e)
                }

                // Adjust delay to maintain 5-second schedule
                val elapsedTime = System.currentTimeMillis() - startTime
                val adjustedDelay = (inferenceIntervalMs - elapsedTime).coerceAtLeast(0)
                nextInferenceTime += inferenceIntervalMs
                val timeToNext = (nextInferenceTime - System.currentTimeMillis()).coerceAtLeast(0)
                Log.d("IpMjpegDetector", "Inference took $elapsedTime ms, delaying $timeToNext ms")
                delay(timeToNext)
            }
        }
    }

    // Stop stream
    fun stopStream() {
        streamJob.value?.cancel()
        streamJob.value = null
        inferenceJob.value?.cancel()
        inferenceJob.value = null
        streaming = false
        latestFrameRef.get()?.recycle()
        latestFrameRef.set(null)
        latestBitmap?.recycle()
        latestBitmap = null
        Log.d("IpMjpegDetector", "Stream stopped")
    }

    DisposableEffect(Unit) {
        onDispose {
            stopStream()
            tfliteHelper.close()
        }
    }

    // UI
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
    ) {
        Text(
            text = "Remote Camera Detector",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = streamUrl,
            onValueChange = { streamUrl = it },
            label = { Text("MJPEG stream URL") },
            placeholder = { Text("http://192.168.x.x:8080/video") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    connectedUrl = streamUrl
                    startStream(streamUrl)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier.weight(1f),
                enabled = !streaming
            ) {
                Text(text = "Connect", color = Color.White)
            }

            Button(
                onClick = { stopStream() },
                modifier = Modifier.weight(1f),
                enabled = streaming
            ) {
                Text(text = "Disconnect")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Display latest frame
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            latestBitmap?.let { bmp ->
                if (!bmp.isRecycled) {
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = "Latest frame",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(text = "Frame recycled", color = Color.White)
                }
            } ?: run {
                Text(text = "No frame yet", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Prediction Table
        Text(
            text = "Prediction History",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            if (predictions.isEmpty()) {
                item {
                    Text(
                        text = "No predictions yet",
                        fontSize = 14.sp,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            items(predictions.reversed()) { prediction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = prediction.timestampRange,
                        fontSize = 14.sp,
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = "${prediction.label} (p=%.2f)".format(prediction.confidence),
                        fontSize = 14.sp,
                        color = if (prediction.label == "Seizure") Color(0xFFFF4444) else Color(0xFF333333)
                    )
                }
            }
        }
    }
}

private class MjpegStreamReader(private val input: InputStream) {
    private val startMarker = byteArrayOf(0xFF.toByte(), 0xD8.toByte())
    private val endMarker = byteArrayOf(0xFF.toByte(), 0xD9.toByte())

    fun readFrameBitmap(): Bitmap? {
        var buffer: ByteArrayOutputStream? = null
        var retryCount = 0
        val maxFrameRetries = 3

        while (retryCount < maxFrameRetries) {
            try {
                buffer = ByteArrayOutputStream()
                val readBuf = ByteArray(4096)
                var foundStart = false

                while (true) {
                    val r = input.read(readBuf)
                    if (r <= 0) {
                        buffer.close()
                        return null
                    }
                    val chunk = readBuf.copyOf(r)
                    if (!foundStart) {
                        val startIndex = indexOf(chunk, startMarker)
                        if (startIndex >= 0) {
                            buffer.write(chunk, startIndex, chunk.size - startIndex)
                            foundStart = true
                            val endIndex = indexOf(chunk, endMarker, startIndex)
                            if (endIndex >= 0) {
                                val len = endIndex + endMarker.size - startIndex
                                val frameBytes = chunk.copyOfRange(startIndex, startIndex + len)
                                buffer.close()
                                return BitmapFactory.decodeByteArray(frameBytes, 0, frameBytes.size)
                            }
                        }
                    } else {
                        val endIndex = indexOf(chunk, endMarker)
                        if (endIndex >= 0) {
                            buffer.write(chunk, 0, endIndex + endMarker.size)
                            val frameBytes = buffer.toByteArray()
                            buffer.close()
                            return BitmapFactory.decodeByteArray(frameBytes, 0, frameBytes.size)
                        } else {
                            buffer.write(chunk)
                        }
                    }
                }
            } catch (e: Exception) {
                retryCount++
                Log.w("MjpegStreamReader", "Error reading MJPEG frame (attempt $retryCount/$maxFrameRetries): ${e.message}")
                buffer?.close()
                if (retryCount < maxFrameRetries) {
                    Thread.sleep(100) // Short delay before retry
                    continue
                }
                Log.e("MjpegStreamReader", "Failed to read MJPEG frame after $maxFrameRetries attempts: ${e.message}", e)
                return null
            }
        }
        return null
    }

    private fun indexOf(data: ByteArray, pattern: ByteArray, startOffset: Int = 0): Int {
        outer@ for (i in startOffset..data.size - pattern.size) {
            for (j in pattern.indices) {
                if (data[i + j] != pattern[j]) continue@outer
            }
            return i
        }
        return -1
    }
}

@Preview(showBackground = true)
@Composable
private fun IpMjpegDetectorPreview() {
    IpMjpegDetector(initialUrl = "http://192.168.0.128:8080/video", userId = 0, modifier = Modifier.fillMaxSize())
}