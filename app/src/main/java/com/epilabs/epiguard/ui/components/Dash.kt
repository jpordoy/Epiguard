package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .requiredHeight(height = 844.dp)
            .clip(shape = RoundedCornerShape(54.dp))
            .background(color = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 51.dp)
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 717.dp)
                .background(color = Color.White)
        ) {
            // Call Component1 here
            Component1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Hi, John!, Welcome to EpiGuard",
                        color = AppColors.color_Gray_600,
                        lineHeight = 8.59.em,
                        style = AppTypes.type_Header_Header_2,
                        modifier = Modifier
                            .requiredHeight(height = 30.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 163.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .requiredWidth(width = 342.dp)
                            .requiredHeight(height = 163.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image__1_),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(12.dp))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = (-40).dp, y = (-55).dp)
                            .requiredSize(size = 154.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffd9d9d9).copy(alpha = 0.2f))
                    )
                    Text(
                        text = "Meet Sera \nyour AI assistant",
                        color = AppColors.color_white,
                        style = AppTypes.type_Body_Large_400,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 11.dp, y = 31.dp)
                    )
                    Text(
                        text = "Ask me anything about EpiGuard.\nI’m here to help.",
                        color = AppColors.color_white,
                        lineHeight = 12.5.em,
                        style = AppTypes.type_Body_Regular_400,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 11.dp, y = 93.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 68.dp, y = 151.dp)
                            .requiredSize(size = 83.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffd9d9d9).copy(alpha = 0.2f))
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        modifier = Modifier
                            .align(alignment = Alignment.BottomStart)
                            .offset(x = 141.dp, y = (-6).dp)
                    ) {
                        ActiveYes()
                        Badge()
                        Badge()
                        Badge()
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Categories",
                        color = AppColors.color_violet,
                        lineHeight = 9.38.em,
                        style = AppTypes.type_Body_Regular_400
                    )
                    Text(
                        text = "See All",
                        color = AppColors.color_Gray_600,
                        textAlign = TextAlign.Center,
                        lineHeight = 10.71.em,
                        style = AppTypes.type_Typography_Body_Small
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xffdc9497))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.plus),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-33.67).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Add Device",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_Regular_400
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xff93c19e))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.face_detection),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34.33).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Monitor",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xfff5ad7e))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.diary),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Tracker",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xffaca1cd))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.evaluation),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Analytics",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xffdc9497))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.plus),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-33.67).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Add Device",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_Regular_400
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xff93c19e))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.face_detection),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34.33).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Monitor",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xfff5ad7e))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.diary),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Tracker",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0xffaca1cd))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.evaluation),
                                            contentDescription = "sms",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Analytics",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .requiredWidth(width = 390.dp)
                    .padding(start = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 342.dp)
                ) {
                    Text(
                        text = "Nearby Medical Centers",
                        color = AppColors.color_violet,
                        lineHeight = 9.38.em,
                        style = AppTypes.type_Body_Regular_400
                    )
                    Text(
                        text = "See All",
                        color = AppColors.color_Gray_600,
                        textAlign = TextAlign.Center,
                        lineHeight = 10.71.em,
                        style = AppTypes.type_Body_Regular_400
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    modifier = Modifier
                        .requiredWidth(width = 366.dp)
                        .padding(end = 4.dp, bottom = 4.dp)
                ) {
                    // Card 1 Bottom
                    Card(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = AppColors.color_Gray_600
                        ),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(1.dp, AppColors.color_Gray_50, RoundedCornerShape(8.dp))
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .requiredWidth(width = 232.dp)
                                .requiredHeight(height = 252.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mayo),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 121.dp)
                                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Sunrise Health Clinic",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 10.71.em,
                                            style = AppTypes.type_Body_small_400,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.sms),
                                                contentDescription = "vuesax/linear/location",
                                                modifier = Modifier.requiredSize(size = 14.dp)
                                            )
                                            Text(
                                                text = "123 Oak Street, CA 98765",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_Regular_400
                                            )
                                        }
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "5.0",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_small_400
                                            )
                                            Image(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "Frame 1000000929"
                                            )
                                        }
                                        Text(
                                            text = "(58 Reviews)",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = AppColors.color_Gray_600
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.Start),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.camera),
                                            contentDescription = "vuesax/bold/routing",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "2.5 km/40min",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mail),
                                            contentDescription = "vuesax/bold/hospital",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "Hospital",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.sms),
                                contentDescription = "Frame 1000001036",
                                modifier = Modifier
                                    .offset(x = 197.dp, y = 8.dp)
                                    .clip(shape = RoundedCornerShape(52.dp))
                                    .padding(all = 6.dp)
                                    .background(color = Color.White)
                            )
                        }
                    }
                    // Card 2 Bottom
                    Card(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = AppColors.color_Gray_600
                        ),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(1.dp, AppColors.color_Gray_50, RoundedCornerShape(8.dp))
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .requiredWidth(width = 232.dp)
                                .requiredHeight(height = 252.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.og),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 121.dp)
                                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Sunrise Health Clinic",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 10.71.em,
                                            style = AppTypes.type_Body_small_400,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.sms),
                                                contentDescription = "vuesax/linear/location",
                                                modifier = Modifier.requiredSize(size = 14.dp)
                                            )
                                            Text(
                                                text = "123 Oak Street, CA 98765",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_Regular_400
                                            )
                                        }
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "5.0",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_small_400
                                            )
                                            Image(
                                                painter = painterResource(id = R.drawable.users),
                                                contentDescription = "Frame 1000000929"
                                            )
                                        }
                                        Text(
                                            text = "(58 Reviews)",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = AppColors.color_Gray_600
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.Start),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.camera),
                                            contentDescription = "vuesax/bold/routing",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "2.5 km/40min",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mail),
                                            contentDescription = "vuesax/bold/hospital",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "Hospital",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.sms),
                                contentDescription = "Frame 1000001036",
                                modifier = Modifier
                                    .offset(x = 197.dp, y = 8.dp)
                                    .clip(shape = RoundedCornerShape(52.dp))
                                    .padding(all = 6.dp)
                                    .background(color = Color.White)
                            )
                        }
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 768.dp)
                .requiredWidth(width = 390.dp)
                .background(color = Color.White)
                .padding(horizontal = 24.dp, vertical = 14.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .requiredHeight(height = 48.dp)
                    .clip(shape = RoundedCornerShape(38.dp))
                    .background(color = AppColors.color_Gray_100)
                    .padding(all = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "vuesax/bold/home"
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.user45),
                contentDescription = "Search by Location"
            )
            Icon(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "Appointment"
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile"
            )
        }
    }
}

@Composable
fun ActiveYes(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 30.dp)
            .requiredHeight(height = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(40.dp))
                .background(color = AppColors.color_white)
        )
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(Modifier)
}