package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors

@Composable
fun SettingItem(
    title: String,
    subtitle: String,
    iconRes: Int,
    onClick: () -> Unit = {}, // Added for navigation/action
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(horizontal = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = androidx.compose.material3.ripple() // Ripple effect on tap
            ) { onClick() }
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 6.dp,
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {}

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(color = Color(0xfff5f7fa), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(AppColors.color_violet),
                    modifier = Modifier
                        .width(14.dp)
                        .height(16.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f) // Take remaining space
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color(0xff25272b),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = subtitle,
                    style = TextStyle(
                        color = Color(0xff90939d),
                        fontSize = 12.sp,
                        letterSpacing = 1.sp
                    )
                )
            }

            // Trailing arrow icon
            Image(
                painter = painterResource(id = R.drawable.arrowright), // Replace with your arrow drawable
                contentDescription = "Navigate",
                colorFilter = ColorFilter.tint(AppColors.color_Gray_600),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun SettingsSection(
    onProfileClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPasswordClick: () -> Unit = {},
    onAIClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {} // Added for the second "Password" item, assuming it’s a typo
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        SettingItem(
            title = "Profile",
            subtitle = "Update Profile Settings",
            iconRes = R.drawable.user45,
            onClick = onProfileClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        SettingItem(
            title = "Notifications",
            subtitle = "Change Notification Settings",
            iconRes = R.drawable.bell,
            onClick = onNotificationsClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        SettingItem(
            title = "Password",
            subtitle = "Change Password Settings",
            iconRes = R.drawable.lock,
            onClick = onPasswordClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        SettingItem(
            title = "Artificial Intelligence",
            subtitle = "Change Seizure Detector Model",
            iconRes = R.drawable.codesandbox,
            onClick = onAIClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        SettingItem(
            title = "Privacy",
            subtitle = "Manage Privacy Settings", // Fixed duplicate "Password"
            iconRes = R.drawable.lock, // Adjust icon if needed
            onClick = onPrivacyClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SettingsSection()
    }
}