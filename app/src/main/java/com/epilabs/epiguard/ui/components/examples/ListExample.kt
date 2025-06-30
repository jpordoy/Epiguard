package com.epilabs.epiguard.ui.components.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.NavBar88Pt
import com.epilabs.epiguard.ui.components.ReviewButton
import com.epilabs.epiguard.ui.components.SmallFilterButton
import com.epilabs.epiguard.ui.theme.MyColors

@Composable
fun QuickAction(
    modifier: Modifier = Modifier,
    iconR80850: Boolean,
    iconL80725: Boolean,
    label808100: String,
    buttonHeight: Dp = 48.dp // Customizable height for TextField and button
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 124.dp)
            .padding(all = 16.dp)
            .background(color = AppColors.color_Gray_White)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.requiredWidth(width = 343.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Icon"
                    )
                },
                textStyle = AppTypes.type_Body_small_400,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = AppColors.color_Gray_900,
                    unfocusedTextColor = AppColors.color_Gray_900,
                    focusedContainerColor = AppColors.color_Gray_50,
                    unfocusedContainerColor = AppColors.color_Gray_50,
                    focusedIndicatorColor = AppColors.color_Gray_100,
                    unfocusedIndicatorColor = AppColors.color_Gray_100,
                    disabledIndicatorColor = AppColors.color_Gray_100,
                    cursorColor = AppColors.color_Primary_500
                ),
                modifier = Modifier
                    .weight(0.75f)
                    .requiredHeight(buttonHeight) // Match button height
                    .clip(RoundedCornerShape(12.dp))
                    .border(BorderStroke(1.dp, AppColors.color_Gray_100), RoundedCornerShape(12.dp))
            )
            Surface(
                modifier = Modifier
                    .size(width = buttonHeight, height = buttonHeight) // Square button with customizable height
                    .clip(RoundedCornerShape(12.dp))
                    .background(AppColors.color_Gray_50) // Custom color from AppColors
                    .clickable(onClick = { }), // Clickable behavior
            ) {
                Box(
                    contentAlignment = Alignment.Center, // Center the icon
                    modifier = Modifier.fillMaxSize()
                        .background(AppColors.color_Gray_50) // Custom color from AppColors

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.xmlid_499),
                        contentDescription = "Menu Icon",
                        tint = AppColors.color_Primary_500,
                        modifier = Modifier
                            .size(width = 15.dp, height = 15.dp) // Square button with customizable height
                    )
                }
            }
        }


        Row(
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.requiredWidth(width = 375.dp)

        ){
            SmallFilterButton(text = "9 Sep", onClick = {}, icon = R.drawable.sms, modifier = Modifier.padding(bottom = 400.dp, top = 15.dp, start = 25.dp))
            SmallFilterButton(text = "9 Sep", onClick = {}, icon = R.drawable.sms, modifier = Modifier.padding(bottom = 400.dp, top = 15.dp, start = 25.dp))
            SmallFilterButton(text = "9 Sep", onClick = {}, icon = R.drawable.sms, modifier = Modifier.padding(bottom = 400.dp, top = 15.dp, start = 25.dp))

        }
    }
}

@Preview(widthDp = 375, heightDp = 124)
@Composable
private fun QuickActionPreview() {
    QuickAction(Modifier, true, true, "Button", buttonHeight = 48.dp)
}