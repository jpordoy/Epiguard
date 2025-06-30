package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun Frame23355(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.color_Gray_White, RoundedCornerShape(5.dp))
            .padding(16.dp) // Minimal padding to keep content tight
    ) {
        // Name Row
        ContactRow(
            iconRes = R.drawable.user45,
            label = "Name",
            value = "Jamie Pordoy",
            endIcon = R.drawable.keyboard_arrow_down
        )

        // Contact No Row
        ContactRow(
            iconRes = R.drawable.bell,
            label = "Contact No",
            value = "07519273204",
            endIcon = R.drawable.keyboard_arrow_down
        )

        // Carer Status Row
        ContactRow(
            iconRes = R.drawable.codesandbox,
            label = "Carer Status",
            value = "Primary Carer",
            endToggle = true
        )

        // Username Row
        ContactRow(
            iconRes = R.drawable.lock,
            label = "Username",
            value = "Further secure your account for safety"
        )
    }
}

@Composable
fun ContactRow(
    iconRes: Int,
    label: String,
    value: String,
    endIcon: Int? = null,
    endToggle: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AppColors.color_Gray_50)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = AppColors.color_violet,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f) // Take available space
        ) {
            Text(
                text = label,
                color = Color(0xff181d27),
                lineHeight = 1.5.em,
                style = AppTypes.type_Body_small_400
            )
            Text(
                text = value,
                color = AppColors.color_Gray_600,
                lineHeight = 1.45.em,
                style = AppTypes.type_Body_small_400,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Optional End Icon or Toggle
        endIcon?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                colorFilter = ColorFilter.tint(AppColors.color_Gray_600),
                modifier = Modifier
                    .size(20.dp)
            )
        }

        if (endToggle) {
            Box(
                modifier = Modifier
                    .size(width = 51.dp, height = 30.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(AppColors.color_Gray_50)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 5.dp)
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(AppColors.color_Gray_600)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Frame23355Preview() {
    Frame23355()
}