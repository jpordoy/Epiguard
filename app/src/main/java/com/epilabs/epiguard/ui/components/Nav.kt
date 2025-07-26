package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors

@Composable
fun BottomNav(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 129.dp)
            //.clip(shape = RoundedCornerShape(24.dp))
            .background(color = AppColors.color_Gray_50)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .offset(x = 0.dp,
                    y = (-25).dp)
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 12.dp,
                    end = 12.dp,
                    top = 15.dp,
                    bottom = 4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(weight = 0.2f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(color = AppColors.navbar2)
                        .padding(all = 5.dp)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.li_home),
                        contentDescription = "li:home",
                        tint = Color.White,
                        modifier = Modifier
                            .size(19.dp)
                            .align(Alignment.Top)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(weight = 0.2f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .padding(all = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.li_search),
                        contentDescription = "li:search",
                        modifier = Modifier
                            .size(19.dp)
                            .align(Alignment.Top)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(weight = 0.2f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .padding(all = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.li_pie_chart),
                        contentDescription = "li:pie-chart",
                        modifier = Modifier
                            .size(19.dp)
                            .align(Alignment.Top)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(weight = 0.2f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .padding(all = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.li_clock),
                        contentDescription = "li:clock",
                        modifier = Modifier
                            .size(19.dp)
                            .align(Alignment.Top)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(weight = 0.2f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .padding(all = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.li_user),
                        contentDescription = "li:user",
                        modifier = Modifier
                            .size(19.dp)
                            .align(Alignment.Top)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .offset(x = 0.dp,
                    y = 0.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 30.dp)
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(x = 0.dp,
                        y = (-8).dp)
                    .requiredWidth(width = 135.dp)
                    .requiredHeight(height = 5.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(color = Color(0xffb9c0c9)))
        }
    }
}

@Preview(widthDp = 393, heightDp = 129)
@Composable
private fun BottomNavPreview() {
    BottomNav(Modifier)
}