package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes

@Composable
fun CardRemindActive(
    modifier: Modifier = Modifier,
    title: String = "Measles vaccine",
    date: String = "30.08.2018 г",
    iconRes: Int = R.drawable.sms,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 120.dp)
            .requiredHeight(height = 144.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MyColors.color_white)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 40.dp, y = 30.dp)
                .requiredWidth(width = 40.dp)
                .requiredHeight(height = 46.dp)
                .clip(CircleShape)
                .background(color = MyColors.color_white)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "Event icon",
                colorFilter = ColorFilter.tint(MyColors.color_violet),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = MyColors.color_black,
                textAlign = TextAlign.Center,
                style = MyTypes.typography.bodySmall
            )
            Text(
                text = date,
                color = MyColors.color_grey,
                textAlign = TextAlign.Center,
                lineHeight = 12.5.em,
                style = MyTypes.typography.bodySmall
            )
        }
    }
}

@Preview(widthDp = 120, heightDp = 144)
@Composable
private fun CardRemindActivePreview() {
    EpiGuardTheme {
        CardRemindActive()
    }
}