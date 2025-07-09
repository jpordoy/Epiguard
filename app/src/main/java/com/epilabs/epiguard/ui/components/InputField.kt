package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes
import com.epilabs.epiguard.ui.components.Spacing

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    label: String = "Email",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    isFocused: Boolean = false,
    errorMessage: String? = null,
    success: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.small)
    ) {
        Text(
            text = label,
            color = when {
                errorMessage != null -> Color(0xffef403b)
                isFocused -> MyColors.color_violet
                isEnabled -> MyColors.color_black
                else -> MyColors.color_grey
            },
            style = MyTypes.typography.titleMedium,
            modifier = Modifier.padding(bottom = Spacing.small)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = if (isFocused || errorMessage != null)
                        MyColors.color_light_grey_2.copy(alpha = 0.2f)
                    else
                        Color.Transparent
                )
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = isEnabled,
                textStyle = TextStyle(
                    color = if (isEnabled) MyColors.color_black else MyColors.color_grey,
                    lineHeight = 1.5.em
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.small, vertical = Spacing.small)
                    .align(Alignment.CenterStart)
            )
            if (errorMessage != null) {
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Clear",
                    colorFilter = ColorFilter.tint(MyColors.color_grey),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-Spacing.small))
                        .requiredSize(16.dp)
                        .clickable { onValueChange("") }
                )
                HorizontalDivider(
                    color = Color(0xffef403b),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            } else if (success) {
                Image(
                    painter = painterResource(id = R.drawable.sms),
                    contentDescription = "Success",
                    colorFilter = ColorFilter.tint(MyColors.color_grey),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-Spacing.small))
                        .requiredSize(16.dp)
                )
                HorizontalDivider(
                    color = Color(0xff01b41f),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            } else {
                HorizontalDivider(
                    color = MyColors.color_grey,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
        }
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color(0xffef403b),
                style = MyTypes.typography.titleMedium,
                modifier = Modifier.padding(top = Spacing.small)
            )
        }
    }
}

@Composable
fun MobileTextField(
    modifier: Modifier = Modifier,
    label: String = "Phone",
    value: String = "",
    countryCode: String = "+380",
    onValueChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    isFocused: Boolean = false,
    errorMessage: String? = null,
    success: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.small)
    ) {
        Text(
            text = label,
            color = when {
                errorMessage != null -> Color(0xffef403b)
                isFocused -> MyColors.color_violet
                isEnabled -> MyColors.color_black
                else -> MyColors.color_grey
            },
            style = MyTypes.typography.titleMedium,
            modifier = Modifier.padding(bottom = Spacing.small)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = if (isFocused || errorMessage != null)
                        MyColors.color_light_grey_2.copy(alpha = 0.2f)
                    else
                        Color.Transparent
                )
        ) {
            BasicTextField(
                value = "$countryCode $value",
                onValueChange = { newValue ->
                    val cleaned = newValue.replace(countryCode, "").trim()
                    onValueChange(cleaned)
                },
                enabled = isEnabled,
                textStyle = TextStyle(
                    color = if (isEnabled) MyColors.color_black else MyColors.color_grey,
                    lineHeight = 1.5.em
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.small, vertical = Spacing.small)
                    .align(Alignment.CenterStart)
            )
            if (errorMessage != null) {
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Clear",
                    colorFilter = ColorFilter.tint(MyColors.color_grey),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-Spacing.small))
                        .requiredSize(16.dp)
                        .clickable { onValueChange("") }
                )
                HorizontalDivider(
                    color = Color(0xffef403b),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            } else if (success) {
                Image(
                    painter = painterResource(id = R.drawable.sms),
                    contentDescription = "Success",
                    colorFilter = ColorFilter.tint(MyColors.color_grey),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-Spacing.small))
                        .requiredSize(16.dp)
                )
                HorizontalDivider(
                    color = Color(0xff01b41f),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            } else {
                HorizontalDivider(
                    color = MyColors.color_grey,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
        }
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color(0xffef403b),
                style = MyTypes.typography.titleMedium,
                modifier = Modifier.padding(top = Spacing.small)
            )
        }
    }
}


@Preview(widthDp = 390)
@Composable
private fun EmailTextFieldPreview() {
    EmailTextField()
}

@Preview(widthDp = 390)
@Composable
private fun MobileTextFieldPreview() {
    MobileTextField()
}