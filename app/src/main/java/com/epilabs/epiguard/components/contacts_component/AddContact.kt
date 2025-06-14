package com.epilabs.epiguard.components.contacts_component

import android.content.Context
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.ContactModel
import com.epilabs.epiguard.utils.saveImageToInternalStorage
import com.epilabs.epiguard.viewmodel.ContactViewModel
import java.util.*

@Composable
fun AddContactForm(
    navController: NavController,
    userID: Int,
    imageLauncher: (Intent) -> Unit,
    contactViewModel: ContactViewModel
) {
    val context = LocalContext.current
    val firstname = remember { mutableStateOf(TextFieldValue()) }
    val lastname = remember { mutableStateOf(TextFieldValue()) }
    val contact = remember { mutableStateOf(TextFieldValue()) }
    val alertType = remember { mutableStateOf(TextFieldValue()) }
    val medicalExperience = remember { mutableStateOf(TextFieldValue()) }
    val status = remember { mutableStateOf(TextFieldValue()) }
    val primaryCarer = remember { mutableStateOf(TextFieldValue()) }
    val timestamp = remember { mutableStateOf(TextFieldValue()) }
    val dbHandler = DatabaseConnector(context)
    var contactExists by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    // Check if a contact exists for this userId
    LaunchedEffect(userID) {
        val contacts = dbHandler.getAllContacts(userID) // Assumes a method to fetch contacts
        contactExists = contacts.isNotEmpty()
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
                text = "Add Contact",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (contactExists) {
                Text(
                    text = "A contact already exists for this user. Only one contact is allowed.",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { navController.navigate("dashboard/$userID") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true
                ) {
                    Text("Go to Dashboard", color = Color.White)
                }
            } else {
                TextField(
                    value = firstname.value,
                    onValueChange = { firstname.value = it },
                    placeholder = { Text("Enter First Name") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = lastname.value,
                    onValueChange = { lastname.value = it },
                    placeholder = { Text("Enter Last Name") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = contact.value,
                    onValueChange = { contact.value = it },
                    placeholder = { Text("Enter Contact Info (e.g., Phone or Email)") },
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
                if (contactViewModel.ContactImage.value != null) {
                    Text("Image selected: ${contactViewModel.ContactImage.value}", color = Color.Black)
                }
                TextField(
                    value = if (contactViewModel.ContactImage.value == null) TextFieldValue() else TextFieldValue(contactViewModel.ContactImage.value!!),
                    onValueChange = { contactViewModel.updateContactImage(it.text) },
                    placeholder = { Text("Or enter Image URL") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = alertType.value,
                    onValueChange = { alertType.value = it },
                    placeholder = { Text("Enter Alert Type") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = medicalExperience.value,
                    onValueChange = { medicalExperience.value = it },
                    placeholder = { Text("Enter Medical Experience") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = false,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = status.value,
                    onValueChange = { status.value = it },
                    placeholder = { Text("Enter Status") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = primaryCarer.value,
                    onValueChange = { primaryCarer.value = it },
                    placeholder = { Text("Enter Primary Carer") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = timestamp.value,
                    onValueChange = { timestamp.value = it },
                    placeholder = { Text("Enter Timestamp (e.g., YYYY-MM-DD HH:MM:SS)") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    try {
                        val newContact = ContactModel(
                            contactID = 0, // Ignored during insert due to AUTOINCREMENT
                            userID = userID,
                            firstname = firstname.value.text,
                            lastname = lastname.value.text,
                            contact = contact.value.text,
                            profileImage = contactViewModel.ContactImage.value,
                            alertType = alertType.value.text,
                            medicalExperience = medicalExperience.value.text,
                            status = status.value.text,
                            primaryCarer = primaryCarer.value.text,
                            timestamp = timestamp.value.text
                        )
                        dbHandler.insertContact(newContact) // Assumes insertContact method exists
                        Toast.makeText(context, "Contact Added to Database", Toast.LENGTH_SHORT).show()
                        navController.navigate("dashboard/$userID")
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }) {
                    Text("Add Contact to Database", color = Color.White)
                }
            }
        }
    }
}