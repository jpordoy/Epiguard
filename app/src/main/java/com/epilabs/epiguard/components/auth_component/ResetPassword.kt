package com.epilabs.epiguard.components.auth_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun ResetPassword(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 852.dp)
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 852.dp)
                .background(color = Color.White)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp, y = 122.dp)
            ) {
                Text(
                    text = "Reset Password",
                    color = AppColors.color_Primary_500,
                    textAlign = TextAlign.Left,
                    lineHeight = 1.25.em,
                    style = AppTypes.type_Header_Header_2
                )
                Text(
                    text = "Enter email address and a link to reset your password will be sent to you.",
                    color = AppColors.color_Gray_900,
                    textAlign = TextAlign.Left,
                    lineHeight = 1.57.em,
                    style = AppTypes.type_Body_Regular_400,
                    modifier = Modifier.requiredWidth(width = 345.dp)

                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 24.dp,
                        y = 247.dp
                    )
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = AppColors.color_Gray_50)
                    .padding(
                        horizontal = 24.dp,
                        vertical = 18.dp
                    )
            ) {
                Text(
                    text = "••••••••",
                    color = AppColors.color_Gray_800,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Body_Large_500,
                    modifier = Modifier
                        .requiredWidth(width = 257.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.eye_icon),
                    contentDescription = "visibility_off",
                    tint = AppColors.color_Gray_800
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 24.dp,
                        y = 323.dp
                    )
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = AppColors.color_Gray_50)
                    .border(
                        border = BorderStroke(1.dp, AppColors.color_Primary_500),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 24.dp,
                        vertical = 18.dp
                    )
            ) {
                Text(
                    text = "••••••••|",
                    color = AppColors.color_Gray_800,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Body_Large_500,
                    modifier = Modifier
                        .requiredWidth(width = 257.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.eye_icon),
                    contentDescription = "visibility_off",
                    tint = AppColors.color_Gray_800
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 24.dp,
                        y = 415.dp
                    )
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(color = AppColors.color_Primary_500)
                        .padding(
                            horizontal = 24.dp,
                            vertical = 18.dp
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "loading-01 1"
                        )
                        Text(
                            text = "Submitting...",
                            color = AppColors.color_Gray_White,
                            lineHeight = 1.5.em,
                            style = AppTypes.type_Body_Large_500
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 24.dp,
                            y = 39.dp
                        )
                        .requiredWidth(width = 297.dp)
                        .requiredHeight(height = 14.dp)
                        .blur(radius = 44.dp)
                        .background(color = AppColors.color_Primary_500)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 137.dp,
                        y = 790.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 32.dp)
                        .requiredHeight(height = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = AppColors.color_Primary_100)
                )
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 32.dp)
                        .requiredHeight(height = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = AppColors.color_Primary_100)
                )
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 32.dp)
                        .requiredHeight(height = 4.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = AppColors.color_Primary_500)
                )
            }
        }
    }
}
@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun ResetPasswordPreview() {
    ResetPassword(Modifier)
}