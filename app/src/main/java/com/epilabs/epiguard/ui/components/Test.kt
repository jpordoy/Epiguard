package com.epilabs.epiguard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.components.DoctorCard
import com.epilabs.epiguard.ui.components.Spacing

@Composable
fun TestScreen(modifier: Modifier = Modifier) {
    val doctorCards = listOf(
        DoctorCard(
            name = "Jamie Pordoy",
            role = "Primary Care Contact",
            imageRes = R.drawable.image,
            gradientBrush = AppColors.blue_violet_gradient,
            overlayOpacity = 0.1f,
            phoneButtonText = "Phone",
            emailButtonText = "Email",
            phoneButtonColor = AppColors.color_Gray_50,
            emailButtonColor = AppColors.color_Gray_50,
            phoneIconRes = R.drawable.star,
            emailIconRes = R.drawable.phone
        ),
        DoctorCard(
            name = "Alex Smith",
            role = "Specialist Doctor",
            imageRes = R.drawable.guy2,
            gradientBrush = Brush.linearGradient(
                colors = listOf(Color(0xFF4CAF50), Color(0xFF81C784)),
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset(301f, 0f)
            ),
            overlayOpacity = 0.1f,
            phoneButtonText = "Call",
            emailButtonText = "Message",
            phoneButtonColor = Color(0xFF4CAF50),
            emailButtonColor = Color(0xFF81C784),
            phoneIconRes = R.drawable.phone,
            emailIconRes = R.drawable.star
        ),
        DoctorCard(
            name = "Maria Jones",
            role = "Pediatrician",
            imageRes = R.drawable.image,
            gradientBrush = Brush.linearGradient(
                colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB)),
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset(301f, 0f)
            ),
            overlayOpacity = 0.1f,
            phoneButtonText = "Contact",
            emailButtonText = "Email",
            phoneButtonColor = Color(0xFF2196F3),
            emailButtonColor = Color(0xFFBBDEFB),
            phoneIconRes = R.drawable.star,
            emailIconRes = R.drawable.phone
        )
    )

    Column(
        modifier = modifier.fillMaxWidth(), // Ensure full width for centering
        horizontalAlignment = Alignment.CenterHorizontally // Center cards horizontally
    ) {
        doctorCards.forEachIndexed { index, card ->
            card.Compose()
            if (index < doctorCards.size - 1) {
                Spacer(modifier = Modifier.height(Spacing.small)) // Add spacer between cards
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 317, heightDp = 400)
@Composable
fun TestScreenPreview() {
    TestScreen()
}