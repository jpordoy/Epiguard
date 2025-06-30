package com.epilabs.epiguard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = MyColors.color_violet,
    onPrimary = MyColors.color_white,
    secondary = MyColors.color_black,
    onSecondary = MyColors.color_white,
    background = MyColors.color_white,
    surface = MyColors.color_white,
    onBackground = MyColors.color_black,
    onSurface = MyColors.color_black,
    outline = MyColors.color_border_grey,
    surfaceVariant = MyColors.color_pale_grey,
    error = Color(0xFFB00020), // Default error color
    onError = MyColors.color_white
)

@Composable
fun EpiGuardTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MyTypes.typography,
        content = content
    )
}