package com.epilabs.epiguard.components.user_component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.UserProfileModel
import com.epilabs.epiguard.viewmodel.ProfileViewModel

@Composable
fun UpdateUserProfileForm(
    navController: NavController,
    existingUserId: Int,
    existingProfileId: Int,
    existingProfileImage: String?,
    name: String?,
    phone: String?,
    dob: String?,
    bio: String?,
    imageLauncher: (Intent) -> Unit,
    profileViewModel: ProfileViewModel
) {
    val context = LocalContext.current
    val fullName = remember { mutableStateOf(name ?: "") }
    val userPhone = remember { mutableStateOf(phone ?: "") }
    val dateOfBirth = remember { mutableStateOf(dob ?: "") }
    val userBio = remember { mutableStateOf(bio ?: "") }
    val profileImage = remember { mutableStateOf(existingProfileImage) }

    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Update User Profile",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "User ID: $existingUserId",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Profile ID: $existingProfileId",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        TextField(
            value = fullName.value,
            onValueChange = { fullName.value = it },
            placeholder = { Text("Enter full name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = userPhone.value,
            onValueChange = { userPhone.value = it },
            placeholder = { Text("Enter phone number") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = dateOfBirth.value,
            onValueChange = { dateOfBirth.value = it },
            placeholder = { Text("Enter date of birth") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                imageLauncher(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Profile Image", color = Color.White)
        }
        if (profileImage.value != null) {
            Text("Image selected: ${profileImage.value}", color = Color.Black)
        }
        TextField(
            value = if (profileImage.value == null) TextFieldValue() else TextFieldValue(profileImage.value!!),
            onValueChange = { profileImage.value = it.text },
            placeholder = { Text("Or enter Image URL") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = userBio.value,
            onValueChange = { userBio.value = it },
            placeholder = { Text("Enter bio") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            val updatedProfile = UserProfileModel(
                userId = existingUserId,
                profileId = existingProfileId,
                fullName = fullName.value,
                phone = userPhone.value,
                dateOfBirth = dateOfBirth.value,
                profileImage = profileImage.value,
                bio = userBio.value
            )
            val result = dbHandler.updateUserProfile(updatedProfile)
            if (result > 0) {
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard/$existingUserId")
            } else {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Update Profile", color = Color.White)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            dbHandler.deleteUserProfile(existingProfileId)
            Toast.makeText(context, "Profile Deleted", Toast.LENGTH_SHORT).show()
            navController.navigate("dashboard/$existingUserId")
        }) {
            Text("Delete Profile", color = Color.White)
        }
    }
}