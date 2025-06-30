package com.epilabs.epiguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epilabs.epiguard.ui.ProfileTabs
import com.epilabs.epiguard.ui.theme.EpiGuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpiGuardTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "add_your_contact"
                ) {
                    composable("add_your_contact") {
                        ProfileTabs() }
                    }

                }
            }
        }
    }