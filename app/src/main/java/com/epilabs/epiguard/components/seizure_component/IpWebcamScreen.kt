package com.epilabs.epiguard.components.seizure_component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun IpWebcamScreen(modifier: Modifier = Modifier) {
    var streamUrl by remember { mutableStateOf("http://192.168.0.128:8080/video") }
    var connectedUrl by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Remote Camera Viewer",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // URL Input
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

        // Connect Button
        Button(
            onClick = {
                if (streamUrl.isNotBlank()) {
                    connectedUrl = streamUrl
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

        // Live Feed Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Black, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (connectedUrl != null) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
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
        }
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun IpWebcamScreenPreview() {
    IpWebcamScreen(Modifier)
}