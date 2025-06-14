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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.epilabs.epiguard.components.auth_component.SignInForm
import com.epilabs.epiguard.components.auth_component.RegisterForm
import com.epilabs.epiguard.components.auth_component.OTPVerificationForm
import com.epilabs.epiguard.components.user_component.AddUserProfileForm
import com.epilabs.epiguard.components.user_component.UpdateUserProfileForm
import com.epilabs.epiguard.components.user_component.ViewUserProfiles
import com.epilabs.epiguard.components.contacts_component.AddContactForm
import com.epilabs.epiguard.components.contacts_component.ViewContacts
import com.epilabs.epiguard.components.contacts_component.UpdateContactForm
import com.epilabs.epiguard.Dashboard
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.viewmodel.ProfileViewModel
import com.epilabs.epiguard.viewmodel.ContactViewModel
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
            val profileViewModel: ProfileViewModel = viewModel() // Single instance for user profiles
            val contactViewModel: ContactViewModel = viewModel() // Single instance for contacts

            // State to track which ViewModel to update (profile or contact)
            var imageTarget by remember { mutableStateOf("profile") } // Default to profile

            val imageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == android.app.Activity.RESULT_OK) {
                    result.data?.data?.let { uri ->
                        try {
                            val filePath = saveImageToInternalStorage(this, uri)
                            Log.i(TAG, "Image saved to: $filePath")
                            // Update the appropriate ViewModel based on imageTarget
                            when (imageTarget) {
                                "profile" -> profileViewModel.updateProfileImage(filePath)
                                "contact" -> contactViewModel.updateContactImage(filePath)
                            }
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
                        imageLauncher = { intent ->
                            imageTarget = "profile"
                            imageLauncher.launch(intent)
                        },
                        profileViewModel = profileViewModel
                    )
                }
                composable(
                    route = "add_contact/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    AddContactForm(
                        navController = navController,
                        userID = backStackEntry.arguments?.getInt("userId") ?: -1, // Fixed from userID
                        imageLauncher = { intent ->
                            imageTarget = "contact"
                            imageLauncher.launch(intent)
                        },
                        contactViewModel = contactViewModel
                    )
                }
                composable(
                    route = "view_user_profile/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    ViewUserProfiles(
                        navController = navController,
                        userId = backStackEntry.arguments?.getInt("userId") ?: -1,
                        profileViewModel = profileViewModel
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
                        imageLauncher = { intent ->
                            imageTarget = "profile"
                            imageLauncher.launch(intent)
                        },
                        profileViewModel = profileViewModel
                    )
                }
                composable(
                    route = "view_contacts/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    ViewContacts(
                        navController = navController,
                        userID = backStackEntry.arguments?.getInt("userId") ?: -1,
                        contactViewModel = contactViewModel
                    )
                }
                composable(
                    route = "update_contact/{userId}/{contactId}/{profileImage}/{firstname}/{lastname}/{contact}/{alertType}/{medicalExperience}/{status}/{primaryCarer}/{timestamp}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType },
                        navArgument("contactId") { type = NavType.IntType },
                        navArgument("profileImage") { type = NavType.StringType; nullable = true },
                        navArgument("firstname") { type = NavType.StringType; nullable = true },
                        navArgument("lastname") { type = NavType.StringType; nullable = true },
                        navArgument("contact") { type = NavType.StringType; nullable = true },
                        navArgument("alertType") { type = NavType.StringType; nullable = true },
                        navArgument("medicalExperience") { type = NavType.StringType; nullable = true },
                        navArgument("status") { type = NavType.StringType; nullable = true },
                        navArgument("primaryCarer") { type = NavType.StringType; nullable = true },
                        navArgument("timestamp") { type = NavType.StringType; nullable = true }
                    )
                ) { backStackEntry ->
                    UpdateContactForm(
                        navController = navController,
                        userID = backStackEntry.arguments?.getInt("userId") ?: -1,
                        contactID = backStackEntry.arguments?.getInt("contactId") ?: -1,
                        profileImage = backStackEntry.arguments?.getString("profileImage"),
                        firstname = backStackEntry.arguments?.getString("firstname"),
                        lastname = backStackEntry.arguments?.getString("lastname"),
                        contact = backStackEntry.arguments?.getString("contact"),
                        alertType = backStackEntry.arguments?.getString("alertType"),
                        medicalExperience = backStackEntry.arguments?.getString("medicalExperience"),
                        status = backStackEntry.arguments?.getString("status"),
                        primaryCarer = backStackEntry.arguments?.getString("primaryCarer"),
                        timestamp = backStackEntry.arguments?.getString("timestamp"),
                        imageLauncher = { intent ->
                            imageTarget = "contact"
                            imageLauncher.launch(intent)
                        },
                        contactViewModel = contactViewModel
                    )
                }
            }
        }
    }

    // Lazy initialization of DatabaseConnector to avoid multiple instances
    private val dbHandler by lazy { DatabaseConnector(this) }
}