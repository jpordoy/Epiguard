package com.epilabs.epiguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.epilabs.epiguard.components.auth_component.SignInForm
import com.epilabs.epiguard.components.auth_component.RegisterForm
import com.epilabs.epiguard.components.auth_component.OTPVerificationForm
import com.epilabs.epiguard.components.user_component.AddUserProfileForm
import com.epilabs.epiguard.components.user_component.UpdateUserProfileForm
import com.epilabs.epiguard.components.user_component.ViewUserProfiles

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "sign_in") {
                composable("sign_in") { SignInForm(navController) }
                composable("register") { RegisterForm(navController) }
                composable(
                    route = "otp_verification/{email}/{otp}",
                    arguments = listOf(
                        navArgument("email") { type = NavType.StringType },
                        navArgument("otp") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    OTPVerificationForm(
                        navController = navController,
                        email = backStackEntry.arguments?.getString("email") ?: "",
                        expectedOTP = backStackEntry.arguments?.getString("otp") ?: ""
                    )
                }
                composable(
                    route = "dashboard/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    Dashboard(
                        navController = navController,
                        userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    )
                }
                composable(
                    route = "add_user_profile/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    AddUserProfileForm(
                        navController = navController,
                        userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    )
                }
                composable(
                    route = "view_user_profile/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    ViewUserProfiles(
                        navController = navController,
                        userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    )
                }
                composable(
                    route = "update_user_profile/{userId}/{profileId}/{profileImage}/{fullName}/{phone}/{dateOfBirth}/{bio}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType },
                        navArgument("profileId") { type = NavType.IntType },
                        navArgument("profileImage") { type = NavType.StringType; nullable = true },
                        navArgument("fullName") { type = NavType.StringType; nullable = true },
                        navArgument("phone") { type = NavType.StringType; nullable = true },
                        navArgument("dateOfBirth") { type = NavType.StringType; nullable = true },
                        navArgument("bio") { type = NavType.StringType; nullable = true }
                    )
                ) { backStackEntry ->
                    UpdateUserProfileForm(
                        navController = navController,
                        existingUserId = backStackEntry.arguments?.getInt("userId") ?: -1,
                        existingProfileId = backStackEntry.arguments?.getInt("profileId") ?: -1,
                        existingProfileImage = backStackEntry.arguments?.getString("profileImage"),
                        name = backStackEntry.arguments?.getString("fullName"),
                        phone = backStackEntry.arguments?.getString("phone"),
                        dob = backStackEntry.arguments?.getString("dateOfBirth"),
                        bio = backStackEntry.arguments?.getString("bio")
                    )
                }
            }
        }
    }
}