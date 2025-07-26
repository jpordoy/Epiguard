package com.epilabs.epiguard.ui.components

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun Frame1000004459(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 368.dp)
            .background(AppColors.color_Gray_100)

    ) {
        Text(
            text = "Our Services",
            color = Color(0xff094067),
            lineHeight = 1.2.em,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .requiredWidth(width = 139.dp)
                .requiredHeight(height = 32.dp)
                .wrapContentHeight(align = Alignment.CenterVertically))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth(width = 78.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = AppColors.color_white)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "glyph_hospital 1",
                    modifier = Modifier
                        .requiredSize(size = 56.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(vertical = 18.dp))
                Text(
                    text = "Hospital",
                    color = Color(0xff094067),
                    lineHeight = 2.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth(width = 78.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = AppColors.color_white)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.guy1),
                    contentDescription = "aid-case (1) 1",
                    modifier = Modifier
                        .requiredSize(size = 56.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp,
                            vertical = 13.dp))
                Text(
                    text = "Medicines",
                    color = Color(0xff094067),
                    lineHeight = 2.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth(width = 78.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = AppColors.color_white)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "ambulance (1) 1",
                    modifier = Modifier
                        .requiredSize(size = 56.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp,
                            vertical = 11.dp))
                Text(
                    text = "Ambulance",
                    color = Color(0xff094067),
                    lineHeight = 2.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth(width = 78.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = AppColors.color_white)
                    .padding(horizontal = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mindfulness_1),
                    contentDescription = "mindfulness 1",
                    modifier = Modifier
                        .requiredSize(size = 56.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(all = 10.dp))
                Text(
                    text = "Health",
                    color = Color(0xff094067),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
    }
}















@Preview(widthDp = 368, heightDp = 128)
@Composable
private fun Frame1000004459Preview() {
    Frame1000004459(Modifier)
}