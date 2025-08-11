package com.epilabs.epiguard.components.devices_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.epilabs.epiguard.BottomMenu
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.BottomMenuContent
import com.epilabs.epiguard.ui.theme.AquaBlue
import com.epilabs.epiguard.ui.theme.ButtonBlue
import com.epilabs.epiguard.ui.theme.DeepBlue

// BottomMenu composable
@Composable
fun BottomMenu1(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember { mutableIntStateOf(initialSelectedItemIndex) }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem1(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) { selectedItemIndex = index }
        }
    }
}

// BottomMenuItem composable
@Composable
fun BottomMenuItem1(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onItemClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(if (isSelected) activeHighlightColor else Color.Transparent).padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(text = item.title, color = if (isSelected) activeTextColor else inactiveTextColor)
    }
}

@Composable
fun HeaderImagesWithDevices(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredSize(281.dp) // Parent container is a square to hold circular pattern
    ) {
        // Largest circle (white, full opacity)
        Box(
            modifier = Modifier
                .requiredSize(281.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Color(0xFFFFFFFF)) // White
                .alpha(0.7f)
        )
        // Second largest circle (light grayish-blue, 7% opacity)
        Box(
            modifier = Modifier
                .requiredSize(241.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Color(0xFFF2F4F8)) // Light grayish-blue, close to #EAEFF5
                .alpha(0.7f)
        )
        // Third circle (darker grayish-blue, 40% opacity)
        Box(
            modifier = Modifier
                .requiredSize(186.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Color(0xFFECEFF4)) // Slightly darker, close to #EAEFF5
                .alpha(0.4f) // Increased from 0.04f for visibility
        )
        // Smallest circle (#EAEFF5, full opacity)
        Box(
            modifier = Modifier
                .requiredSize(168.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Color(0xFFEAEFF5)) // #EAEFF5
                .alpha(1.0f) // Corrected from 9f
        )
        // Centered image
        Image(
            painter = painterResource(id = R.drawable.cameradevice),
            contentDescription = "Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .requiredSize(167.dp) // Slightly smaller than the smallest circle for padding
                .align(Alignment.Center)
        )
    }
}

@Composable
fun AddNewSpaceLinkDevice(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp),
        color = AppColors.color_Gray_50,
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.header1),
                contentDescription = "Header Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(277.dp)
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(908.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredWidth(327.dp)
                            .requiredHeight(44.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu1),
                            contentDescription = "Back",
                            tint = AppColors.color_Gray_White,
                            modifier = Modifier
                                .requiredSize(44.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .padding(vertical = 9.dp)
                                .zIndex(1f)
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Link New device",
                                color = AppColors.color_Gray_White,
                                textAlign = TextAlign.Center,
                                style = AppTypes.type_Header_Header_2,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "Connect with your space",
                                color = AppColors.color_Gray_100,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 14.sp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.mask_group),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .requiredSize(size = 35.dp)
                                .clip(CircleShape)
                        )
                    }
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .requiredWidth(327.dp)
                    ) {
                        item {
                            HeaderImagesWithDevices()
                        }
                        item {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Seizure Detector Name",
                                    color = AppColors.color_Gray_600,
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredWidth(width = 347.dp)
                                        .requiredHeight(height = 54.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(AppColors.color_Gray_White)
                                        .border(
                                            border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                            shape = RoundedCornerShape(14.dp)
                                        )
                                        .padding(horizontal = 16.dp, vertical = 12.dp)
                                ) {
                                    Text(
                                        text = "Enter detector name",
                                        color = AppColors.color_Gray_600,
                                        lineHeight = 1.29.em,
                                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                        item {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Location Name",
                                    color = AppColors.color_Gray_600,
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredWidth(width = 347.dp)
                                        .requiredHeight(height = 54.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(AppColors.color_Gray_White)
                                        .border(
                                            border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                            shape = RoundedCornerShape(14.dp)
                                        )
                                        .padding(horizontal = 16.dp, vertical = 12.dp)
                                ) {
                                    Text(
                                        text = "Enter location name",
                                        color = AppColors.color_Gray_600,
                                        lineHeight = 1.29.em,
                                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                        item {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Device ID",
                                    color = AppColors.color_Gray_600,
                                    lineHeight = 1.43.em,
                                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredWidth(width = 347.dp)
                                        .requiredHeight(height = 54.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(AppColors.color_Gray_White)
                                        .border(
                                            border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                            shape = RoundedCornerShape(14.dp)
                                        )
                                        .padding(horizontal = 16.dp, vertical = 12.dp)
                                ) {
                                    Text(
                                        text = "Enter device ID",
                                        color = AppColors.color_Gray_600,
                                        lineHeight = 1.29.em,
                                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                        item {
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
                                    painter = painterResource(id = R.drawable.loading),
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
                    }
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
}

@Preview(widthDp = 375, heightDp = 975)
@Composable
private fun AddNewSpaceLinkDevicePreview() {
    AddNewSpaceLinkDevice()
}