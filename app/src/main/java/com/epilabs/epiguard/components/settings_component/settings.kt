package com.epilabs.epiguard.components.settings_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.components.NavBar88Pt
import com.epilabs.epiguard.ui.components.ProfileSectionWhite
import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.components.SettingsSection

@Composable
fun HomeScreen(
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F8FD)) // Set background color to #F9F8FD
    ) {
        // Main content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            NavBar88Pt(
                title = "Settings",
                iconResId = R.drawable.settings1,
                onBackClick = onBackClick,
                onIconClick = onSettingsClick
            )
            ProfileSectionWhite(
                name = "Jamie Pordoy",
                breed = "jamiepordoy@hotmail.com",
                profileImageRes = R.drawable.guy2,
                overlayIconRes = R.drawable.sms
            )
        }

        SettingsSection()


        // NavBar at the bottom
        NavBar(
            selectedItem = "Home",
            onItemSelected = { /* Handle navigation */ }
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 812)
@Composable
fun HomeScreenPreview() {
    EpiGuardTheme {
        HomeScreen()
    }
}