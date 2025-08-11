package com.epilabs.epiguard.components.devices_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.BottomMenu
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.BottomMenuContent


@Composable
fun NewDevice(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 381.dp)
            .requiredHeight(height = 794.dp)
            .background(Color.White)
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 140.dp)
                .background(Color.White)
        ) {
            // Title and Icons
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 23.69.dp, y = 25.dp)
                    .requiredWidth(width = 325.dp)
                    .requiredHeight(height = 35.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mask_group),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 290.31.dp, y = 0.dp)
                        .requiredSize(size = 35.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Setup New Device",
                    color = Color(0xff343c6a),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 70.dp, y = 6.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.menu1),
                    contentDescription = "Menu Icon",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 0.dp, y = 12.35.dp)
                        .requiredWidth(width = 15.dp)
                        .requiredHeight(height = 17.dp)
                )
            }
            // Search Bar
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 29.dp, y = 70.dp)
                    .requiredWidth(width = 325.dp)
                    .requiredHeight(height = 40.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(AppColors.color_Gray_50)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 19.dp, y = 12.dp)
                        .requiredWidth(width = 158.dp)
                        .requiredHeight(height = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_normal),
                        contentDescription = "Search Icon",
                        colorFilter = ColorFilter.tint(Color(0xff718ebf)),
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )
                    Text(
                        text = "Search for something",
                        color = Color(0xff8ba3cb),
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .offset(x = 26.dp, y = 0.dp)
                    )
                }
            }
        }
        // Content Section
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 3.dp, y = 161.dp)
                .requiredWidth(width = 378.dp)
                .requiredHeight(height = 455.dp)
                .padding(all = 24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .requiredWidth(width = 327.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_758),
                    contentDescription = "No Devices Image",
                    modifier = Modifier
                        .requiredWidth(width = 327.dp)
                        .requiredHeight(height = 220.dp)
                )
                Text(
                    text = "Looks like you have no devices set up.",
                    color = AppColors.color_Gray_700,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.31.em,
                    style = AppTypes.type_Header_Header_2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.Bottom)
                )
                Text(
                    text = "Add a new device to start your seizure detector",
                    color = AppColors.color_Gray_600,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Typography_Body_Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.Bottom)
                )
            }
        }
        // Bottom Section
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 0.dp, y = 550.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 110.dp)
                .clip(RoundedCornerShape(26.dp))
                .padding(all = 16.dp)
        ) {
            // Primary Button
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(AppColors.color_violet)
                    .padding(all = 17.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "Umbrella Icon",
                    tint = AppColors.color_Gray_White,
                    modifier = Modifier.requiredSize(size = 20.dp)
                )
                Text(
                    text = "Add Device",
                    color = AppColors.color_Gray_White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.43.em,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Detector", R.drawable.ic_videocam),
                BottomMenuContent("Models", R.drawable.codesandbox),
                BottomMenuContent("Contacts", R.drawable.users1),
                BottomMenuContent("Settings", R.drawable.settings)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(widthDp = 381, heightDp = 794)
@Composable
private fun NewDevicePreview() {
    NewDevice(Modifier)
}