package com.epilabs.epiguard.components.seizure_component

import android.graphics.BitmapFactory
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.epilabs.epiguard.utils.TFLiteHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Composable
fun RemoteSeizureDetector(navController: NavController, userId: Int, modifier: Modifier = Modifier) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val inferenceExecutor = remember { Executors.newSingleThreadExecutor() }
    val scope = rememberCoroutineScope()
    val tfliteHelper = remember { TFLiteHelper(context) }
    var streamUrl by remember { mutableStateOf("http://192.168.0.128:8080/shot.jpg") }
    var connectedUrl by remember { mutableStateOf<String?>(null) }
    var predictionText by remember { mutableStateOf("Waiting for 10 frames...") }
    var job by remember { mutableStateOf<Job?>(null) }
    val analysisIntervalMs = 2000L // 0.5 FPS (one frame every 2 seconds)
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }

    DisposableEffect(Unit) {
        onDispose {
            job?.cancel()
            inferenceExecutor.shutdown()
            try {
                inferenceExecutor.awaitTermination(1, TimeUnit.SECONDS)
            } catch (_: InterruptedException) {}
            tfliteHelper.close()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Remote Seizure Detector",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = streamUrl,
            onValueChange = { streamUrl = it },
            label = { Text("Enter IP Webcam URL") },
            placeholder = { Text("http://192.168.x.x:8080/shot.jpg") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (streamUrl.isNotEmpty()) {
                    job?.cancel()
                    connectedUrl = streamUrl.replace("/shot.jpg", "/video")
                    predictionText = "Waiting for 10 frames..."
                    job = scope.launch(Dispatchers.IO) {
                        runJpegStream(
                            streamUrl = streamUrl,
                            inferenceExecutor = inferenceExecutor,
                            tfliteHelper = tfliteHelper,
                            userId = userId,
                            analysisIntervalMs = analysisIntervalMs,
                            onPrediction = { probs ->
                                scope.launch(Dispatchers.Main) {
                                    val timestamp = dateFormat.format(System.currentTimeMillis())
                                    predictionText = "$timestamp - Not Seizure: %.2f | Seizure: %.2f".format(probs[0], probs[1])
                                    Log.d("JpegFetcher", "Prediction: $predictionText")
                                }
                            },
                            onError = { error ->
                                scope.launch(Dispatchers.Main) {
                                    predictionText = "Error: $error"
                                    Log.e("JpegFetcher", "Error: $error")
                                }
                            }
                        )
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
                .weight(0.6f)
                .background(Color.Black, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (connectedUrl != null) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        WebView(ctx).apply {
                            settings.javaScriptEnabled = false
                            settings.useWideViewPort = true
                            settings.loadWithOverviewMode = true
                            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
                            settings.cacheMode = WebSettings.LOAD_NO_CACHE
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Prediction",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = predictionText,
            fontSize = 14.sp,
            color = Color(0xFF333333),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .padding(8.dp)
        )

        Button(
            onClick = { navController.navigate("dashboard/$userId") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Stop Detection",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private suspend fun runJpegStream(
    streamUrl: String,
    inferenceExecutor: ExecutorService,
    tfliteHelper: TFLiteHelper,
    userId: Int,
    analysisIntervalMs: Long,
    onPrediction: (FloatArray) -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    try {
        while (true) {
            val request = Request.Builder().url(streamUrl).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    onError("HTTP error: ${response.code}")
                    Log.e("JpegFetcher", "HTTP error: ${response.code}")
                    delay(analysisIntervalMs)
                    return@use
                }

                val contentType = response.header("Content-Type") ?: ""
                if (!contentType.contains("image/jpeg")) {
                    onError("Invalid Content-Type: $contentType. Expected image/jpeg.")
                    Log.e("JpegFetcher", "Invalid Content-Type: $contentType")
                    delay(analysisIntervalMs)
                    return@use
                }

                val jpegBytes = response.body?.bytes() ?: run {
                    onError("Empty response body")
                    Log.e("JpegFetcher", "Empty response body")
                    delay(analysisIntervalMs)
                    return@use
                }
                Log.d("JpegFetcher", "Fetched JPEG: ${jpegBytes.size} bytes")

                processFrame(jpegBytes, tfliteHelper, userId, inferenceExecutor, onPrediction, onError)
            }
            delay(analysisIntervalMs) // Wait before next fetch
        }
    } catch (e: Exception) {
        Log.e("JpegFetcher", "Stream error: ${e.message}")
        onError("Stream error: ${e.message}")
    }
}

private fun processFrame(
    jpegBytes: ByteArray,
    tfliteHelper: TFLiteHelper,
    userId: Int,
    inferenceExecutor: ExecutorService,
    onPrediction: (FloatArray) -> Unit,
    onError: (String) -> Unit
) {
    // Basic JPEG validation
    if (jpegBytes.size < 4) {
        Log.e("JpegFetcher", "JPEG too short: ${jpegBytes.size} bytes")
        onError("JPEG too short: ${jpegBytes.size} bytes")
        return
    }

    if ((jpegBytes[0].toInt() and 0xFF) != 0xFF || (jpegBytes[1].toInt() and 0xFF) != 0xD8) {
        Log.e(
            "JpegFetcher",
            "Invalid JPEG start: First 4 bytes=${jpegBytes.take(4).joinToString { "0x%02X".format(it.toInt() and 0xFF) }}"
        )
        onError("Invalid JPEG start")
        return
    }

    // Check for EOI (0xFFD9) in last 10 bytes
    var eoiFound = false
    for (i in jpegBytes.size - 10.coerceAtMost(jpegBytes.size)..jpegBytes.size - 2) {
        if ((jpegBytes[i].toInt() and 0xFF) == 0xFF && (jpegBytes[i + 1].toInt() and 0xFF) == 0xD9) {
            eoiFound = true
            break
        }
    }
    if (!eoiFound) {
        Log.e(
            "JpegFetcher",
            "Invalid JPEG end: Last 10 bytes=${jpegBytes.takeLast(10).joinToString { "0x%02X".format(it.toInt() and 0xFF) }}"
        )
        onError("Invalid JPEG end")
        return
    }

    Log.d("JpegFetcher", "Valid JPEG markers: SOI=0xFFD8, EOI=0xFFD9")

    inferenceExecutor.submit {
        try {
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size, options)
            if (options.outWidth <= 0 || options.outHeight <= 0) {
                Log.e("JpegFetcher", "Invalid JPEG dimensions: ${options.outWidth}x${options.outHeight}")
                onError("Invalid JPEG dimensions")
                return@submit
            }
            Log.d("JpegFetcher", "JPEG dimensions: ${options.outWidth}x${options.outHeight}")

            val bitmap = BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size)
            if (bitmap == null) {
                Log.e("JpegFetcher", "Failed to decode JPEG frame")
                onError("Failed to decode frame")
                return@submit
            }
            Log.d("JpegFetcher", "Decoded frame: ${bitmap.width}x${bitmap.height}")

            val frameCount = try {
                tfliteHelper.javaClass.getDeclaredField("frameBuffer")
                    .apply { isAccessible = true }
                    .get(tfliteHelper)?.let { it as ArrayList<*> }?.size ?: 0
            } catch (e: Exception) {
                Log.e("JpegFetcher", "Failed to access frameBuffer: ${e.message}")
                0
            }
            Log.d("JpegFetcher", "Frame buffer size: $frameCount")

            val result = tfliteHelper.addFrameAndPredict(bitmap, userId)
            if (result != null) {
                Log.d("JpegFetcher", "Prediction result: Not Seizure=${result[0]}, Seizure=${result[1]}")
                onPrediction(result)
            } else {
                Log.w("JpegFetcher", "No prediction (frame buffer size: $frameCount)")
                onError("No prediction (frame buffer size: $frameCount)")
            }
        } catch (e: Exception) {
            Log.e("JpegFetcher", "Error processing frame: ${e.message}")
            onError("Frame processing error: ${e.message}")
        }
    }
}
