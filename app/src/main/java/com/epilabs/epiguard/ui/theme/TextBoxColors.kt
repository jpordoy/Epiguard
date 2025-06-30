package com.epilabs.epiguard.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R

object TextboxColors {
    val gray50 = Color(0xFFF5F9FE) // AppColors.color_Gray_50, for background
    val gray100 = Color(0xFFEAEFF5) // AppColors.color_Gray_100, for unfocused border
    val gray600 = Color(0xFF757575) // AppColors.color_Gray_600, for label
    val gray900 = Color(0xFF262626) // AppColors.color_Gray_900, for text
    val primary500 = Color(0xFF3461FD) // AppColors.color_Primary_500, for focused border/cursor
}

object TextboxTypes {
    private val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    private val Poppins = FontFamily(
        Font(googleFont = GoogleFont("Poppins"), fontProvider = provider, weight = FontWeight.Medium)
    )

    val bodyLarge400 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}

// Reuse existing Spacing
object Spacing {
    val small = 8.dp
    val medium = 16.dp
    val textboxSpacing = 12.dp // From provided Column arrangement
}