package com.epilabs.epiguard.components.devices_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
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
import androidx.compose.ui.zIndex
import com.epilabs.epiguard.BottomMenu
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.BottomMenuContent
import com.epilabs.epiguard.ui.theme.TextWhite

@Composable
fun Component2(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 750.dp)
            .background(AppColors.blackbackground)

    ) {
        NewHeader()
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
fun NewHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(AppColors.blackbackground)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .requiredHeight(height = 140.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.blackbackground)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 25.dp)
                    .padding(horizontal = 16.dp)
                    .requiredHeight(height = 35.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 320.dp, y = 0.dp)
                        .requiredSize(size = 35.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mask_group),
                        contentDescription = "pexels-christina-morillo-1181690 1",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = "EpiGuard",
                    color = Color(0xff343c6a),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier
                        .width(350.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.menu1),
                    contentDescription = "Group 692",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 0.dp, y = 10.dp)
                        .requiredWidth(width = 14.dp)
                        .requiredHeight(height = 18.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 80.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .requiredHeight(height = 40.dp)
                    .clip(RoundedCornerShape(8.dp))
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
                        contentDescription = "magnifying-glass 1",
                        colorFilter = ColorFilter.tint(Color(0xff718ebf)),
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = "Search for something",
                        color = Color(0xff8ba3cb),
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .offset(x = 24.dp, y = 0.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DeviceBanner() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.color_pink)
            .fillMaxWidth()
            .requiredHeight(120.dp)
            .clipToBounds()
    ) {
        Image(
            painter = painterResource(id = R.drawable.heroimage),
            contentDescription = "AI Assistant",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(width = 190.dp, height = 190.dp)
                .padding(end = 5.dp)
                .zIndex(1f)
                .clipToBounds()
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(IntrinsicSize.Max)
                .padding(start = 15.dp, end = 8.dp, top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "New AI Test Lab!",
                style = TextStyle(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp
                ),
                color = TextWhite,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Upload seizure videos & \ntest AI models for epilepsy care.",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                ),
                color = TextWhite.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(120.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.color_Gray_White)
                    .padding(horizontal = 8.dp)
                    .zIndex(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Try Now",
                    tint = AppColors.color_violet,
                    modifier = Modifier.size(11.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Try Now",
                    color = AppColors.color_violet,
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
fun HeaderContent1(modifier: Modifier = Modifier) {
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
                    text = "Connect your devices",
                    color = AppColors.color_Gray_600,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.43.em,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.Bottom)
                        .offset(y=12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        DeviceBanner()



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
                                            text = "Smart Phone",
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
                                            text = "Wireless Camera",
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

@Preview(widthDp = 375, heightDp = 750)
@Composable
private fun Component2Preview() {
    Component2(Modifier)
}