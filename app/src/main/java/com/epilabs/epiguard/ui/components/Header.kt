package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
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

@Composable
fun Component1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 340.dp)
            .requiredHeight(height = 70.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = Color.White)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 15.dp, y = 15.dp)
                .requiredSize(size = 40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.company_s_logo),
                contentDescription = "Profile",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(12.dp))
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "icn/general/notifications",
            modifier = Modifier
                .requiredSize(size = 24.dp)
                .align(alignment = Alignment.CenterEnd)
                .offset(x = ((-99).dp), y = 0.dp) // 24.dp (third icon) + 25.dp + 24.dp (second icon) + 25.dp + 15.dp (right edge)
        )
        Icon(
            painter = painterResource(id = R.drawable.phone),
            contentDescription = "icn/general/search",
            modifier = Modifier
                .requiredSize(size = 24.dp)
                .align(alignment = Alignment.CenterEnd)
                .offset(x = ((-50).dp), y = 0.dp) // 24.dp (third icon) + 25.dp + 15.dp (right edge)
        )
        Image(
            painter = painterResource(id = R.drawable.guy_4),
            contentDescription = "profile",
            modifier = Modifier
                .requiredSize(size = 24.dp)
                .align(alignment = Alignment.CenterEnd)
                .offset(x = ((-15).dp), y = 0.dp) // 15.dp from right edge
        )
    }
}

@Preview(widthDp = 340, heightDp = 70)
@Composable
private fun Component1Preview() {
    Component1(Modifier)
}