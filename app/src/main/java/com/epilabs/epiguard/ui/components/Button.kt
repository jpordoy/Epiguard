package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes

@Composable
fun LargeSolidButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isHover: Boolean = false
) {
    Box(
        modifier = modifier
            .requiredWidth(280.dp)
            .requiredHeight(56.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(if (isHover) MyColors.color_violet.copy(alpha = 0.8f) else MyColors.color_violet)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = MyColors.color_white,
            textAlign = TextAlign.Center,
            style = MyTypes.typography.labelLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun LargeOutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isHover: Boolean = false
) {
    Box(
        modifier = modifier
            .requiredWidth(280.dp)
            .requiredHeight(56.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(if (isHover) MyColors.color_pale_grey.copy(alpha = 0.8f) else MyColors.color_pale_grey)
            .border(BorderStroke(1.dp, MyColors.color_light_grey_border), RoundedCornerShape(25.dp))
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = MyColors.color_black,
            textAlign = TextAlign.Center,
            style = MyTypes.typography.labelLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SmallFilterButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    icon: Int? = null
) {
    Box(
        modifier = modifier
            .requiredWidth(91.dp)
            .requiredHeight(28.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(if (isActive) MyColors.color_violet else MyColors.color_pale_grey)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = if (isActive) MyColors.color_white else MyColors.color_black,
                    modifier = Modifier.requiredSize(16.dp)
                )
            }
            Text(
                text = text,
                color = if (isActive) MyColors.color_white else MyColors.color_black,
                style = MyTypes.typography.bodySmall,
                modifier = Modifier
                    .padding(start = if (icon != null) 8.dp else 0.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun CircularIconButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredSize(24.dp)
            .clip(CircleShape)
            .background(MyColors.color_violet)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MyColors.color_black),
            modifier = Modifier
                .align(Alignment.Center)
                .requiredSize(16.dp)
        )
    }
    Text(
        text = text,
        color = MyColors.color_black,
        modifier = Modifier
            .padding(start = 28.dp)
            .requiredWidth(34.dp)
            .requiredHeight(14.dp)
    )
}

@Composable
fun ReviewButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Int? = null
) {
    Box(
        modifier = modifier
            .requiredWidth(if (icon != null) 159.dp else 261.dp)
            .requiredHeight(24.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(MyColors.color_pale_grey)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = MyColors.color_violet,
                    modifier = Modifier.requiredSize(16.dp)
                )
            }
            Text(
                text = text,
                color = MyColors.color_violet,
                style = MyTypes.typography.labelLarge,
                modifier = Modifier.padding(start = if (icon != null) 8.dp else 0.dp)
            )
        }
    }
}

@Composable
fun SegmentedControlButton(
    activeText: String,
    inactiveText: String,
    onActiveClick: () -> Unit,
    onInactiveClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFirstActive: Boolean = true
) {
    Box(
        modifier = modifier
            .requiredWidth(335.dp)
            .requiredHeight(28.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            ElevatedButton(
                onClick = if (isFirstActive) onActiveClick else onInactiveClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFirstActive) MyColors.color_violet else Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = if (isFirstActive) activeText else inactiveText,
                    color = if (isFirstActive) MyColors.color_white else MyColors.color_black,
                    textAlign = TextAlign.Center,
                    style = MyTypes.typography.bodyMedium
                )
            }
            ElevatedButton(
                onClick = if (isFirstActive) onInactiveClick else onActiveClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFirstActive) Color.Transparent else MyColors.color_violet
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = if (isFirstActive) inactiveText else activeText,
                    color = if (isFirstActive) MyColors.color_black else MyColors.color_white,
                    textAlign = TextAlign.Center,
                    style = MyTypes.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun EditButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredWidth(64.dp)
            .requiredHeight(24.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(MyColors.color_pale_grey)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.requiredSize(16.dp)
            )
            Text(
                text = text,
                color = MyColors.color_violet,
                style = MyTypes.typography.labelSmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun GenderButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false
) {
    Box(
        modifier = modifier
            .requiredWidth(158.dp)
            .requiredHeight(32.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(if (isActive) MyColors.color_violet else MyColors.color_pale_grey)
            .border(BorderStroke(1.dp, MyColors.color_light_grey_border), RoundedCornerShape(25.dp))
            .shadow(if (isActive) 4.dp else 0.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (isActive) MyColors.color_white else if (text == "Female") MyColors.color_pink else MyColors.color_violet),
                modifier = Modifier.requiredSize(24.dp)
            )
            Text(
                text = text,
                color = if (isActive) MyColors.color_white else MyColors.color_black,
                style = MyTypes.typography.labelSmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun DateButton(
    day: String,
    weekday: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hasBadge: Boolean = false
) {
    Box(
        modifier = modifier
            .requiredWidth(48.dp)
            .requiredHeight(48.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = day,
            color = MyColors.color_violet,
            textAlign = TextAlign.Center,
            style = MyTypes.typography.headlineSmall,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = weekday,
            color = MyColors.color_black,
            textAlign = TextAlign.Center,
            style = MyTypes.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 4.dp)
        )
        if (hasBadge) {
            Badge(
                containerColor = MyColors.color_violet,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .requiredSize(8.dp)
            )
        }
    }
}

@Preview(widthDp = 356, heightDp = 853)
@Composable
private fun ButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LargeSolidButton(text = "Login", onClick = {}, modifier = Modifier.padding(bottom = 600.dp))
        LargeOutlineButton(text = "Create Account", onClick = {}, modifier = Modifier.padding(bottom = 500.dp))
        SmallFilterButton(text = "9 Sep", onClick = {}, icon = R.drawable.sms, modifier = Modifier.padding(bottom = 400.dp))
        CircularIconButton(text = "1.5 km", icon = R.drawable.sms, onClick = {}, modifier = Modifier.padding(bottom = 300.dp))
        ReviewButton(text = "Write a Review", onClick = {}, icon = R.drawable.sms, modifier = Modifier.padding(bottom = 200.dp))
        SegmentedControlButton(
            activeText = "Upcoming",
            inactiveText = "Past",
            onActiveClick = {},
            onInactiveClick = {},
            modifier = Modifier.padding()
        )
        EditButton(text = "Edit", icon = R.drawable.sms, onClick = {}, modifier = Modifier.padding(bottom = 50.dp))
        GenderButton(text = "Female", icon = R.drawable.sms, onClick = {}, isActive = true)
        DateButton(day = "7", weekday = "Mon", onClick = {}, hasBadge = true, modifier = Modifier.padding(top = 50.dp))
    }
}