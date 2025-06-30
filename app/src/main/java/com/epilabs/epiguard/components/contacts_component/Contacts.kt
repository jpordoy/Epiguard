package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.NavBar

@Composable
fun ViewContactScreen(modifier: Modifier = Modifier) {
    // State for NavBar selection (optional, for preview purposes)
    val selectedItem = remember { mutableStateOf("Home") }

    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = AppColors.color_Gray_White)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 56.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 550.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 20.dp, y = 0.dp)
                    .requiredWidth(width = 335.dp)
                    .requiredHeight(height = 44.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 44.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 44.dp)
                            .clip(shape = CircleShape)
                            .background(color = AppColors.color_Gray_100)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.arrow_left), // Replace with R.drawable.arrow
                        contentDescription = "Back/Arrow",
                        colorFilter = ColorFilter.tint(AppColors.color_violet),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 10.dp, y = 10.dp)
                            .requiredWidth(width = 24.dp)
                            .requiredHeight(height = 24.dp)
                    )
                }
                Text(
                    text = "Contacts",
                    color = AppColors.color_Gray_900,
                    textAlign = TextAlign.Center,
                    style = AppTypes.type_Typography_Navbar_Title,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 120.dp, y = 11.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.plus), // Replace with R.drawable.arrow
                    contentDescription = "Back/Arrow",
                    colorFilter = ColorFilter.tint(AppColors.color_violet),
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .offset(x = 10.dp, y = 10.dp)
                        .requiredWidth(width = 24.dp)
                        .requiredHeight(height = 24.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 74.dp)
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 476.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 375.dp)
                        .requiredHeight(height = 32.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 20.dp, y = 0.dp)
                            .requiredWidth(width = 335.dp)
                            .requiredHeight(height = 20.dp)
                    ) {
                        Text(
                            text = "All",
                            color = AppColors.color_Primary_500,
                            style = AppTypes.type_Body_Large_500
                        )
                        Text(
                            text = "Primary",
                            color = AppColors.color_Gray_900,
                            textAlign = TextAlign.Center,
                            style = AppTypes.type_Body_Large_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 143.dp, y = 0.dp)
                        )
                        Text(
                            text = "Secondary",
                            color = AppColors.color_Gray_900,
                            textAlign = TextAlign.End,
                            style = AppTypes.type_Body_Large_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 259.dp, y = 0.dp)
                        )
                    }

                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 48.dp)
                        .requiredWidth(width = 375.dp)
                        .requiredHeight(height = 428.dp)
                ) {
                    // Notification Item 1
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 375.dp)
                            .requiredHeight(height = 76.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 375.dp)
                                .requiredHeight(height = 76.dp)
                                .background(color = AppColors.color_Gray_50)
                        )
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 20.dp, y = 14.dp)
                                .requiredWidth(width = 335.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Text(
                                text = "Sun,12:40pm",
                                color = AppColors.color_Gray_700,
                                textAlign = TextAlign.End,
                                style = AppTypes.type_Body_small_400,
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 268.dp, y = 0.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 259.dp)
                                    .requiredHeight(height = 48.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 62.dp, y = 0.dp)
                                        .requiredWidth(width = 197.dp)
                                        .requiredHeight(height = 48.dp)
                                ) {
                                    Text(
                                        text = "Super Offer",
                                        color = AppColors.color_Gray_900,
                                        style = AppTypes.type_Body_Large_500,
                                        modifier = Modifier.offset(y = (-4).dp)
                                    )
                                    Text(
                                        text = "Get 60% off in our first booking",
                                        color = AppColors.color_Gray_700,
                                        style = AppTypes.type_Body_Regular_400,
                                        modifier = Modifier
                                            .align(alignment = Alignment.TopStart)
                                            .offset(x = 0.dp, y = 24.dp)
                                    )
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.guy_3),
                                    contentDescription = "Profile",
                                    modifier = Modifier
                                        .requiredSize(size = 48.dp)
                                        .clip(shape = CircleShape)
                                )
                            }
                        }
                    }
                    // Notification Item 2
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 20.dp, y = 92.dp)
                            .requiredWidth(width = 335.dp)
                            .requiredHeight(height = 48.dp)
                    ) {
                        Text(
                            text = "Mon,11:50pm",
                            color = AppColors.color_Gray_700,
                            textAlign = TextAlign.End,
                            style = AppTypes.type_Body_small_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 267.dp, y = 0.dp)
                        )
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 259.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 62.dp, y = 0.dp)
                                    .requiredWidth(width = 197.dp)
                                    .requiredHeight(height = 48.dp)
                            ) {
                                Text(
                                    text = "Super Offer",
                                    color = AppColors.color_Gray_900,
                                    style = AppTypes.type_Body_Large_500,
                                    modifier = Modifier.offset(y = (-4).dp)
                                )
                                Text(
                                    text = "Get 60% off in our first booking",
                                    color = AppColors.color_Gray_700,
                                    style = AppTypes.type_Body_Regular_400,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp, y = 24.dp)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.woman_5),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .requiredSize(size = 48.dp)
                                    .clip(shape = CircleShape)
                            )
                        }
                    }
                    // Notification Item 3
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 20.dp, y = 164.dp)
                            .requiredWidth(width = 335.dp)
                            .requiredHeight(height = 48.dp)
                    ) {
                        Text(
                            text = "Tue,10:56pm",
                            color = AppColors.color_Gray_700,
                            textAlign = TextAlign.End,
                            style = AppTypes.type_Body_small_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 269.dp, y = 0.dp)
                        )
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 259.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 62.dp, y = 0.dp)
                                    .requiredWidth(width = 197.dp)
                                    .requiredHeight(height = 48.dp)
                            ) {
                                Text(
                                    text = "Super Offer",
                                    color = AppColors.color_Gray_900,
                                    style = AppTypes.type_Body_Large_500,
                                    modifier = Modifier.offset(y = (-4).dp)
                                )
                                Text(
                                    text = "Get 60% off in our first booking",
                                    color = AppColors.color_Gray_700,
                                    style = AppTypes.type_Body_Regular_400,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp, y = 24.dp)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.woman_1),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .requiredSize(size = 48.dp)
                                    .clip(shape = CircleShape)
                            )
                        }
                    }
                    // Notification Item 4
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 20.dp, y = 236.dp)
                            .requiredWidth(width = 335.dp)
                            .requiredHeight(height = 48.dp)
                    ) {
                        Text(
                            text = "Wed,12:40pm",
                            color = AppColors.color_Gray_700,
                            textAlign = TextAlign.End,
                            style = AppTypes.type_Body_small_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 265.dp, y = 0.dp)
                        )
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 259.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 62.dp, y = 0.dp)
                                    .requiredWidth(width = 197.dp)
                                    .requiredHeight(height = 48.dp)
                            ) {
                                Text(
                                    text = "Super Offer",
                                    color = AppColors.color_Gray_900,
                                    style = AppTypes.type_Body_Large_500,
                                    modifier = Modifier.offset(y = (-4).dp)
                                )
                                Text(
                                    text = "Get 60% off in our first booking",
                                    color = AppColors.color_Gray_700,
                                    style = AppTypes.type_Body_Regular_400,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp, y = 24.dp)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.guy2),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .requiredSize(size = 48.dp)
                                    .clip(shape = CircleShape)
                            )
                        }
                    }
                    // Notification Item 5
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 20.dp, y = 308.dp)
                            .requiredWidth(width = 335.dp)
                            .requiredHeight(height = 48.dp)
                    ) {
                        Text(
                            text = "Fri,11:50pm",
                            color = AppColors.color_Gray_700,
                            textAlign = TextAlign.End,
                            style = AppTypes.type_Body_small_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 277.dp, y = 0.dp)
                        )
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 259.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 62.dp, y = 0.dp)
                                    .requiredWidth(width = 197.dp)
                                    .requiredHeight(height = 48.dp)
                            ) {
                                Text(
                                    text = "Super Offer",
                                    color = AppColors.color_Gray_900,
                                    style = AppTypes.type_Body_Large_500,
                                    modifier = Modifier.offset(y = (-4).dp)
                                )
                                Text(
                                    text = "Get 60% off in our first booking",
                                    color = AppColors.color_Gray_700,
                                    style = AppTypes.type_Body_Regular_400,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp, y = 24.dp)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.woman_1),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .requiredSize(size = 48.dp)
                                    .clip(shape = CircleShape)
                            )
                        }
                    }
                    // Notification Item 6
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 20.dp, y = 380.dp)
                            .requiredWidth(width = 335.dp)
                            .requiredHeight(height = 48.dp)
                    ) {
                        Text(
                            text = "Sat,10:56pm",
                            color = AppColors.color_Gray_700,
                            textAlign = TextAlign.End,
                            style = AppTypes.type_Body_small_400,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 271.dp, y = 0.dp)
                        )
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 259.dp)
                                .requiredHeight(height = 48.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 62.dp, y = 0.dp)
                                    .requiredWidth(width = 197.dp)
                                    .requiredHeight(height = 48.dp)
                            ) {
                                Text(
                                    text = "Super Offer",
                                    color = AppColors.color_Gray_900,
                                    style = AppTypes.type_Body_Large_500,
                                    modifier = Modifier.offset(y = (-4).dp)
                                )
                                Text(
                                    text = "Get 60% off in our first booking",
                                    color = AppColors.color_Gray_700,
                                    style = AppTypes.type_Body_Regular_400,
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp, y = 24.dp)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.guy_4),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .requiredSize(size = 48.dp)
                                    .clip(shape = CircleShape)
                            )
                        }
                    }
                }
            }
        }
        // NavBar at the bottom
        NavBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .requiredWidth(375.dp)
                .requiredHeight(83.dp),
            selectedItem = selectedItem.value,
            onItemSelected = { item -> selectedItem.value = item }
        )
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun ViewContactScreenPreview() {
    ViewContactScreen(Modifier)
}