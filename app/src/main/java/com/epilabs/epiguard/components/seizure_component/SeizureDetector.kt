package com.epilabs.epiguard.components.seizure_component

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.epilabs.epiguard.utils.TFLiteHelper
import java.io.ByteArrayOutputStream
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class)
@Composable
fun SeizureDetector(navController: NavController, userId: Int) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    val tfliteHelper = remember { TFLiteHelper(context) }
    var hasCameraPermission by remember { mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) }
    var predictionText by remember { mutableStateOf("Waiting for 10 frames...") }
    var lastAnalyzedTime by remember { mutableStateOf(0L) }
    val analysisIntervalMs = 100L // 10 fps max

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        hasCameraPermission = isGranted
        if (!isGranted) {
            predictionText = "Camera permission required"
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            tfliteHelper.close()
            cameraExecutor.shutdown()
        }
    }

    if (!hasCameraPermission) {
        LaunchedEffect(Unit) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = predictionText, color = Color.Red, fontSize = 20.sp)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Camera Preview
        AndroidView(
            factory = { PreviewView(context).apply { id = android.view.View.generateViewId() } },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            update = { previewView ->
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.surfaceProvider = previewView.surfaceProvider
                    }
                    val imageAnalyzer = ImageAnalysis.Builder()
                        .setTargetResolution(android.util.Size(224, 224))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor) { imageProxy ->
                                val currentTime = System.currentTimeMillis()
                                if (currentTime - lastAnalyzedTime < analysisIntervalMs) {
                                    imageProxy.close()
                                    return@setAnalyzer
                                }
                                lastAnalyzedTime = currentTime

                                val image = imageProxy.image ?: run {
                                    imageProxy.close()
                                    return@setAnalyzer
                                }

                                val bitmap = imageToBitmap(image, imageProxy.imageInfo.rotationDegrees)
                                val result = tfliteHelper.addFrameAndPredict(bitmap, userId)
                                result?.let {
                                    predictionText = "Not Seizure: %.2f | Seizure: %.2f".format(it[0], it[1])
                                }
                                imageProxy.close()
                            }
                        }
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalyzer)
                    } catch (exc: Exception) {
                        predictionText = "Camera error: ${exc.message}"
                    }
                }, ContextCompat.getMainExecutor(context))
            }
        )

        // Prediction Text
        Text(
            text = predictionText,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        // Stop Button
        Button(
            onClick = { navController.navigate("dashboard/$userId") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Stop Detection", color = Color.White, fontSize = 16.sp)
        }
    }
}

private fun imageToBitmap(image: Image, rotationDegrees: Int): Bitmap {
    val yBuffer = image.planes[0].buffer
    val uBuffer = image.planes[1].buffer
    val vBuffer = image.planes[2].buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val nv21 = ByteArray(ySize + uSize + vSize)
    yBuffer.get(nv21, 0, ySize)
    vBuffer.get(nv21, ySize, vSize)
    uBuffer.get(nv21, ySize + vSize, uSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, image.width, image.height), 100, out)
    val jpegBytes = out.toByteArray()
    val bmp = BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size)

    val matrix = Matrix().apply { postRotate(rotationDegrees.toFloat()) }
    return Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, true)
}