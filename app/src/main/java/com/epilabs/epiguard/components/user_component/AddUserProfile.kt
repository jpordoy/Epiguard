package com.epilabs.epiguard.components.user_component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.UserProfileModel
import com.epilabs.epiguard.utils.saveImageToInternalStorage
import com.epilabs.epiguard.viewmodel.ProfileViewModel
import java.util.*

@Composable
fun AddUserProfileForm(
    navController: NavController,
    userId: Int,
    imageLauncher: (Intent) -> Unit, // Added to match MainActivity
    profileViewModel: ProfileViewModel // Added to match MainActivity
) {
    val context = LocalContext.current
    val fullName = remember { mutableStateOf(TextFieldValue()) }
    val phone = remember { mutableStateOf(TextFieldValue()) }
    val dateOfBirth = remember { mutableStateOf(TextFieldValue()) }
    val bio = remember { mutableStateOf(TextFieldValue()) }
    val dbHandler = DatabaseConnector(context)
    var profileExists by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    // Check if a profile exists for this userId
    LaunchedEffect(userId) {
        val profiles = dbHandler.getAllUserProfiles(userId)
        profileExists = profiles.isNotEmpty()
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "Add User Profile",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (profileExists) {
                Text(
                    text = "A profile already exists for this user. Only one profile is allowed.",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { navController.navigate("dashboard/$userId") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true
                ) {
                    Text("Go to Dashboard", color = Color.White)
                }
            } else {
                TextField(
                    value = fullName.value,
                    onValueChange = { fullName.value = it },
                    placeholder = { Text("Enter Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = phone.value,
                    onValueChange = { phone.value = it },
                    placeholder = { Text("Enter Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = dateOfBirth.value,
                    onValueChange = { dateOfBirth.value = it },
                    placeholder = { Text("Enter Date of Birth (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { imageLauncher(Intent(Intent.ACTION_PICK).apply { type = "image/*" }) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Upload Profile Image", color = Color.White)
                }
                if (profileViewModel.profileImage.value != null) {
                    Text("Image selected: ${profileViewModel.profileImage.value}", color = Color.Black)
                }
                TextField(
                    value = if (profileViewModel.profileImage.value == null) TextFieldValue() else TextFieldValue(profileViewModel.profileImage.value!!),
                    onValueChange = { profileViewModel.updateProfileImage(it.text) },
                    placeholder = { Text("Or enter Image URL") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = bio.value,
                    onValueChange = { bio.value = it },
                    placeholder = { Text("Enter Bio") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = false,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    try {
                        val profile = UserProfileModel(
                            userId = userId,
                            fullName = fullName.value.text,
                            phone = phone.value.text,
                            dateOfBirth = dateOfBirth.value.text,
                            profileImage = profileViewModel.profileImage.value,
                            bio = bio.value.text
                        )
                        dbHandler.insertUserProfile(profile)
                        Toast.makeText(context, "Profile Added to Database", Toast.LENGTH_SHORT).show()
                        navController.navigate("dashboard/$userId")
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }) {
                    Text("Add Profile to Database", color = Color.White)
                }
            }
        }
    }
}