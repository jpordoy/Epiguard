package com.epilabs.epiguard

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
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
import com.epilabs.epiguard.Dashboard
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.viewmodel.ProfileViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epilabs.epiguard.utils.saveImageToInternalStorage
import java.io.File

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            Log.i(TAG, "Storage permission granted for reading images")
        } else {
            Log.w(TAG, "Storage permission denied for reading images")
            Toast.makeText(this, "Storage permission is required to upload images. Please grant it in settings.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Request storage permission if not granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Requesting READ_EXTERNAL_STORAGE permission")
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            Log.i(TAG, "READ_EXTERNAL_STORAGE permission already granted")
        }

        setContent {
            val navController = rememberNavController()
            val profileViewModel: ProfileViewModel = viewModel() // Single instance for the activity
            val imageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == android.app.Activity.RESULT_OK) {
                    result.data?.data?.let { uri ->
                        try {
                            val filePath = saveImageToInternalStorage(this, uri)
                            Log.i(TAG, "Image saved to: $filePath")
                            profileViewModel.updateProfileImage(filePath) // Update ViewModel state
                            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Log.e(TAG, "Error saving image: ${e.message}")
                            Toast.makeText(this, "Failed to save image: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    } ?: run {
                        Log.w(TAG, "No data returned from image picker")
                        Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
                    }
                }
            }

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
                        userId = backStackEntry.arguments?.getInt("userId") ?: -1,
                        imageLauncher = { intent -> imageLauncher.launch(intent) },
                        profileViewModel = profileViewModel // Pass the ViewModel instance
                    )
                }
                composable(
                    route = "view_user_profile/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    ViewUserProfiles(
                        navController = navController,
                        userId = backStackEntry.arguments?.getInt("userId") ?: -1,
                        profileViewModel = profileViewModel // Pass the ViewModel instance
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
                        bio = backStackEntry.arguments?.getString("bio"),
                        imageLauncher = { intent -> imageLauncher.launch(intent) },
                        profileViewModel = profileViewModel // Pass the ViewModel instance
                    )
                }
            }
        }
    }

    // Lazy initialization of DatabaseConnector to avoid multiple instances
    private val dbHandler by lazy { DatabaseConnector(this) }
}