package com.epilabs.epiguard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R // Adjust package based on your project
import com.epilabs.epiguard.ui.theme.MyColors

object MyTypes {
    // Standard Typography for Material Design roles
    val typography = Typography(
        headlineLarge = TextStyle( // H3 Headline
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.02.sp
        ),
        headlineMedium = TextStyle( // H4 Headline
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 20.sp
        ),
        headlineSmall = TextStyle( // H6 Headline
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.01.sp
        ),
        bodyLarge = TextStyle( // Body 1
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.01.sp
        ),
        labelLarge = TextStyle( // Button
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp
        ),
        labelSmall = TextStyle( // Menu
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.04.sp
        ),
        bodySmall = TextStyle( // Status Time
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.165).sp
        )
    )

    // Custom TextStyle for type_H6_Headline
    val type_H6_Headline = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.01.em,
        color = MyColors.color_h6_headline // Reference new color
    )
}