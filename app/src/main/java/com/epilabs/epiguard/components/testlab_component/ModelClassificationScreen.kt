package com.epilabs.epiguard.components.testlab_component

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.epilabs.epiguard.R
import com.epilabs.epiguard.models.VideoModel
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.BottomNav
import com.epilabs.epiguard.utils.TFLiteVideoHelper
import com.epilabs.epiguard.utils.VideoPredictionResult
import com.epilabs.epiguard.viewmodel.VideoViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelClassificationScreen(
    modifier: Modifier = Modifier,
    userId: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val viewModel: VideoViewModel? = if (!isPreview) {
        viewModel(factory = VideoViewModel.Factory(context, userId))
    } else {
        null
    }
    val videos = if (isPreview) {
        previewVideos()
    } else {
        viewModel?.videos?.collectAsState()?.value ?: emptyList()
    }

    val models = listOf(
        ModelInfo("Model A", "Detects seizure events with high accuracy", "model.tflite")
    )

    var selectedVideo by remember { mutableStateOf<VideoModel?>(null) }
    var selectedModel by remember { mutableStateOf<ModelInfo?>(null) }
    var videoExpanded by remember { mutableStateOf(false) }
    var modelExpanded by remember { mutableStateOf(false) }
    var showModelInfo by remember { mutableStateOf(false) }
    var modelInfoToShow by remember { mutableStateOf<ModelInfo?>(null) }

    var classificationResults by remember { mutableStateOf<List<VideoPredictionResult>?>(null) }
    var isClassifying by remember { mutableStateOf(false) }
    var currentPrediction by remember { mutableStateOf<VideoPredictionResult?>(null) }

    // Permission handling
    var hasStoragePermission by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        hasStoragePermission = permissions.all { it.value }
        if (!hasStoragePermission) {
            Toast.makeText(context, "Storage permissions required", Toast.LENGTH_LONG).show()
        }
    }

    // Video picker
    val videoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val fileName = "video_${System.currentTimeMillis()}.mp4"
            try {
                // Copy video to internal storage
                val videoDir = File(context.filesDir, "videos").apply { mkdirs() }
                val videoFile = File(videoDir, fileName)
                context.contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(videoFile).use { output ->
                        input.copyTo(output)
                    }
                }
                // Generate content:// URI
                val contentUri = FileProvider.getUriForFile(context, "com.epilabs.epiguard.fileprovider", videoFile)
                viewModel?.saveVideo(
                    userId = userId,
                    fileName = fileName,
                    filePath = contentUri.toString(),
                    timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                )
                Log.d("ModelClassificationScreen", "Video saved: $contentUri")
                Toast.makeText(context, "Video added", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("ModelClassificationScreen", "Failed to save video: ${e.message}", e)
                Toast.makeText(context, "Failed to add video: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    val tfliteHelper = remember { TFLiteVideoHelper(context) }

    DisposableEffect(Unit) {
        onDispose {
            tfliteHelper.close()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Classify Video",
                        color = Color(0xff1e293b),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = AppColors.color_Gray_100,
                        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.32f)),
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "Back",
                            tint = AppColors.color_violet,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { navController.popBackStack() }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            BottomNav(modifier = Modifier.fillMaxWidth())
        },
        containerColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .background(Color.White)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Add Video Button
            item {
                Button(
                    onClick = {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && !hasStoragePermission) {
                            permissionLauncher.launch(arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ))
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasStoragePermission) {
                            permissionLauncher.launch(arrayOf(Manifest.permission.READ_MEDIA_VIDEO))
                        } else {
                            videoPickerLauncher.launch("video/*")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff4f46e5),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Add Video",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }

            // Video Selection Dropdown
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Select Video",
                        color = Color(0xff1e293b),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                    ExposedDropdownMenuBox(
                        expanded = videoExpanded,
                        onExpandedChange = { videoExpanded = !videoExpanded }
                    ) {
                        TextField(
                            value = selectedVideo?.name ?: "Choose a video",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = videoExpanded)
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedIndicatorColor = Color(0xffcbd5e1),
                                focusedIndicatorColor = Color(0xff4f46e5)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .border(1.dp, Color(0xffcbd5e1), RoundedCornerShape(8.dp))
                        )
                        ExposedDropdownMenu(
                            expanded = videoExpanded,
                            onDismissRequest = { videoExpanded = false }
                        ) {
                            videos.filter { it.status == "Uploaded" }.forEach { video ->
                                DropdownMenuItem(
                                    text = { Text(video.name) },
                                    onClick = {
                                        selectedVideo = video
                                        videoExpanded = false
                                        classificationResults = null
                                        currentPrediction = null
                                        Log.d("ModelClassificationScreen", "Selected video: ${video.name}, localPath: ${video.localPath}")
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Model Selection Dropdown
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Select Model",
                            color = Color(0xff1e293b),
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = {
                                modelInfoToShow = selectedModel
                                showModelInfo = true
                            },
                            enabled = selectedModel != null
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.codesandbox),
                                contentDescription = "Model Info",
                                tint = if (selectedModel != null) Color(0xff4f46e5) else Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    ExposedDropdownMenuBox(
                        expanded = modelExpanded,
                        onExpandedChange = { modelExpanded = !modelExpanded }
                    ) {
                        TextField(
                            value = selectedModel?.name ?: "Choose a model",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = modelExpanded)
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedIndicatorColor = Color(0xffcbd5e1),
                                focusedIndicatorColor = Color(0xff4f46e5)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .border(1.dp, Color(0xffcbd5e1), RoundedCornerShape(8.dp))
                        )
                        ExposedDropdownMenu(
                            expanded = modelExpanded,
                            onDismissRequest = { modelExpanded = false }
                        ) {
                            models.forEach { model ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = model.name,
                                                style = TextStyle(fontSize = 14.sp)
                                            )
                                            IconButton(
                                                onClick = {
                                                    navController.navigate("model_details/${model.name}")
                                                    modelExpanded = false
                                                },
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.codesandbox),
                                                    contentDescription = "Model Details",
                                                    tint = Color(0xff4f46e5),
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }
                                    },
                                    onClick = {
                                        selectedModel = model
                                        modelExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Classify Button
            item {
                Button(
                    onClick = {
                        if (selectedVideo != null && selectedModel != null) {
                            isClassifying = true
                            val videoUri = try {
                                val parsedUri = selectedVideo!!.localPath.toUri()
                                // Validate content:// URI
                                if (parsedUri.scheme == "content") {
                                    parsedUri
                                } else {
                                    throw IllegalStateException("Invalid URI scheme: ${parsedUri.scheme}")
                                }
                            } catch (e: Exception) {
                                Log.e("ModelClassificationScreen", "Invalid video URI: ${selectedVideo!!.localPath}, error: ${e.message}", e)
                                Toast.makeText(context, "Invalid video URI: ${e.message}", Toast.LENGTH_LONG).show()
                                isClassifying = false
                                return@Button
                            }
                            Log.d("ModelClassificationScreen", "Starting classification for video: $videoUri")
                            // Validate URI accessibility
                            try {
                                context.contentResolver.openInputStream(videoUri)?.close()
                                    ?: throw IllegalStateException("Cannot access video file")
                            } catch (e: Exception) {
                                Log.e("ModelClassificationScreen", "Cannot access video: $videoUri, error: ${e.message}", e)
                                Toast.makeText(context, "Cannot access video: ${e.message}", Toast.LENGTH_LONG).show()
                                isClassifying = false
                                return@Button
                            }
                            tfliteHelper.classifyVideo(context, videoUri, intervalMs = 2000L) { results ->
                                classificationResults = results
                                if (results.isNotEmpty()) {
                                    try {
                                        val pdfUri = generateResultsPdf(context, selectedVideo!!.name, results)
                                        viewModel?.saveResultFile(
                                            userId = userId,
                                            fileName = "${selectedVideo!!.name}_results.pdf",
                                            filePath = pdfUri.toString(),
                                            timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                                        )
                                        Toast.makeText(context, "Classification results saved", Toast.LENGTH_SHORT).show()
                                    } catch (e: Exception) {
                                        Log.e("ModelClassificationScreen", "Failed to save PDF: ${e.message}", e)
                                        Toast.makeText(context, "Failed to save PDF: ${e.message}", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Log.w("ModelClassificationScreen", "No classification results for $videoUri")
                                    Toast.makeText(context, "No classification results", Toast.LENGTH_LONG).show()
                                }
                                isClassifying = false
                                Toast.makeText(context, "Classification completed", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Please select a video and model", Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff4f46e5),
                        contentColor = Color.White
                    ),
                    enabled = !isClassifying,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    if (isClassifying) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text(
                            text = "Classify Video",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }

            // Video Player with Prediction Overlay
            if (classificationResults != null && selectedVideo != null) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Video Preview",
                            color = Color(0xff1e293b),
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        )
                        VideoPlayerWithPrediction(
                            videoUri = try {
                                val parsedUri = selectedVideo!!.localPath.toUri()
                                if (parsedUri.scheme == "content") {
                                    parsedUri
                                } else {
                                    Log.e("ModelClassificationScreen", "Invalid video URI for player: ${selectedVideo!!.localPath}")
                                    Uri.EMPTY
                                }
                            } catch (e: Exception) {
                                Log.e("ModelClassificationScreen", "Invalid video URI for player: ${selectedVideo!!.localPath}, error: ${e.message}", e)
                                Uri.EMPTY
                            },
                            classificationResults = classificationResults,
                            currentPrediction = currentPrediction,
                            onPredictionChange = { prediction -> currentPrediction = prediction },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color.Black)
                        )
                        Button(
                            onClick = {
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && !hasStoragePermission) {
                                    permissionLauncher.launch(arrayOf(
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                    ))
                                    return@Button
                                }
                                try {
                                    val pdfUri = generateResultsPdf(context, selectedVideo!!.name, classificationResults!!)
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        setDataAndType(pdfUri, "application/pdf")
                                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY)
                                    }
                                    context.startActivity(Intent.createChooser(intent, "Open PDF"))
                                    Toast.makeText(context, "PDF opened", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    Log.e("ModelClassificationScreen", "Failed to open PDF: ${e.message}", e)
                                    Toast.makeText(context, "Failed to open PDF: ${e.message}", Toast.LENGTH_LONG).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xff4f46e5),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = "Download Results as PDF",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }
    }

    if (showModelInfo && modelInfoToShow != null) {
        AlertDialog(
            onDismissRequest = { showModelInfo = false },
            title = { Text(modelInfoToShow!!.name, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) },
            text = { Text(modelInfoToShow!!.description, style = TextStyle(fontSize = 14.sp)) },
            confirmButton = {
                TextButton(onClick = { showModelInfo = false }) {
                    Text("OK", color = Color(0xff4f46e5))
                }
            },
            containerColor = Color.White
        )
    }
}

@Composable
fun VideoPlayerWithPrediction(
    videoUri: Uri,
    classificationResults: List<VideoPredictionResult>?,
    currentPrediction: VideoPredictionResult?,
    onPredictionChange: (VideoPredictionResult?) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            if (videoUri != Uri.EMPTY) {
                setMediaItem(MediaItem.fromUri(videoUri))
                prepare()
                playWhenReady = true
            }
        }
    }

    var currentPosition by remember { mutableLongStateOf(0L) }

    LaunchedEffect(classificationResults, videoUri) {
        Log.d("VideoPlayerWithPrediction", "Playing video: $videoUri, results: $classificationResults")
        if (videoUri == Uri.EMPTY) {
            Log.w("VideoPlayerWithPrediction", "Invalid video URI")
            onPredictionChange(null)
            return@LaunchedEffect
        }
        if (classificationResults.isNullOrEmpty()) {
            Log.w("VideoPlayerWithPrediction", "No classification results available")
            onPredictionChange(null)
            return@LaunchedEffect
        }
        while (true) {
            currentPosition = exoPlayer.currentPosition
            val prediction = classificationResults.minByOrNull { result ->
                abs(result.timestamp - currentPosition)
            }?.takeIf { result ->
                abs(result.timestamp - currentPosition) <= 3000L
            }
            Log.d("VideoPlayerWithPrediction", "Position: $currentPosition, Prediction: $prediction")
            onPredictionChange(prediction)
            delay(100)
        }
    }

    DisposableEffect(videoUri) {
        onDispose {
            exoPlayer.stop()
            exoPlayer.clearVideoSurface()
            coroutineScope.launch {
                delay(100) // Ensure buffers are cleared
                exoPlayer.release()
                Log.d("VideoPlayerWithPrediction", "ExoPlayer released")
            }
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { playerView ->
                playerView.player = exoPlayer
            }
        )
        currentPrediction?.let { prediction ->
            Text(
                text = "${prediction.predictedLabel}: ${"%.2f".format(prediction.confidence)}",
                color = Color.Green,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                    .padding(4.dp)
            )
        }
    }
}

fun generateResultsPdf(context: Context, videoName: String, results: List<VideoPredictionResult>): Uri {
    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = document.startPage(pageInfo)
    val canvas = page.canvas
    val paint = android.graphics.Paint().apply {
        color = android.graphics.Color.BLACK
        textSize = 12f
    }

    canvas.drawText("Classification Results for $videoName", 40f, 40f, paint)
    var yPosition = 80f
    results.forEach { result ->
        val text = "Time: ${(result.timestamp / 1000).toInt()}s, ${result.predictedLabel} (Confidence: ${"%.2f".format(result.confidence)})"
        canvas.drawText(text, 40f, yPosition, paint)
        yPosition += 20f
    }

    document.finishPage(page)

    val documentsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        ?: throw IllegalStateException("Cannot access Documents directory")
    val file = File(documentsDir, "${videoName}_results_${System.currentTimeMillis()}.pdf")
    Log.d("ModelClassificationScreen", "Generating PDF at: ${file.absolutePath}")
    try {
        FileOutputStream(file).use { outputStream ->
            document.writeTo(outputStream)
        }
        document.close()
        val pdfUri = FileProvider.getUriForFile(context, "com.epilabs.epiguard.fileprovider", file)
        Log.d("ModelClassificationScreen", "PDF URI: $pdfUri")
        return pdfUri
    } catch (e: Exception) {
        document.close()
        Log.e("ModelClassificationScreen", "Failed to generate PDF: ${e.message}", e)
        throw e
    }
}

data class ModelInfo(val name: String, val description: String, val fileName: String)

private fun previewVideos(): List<VideoModel> {
    return listOf(
        VideoModel(
            id = 1,
            userId = 1,
            name = "SampleVideo1.mp4",
            localPath = "content://com.epilabs.epiguard.fileprovider/videos/SampleVideo1.mp4",
            uploadDate = "2025-07-20 12:00:00",
            status = "Uploaded"
        ),
        VideoModel(
            id = 2,
            userId = 1,
            name = "SampleVideo2.mp4",
            localPath = "content://com.epilabs.epiguard.fileprovider/videos/SampleVideo2.mp4",
            uploadDate = "2025-07-20 10:30:00",
            status = "Uploaded"
        )
    )
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
private fun ModelClassificationScreenPreview() {
    ModelClassificationScreen(userId = 1, navController = NavController(LocalContext.current))
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
private fun ModelClassificationScreenLandscapePreview() {
    ModelClassificationScreen(userId = 1, navController = NavController(LocalContext.current))
}