package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.R

@Composable
fun Frame3(modifier: Modifier = Modifier, navController: NavController, modelName: String) {
    Scaffold(
        bottomBar = { BottomNav(modifier = Modifier.fillMaxWidth()) },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .paint(
                        painter = painterResource(id = R.drawable._228579_desktop_wallpaper),
                        contentScale = ContentScale.Crop
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.menu1),
                contentDescription = "fi:menu",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .offset(x = 16.dp, y = 32.dp)
                    .size(32.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 200.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White)
                    .padding(20.dp)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = modelName,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.folderopen),
                        contentDescription = "u:network",
                        colorFilter = ColorFilter.tint(Color(0xFF757575)),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Model Type: CNN",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF757575)
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.folderopen),
                        contentDescription = "u:learning",
                        colorFilter = ColorFilter.tint(Color(0xFF757575)),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Learning Type: Supervised",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF757575)
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { 0.92f },
                        color = Color(0xFF3F51B5),
                        trackColor = Color(0xFFE8EAF6),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Average Confidence Score: 0.92",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF757575)
                        )
                    )
                }
                Text(
                    text = "Best Suited For",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TagButton(text = "Tonic-Clonic")
                    TagButton(text = "General-Epilepsy")
                    TagButton(text = "Violent Jerking")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFE0E0E0))
                )
                Text(
                    text = "Model Description",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                )
                Text(
                    text = "Model trained on tonic-clonic data from multiple participants in real-world situations. This model is able to detect clonic jerking and repetitive movements with high accuracy.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        lineHeight = 1.4.em,
                        color = Color(0xFF757575)
                    )
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF3F51B5), Color(0xFF7986CB))
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = "Close",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TagButton(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF3F51B5), RoundedCornerShape(12.dp))
            .background(Color(0xFFE8EAF6))
            .clickable { /* Static for now */ }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3F51B5)
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(widthDp = 415, heightDp = 896)
@Composable
private fun Frame3Preview() {
    Frame3(navController = NavController(LocalContext.current), modelName = "SeizureGuard CNN")
}