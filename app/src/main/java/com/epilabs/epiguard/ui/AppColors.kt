package com.epilabs.epiguard.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object AppColors {
    val color_900 = Color(0xFF262626) // Gray/900, used for icons
    val color_Gray_900 = Color(0xFF262626) // Alias for color_900
    val color_Primary_600 = Color(0xFF2A4ECA) // For "Sign Up" text
    val color_Gray_700 = Color(0xFF61677D) // For description text
    val color_Gray_50 = Color(0xFFF5F9FE) // For text field backgrounds
    val color_Gray_100 = Color(0xFFEAEFF5) // For text field borders
    val color_Primary_500 = Color(0xFF3461FD) // For button and blur
    val color_Gray_White = Color(0xFFFFFFFF) // White, for background and text
    val color_Gray_800 = Color(0xFF3B4054) // For "Do you have account?" text
    val color_Primary_100 = Color(0xFFD6DFFF) // For inactive dots
    val color_Gray_600 = Color(0xFF757575) // Placeholder for medium gray, adjust per Figma
    val color_Error_500 = Color(0xFFDC3545) // Bootstrap-style danger red
    val color_gradient_start = Color(0xFF5B69E8) // Example
    val color_gradient_end = Color(0xFF4552CB) // Example
    val color_violet = Color(0xFF4552CB)
    val color_pale_grey = Color(0xFFF5F7FA)
    val color_white = Color.White
    val color_grey = Color(0xFF8A8A8A)
    val color_light_grey_border = Color(0xFFF0F0F8) // New for borders
    val color_pink = Color(0xFFFF69B4) // New for female icon
    val color_black = Color.Black
    val darkblue = Color(0xFF254EDB)
    val myCustomColor = Color(0xFF183188) // Matches the 0xAARRGGBB format
    val wallpaper = Color(0xFF254EDB)

    // Linear gradient for blue-violet
    val blue_violet_gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4552CB), // 30% start
            Color(0xFF4596EA)  // 100% end
        ),
        start = androidx.compose.ui.geometry.Offset(0f, 0f), // Left
        end = androidx.compose.ui.geometry.Offset(301f, 0f)  // Right, matching 301.dp width
    )
}