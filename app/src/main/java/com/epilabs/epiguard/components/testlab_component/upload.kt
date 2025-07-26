package com.epilabs.epiguard.components.testlab_component

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.epilabs.epiguard.R
import com.epilabs.epiguard.models.VideoModel
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.BottomNav
import com.epilabs.epiguard.ui.components.DeleteConfirmationPopup
import com.epilabs.epiguard.viewmodel.FilePickerLauncher
import com.epilabs.epiguard.viewmodel.VideoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileUI(
    modifier: Modifier = Modifier,
    userId: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val isPreview = isPreviewMode()
    val viewModel: VideoViewModel? = if (!isPreview) {
        viewModel(factory = VideoViewModel.Factory(context, userId))
    } else {
        null
    }
    val videos = if (isPreview) {
        previewVideos()
    } else {
        viewModel!!.videos.collectAsState().value
    }
    var isUploading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedVideoId by remember { mutableStateOf<Int?>(null) }
    var videoToDelete by remember { mutableStateOf<VideoModel?>(null) }
    val horizontalScrollState = rememberScrollState()

    val launcher = FilePickerLauncher.create(
        onUriSelected = { uri ->
            if (!isUploading) {
                isUploading = true
                println("DEBUG: FilePickerLauncher onUriSelected called with URI: $uri")
                viewModel?.uploadVideo(uri, context) { error ->
                    errorMessage = error
                    isUploading = false
                    if (error != null) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                println("DEBUG: FilePickerLauncher onUriSelected skipped due to isUploading")
            }
        },
        onError = { error ->
            errorMessage = error
            isUploading = false
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    )

    errorMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        errorMessage = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty title */ },
                navigationIcon = {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = AppColors.color_Gray_100,
                        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.32f)),
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "Back",
                            tint = AppColors.color_violet,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Placeholder for menu action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Menu",
                            tint = AppColors.color_violet,
                            modifier = Modifier.size(30.dp)
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
            // File Section and Search Box
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xfff8fafc))
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Files",
                            color = Color(0xff1e293b),
                            lineHeight = 1.33.em,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.rightarrow),
                            contentDescription = "CaretRight",
                            tint = Color(0xff1e293b)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(1234.dp))
                                .border(BorderStroke(1.dp, Color(0xffcbd5e1)), RoundedCornerShape(1234.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.stars_1),
                                contentDescription = "Filter",
                                tint = Color(0xff475569),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Filter",
                                color = Color(0xff475569),
                                lineHeight = 1.43.em,
                                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(1234.dp))
                                .background(Color(0xff4f46e5))
                                .clickable { navController.navigate("model_classification/$userId") }
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = "Classify",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Classify",
                                color = Color.White,
                                lineHeight = 1.43.em,
                                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(123.dp))
                                .background(Color.White)
                                .border(BorderStroke(1.dp, Color(0xffcbd5e1)), RoundedCornerShape(123.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_normal),
                                contentDescription = "Search",
                                tint = Color(0xff475569),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Search files...",
                                color = Color(0xff475569),
                                lineHeight = 1.38.em,
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Recent Files
            item {
                if (videos.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Recent Files",
                            color = Color(0xff1e293b),
                            lineHeight = 1.33.em,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            videos.take(4).forEach { video ->
                                FileCard(
                                    video = video,
                                    navController = navController,
                                    userId = userId,
                                    isPreview = isPreview
                                )
                            }
                        }
                    }
                }
            }

            // Public Files and Upload/Classify Buttons
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.width(190.dp)
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = "Public Files",
                                color = Color(0xff1e293b),
                                lineHeight = 1.33.em,
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(1234.dp))
                                    .background(Color(0xffeef2ff))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "New",
                                    color = Color(0xff4f46e5),
                                    textAlign = TextAlign.Center,
                                    lineHeight = 1.33.em,
                                    style = TextStyle(fontSize = 12.sp)
                                )
                                Badge(
                                    containerColor = Color(0xff475569),
                                    modifier = Modifier.size(8.dp)
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                onClick = { launcher.launchPicker() },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(123.dp))
                                    .border(BorderStroke(1.dp, Color(0xffcbd5e1)), RoundedCornerShape(123.dp))
                                    .padding(8.dp)
                                    .size(24.dp),
                                enabled = !isUploading && !isPreview
                            ) {
                                if (isUploading) {
                                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                                } else {
                                    Icon(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "Add",
                                        tint = Color(0xff475569),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(1234.dp))
                                    .background(Color(0xff4f46e5))
                                    .clickable { navController.navigate("model_classification/$userId") }
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.play),
                                    contentDescription = "Classify",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "Classify",
                                    color = Color.White,
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }
                }
            }

            // Table Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(horizontalScrollState)
                        .background(Color.White)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "File Name",
                        color = Color(0xff1e293b),
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.width(120.dp).padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Last Modified",
                        color = Color(0xff1e293b),
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.width(100.dp).padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Status",
                        color = Color(0xff1e293b),
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.width(80.dp).padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Actions",
                        color = Color(0xff1e293b),
                        lineHeight = 1.43.em,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.width(80.dp).padding(horizontal = 8.dp)
                    )
                }
            }

            // Scrollable Table
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.White)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(horizontalScrollState)
                    ) {
                        items(videos) { video ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (selectedVideoId == video.id) Color(0xffe0e7ff) else Color.White
                                    )
                                    .clickable { selectedVideoId = video.id }
                                    .padding(horizontal = 8.dp, vertical = 8.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.width(120.dp).padding(horizontal = 8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = if (video.status == "Uploaded") R.drawable.play else R.drawable.folderopen),
                                        contentDescription = "File",
                                        tint = Color(0xff4f46e5),
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = video.name,
                                            color = Color(0xff475569),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                        )
                                        Text(
                                            text = if (video.status == "Uploaded") "Video" else "Result",
                                            color = Color(0xff475569),
                                            lineHeight = 1.43.em,
                                            style = TextStyle(fontSize = 14.sp)
                                        )
                                    }
                                }
                                Text(
                                    text = video.uploadDate,
                                    color = Color(0xff475569),
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(80.dp)
                                        .padding(horizontal = 8.dp)
                                        .clip(RoundedCornerShape(1234.dp))
                                        .background(Color(0xfff8fafc))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = video.status,
                                        color = Color(0xff475569),
                                        textAlign = TextAlign.Center,
                                        lineHeight = 1.33.em,
                                        style = TextStyle(fontSize = 12.sp)
                                    )
                                    Badge(
                                        containerColor = Color(0xff475569),
                                        modifier = Modifier.size(8.dp)
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.width(80.dp).padding(horizontal = 8.dp)
                                ) {
                                    if (video.status == "Uploaded") {
                                        IconButton(
                                            onClick = {
                                                if (!isPreview) {
                                                    navController.navigate("video_player/${video.id}/$userId")
                                                }
                                            },
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(123.dp))
                                                .size(24.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.play),
                                                contentDescription = "Play",
                                                tint = Color(0xff475569),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    } else {
                                        IconButton(
                                            onClick = {
                                                Toast.makeText(context, "Opening ${video.name}", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(123.dp))
                                                .size(24.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.folderopen),
                                                contentDescription = "View",
                                                tint = Color(0xff475569),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                    IconButton(
                                        onClick = {
                                            if (!isPreview) {
                                                videoToDelete = video
                                            }
                                        },
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(123.dp))
                                            .size(24.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.bag),
                                            contentDescription = "Delete",
                                            tint = Color(0xffef4444),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Popup for delete confirmation
        videoToDelete?.let { video ->
            DeleteConfirmationPopup(
                videoName = video.name,
                onConfirm = {
                    if (!isPreview) {
                        viewModel?.deleteVideo(video.id, context)
                        Toast.makeText(context, "${video.name} deleted", Toast.LENGTH_SHORT).show()
                    }
                    videoToDelete = null
                },
                onDismiss = { videoToDelete = null }
            )
        }
    }
}

@Composable
fun FileCard(
    video: VideoModel? = null,
    navController: NavController? = null,
    userId: Int = 1, // Default for preview
    isPreview: Boolean = false
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xfff8fafc))
            .border(BorderStroke(1.dp, Color(0xffe2e8f0)), RoundedCornerShape(12.dp))
            .then(
                if (video != null && !isPreview) {
                    Modifier.clickable {
                        if (video.status == "Uploaded") {
                            navController?.navigate("video_player/${video.id}/$userId")
                        } else {
                            Toast.makeText(context, "Opening ${video.name}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Modifier
                }
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.folder),
            contentDescription = "File",
            colorFilter = ColorFilter.tint(Color(0xff94a3b8)),
            modifier = Modifier
                .size(48.dp)
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = video?.name ?: "filename.txt",
            color = Color(0xff1e293b),
            lineHeight = 1.43.em,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun isPreviewMode(): Boolean {
    return LocalInspectionMode.current
}

private fun previewVideos(): List<VideoModel> {
    return listOf(
        VideoModel(
            id = 1,
            userId = 1,
            name = "SampleVideo1.mp4",
            localPath = "",
            uploadDate = "2025-07-17 12:00:00",
            status = "Uploaded"
        ),
        VideoModel(
            id = 2,
            userId = 1,
            name = "SampleVideo2.mp4",
            localPath = "",
            uploadDate = "2025-07-16 10:30:00",
            status = "Uploaded"
        ),
        VideoModel(
            id = 3,
            userId = 1,
            name = "SampleVideo1_results.pdf",
            localPath = "",
            uploadDate = "2025-07-17 12:05:00",
            status = "Result"
        )
    )
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
private fun MobileUIPreview() {
    MobileUI(userId = 1, navController = NavController(LocalContext.current))
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
private fun MobileUILandscapePreview() {
    MobileUI(userId = 1, navController = NavController(LocalContext.current))
}