package com.epilabs.epiguard.components.devices_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
fun Component22(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
    ) {
        AddNewSpaceSetSpaceName()
        HeaderContent1()
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Detector", R.drawable.ic_videocam),
                BottomMenuContent("Models", R.drawable.codesandbox),
                BottomMenuContent("Contacts", R.drawable.users1),
                BottomMenuContent("Settings", R.drawable.settings)
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Position at bottom of screen
        )
    }
}

@Composable
fun AddNewSpaceSetSpaceName(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(26.dp))
            .background(color = AppColors.color_Gray_White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .requiredWidth(width = 327.dp)
                .requiredHeight(height = 116.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth(width = 303.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Image (CHANGE HERE)",
                    modifier = Modifier
                        .requiredWidth(width = 100.dp)
                        .requiredHeight(height = 100.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
                    ) {
                        Text(
                            text = "My Home",
                            color = AppColors.color_Gray_800,
                            lineHeight = 1.5.em,
                            style = AppTypes.type_Body_Regular_400,
                            modifier = Modifier
                                .requiredWidth(width = 169.dp)
                                .wrapContentHeight(align = Alignment.Bottom)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.requiredWidth(width = 169.dp)
                        ) {
                            Text(
                                text = "11-5 RPddington Rd, London, UK",
                                color = AppColors.color_Gray_600,
                                lineHeight = 1.43.em,
                                style = AppTypes.type_Body_small_400,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(align = Alignment.Bottom)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.Top),
        modifier = modifier
            .fillMaxSize()
            .padding(all = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 41.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Create a new space",
                    color = AppColors.color_Gray_White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.38.em,
                    style = AppTypes.type_Header_Header_2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.Bottom)
                )
                Text(
                    text = "Connect your devices",
                    color = AppColors.color_Gray_100,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.43.em,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.Bottom)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .requiredWidth(width = 327.dp)
        ) {
            // Search Bar
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 54.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(AppColors.color_Gray_White)
                    .border(
                        border = BorderStroke(1.dp, AppColors.color_Gray_100),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_normal), // Replace with actual drawable
                    contentDescription = "Search Icon",
                    tint = AppColors.color_Gray_600,
                    modifier = Modifier.requiredSize(size = 20.dp)
                )
                Text(
                    text = "Search devices",
                    color = AppColors.color_Gray_600,
                    lineHeight = 1.29.em,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier.weight(1f)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
                ) {
                    Text(
                        text = "Current devices nearby",
                        color = AppColors.color_Gray_700,
                        lineHeight = 1.43.em,
                        style = AppTypes.type_Body_small_400,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.Bottom)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                            modifier = Modifier
                                .requiredWidth(width = 327.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .padding(horizontal = 2.dp, vertical = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(weight = 1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredSize(size = 46.dp)
                                            .clip(shape = RoundedCornerShape(54.dp))
                                            .background(color = AppColors.color_Gray_100)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.photo),
                                            contentDescription = "Photo",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp)
                                    ) {
                                        Text(
                                            text = "Smart Lamp",
                                            color = AppColors.color_Gray_800,
                                            lineHeight = 1.43.em,
                                            style = AppTypes.type_Typography_Body_Small,
                                            modifier = Modifier.wrapContentHeight(align = Alignment.Bottom)
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                                                modifier = Modifier
                                                    .clip(shape = RoundedCornerShape(16.dp))
                                                    .background(color = AppColors.color_violet)
                                                    .padding(all = 4.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.plus),
                                                    contentDescription = "Old/Icon/Check",
                                                    colorFilter = ColorFilter.tint(AppColors.darkblue),
                                                    modifier = Modifier.requiredSize(size = 8.dp)
                                                )
                                            }
                                            Text(
                                                text = "connected",
                                                color = AppColors.color_Error_500,
                                                lineHeight = 1.43.em,
                                                style = AppTypes.type_Body_small_400,
                                                modifier = Modifier.wrapContentHeight(align = Alignment.Bottom)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        HorizontalDivider(
                            modifier = Modifier.requiredWidth(width = 327.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                            modifier = Modifier
                                .requiredWidth(width = 327.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .padding(horizontal = 2.dp, vertical = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(weight = 1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredSize(size = 46.dp)
                                            .clip(shape = RoundedCornerShape(54.dp))
                                            .background(color = AppColors.color_Gray_100)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.photo),
                                            contentDescription = "Photo",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp)
                                    ) {
                                        Text(
                                            text = "Speaker",
                                            color = AppColors.color_Gray_800,
                                            lineHeight = 1.43.em,
                                            style = AppTypes.type_Typography_Body_Small,
                                            modifier = Modifier.wrapContentHeight(align = Alignment.Bottom)
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                                                modifier = Modifier
                                                    .clip(shape = RoundedCornerShape(16.dp))
                                                    .background(color = AppColors.color_Gray_100)
                                                    .padding(all = 4.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.plus),
                                                    contentDescription = "Old/Icon/Minus",
                                                    colorFilter = ColorFilter.tint(AppColors.color_violet),
                                                    modifier = Modifier.requiredSize(size = 8.dp)
                                                )
                                            }
                                            Text(
                                                text = "not connected",
                                                color = AppColors.color_Gray_700,
                                                lineHeight = 1.43.em,
                                                style = AppTypes.type_Body_small_400,
                                                modifier = Modifier.wrapContentHeight(align = Alignment.Bottom)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        HorizontalDivider(
                            modifier = Modifier.requiredWidth(width = 327.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                            modifier = Modifier
                                .requiredWidth(width = 327.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .padding(horizontal = 2.dp, vertical = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(weight = 1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredSize(size = 46.dp)
                                            .clip(shape = RoundedCornerShape(54.dp))
                                            .background(color = AppColors.color_Gray_100)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.photo),
                                            contentDescription = "Photo",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp)
                                    ) {
                                        Text(
                                            text = "Humidifier",
                                            color = AppColors.color_Gray_800,
                                            lineHeight = 1.43.em,
                                            style = AppTypes.type_Typography_Body_Small,
                                            modifier = Modifier.wrapContentHeight(align = Alignment.Bottom)
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                                                modifier = Modifier
                                                    .clip(shape = RoundedCornerShape(16.dp))
                                                    .background(color = AppColors.color_Gray_100)
                                                    .padding(all = 4.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.plus),
                                                    contentDescription = "Old/Icon/Minus",
                                                    colorFilter = ColorFilter.tint(AppColors.color_Gray_700),
                                                    modifier = Modifier.requiredSize(size = 8.dp)
                                                )
                                            }
                                            Text(
                                                text = "not connected",
                                                color = AppColors.color_Gray_700,
                                                lineHeight = 1.43.em,
                                                style = AppTypes.type_Body_small_400,
                                                modifier = Modifier.wrapContentHeight(align = Alignment.Bottom)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun Component2Preview() {
    Component2(Modifier)
}