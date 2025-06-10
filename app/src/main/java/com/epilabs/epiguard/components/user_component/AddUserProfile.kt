package com.epilabs.epiguard.components.user_component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.UserProfileModel

@Composable
fun AddUserProfileForm(navController: NavController, userId: Int) {
    val context = LocalContext.current
    val fullName = remember { mutableStateOf(TextFieldValue()) }
    val phone = remember { mutableStateOf(TextFieldValue()) }
    val dateOfBirth = remember { mutableStateOf(TextFieldValue()) }
    val profileImage = remember { mutableStateOf(TextFieldValue()) }
    val bio = remember { mutableStateOf(TextFieldValue()) }
    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add User Profile",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
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
        TextField(
            value = profileImage.value,
            onValueChange = { profileImage.value = it },
            placeholder = { Text("Enter Profile Image URL or Path") },
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
                    profileImage = profileImage.value.text,
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