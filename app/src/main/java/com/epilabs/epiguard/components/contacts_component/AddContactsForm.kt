package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.AddContactsForm
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.components.NavBar88Pt
import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import com.epilabs.epiguard.ui.components.Spacing


@Composable
fun HomeScreen(
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.color_light_grey_border) // Set background color to #F9F8FD
    ) {
        // Main content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()

        ) {
            NavBar88Pt(
                title = "Add Contact",
                iconResId = com.epilabs.epiguard.R.drawable.settings,
                onBackClick = onBackClick,
                onIconClick = onSettingsClick
            )
        }


        Spacer(Modifier.height(Spacing.medium))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AddContactsForm()
        }
        // NavBar at the bottom
        NavBar(
            selectedItem = "Home",
            onItemSelected = { /* Handle navigation */ }
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 1900)
@Composable
fun HomeScreenPreview() {
    EpiGuardTheme {
        HomeScreen()
    }
}