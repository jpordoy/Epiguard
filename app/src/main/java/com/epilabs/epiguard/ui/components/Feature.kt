package com.epilabs.epiguard.ui.components

import androidx.compose.ui.graphics.Color

// Updated Feature data class (removed iconId)
data class Feature(
    val title: String,
    val imageId: Int,
    val darkColor: Color,
    val mediumColor: Color,
    val lightColor: Color,
    val route: String
)