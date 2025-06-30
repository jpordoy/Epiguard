package com.epilabs.epiguard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBarComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Time
        Text(
            text = "9:41",
            color = Color(0xFF020E22),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.5).sp
        )

        // Right side indicators (Signal, WiFi, Battery)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Signal bars
            SignalBarsIcon()
            // WiFi icon
            WiFiIcon()
            // Battery icon
            BatteryIcon()
        }
    }
}

@Composable
private fun SignalBarsIcon() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height((4 + index * 2).dp)
                    .background(Color(0xFF020E22))
            )
        }
    }
}

@Composable
private fun WiFiIcon() {
    // Simplified WiFi icon representation
    Box(
        modifier = Modifier.size(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "📶",
            fontSize = 10.sp
        )
    }
}

@Composable
private fun BatteryIcon() {
    // Simplified battery icon
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(10.dp)
            .background(Color(0xFF020E22))
    )
}

@Preview
@Composable
fun StatusBarComponentPreview() {
    StatusBarComponent()
}
