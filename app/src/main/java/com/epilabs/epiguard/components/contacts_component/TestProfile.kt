package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.epilabs.epiguard.R
import com.epilabs.epiguard.components.contacts_component.UserProfileNew
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.components.NavBar88Pt

@Composable
fun ProfileScreenTemplate(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onBottomNavClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            NavBar88Pt(
                title = "Contact Profile",
                iconResId = R.drawable.settings1,
                onBackClick = onBackClick,
                onIconClick = onSettingsClick
            )
        },
        bottomBar = {
            NavBar(
                selectedItem = "Home",
                onItemSelected = onBottomNavClick
            )
        },
        containerColor = Color(0xFFF9F8FD),
        modifier = modifier
    ) { innerPadding ->
        UserProfileNew(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun ProfileScreenTemplatePreview() {
    ProfileScreenTemplate()
}