package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.InputChip
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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.NavBar


@Composable
fun Frame34219(
    modifier: Modifier = Modifier
) {
    // Define selectedItem locally for visual NavBar
    val selectedItem = remember { mutableStateOf("home") } // Placeholder value, adjust as needed

    Column(
        modifier = modifier
            .requiredHeight(height = 812.dp)
            .background(AppColors.color_Gray_50)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White.copy(alpha = 0.1f))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 375.dp)
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "heroicons:arrow-up-20-solid",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .requiredSize(size = 20.dp)
                    )
                    Text(
                        text = "View Contacts",
                        color = Color(0xff122d4d),
                        textAlign = TextAlign.Center,
                        lineHeight = 5.33.em,
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
        ) {
            InputChip(
                label = {
                    Text(
                        text = "All",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = true,
                onClick = { }
            )
            InputChip(
                label = {
                    Text(
                        text = "Primary",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = true,
                onClick = { }
            )
            InputChip(
                label = {
                    Text(
                        text = "Secondary",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = true,
                onClick = { }
            )
            InputChip(
                label = {
                    Text(
                        text = "Status",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = true,
                onClick = { }
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 89.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 89.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.guy_3),
                                contentDescription = "Frame 64",
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .fillMaxWidth()
                                    .requiredSize(size = 89.dp)
                                    .clip(shape = RoundedCornerShape(25.dp))
                                    .padding(all = 10.dp)
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(9.dp, Alignment.Top),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .weight(weight = 1f)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.phone),
                                        contentDescription = "Vector",
                                        modifier = Modifier
                                            .requiredWidth(width = 14.dp)
                                            .requiredHeight(height = 13.dp)
                                    )
                                    Text(
                                        text = "Call",
                                        color = Color(0xff8997a9),
                                        lineHeight = 8.em,
                                        style = TextStyle(fontSize = 12.sp),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .weight(weight = 1f)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.mail),
                                        contentDescription = "Vector",
                                        modifier = Modifier
                                            .requiredWidth(width = 14.dp)
                                            .requiredHeight(height = 13.dp)
                                    )
                                    Text(
                                        text = "Email",
                                        color = Color(0xff8997a9),
                                        lineHeight = 8.em,
                                        style = TextStyle(fontSize = 12.sp),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(5.dp))
                                        .background(color = Color(0xfff4f6f9))
                                        .padding(horizontal = 6.dp, vertical = 5.dp)
                                ) {
                                    Text(
                                        text = "primaryCarer",
                                        color = Color(0xff478ff1),
                                        lineHeight = 9.6.em,
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.Top)
                            ) {
                                Text(
                                    text = "firstname + lastname",
                                    color = Color(0xff122d4d),
                                    lineHeight = 5.65.em,
                                    style = TextStyle(fontSize = 17.sp),
                                    modifier = Modifier
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.bell),
                                        contentDescription = "Vector",
                                        modifier = Modifier
                                            .requiredWidth(width = 8.dp)
                                            .requiredHeight(height = 10.dp)
                                    )
                                    Text(
                                        text = "Contact Status: status",
                                        color = Color(0xff415770),
                                        lineHeight = 9.6.em,
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.users),
                                            contentDescription = "Group 41",
                                            modifier = Modifier
                                                .requiredWidth(width = 13.dp)
                                                .requiredHeight(height = 9.dp)
                                        )
                                        Text(
                                            text = "Relationship:",
                                            color = Color(0xff122d4d),
                                            style = TextStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Medium
                                            ),
                                            modifier = Modifier
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                        )
                                        Text(
                                            text = "Relationship",
                                            color = Color(0xff0066ff),
                                            lineHeight = 8.73.em,
                                            style = TextStyle(fontSize = 11.sp),
                                            modifier = Modifier
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // NavBar
        NavBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(83.dp),
            selectedItem = selectedItem.value
        )
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun Frame34219Preview() {
    Frame34219()
}