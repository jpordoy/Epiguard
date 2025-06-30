package com.epilabs.epiguard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.theme.EpiGuardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 88.dp) // Space for bottom navigation
                .verticalScroll(scrollState)
        ) {
            // Status Bar
            StatusBarComponent()

            // Header Section
            HeaderSection()

            // Service Cards
            ServiceCardsSection()

            // Promotion Cards
            PromotionCardsSection()
        }

        // Bottom Navigation
        BottomNavigationComponent(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 927)
@Composable
fun DashboardScreenPreview() {
    EpiGuardTheme {
        DashboardScreen()
    }
}

