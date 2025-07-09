package com.epilabs.epiguard.components.contacts_component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.zIndex
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.theme.AppShadows

@Composable
fun Frame1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = AppColors.wallpaper)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 1.5.dp,
                    y = 70.dp
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = AppColors.color_Gray_White)
                .border(
                    border = BorderStroke(10.dp, AppColors.wallpaper),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(all = 12.dp)
                .zIndex(1f)                 // draw above the card

        ) {
            Image(
                painter = painterResource(id = R.drawable.tick),
                contentDescription = "check-double-fill",
                modifier = Modifier
                    .requiredSize(size = 64.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 1.dp,
                    y = 128.dp
                )
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 630.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = AppColors.color_Gray_White)

        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 16.dp,
                        y = 90.dp
                    )
                    .requiredWidth(width = 303.dp)
            ) {
                Text(
                    text = "You have successfully added a new contact",
                    color = AppColors.color_Gray_700,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.6.em,
                    style = AppTypes.type_Header_Header_2,
                    modifier = Modifier
                        .fillMaxWidth())
                Text(
                    text = "Your new contact is now set to receive real-time alerts and notifications from EpiGuard.",
                    color = AppColors.color_Gray_700,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.67.em,
                    style = AppTypes.type_Body_Regular_400,
                    modifier = Modifier
                        .fillMaxWidth())
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 16.dp,
                        y = 226.dp
                    )
                    .requiredWidth(width = 303.dp)
                    .padding(all = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 80.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.guy_3),
                        contentDescription = "image 2",
                        contentScale = Crop,
                        modifier = Modifier
                            .requiredSize(size = 80.dp))
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Dr. Stone Gaze",
                        color = AppColors.color_Gray_700,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.75.em,
                        style = AppTypes.type_Typography_Body_Medium,
                        modifier = Modifier
                            .fillMaxWidth())
                    Text(
                        text = "stone.gaze@hotmail.com",
                        color = AppColors.color_Gray_700,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.67.em,
                        style = AppTypes.type_Typography_Body_Medium,
                        modifier = Modifier
                            .fillMaxWidth())
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 16.dp,
                        y = 394.dp
                    )
                    .requiredWidth(width = 303.dp)
            ) {
                IconBooking()
                Column(
                    modifier = Modifier
                        .requiredWidth(width = 222.dp)
                ) {
                    Text(
                        text = "Contact Status",
                        color = AppColors.color_Gray_700,
                        lineHeight = 1.67.em,
                        style = AppTypes.type_Body_small_400,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Emergency Contact",
                        color = AppColors.color_Gray_700,
                        lineHeight = 1.71.em,
                        style = AppTypes.type_Body_small_400,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }



            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 450.dp)
                    .requiredWidth(width = 303.dp)
            ) {
                IconBooking()
                Column(
                    modifier = Modifier
                        .requiredWidth(width = 222.dp)
                ) {
                    Text(
                        text = "Contact Method",
                        color = AppColors.color_Gray_700,
                        lineHeight = 1.67.em,
                        style = AppTypes.type_Body_small_400,
                        modifier = Modifier
                            .fillMaxWidth())
                    Text(
                        text = "Mobile/SMS",
                        color = AppColors.color_Gray_700,
                        lineHeight = 1.71.em,
                        style = AppTypes.type_Body_small_400,
                        modifier = Modifier
                            .fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun IconBooking(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = AppColors.color_Gray_50,
        border = BorderStroke(1.dp, AppColors.color_Gray_100),
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .requiredSize(size = 42.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "menu-board",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 5.dp,
                        y = 5.dp
                    )
                    .requiredSize(size = 32.dp))
        }
    }
}

@Composable
fun SizeLargeTypePrimaryStateDefaultVariationDefaultTextTrue(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 303.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = AppColors.color_violet)
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            )
            .shadow(
                elevation = AppShadows.effect_switch_shadow,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = "tempalte",
            color = AppColors.color_Gray_White,
            lineHeight = 1.71.em,
            style = AppTypes.type_Body_small_400)
    }
}




@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun Frame1Preview() {
    Frame1(Modifier)
}