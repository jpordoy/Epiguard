package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import com.epilabs.epiguard.ui.components.ProfileSectionWhite

@Composable
fun ProfileScreen(
    title: String = "Profile", // Dynamic title for NavBar88Pt
    iconResId: Int? = null, // Dynamic icon for NavBar88Pt
    profileName: String = "Jane Doe", // Dynamic profile name for Section
    accountNumber: String = "A/N: 1234567890", // Dynamic account number for Section
    onBackClick: () -> Unit = {},
    onIconClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Bar
        NavBar88Pt(
            title = title,
            iconResId = iconResId,
            onBackClick = onBackClick,
            onIconClick = onIconClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // Profile Section
        Section(
            name = profileName,
            accountNumber = accountNumber,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // Placeholder for additional content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // Add dynamic content here if needed (e.g., text fields)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    EpiGuardTheme {
        ProfileScreen(
            title = "User Profile",
            iconResId = R.drawable.guy2,
            profileName = "Jane Doe",
            accountNumber = "A/N: 1234567890"
        )
    }
}