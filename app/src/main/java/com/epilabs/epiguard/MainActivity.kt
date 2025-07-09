package com.epilabs.epiguard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.epilabs.epiguard.components.auth_component.OTPVerificationForm
import com.epilabs.epiguard.components.auth_component.RegisterForm
import com.epilabs.epiguard.components.auth_component.SignInForm
import com.epilabs.epiguard.components.contacts_component.AddContactDebugForm
import com.epilabs.epiguard.components.contacts_component.UpdateContactForm
import com.epilabs.epiguard.components.contacts_component.ViewContactScreen
import com.epilabs.epiguard.components.user_component.AddUserProfileForm
import com.epilabs.epiguard.components.user_component.UpdateUserProfileForm
import com.epilabs.epiguard.components.user_component.ViewUserProfiles
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.utils.saveImageToInternalStorage
import com.epilabs.epiguard.viewmodel.ContactViewModel
import com.epilabs.epiguard.viewmodel.ProfileViewModel
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val profileViewModel: ProfileViewModel = viewModel()
            val contactViewModel: ContactViewModel = viewModel()
            var imageTarget by remember { mutableStateOf("profile") }

            val photoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    try {
                        val filePath = saveImageToInternalStorage(this, uri)
                        Log.i(TAG, "Image saved to: $filePath")
                        when (imageTarget) {
                            "profile" -> profileViewModel.updateProfileImage(filePath)
                            "contact" -> contactViewModel.updateContactImage(filePath)
                        }
                        Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Log.e(TAG, "Error saving image: ${e.message}")
                        Toast.makeText(this, "Failed to save image: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.w(TAG, "No image selected")
                    Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
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
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        profileViewModel = profileViewModel
                    )
                }
                composable(
                    route = "add_contact/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    AddContactDebugForm(
                        navController = navController,
                        userID = backStackEntry.arguments?.getInt("userId") ?: 0,
                        imageLauncher = { intent ->
                            imageTarget = "contact"
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        profileViewModel = profileViewModel
                    )
                }
                composable(
                    route = "view_contacts/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    ViewContactScreen(
                        userID = backStackEntry.arguments?.getInt("userId") ?: -1,
                        navController = navController,
                        modifier = Modifier
                    )
                }
                composable(
                    route = "update_contact/{userId}/{contactId}/{profileImage}/{firstname}/{lastname}/{contact}/{email}/{alertType}/{about}/{epilepsyFirstAid}/{cPR}/{mentalHealthFirstAid}/{status}/{primaryCarer}/{timestamp}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType },
                        navArgument("contactId") { type = NavType.IntType },
                        navArgument("profileImage") { type = NavType.StringType; nullable = true },
                        navArgument("firstname") { type = NavType.StringType; nullable = true },
                        navArgument("lastname") { type = NavType.StringType; nullable = true },
                        navArgument("contact") { type = NavType.StringType; nullable = true },
                        navArgument("email") { type = NavType.StringType; nullable = true },
                        navArgument("alertType") { type = NavType.StringType; nullable = true },
                        navArgument("about") { type = NavType.StringType; nullable = true },
                        navArgument("epilepsyFirstAid") { type = NavType.StringType; nullable = true },
                        navArgument("cPR") { type = NavType.StringType; nullable = true },
                        navArgument("mentalHealthFirstAid") { type = NavType.StringType; nullable = true },
                        navArgument("status") { type = NavType.StringType; nullable = true },
                        navArgument("primaryCarer") { type = NavType.StringType; nullable = true },
                        navArgument("relationship") { type = NavType.StringType; nullable = true },
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
                        email = backStackEntry.arguments?.getString("email"),
                        alertType = backStackEntry.arguments?.getString("alertType"),
                        about = backStackEntry.arguments?.getString("about"),
                        epilepsyFirstAid = backStackEntry.arguments?.getString("epilepsyFirstAid"),
                        cPR = backStackEntry.arguments?.getString("cPR"),
                        mentalHealthFirstAid = backStackEntry.arguments?.getString("mentalHealthFirstAid"),
                        status = backStackEntry.arguments?.getString("status"),
                        primaryCarer = backStackEntry.arguments?.getString("primaryCarer"),
                        relationship = backStackEntry.arguments?.getString("relationship"),
                        timestamp = backStackEntry.arguments?.getString("timestamp"),
                        imageLauncher = { intent ->
                            imageTarget = "contact"
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        contactViewModel = contactViewModel
                    )
                }
            }
        }
    }

    private val dbHandler by lazy { DatabaseConnector(this) }
}