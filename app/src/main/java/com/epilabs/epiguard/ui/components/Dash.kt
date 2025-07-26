package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.theme.AppShadows

@Composable
fun Component3(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.color_white)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            modifier = Modifier.fillMaxSize()
        ) {
            // Image section (~80% height)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp) // ~80% of 230.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.unsplash_emqnsqwqqdo),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                )

                // Text elements positioned relative to the image
                Text(
                    text = "25 - 5 2023",
                    color = AppColors.color_white,
                    lineHeight = 1.43.em,
                    style = AppTypes.type_Body_small_400,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 16.dp, y = 12.dp)
                )
                Text(
                    text = "Sun 11:26",
                    color = AppColors.color_white,
                    lineHeight = 1.43.em,
                    style = AppTypes.type_Body_small_400,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 100.dp, y = 12.dp)
                        .shadow(elevation = AppShadows.effect_switch_shadow)
                )
                Text(
                    text = "Camera 01",
                    color = AppColors.color_white,
                    textAlign = TextAlign.End,
                    lineHeight = 1.43.em,
                    style = AppTypes.type_Body_small_400,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 268.dp, y = 148.dp)
                        .shadow(elevation = AppShadows.effect_switch_shadow)
                )
                Text(
                    text = "Living room",
                    color = AppColors.color_Gray_700,
                    lineHeight = 1.71.em,
                    style = AppTypes.type_Body_small_400,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 16.dp, y = 198.dp)
                )
            }

            // White strip at the bottom (~20% height)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(AppColors.color_white)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Bedroom",
                        color = AppColors.color_Gray_700,
                        lineHeight = 1.71.em,
                        style = AppTypes.type_Body_small_400
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(AppColors.color_violet)
                            .padding(start = 6.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = "Live",
                            color = AppColors.color_Gray_50,
                            lineHeight = 1.67.em,
                            style = AppTypes.type_Body_small_400
                        )
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 358, heightDp = 230)
@Composable
private fun Component3Preview() {
    Component3(Modifier)
}