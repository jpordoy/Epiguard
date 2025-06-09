package com.epilabs.epiguard.components.user_component

import android.content.Context
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.UserProfileModel

@Composable
fun UpdateUserProfileForm(
    navController: NavController,
    existingUserId: Int,
    existingProfileId: Int,
    existingProfileImage: String?,
    name: String?,
    phone: String?,
    dob: String?,
    bio: String?
) {
    val context = LocalContext.current
    val fullName = remember { mutableStateOf(name ?: "") }
    val userPhone = remember { mutableStateOf(phone ?: "") }
    val dateOfBirth = remember { mutableStateOf(dob ?: "") }
    val userBio = remember { mutableStateOf(bio ?: "") }

    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Update User Profile",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
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
                profileImage = existingProfileImage ?: "",
                bio = userBio.value
            )
            val result = dbHandler.updateUserProfile(updatedProfile)
            if (result > 0) {
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard")
            } else {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Update Profile", color = Color.White)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            dbHandler.deleteUserProfile(existingUserId)
            Toast.makeText(context, "Profile Deleted", Toast.LENGTH_SHORT).show()
            navController.navigate("dashboard")
        }) {
            Text("Delete Profile", color = Color.White)
        }
    }
}