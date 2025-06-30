package com.epilabs.epiguard.ui

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R // Adjust package

object AppTypes {

    private val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    private val Poppins = FontFamily(
        Font(googleFont = GoogleFont("Poppins"), fontProvider = provider, weight = FontWeight.Medium)
    )

    val type_Header_Header_2 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium, // Fallback, adjust if needed
        fontSize = 24.sp
    )
    val type_Body_Regular_400 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium, // Fallback
        fontSize = 14.sp
    )
    val type_Body_Large_500 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
    val type_Body_Large_400 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium, // Fallback
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val type_Body_small_400 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light, // Fallback
        fontSize = 12.sp,
        lineHeight = 18.sp
    )


    val type_Typography_Navbar_Title = TextStyle(
        fontFamily = FontFamily.Default, // Replace with FontFamily("Inter") if available
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    )

    val type_Typography_Body_Small = TextStyle(
        fontFamily = FontFamily.Default, // Replace with FontFamily("Roboto") if available
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.2).sp
    )

    val type_Typography_Body_Medium = TextStyle(
        fontFamily = FontFamily.Default, // Replace with FontFamily("Roboto") if available
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )

    val type_Typography_Label_Large = TextStyle(
        fontFamily = FontFamily.Default, // Replace with FontFamily("Roboto") if available
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
}
