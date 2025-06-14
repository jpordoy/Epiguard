package com.epilabs.epiguard.components.contacts_component

import android.content.Intent
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
import com.epilabs.epiguard.models.ContactModel
import com.epilabs.epiguard.viewmodel.ContactViewModel

@Composable
fun UpdateContactForm(
    navController: NavController,
    userID: Int,
    contactID: Int,
    profileImage: String?,
    firstname: String?,
    lastname: String?,
    contact: String?,
    alertType: String?,
    medicalExperience: String?,
    status: String?,
    primaryCarer: String?,
    timestamp: String?,
    imageLauncher: (Intent) -> Unit,
    contactViewModel: ContactViewModel
) {
    val context = LocalContext.current
    val firstName = remember { mutableStateOf(firstname ?: "") }
    val lastName = remember { mutableStateOf(lastname ?: "") }
    val contactInfo = remember { mutableStateOf(contact ?: "") }
    val alertTypeState = remember { mutableStateOf(alertType ?: "") }
    val medicalExperienceState = remember { mutableStateOf(medicalExperience ?: "") }
    val statusState = remember { mutableStateOf(status ?: "") }
    val primaryCarerState = remember { mutableStateOf(primaryCarer ?: "") }
    val timestampState = remember { mutableStateOf(timestamp ?: "") }
    val profileImageState = remember { mutableStateOf(profileImage) }

    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Update Contact",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "User ID: $userID",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Contact ID: $contactID",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        TextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            placeholder = { Text("Enter first name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            placeholder = { Text("Enter last name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = contactInfo.value,
            onValueChange = { contactInfo.value = it },
            placeholder = { Text("Enter contact info (e.g., phone or email)") },
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
        if (profileImageState.value != null) {
            Text("Image selected: ${profileImageState.value}", color = Color.Black)
        }
        TextField(
            value = if (profileImageState.value == null) TextFieldValue() else TextFieldValue(profileImageState.value!!),
            onValueChange = { profileImageState.value = it.text },
            placeholder = { Text("Or enter Image URL") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = alertTypeState.value,
            onValueChange = { alertTypeState.value = it },
            placeholder = { Text("Enter alert type") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = medicalExperienceState.value,
            onValueChange = { medicalExperienceState.value = it },
            placeholder = { Text("Enter medical experience") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = false,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = statusState.value,
            onValueChange = { statusState.value = it },
            placeholder = { Text("Enter status") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = primaryCarerState.value,
            onValueChange = { primaryCarerState.value = it },
            placeholder = { Text("Enter primary carer") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = timestampState.value,
            onValueChange = { timestampState.value = it },
            placeholder = { Text("Enter timestamp") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            val updatedContact = ContactModel(
                contactID = contactID,
                userID = userID,
                firstname = firstName.value,
                lastname = lastName.value,
                contact = contactInfo.value,
                profileImage = profileImageState.value ?: "",
                alertType = alertTypeState.value,
                medicalExperience = medicalExperienceState.value,
                status = statusState.value,
                primaryCarer = primaryCarerState.value,
                timestamp = timestampState.value
            )
            val result = dbHandler.updateContact(updatedContact)
            if (result > 0) {
                Toast.makeText(context, "Contact Updated", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard/$userID")
            } else {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Update Contact", color = Color.White)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            dbHandler.deleteContact(contactID)
            Toast.makeText(context, "Contact Deleted", Toast.LENGTH_SHORT).show()
            navController.navigate("dashboard/$userID")
        }) {
            Text("Delete Contact", color = Color.White)
        }
    }
}