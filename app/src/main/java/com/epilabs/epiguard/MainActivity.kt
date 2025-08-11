package com.epilabs.epiguard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.epilabs.epiguard.components.seizure_component.IpMjpegDetector
import com.epilabs.epiguard.components.seizure_component.IpWebcamConnector
import com.epilabs.epiguard.components.seizure_component.RemoteSeizureDetector
import com.epilabs.epiguard.components.seizure_component.SeizureDetector
import com.epilabs.epiguard.components.testlab_component.MobileUI
import com.epilabs.epiguard.components.testlab_component.ModelClassificationScreen
import com.epilabs.epiguard.components.testlab_component.VideoPlayerScreen
import com.epilabs.epiguard.components.user_component.AddUserProfileForm
import com.epilabs.epiguard.components.user_component.UpdateUserProfileForm
import com.epilabs.epiguard.components.user_component.ViewUserProfiles
import com.epilabs.epiguard.ui.components.Frame3
import com.epilabs.epiguard.utils.saveImageToInternalStorage
import com.epilabs.epiguard.viewmodel.ContactViewModel
import com.epilabs.epiguard.viewmodel.ProfileViewModel
import com.epilabs.epiguard.viewmodel.VideoViewModel

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val profileViewModel: ProfileViewModel = viewModel()
            val contactViewModel: ContactViewModel = viewModel()

            // Photo picker logic
            var imageTarget by remember { mutableStateOf("profile") }
            val photoPickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia()
            ) { uri ->
                handlePhotoPickerResult(uri, imageTarget, profileViewModel, contactViewModel)
            }

            NavHost(navController = navController, startDestination = "sign_in") {
                // Authentication Routes
                composable("sign_in") {
                    SignInForm(navController)
                }
                composable("register") {
                    RegisterForm(navController)
                }
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

                // Dashboard Route
                composable(
                    route = "dashboard/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    Dashboard(
                        navController = navController,
                        userId = userId
                    )
                }

                // Seizure Detection Routes
                composable(
                    route = "seizure_detector/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    SeizureDetector(navController, userId)
                }
                // IP Webcam route with userId parameter
                composable(
                    route = "ip_webcam_connector/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    IpWebcamConnector(
                        modifier = Modifier.fillMaxSize(),
                        userId = userId
                    )
                }

                // Video and Model Routes
                composable(
                    route = "upload_video/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    MobileUI(userId = userId, navController = navController)
                }
                composable(
                    route = "remote_seizure_detector/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    RemoteSeizureDetector(navController, userId, Modifier.fillMaxSize())
                }

                composable(
                    route = "IpMjpegDetector/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    IpMjpegDetector()
                }

                composable(
                    route = "video_player/{videoId}/{userId}",
                    arguments = listOf(
                        navArgument("videoId") { type = NavType.IntType },
                        navArgument("userId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val videoId = backStackEntry.arguments?.getInt("videoId") ?: -1
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    val viewModel: VideoViewModel = viewModel(factory = VideoViewModel.Factory(LocalContext.current, userId))
                    VideoPlayerScreen(navController = navController, videoId = videoId, viewModel = viewModel)
                }
                composable(
                    route = "model_classification/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    ModelClassificationScreen(userId = userId, navController = navController)
                }
                composable(
                    route = "model_details/{modelName}",
                    arguments = listOf(navArgument("modelName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val modelName = backStackEntry.arguments?.getString("modelName") ?: ""
                    Frame3(navController = navController, modelName = modelName)
                }

                // User Profile Routes
                composable(
                    route = "add_user_profile/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    AddUserProfileForm(
                        navController = navController,
                        userId = userId,
                        imageLauncher = {
                            imageTarget = "profile"
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        profileViewModel = profileViewModel
                    )
                }
                composable(
                    route = "view_user_profile/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    ViewUserProfiles(
                        navController = navController,
                        userId = userId,
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
                        imageLauncher = {
                            imageTarget = "profile"
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        profileViewModel = profileViewModel
                    )
                }

                // Contact Routes
                composable(
                    route = "add_contact/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    AddContactDebugForm(
                        navController = navController,
                        userID = userId,
                        imageLauncher = {
                            imageTarget = "contact"
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        contactViewModel = contactViewModel
                    )
                }
                composable(
                    route = "view_contacts/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    ViewContactScreen(
                        userID = userId,
                        navController = navController,
                        modifier = Modifier
                    )
                }
                composable(
                    route = "update_contact/{userId}/{contactId}/{profileImage}/{firstname}/{lastname}/{contact}/{email}/{alertType}/{about}/{epilepsyFirstAid}/{cPR}/{mentalHealthFirstAid}/{status}/{primaryCarer}/{relationship}/{timestamp}",
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
                        imageLauncher = {
                            imageTarget = "contact"
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        contactViewModel = contactViewModel
                    )
                }
            }
        }
    }

    private fun handlePhotoPickerResult(
        uri: android.net.Uri?,
        imageTarget: String,
        profileViewModel: ProfileViewModel,
        contactViewModel: ContactViewModel
    ) {
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
}