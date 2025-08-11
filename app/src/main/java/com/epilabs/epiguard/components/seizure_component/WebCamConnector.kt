package com.epilabs.epiguard.components.seizure_component

import android.graphics.BitmapFactory
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.epilabs.epiguard.utils.TFLiteHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Composable
fun IpWebcamConnector(modifier: Modifier = Modifier, userId: Int) {
    var streamUrl by remember { mutableStateOf("http://192.168.0.128:8080/video") }
    var connectedUrl by remember { mutableStateOf<String?>(null) }
    var currentPrediction by remember { mutableStateOf("Waiting for 10 frames...") }
    val predictionHistory = remember { mutableStateListOf<String>() }  // List to hold timestamped predictions

    val context = androidx.compose.ui.platform.LocalContext.current
    val inferenceExecutor = remember { Executors.newSingleThreadExecutor() }
    val scope = rememberCoroutineScope()
    var mjpegJob by remember { mutableStateOf<Job?>(null) }
    val tfliteHelper = remember { TFLiteHelper(context) }

    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }

    DisposableEffect(Unit) {
        onDispose {
            mjpegJob?.cancel()
            inferenceExecutor.shutdown()
            try {
                inferenceExecutor.awaitTermination(1, TimeUnit.SECONDS)
            } catch (_: InterruptedException) { }
            tfliteHelper.close()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
    ) {
        Text(
            text = "Remote Camera Viewer",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = streamUrl,
            onValueChange = { streamUrl = it },
            label = { Text("Enter IP Webcam URL") },
            placeholder = { Text("http://192.168.x.x:8080/video") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (streamUrl.isNotBlank()) {
                    mjpegJob?.cancel()
                    connectedUrl = streamUrl
                    predictionHistory.clear()  // Clear history on new connection
                    currentPrediction = "Waiting for 10 frames..."
                    mjpegJob = scope.launch(Dispatchers.IO) {
                        runMjpegStream(
                            streamUrl,
                            inferenceExecutor,
                            tfliteHelper,
                            userId
                        ) { probs ->
                            scope.launch(Dispatchers.Main) {
                                val timestamp = dateFormat.format(System.currentTimeMillis())
                                val predictionStr = "$timestamp - Not Seizure: %.2f | Seizure: %.2f".format(probs[0], probs[1])
                                predictionHistory.add(predictionStr)
                                currentPrediction = predictionStr  // Optional: Keep showing latest at bottom of video
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Connect",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)  // Allocate space for video (adjust weight as needed)
                .background(Color.Black, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (connectedUrl != null) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        WebView(ctx).apply {
                            settings.javaScriptEnabled = true
                            settings.useWideViewPort = true
                            settings.loadWithOverviewMode = true
                            settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            webViewClient = WebViewClient()
                            loadUrl(connectedUrl!!)
                        }
                    }
                )
            } else {
                Text(
                    text = "No camera connected",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
            ) {
                Text(
                    text = currentPrediction,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Prediction History",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)  // Allocate remaining space for history list
                .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            items(predictionHistory) { prediction ->
                Text(
                    text = prediction,
                    fontSize = 14.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

private suspend fun runMjpegStream(
    streamUrl: String,
    inferenceExecutor: java.util.concurrent.ExecutorService,
    tfliteHelper: TFLiteHelper,
    userId: Int,
    onPrediction: (FloatArray) -> Unit
) {
    var connection: HttpURLConnection? = null
    var bis: BufferedInputStream? = null
    try {
        val url = URL(streamUrl)
        connection = withContext(Dispatchers.IO) {
            url.openConnection()
        } as HttpURLConnection
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        connection.doInput = true
        withContext(Dispatchers.IO) {
            connection.connect()
        }

        bis = BufferedInputStream(connection.inputStream)
        val buffer = ByteArrayOutputStream()
        val readBuffer = ByteArray(4096)
        var lastByte = -1
        var started = false
        var lastSubmitTime = 0L
        val minIntervalMs = 100L

        while (true) {
            val read = withContext(Dispatchers.IO) {
                bis.read(readBuffer)
            }
            if (read == -1) break
            for (i in 0 until read) {
                val b = readBuffer[i].toInt() and 0xFF
                if (!started) {
                    if (lastByte == 0xFF && b == 0xD8) {
                        buffer.reset()
                        buffer.write(0xFF)
                        buffer.write(0xD8)
                        started = true
                    }
                } else {
                    buffer.write(b)
                    if (lastByte == 0xFF && b == 0xD9) {
                        val jpegBytes = buffer.toByteArray()
                        val now = System.currentTimeMillis()
                        if (now - lastSubmitTime >= minIntervalMs) {
                            lastSubmitTime = now
                            inferenceExecutor.submit {
                                try {
                                    val bmp = BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size)
                                    val result = tfliteHelper.addFrameAndPredict(bmp, userId)
                                    result?.let { onPrediction(it) }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                        started = false
                        buffer.reset()
                    }
                }
                lastByte = b
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try { bis?.close() } catch (_: Exception) {}
        try { connection?.disconnect() } catch (_: Exception) {}
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun IpWebcamConnectorPreview() {
    IpWebcamConnector(Modifier, userId = 0)
}