package com.epilabs.epiguard.ui.components

import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes

@Composable
fun CardRemindAdd(
    modifier: Modifier = Modifier,
    text: String = "Add event",
    iconRes: Int = R.drawable.plus,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 120.dp)
            .requiredHeight(height = 144.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MyColors.color_light_grey_2)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 40.dp, y = 40.dp)
                .requiredWidth(width = 40.dp)
                .requiredHeight(height = 46.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(MyColors.color_violet, MyColors.color_grey),
                        start = Offset(4.06f, 50.63f),
                        end = Offset(46.25f, -28.44f)
                    )
                )
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "Add icon",
                colorFilter = ColorFilter.tint(MyColors.color_white),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, end = 0.dp, top = 40.dp, bottom = 0.dp)
                    .offset(x = 0.dp, y = (-34).dp)
            )
        }
        Text(
            text = text,
            color = MyColors.color_violet,
            textAlign = TextAlign.Center,
            style = MyTypes.typography.headlineSmall,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp) // Adjust to avoid overlap with icon
        )
    }
}

@Preview(widthDp = 120, heightDp = 144)
@Composable
private fun CardRemindAddPreview() {
    EpiGuardTheme {
        CardRemindAdd()
    }
}