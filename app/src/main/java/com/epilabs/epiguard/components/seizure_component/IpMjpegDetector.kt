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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
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
import java.util.concurrent.atomic.AtomicReference

/**
 * MJPEG composable that decodes frames from an MJPEG HTTP stream and runs your TFLite model on them.
 *
 * Usage:
 * IpMjpegDetector(streamUrl = "http://192.168.0.128:8080/video", userId = 1)
 *
 * NOTE: Add android.permission.INTERNET to the manifest.
 */

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
    var predictionText by remember { mutableStateOf("Waiting for frames...") }
    var streaming by remember { mutableStateOf(false) }

    // Jobs to run the streaming coroutine and inference coroutine
    val streamJob = remember { mutableStateOf<Job?>(null) }
    val inferenceJob = remember { mutableStateOf<Job?>(null) }

    // Atomic reference used to give inference coroutine the most recent frame without locking
    val latestFrameRef = remember { AtomicReference<Bitmap?>(null) }

    // Inference settings (tweak these if you want)
    val inferenceIntervalMs = 200L                    // run model every 200ms (~5/sec)
    val consecutiveTriggerCount = 3                  // trigger when N consecutive positives
    val seizureThreshold = 0.8f                       // probability threshold for "positive"
    val predictionHistory = remember { ArrayDeque<Boolean>() }

    // Start stream function (decoding only)
    fun startStream(url: String) {
        if (streaming) return
        streaming = true
        predictionText = "Connecting..."

        // Decode/display coroutine: reads MJPEG frames as fast as possible and updates UI
        streamJob.value = CoroutineScope(Dispatchers.IO).launch {
            var connection: HttpURLConnection? = null
            var input: InputStream? = null
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

                // Read frames in a tight loop, update UI and atomic ref
                while (isActive && streaming) {
                    val frameBitmap = mjpegReader.readFrameBitmap() ?: continue

                    // Update UI frame (Compose state) quickly on main thread
                    withContext(Dispatchers.Main) {
                        latestBitmap = frameBitmap
                    }

                    // Update atomic ref used by inference loop
                    latestFrameRef.set(frameBitmap)
                }
            } catch (e: Exception) {
                Log.e("IpMjpegDetector", "Stream error: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    predictionText = "Stream error: ${e.message}"
                }
            } finally {
                input?.close()
                connection?.disconnect()
                withContext(Dispatchers.Main) {
                    streaming = false
                    connectedUrl = null
                }
            }
        }

        // Inference coroutine: runs at a reduced rate and reads latestFrameRef
        inferenceJob.value = CoroutineScope(Dispatchers.Default).launch {
            while (isActive && streaming) {
                try {
                    val frame = latestFrameRef.get()
                    if (frame != null) {
                        // Make a copy to avoid potential concurrent bitmap issues
                        val copy = frame.copy(frame.config ?: Bitmap.Config.ARGB_8888, true)

                        val result = try {
                            tfliteHelper.addFrameAndPredict(copy, userId)
                        } catch (e: Exception) {
                            Log.e("IpMjpegDetector", "Inference error: ${e.message}", e)
                            null
                        }

                        result?.let {
                            // result assumed to be [NotSeizureProb, SeizureProb] as in your TFLiteHelper return
                            val notSeizureProb = it[0]
                            val seizureProb = it[1]

                            // Rolling boolean history for consecutive detection logic
                            val isPositive = seizureProb >= seizureThreshold
                            predictionHistory.add(isPositive)
                            if (predictionHistory.size > consecutiveTriggerCount) {
                                predictionHistory.removeFirst()
                            }

                            val triggered = (predictionHistory.size == consecutiveTriggerCount)
                                    && predictionHistory.all { it }

                            val text = if (triggered) {
                                "SEIZURE DETECTED (p=%.2f)".format(seizureProb)
                            } else {
                                "Not Seizure: %.2f | Seizure: %.2f".format(notSeizureProb, seizureProb)
                            }

                            withContext(Dispatchers.Main) {
                                predictionText = text
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("IpMjpegDetector", "Inference loop error: ${e.message}", e)
                }

                // Sleep until next inference; does not block display loop at all
                delay(inferenceIntervalMs)
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
        predictionText = "Stopped"
        latestFrameRef.set(null)
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
                // Use asImageBitmap to display
                Image(
                    bitmap = bmp.asImageBitmap(),
                    contentDescription = "Latest frame",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            } ?: run {
                Text(text = "No frame yet", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = predictionText, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

/**
 * Simple MJPEG stream parser.
 * Reads bytes from an InputStream and extracts JPEG frames by searching for 0xFFD8...0xFFD9 markers.
 *
 * Note: This is a simple implementation intended for MJPEG streams served over HTTP (common for IP Webcam apps).
 * If your camera uses RTSP/H.264, use ExoPlayer / MediaCodec to decode frames instead.
 */
private class MjpegStreamReader(private val input: InputStream) {
    private val startMarker = byteArrayOf(0xFF.toByte(), 0xD8.toByte()) // SOI
    private val endMarker = byteArrayOf(0xFF.toByte(), 0xD9.toByte())   // EOI

    /**
     * Read bytes until a full JPEG frame (SOI..EOI) is found. Returns the decoded Bitmap or null on EOF.
     */
    fun readFrameBitmap(): Bitmap? {
        try {
            val buffer = ByteArrayOutputStream()
            val readBuf = ByteArray(4096)
            var foundStart = false

            // Read until SOI
            while (true) {
                val r = input.read(readBuf)
                if (r <= 0) return null
                val chunk = readBuf.copyOf(r)
                if (!foundStart) {
                    val startIndex = indexOf(chunk, startMarker)
                    if (startIndex >= 0) {
                        // write from startMarker to buffer
                        buffer.write(chunk, startIndex, chunk.size - startIndex)
                        foundStart = true
                        // check if EOI is in same chunk
                        val endIndex = indexOf(chunk, endMarker, startIndex)
                        if (endIndex >= 0) {
                            // write up to endMarker and return
                            val len = endIndex + endMarker.size - startIndex
                            val frameBytes = chunk.copyOfRange(startIndex, startIndex + len)
                            return BitmapFactory.decodeByteArray(frameBytes, 0, frameBytes.size)
                        }
                    }
                } else {
                    val endIndex = indexOf(chunk, endMarker)
                    if (endIndex >= 0) {
                        // write up to end marker
                        buffer.write(chunk, 0, endIndex + endMarker.size)
                        val frameBytes = buffer.toByteArray()
                        return BitmapFactory.decodeByteArray(frameBytes, 0, frameBytes.size)
                    } else {
                        buffer.write(chunk)
                    }
                }
            }
        } catch (e: Exception) {
            // swallow and return null (caller handles)
            Log.e("MjpegStreamReader", "Error reading MJPEG frame: ${e.message}", e)
            return null
        }
    }

    // helper to find a byte pattern in a byte array, optionally starting from offset
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
    // Preview can't actually stream; it will show the layout only.
    IpMjpegDetector(initialUrl = "http://192.168.0.128:8080/video", userId = 0, modifier = Modifier.fillMaxSize())
}
