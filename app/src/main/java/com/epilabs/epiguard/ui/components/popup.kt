package com.epilabs.epiguard.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.epilabs.epiguard.R

@Composable
fun DeleteConfirmationPopup(
    videoName: String = "SampleVideo.mp4",
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)) // Darkens entire background
        ) {
            // Popup container
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Spacer to account for the square icon overlapping the top
                        Spacer(modifier = Modifier.height(25.dp)) // Half the square's height
                        Text(
                            text = "Delete $videoName?",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 18.sp,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = "Are you sure you want to delete this video? This action cannot be undone.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF475569),
                            textAlign = TextAlign.Center
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, Color(0xFF4F46E5), RoundedCornerShape(8.dp))
                            ) {
                                Text(
                                    text = "Cancel",
                                    color = Color(0xFF4F46E5),
                                    fontSize = 14.sp,
                                    style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
                                )
                            }
                            Button(
                                onClick = onConfirm,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3F51B5) // Red for delete
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Delete",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
                                )
                            }
                        }
                    }
                }
            }
            // Independent square with trash icon, overlayed on top
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-70.dp - 100.dp)) // Adjust to position square above popup
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp) // 50x50.dp square
                        .background(
                            color = Color(0xFF3F51B5), // Violet color from AppColors
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            border = BorderStroke(1.dp, Color(0xFFCBD5E1)), // Light border
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bag), // Replace with your trash icon
                        contentDescription = "Trash",
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp) // Smaller icon to fit 50.dp square
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
private fun DeleteConfirmationPopupPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC)) // Background similar to MobileUI
        ) {
            DeleteConfirmationPopup(
                videoName = "SampleVideo.mp4",
                onConfirm = {},
                onDismiss = {}
            )
        }
    }
}