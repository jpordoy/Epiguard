package com.epilabs.epiguard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun Home(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 812.dp)
            .background(color = AppColors.color_Gray_100) // Replaced Color(0xfff8faff)
    ) {
        // Status Bar (Top)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 44.dp)
        ) {
            Text(
                text = "9:41",
                color = AppColors.color_black,
                textAlign = TextAlign.Center,
                style = AppTypes.type_Typography_Body_Small, // Closest to 15sp
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 21.dp, y = 2.5.dp)
                    .requiredWidth(width = 54.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Cellular Connection",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 294.dp, y = 17.67.dp)
                    .requiredWidth(width = 17.dp)
                    .requiredHeight(height = 11.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.fast_rewind),
                contentDescription = "Wifi",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 316.dp, y = 17.33.dp)
                    .requiredWidth(width = 15.dp)
                    .requiredHeight(height = 11.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 336.33.dp, y = 17.33.dp)
                    .requiredWidth(width = 24.dp)
                    .requiredHeight(height = 11.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-2.33).dp, y = 0.dp)
                        .requiredWidth(width = 22.dp)
                        .requiredHeight(height = 11.dp)
                        .clip(shape = RoundedCornerShape(2.67.dp))
                        .border(
                            border = BorderStroke(1.dp, AppColors.color_black),
                            shape = RoundedCornerShape(2.67.dp)
                        )
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-4.33).dp, y = 0.dp)
                        .requiredWidth(width = 18.dp)
                        .requiredHeight(height = 7.dp)
                        .clip(shape = RoundedCornerShape(1.33.dp))
                        .background(color = AppColors.color_black)
                )
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Cap",
                    alpha = 0.4f,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = 0.dp, y = 0.dp)
                        .requiredWidth(width = 1.dp)
                        .requiredHeight(height = 4.dp)
                )
            }
        }

        // Greeting Section
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 30.dp, y = 51.dp)
                .requiredWidth(width = 135.dp)
                .requiredHeight(height = 55.dp)
        ) {
            Text(
                text = "👋  Good Morning!",
                color = AppColors.color_Gray_700, // Replaced Color(0xff001133).copy(alpha = 0.6f)
                style = AppTypes.type_Typography_Body_Small, // Closest to 12.84sp, Medium
                modifier = Modifier
            )
            Text(
                text = "Alexandar",
                color = AppColors.color_black, // Replaced Color(0xff001133)
                style = AppTypes.type_Header_Header_2, // Closest to 28.84sp, Medium
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 0.dp, y = 19.dp)
            )
        }

        // Profile Image Section
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 296.dp, y = 53.dp)
                .requiredWidth(width = 50.dp)
                .requiredHeight(height = 54.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 50.dp)
                    .requiredHeight(height = 54.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 50.dp)
                        .clip(shape = RoundedCornerShape(11.dp))
                        .background(color = AppColors.color_Gray_50.copy(alpha = 0.1f)) // Replaced Color(0xffac886f).copy(alpha = 0.1f)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 1.dp, y = 9.dp)
                        .requiredSize(size = 40.dp)
                        .background(Color(0xff00a911))

                ) {
                    Image(
                        painter = painterResource(id = R.drawable._1),
                        contentDescription = "21",
                        modifier = Modifier.fillMaxSize()
                    )

                }
            }
        }

        // Specialist Finder Section
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 40.dp, y = 72.dp)
                .requiredWidth(width = 346.dp)
                .requiredHeight(height = 237.dp)
        ) {
            // Background with shadow
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 0.dp, y = 64.dp)
                    .requiredWidth(width = 315.dp)
                    .requiredHeight(height = 173.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(color = AppColors.color_Gray_50) // Replaced Color.White
            )
            // Main Image
            Image(
                painter = painterResource(id = R.drawable.image_46),
                contentDescription = "image 46",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 130.dp, y = 0.dp)
                    .requiredSize(size = 216.dp)
            )
            // Title Text
            Text(
                text = "Lets Find your\nSpecialist",
                color = AppColors.color_black, // Replaced Color(0xff001133)
                style = AppTypes.type_Header_Header_2, // Closest to 20.84sp, Medium
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 20.dp, y = 89.dp)
                    .requiredWidth(width = 225.dp)
            )
            Box(
                modifier = Modifier
                    .requiredWidth(width = 275.dp)
                    .requiredHeight(height = 46.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = AppColors.color_black)
                    .offset(x = 40.dp, y = 72.dp)
                    .border(
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(15.dp)
                    )
            )
            Text(
                text = "Search",
                color = Color(0xff001133).copy(alpha = 0.4f),
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 45.dp,
                        y = 15.dp
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.search_normal),
                contentDescription = "Group 6",
                alpha = 0.5f,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 20.dp,
                        y = 18.dp
                    )
                    .requiredSize(size = 11.dp)
            )
        }





        // Quick Help Section
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 30.dp, y = 339.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 99.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 99.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 99.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = AppColors.color_Primary_500) // Replaced Color(0xff54c1fb)
                )
                Image(
                    painter = painterResource(id = R.drawable.image_47),
                    contentDescription = "image 47",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 17.dp, y = 14.dp)
                        .requiredWidth(width = 65.dp)
                        .requiredHeight(height = 48.dp)
                        .clip(shape = RoundedCornerShape(31.dp))
                        .border(border = BorderStroke(1.dp, AppColors.color_Gray_50), shape = RoundedCornerShape(31.dp)) // Replaced Color(0xffc8eafd)
                )
                Text(
                    text = "Quick Help",
                    color = AppColors.color_Gray_50, // Replaced Color.White
                    style = AppTypes.type_Typography_Body_Small, // Closest to 12sp
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 18.dp, y = 69.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 114.dp, y = 0.dp)
                    .requiredWidth(width = 77.dp)
                    .requiredHeight(height = 99.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 77.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = AppColors.color_Gray_50) // Replaced Color.White
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x =  10.dp, y = 19.dp)
                        .requiredSize(size = 40.dp)
                        .clip(shape = CircleShape)
                        .background(color = AppColors.color_Primary_500.copy(alpha = 0.1f)) // Replaced Color(0xff518cff).copy(alpha = 0.1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "Vector",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 21.dp, y = 30.dp)
                        .requiredWidth(width = 17.dp)
                        .requiredHeight(height = 17.dp)                )
                Text(
                    text = "Hospital",
                    color = AppColors.color_black,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 10sp
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 86.dp)

                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 206.dp, y = 0.dp)
                    .requiredWidth(width = 77.dp)
                    .requiredHeight(height = 99.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 77.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 77.dp)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(color = AppColors.color_Gray_50) // Replaced Color.White
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 18.dp, y = 19.dp)
                            .requiredSize(size = 40.dp)
                            .clip(shape = CircleShape)
                            .background(color = AppColors.color_Primary_500.copy(alpha = 0.1f)) // Replaced Color(0xff00a911).copy(alpha = 0.1f)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "Group 774",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 30.dp, y = 33.dp)
                        .requiredWidth(width = 17.dp)
                        .requiredHeight(height = 13.dp)
                )
                Text(
                    text = "Ambulance",
                    color = AppColors.color_black,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 10sp
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 14.dp, y = 86.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 298.dp, y = 0.dp)
                    .requiredWidth(width = 77.dp)
                    .requiredHeight(height = 99.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 77.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 77.dp)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(color = AppColors.color_Gray_50) // Replaced Color.White
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 18.dp, y = 19.dp)
                            .requiredSize(size = 40.dp)
                            .clip(shape = CircleShape)
                            .background(color = AppColors.color_Primary_500.copy(alpha = 0.1f)) // Replaced Color(0xff518cff).copy(alpha = 0.1f)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "Group",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 30.dp, y = 30.dp)
                        .requiredWidth(width = 16.dp)
                        .requiredHeight(height = 17.dp)
                )
                Text(
                    text = "Doctors",
                    color = AppColors.color_black,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 10sp
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 21.dp, y = 86.dp)
                )
            }
        }

        // Top Doctors Section
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 30.dp, y = 468.dp)
                .requiredWidth(width = 325.dp) // Adjusted to fit within 375dp
                .requiredHeight(height = 224.dp)
        ) {
            Text(
                text = "Top Doctors",
                color = AppColors.color_black, // Replaced Color(0xff001133)
                lineHeight = 7.43.em,
                style = AppTypes.type_Header_Header_2, // Closest to 18.84sp, Medium
                modifier = Modifier
            )
            Text(
                text = "See All",
                color = AppColors.color_Primary_500, // Replaced Color(0xff54c1fb)
                textAlign = TextAlign.End,
                lineHeight = 10.9.em,
                style = AppTypes.type_Typography_Body_Small, // Closest to 12.84sp
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 274.dp, y = 6.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 0.dp, y = 51.dp)
                    .requiredWidth(width = 325.dp)
                    .requiredHeight(height = 173.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 325.dp)
                        .requiredHeight(height = 173.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = AppColors.color_Gray_50) // Replaced Color.White
                )
                Image(
                    painter = painterResource(id = R.drawable.ttzbqv0gpo_removebg_preview_1),
                    contentDescription = "TtzbQv0GpO-removebg-preview 1",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = (-49).dp, y = 6.dp)
                        .requiredWidth(width = 326.dp)
                        .requiredHeight(height = 217.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                )
                Text(
                    text = "CARDIOLOGIST",
                    color = AppColors.color_Primary_500, // Replaced Color(0xfffb9a54)
                    lineHeight = 14.em,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 10sp, Medium
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 130.dp, y = 25.dp)
                        .requiredWidth(width = 74.dp)
                        .requiredHeight(height = 14.dp)
                )
                Text(
                    text = "Dr. Maria Watson",
                    color = AppColors.color_black, // Replaced Color(0xff001133)
                    lineHeight = 8.31.em,
                    style = AppTypes.type_Body_Large_400, // Closest to 16.84sp, Medium
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 130.dp, y = 47.dp)
                )
                Text(
                    text = "10.00 AM - 12.00 PM",
                    color = AppColors.color_Gray_700, // Replaced Color(0xff001133).copy(alpha = 0.5f)
                    lineHeight = 10.9.em,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 12.84sp
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 130.dp, y = 73.dp)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 169.dp, y = 114.dp)
                        .requiredWidth(width = 136.dp)
                        .requiredHeight(height = 39.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 136.dp)
                            .requiredHeight(height = 39.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = AppColors.color_Primary_500) // Replaced Color(0xff518cff)
                    )
                    Text(
                        text = "Get Appointment",
                        color = AppColors.color_black, // Replaced Color.White
                        lineHeight = 12.92.em,
                        style = AppTypes.type_Typography_Body_Small, // Closest to 10.84sp, Medium
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 24.dp, y = 12.dp)
                    )
                }
            }
            // Second Doctor Card (adjusted to fit within 325dp)
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 0.dp, y = 51.dp)
                    .requiredWidth(width = 325.dp)
                    .requiredHeight(height = 173.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 325.dp)
                        .requiredHeight(height = 173.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = AppColors.color_Gray_50) // Replaced Color.White
                )
                Image(
                    painter = painterResource(id = R.drawable.image_30_removebg_preview_1),
                    contentDescription = "image_30-removebg-preview 1",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = (-52).dp, y = 12.dp)
                        .requiredWidth(width = 274.dp)
                        .requiredHeight(height = 182.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                )
                Text(
                    text = "CARDIOLOGIST",
                    color = AppColors.color_black, // Replaced Color(0xff001133)
                    lineHeight = 14.em,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 10sp, Medium
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 130.dp, y = 25.dp)
                        .requiredWidth(width = 74.dp)
                        .requiredHeight(height = 14.dp)
                )
                Text(
                    text = "Dr. Maria Watson",
                    color = AppColors.color_black, // Replaced Color(0xff001133)
                    lineHeight = 8.31.em,
                    style = AppTypes.type_Body_Large_400, // Closest to 16.84sp, Medium
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 130.dp, y = 45.dp)
                )
                Text(
                    text = "10.00 AM - 12.00 PM",
                    color = AppColors.color_Gray_700, // Replaced Color(0xff001133).copy(alpha = 0.5f)
                    lineHeight = 10.9.em,
                    style = AppTypes.type_Typography_Body_Small, // Closest to 12.84sp
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 130.dp, y = 69.dp)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 169.dp, y = 114.dp)
                        .requiredWidth(width = 136.dp)
                        .requiredHeight(height = 39.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 136.dp)
                            .requiredHeight(height = 39.dp)
                            .clip(shape = RoundedCornerShape(9.dp))
                            .background(color = AppColors.color_gradient_start) // Replaced Color(0xffc4c4c4)
                    )
                    Text(
                        text = "Get Appointment",
                        color = AppColors.color_white, // Replaced Color(0xff001133)
                        lineHeight = 12.92.em,
                        style = AppTypes.type_Typography_Body_Small, // Closest to 10.84sp, Medium
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 24.dp, y = 12.dp)
                    )
                }
            }
        }

        // Bottom Navigation
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 25.dp, y = 720.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 67.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 325.dp)
                    .requiredHeight(height = 67.dp)
                    .clip(shape = RoundedCornerShape(29.dp))
                    .background(color = AppColors.color_Gray_50) // Replaced Color.White
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 21.dp, y = 15.dp)
                    .requiredSize(size = 39.dp)
                    .clip(shape = CircleShape)
                    .background(color = AppColors.color_Primary_500) // Replaced Color(0xff518cff)
                    .shadow(elevation = 4.dp, shape = CircleShape)
            )
            Image(
                painter = painterResource(id = R.drawable.frame_2),
                contentDescription = "Frame 2",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 32.dp, y = 25.dp)
            )
        }
    }
}

@Preview(heightDp = 812)
@Composable
private fun HomePreview() {
    Home(Modifier)
}