package com.epilabs.epiguard.ui

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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R

@Composable
fun MyProfile(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .requiredHeight(height = 844.dp)
            .background(color = Color.White)
    ) {
        // Purple banner with custom shape
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp, y = (-260).dp)
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 500.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 25.dp,
                        bottomEnd = 25.dp
                    )
                )
                .background(color = Color(0xff795ffc))
        ) {
            // Overlay Box to create indented effect
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(y = 10.dp)
                    .requiredWidth(width = 390.dp)
                    .requiredHeight(height = 100.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 25.dp,
                            topEnd = 25.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(color = Color.White)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(164.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 15.dp, y = 58.dp)
                .requiredWidth(width = 360.dp)
        ) {
            Text(
                text = "My Profile",
                color = Color.White,
                style = TextStyle(
                    fontSize = 22.sp,
                    letterSpacing = (-0.5).sp
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Profile image",
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp, y = 116.dp)
                .requiredSize(size = 120.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(12.dp)
                )
        )

        // White content box (adjusted height to avoid navbar overlap)
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 302.dp)
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 467.dp) // 844 - 302 - 75 = 467.dp
                .clip(shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(color = Color.White)
                .border(
                    border = BorderStroke(1.dp, Color(0xffe0e0e0)),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 117.dp, y = 248.dp)
                .requiredWidth(width = 156.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tonald Drump",
                    color = AppColors.color_Primary_500,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 18.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 120.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(id = R.drawable.materialsymbolsverifiedrounded),
                    contentDescription = "Verified badge",
                    tint = Color(0xff675aff),
                    modifier = Modifier
                        .requiredSize(size = 20.dp)
                )
            }
            Text(
                text = "Junior Full Stack Developer",
                color = AppColors.color_Gray_600,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = (-0.5).sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 15.dp, y = 302.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier
                    .requiredWidth(width = 360.dp)
            ) {
                Text(
                    text = "CONTACT",
                    color = AppColors.color_Primary_500,
                    style = TextStyle(
                        fontSize = 12.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 92.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = Color(0xfff4f6f9))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp, y = 20.dp)
                            .requiredWidth(width = 328.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sms),
                            contentDescription = "Email icon",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 18.dp)
                        )
                        Text(
                            text = "Tonald@gmail.com",
                            color = Color(0xff4f5464),
                            lineHeight = 1.82.em,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp, y = 52.dp)
                            .requiredWidth(width = 328.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.locationtick),
                            contentDescription = "Location icon",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 18.dp)
                        )
                        Text(
                            text = "Taman Anggrek",
                            color = Color(0xff4f5464),
                            lineHeight = 1.82.em,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier
                    .requiredWidth(width = 360.dp)
            ) {
                Text(
                    text = "ACCOUNT",
                    color = AppColors.color_Primary_500,
                    style = TextStyle(
                        fontSize = 12.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 127.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = Color(0xfff4f6f9))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp, y = 20.dp)
                            .requiredWidth(width = 328.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.user45),
                            contentDescription = "User icon",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 18.dp)
                        )
                        Text(
                            text = "Personal Data",
                            color = Color(0xff4f5464),
                            lineHeight = 1.82.em,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .weight(weight = 1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrowright),
                            contentDescription = "Arrow right",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp, y = 52.dp)
                            .requiredWidth(width = 328.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.folder),
                            contentDescription = "Folder icon",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 18.dp)
                        )
                        Text(
                            text = "Office Assets",
                            color = Color(0xff4f5464),
                            lineHeight = 1.82.em,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .weight(weight = 1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrowright),
                            contentDescription = "Arrow right",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp, y = 84.dp)
                            .requiredWidth(width = 328.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.money),
                            contentDescription = "Money icon",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 18.dp)
                        )
                        Text(
                            text = "Payroll & Tax",
                            color = Color(0xff4f5464),
                            lineHeight = 1.82.em,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .weight(weight = 1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrowright),
                            contentDescription = "Arrow right",
                            tint = Color(0xff675aff),
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier
                    .requiredWidth(width = 360.dp)
            ) {
                Text(
                    text = "SETTINGS",
                    color = AppColors.color_Primary_500,
                    style = TextStyle(
                        fontSize = 12.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 156.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = Color(0xfff4f6f9))
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp, y = 20.dp)
                            .requiredWidth(width = 328.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_settings),
                                contentDescription = "Settings icon",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 18.dp)
                            )
                            Text(
                                text = "Change Password",
                                color = Color(0xff4f5464),
                                lineHeight = 1.82.em,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .weight(weight = 1f)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.arrowright),
                                contentDescription = "Arrow right",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.scroll),
                                contentDescription = "Scroll icon",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 18.dp)
                            )
                            Text(
                                text = "Versioning",
                                color = Color(0xff4f5464),
                                lineHeight = 1.82.em,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .weight(weight = 1f)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.arrowright),
                                contentDescription = "Arrow right",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.message),
                                contentDescription = "Message icon",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 18.dp)
                            )
                            Text(
                                text = "FAQ and Help",
                                color = Color(0xff4f5464),
                                lineHeight = 1.82.em,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .weight(weight = 1f)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.arrowright),
                                contentDescription = "Arrow right",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = "Logout icon",
                                tint = Color(0xffd32f2f),
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                            Text(
                                text = "Logout",
                                color = Color(0xff4f5464),
                                lineHeight = 1.82.em,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .weight(weight = 1f)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.arrowright),
                                contentDescription = "Arrow right",
                                tint = Color(0xff675aff),
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun MyProfilePreview() {
    MyProfile(Modifier)
}