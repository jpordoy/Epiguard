package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors

data class DoctorCard(
    val name: String,
    val role: String,
    val imageRes: Int,
    val gradientBrush: Brush,
    val overlayOpacity: Float = 0.4f,
    val phoneButtonText: String = "Phone",
    val emailButtonText: String = "Email",
    val phoneButtonColor: Color = AppColors.color_Gray_50,
    val emailButtonColor: Color = AppColors.color_Gray_50,
    val phoneIconRes: Int = R.drawable.star,
    val emailIconRes: Int = R.drawable.phone
) {
    @Composable
    fun Compose(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(9.63.dp, Alignment.Top),
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(height = 100.dp) // Fixed height as requested
                .clip(shape = RoundedCornerShape(9.629999160766602.dp))
                .background(brush = gradientBrush)
                .padding(horizontal = 25.dp, vertical = 12.839999198913574.dp) // 25.dp left/right, original vertical
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(9.63.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 39.dp)
                        .clip(shape = RoundedCornerShape(6.419999599456787.dp))
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "profile image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(size = 39.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .requiredHeight(height = 40.dp)
                        .weight(weight = 1f)
                ) {
                    Column(
                        modifier = Modifier
                            .requiredWidth(width = 192.dp)
                    ) {
                        Text(
                            text = name,
                            color = AppColors.color_white,
                            lineHeight = 1.75.em,
                            style = TextStyle(
                                fontSize = 12.839999198913574.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.16.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = role,
                            color = AppColors.color_white,
                            lineHeight = 1.67.em,
                            style = TextStyle(
                                fontSize = 9.629999160766602.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.16.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    // Circle positioned outside, only partially visible
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .offset(x = 140.dp, y = ((-26.84).dp)) // Positive x to extend outside
                            .requiredSize(size = 200.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.Transparent.copy(alpha = overlayOpacity))
                    )
                }
                SizeSmallTypeSecondaryStateDefaultVariationFloatingTextFalse()
            }
            Box(
                modifier = Modifier
                    .requiredWidth(width = 171.dp)
                    .requiredHeight(height = 25.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.42.dp, Alignment.Start),
                    modifier = Modifier
                        .requiredWidth(width = 83.dp)
                        .requiredHeight(height = 25.dp)
                        .clip(shape = RoundedCornerShape(6.419999599456787.dp))
                        .background(color = phoneButtonColor)
                        .padding(all = 6.419999599456787.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.42.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredWidth(width = 68.dp)
                            .requiredHeight(height = 14.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.02.dp, Alignment.Start),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(6.419999599456787.dp))
                                .background(color = phoneButtonColor)
                                .padding(all = 3.2099997997283936.dp)
                        ) {
                            Image(
                                painter = painterResource(id = phoneIconRes),
                                contentDescription = "phone icon",
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                        Text(
                            text = phoneButtonText,
                            color = AppColors.color_Gray_600,
                            lineHeight = 1.67.em,
                            style = TextStyle(
                                fontSize = 9.629999160766602.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.16.sp
                            )
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.42.dp, Alignment.Start),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 88.dp, y = 0.dp)
                        .requiredWidth(width = 83.dp)
                        .requiredHeight(height = 25.dp)
                        .clip(shape = RoundedCornerShape(6.419999599456787.dp))
                        .background(color = emailButtonColor)
                        .padding(all = 6.419999599456787.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.42.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredWidth(width = 68.dp)
                            .requiredHeight(height = 14.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.02.dp, Alignment.Start),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(6.419999599456787.dp))
                                .background(color = emailButtonColor)
                                .padding(all = 3.2099997997283936.dp)
                        ) {
                            Image(
                                painter = painterResource(id = emailIconRes),
                                contentDescription = "email icon",
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                        Text(
                            text = emailButtonText,
                            color = AppColors.color_white,
                            lineHeight = 1.67.em,
                            style = TextStyle(
                                fontSize = 9.629999160766602.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.16.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SizeSmallTypeSecondaryStateDefaultVariationFloatingTextFalse(
    modifier: Modifier = Modifier,
    iconL80725: Boolean = false,
    iconR80850: Boolean = false,
    label808100: String = ""
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(9.63.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(shape = RoundedCornerShape(72.2249984741211.dp))
            .background(color = AppColors.color_Gray_600)
            .border(
                border = BorderStroke(0.8024999499320984.dp, AppColors.color_Gray_600),
                shape = RoundedCornerShape(72.2249984741211.dp)
            )
            .padding(all = 4.814999580383301.dp)
            .shadow(
                elevation = 1.6049998998641968.dp,
                shape = RoundedCornerShape(72.2249984741211.dp)
            )
    ) {
        if (iconL80725) {
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Icon",
                modifier = Modifier
                    .requiredSize(size = 16.dp)
            )
        }
        if (label808100.isNotEmpty()) {
            Text(
                text = label808100,
                color = AppColors.color_Error_500,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        if (iconR80850) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Icon",
                modifier = Modifier
                    .requiredSize(size = 16.dp)
            )
        }
    }
}

@Preview(heightDp = 100, widthDp = 365)
@Composable
private fun DoctorCardPreview() {
    // Example instances
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
    ).Compose()
}