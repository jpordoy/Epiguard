package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.epilabs.epiguard.R
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.viewmodel.ContactViewModel
import java.net.URLEncoder

@Composable
fun ViewContacts(
    navController: NavController,
    userID: Int,
    contactViewModel: ContactViewModel
) {
    val context = LocalContext.current
    val dbHandler = DatabaseConnector(context)
    val contacts = dbHandler.getAllContacts(userID)

    LazyColumn {
        itemsIndexed(contacts) { _, contact ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp),
                onClick = {
                    val profileImage = URLEncoder.encode(contact.profileImage ?: "", "UTF-8")
                    val firstname = URLEncoder.encode(contact.firstname ?: "", "UTF-8")
                    val lastname = URLEncoder.encode(contact.lastname ?: "", "UTF-8")
                    val contactInfo = URLEncoder.encode(contact.contact ?: "", "UTF-8")
                    val alertType = URLEncoder.encode(contact.alertType ?: "", "UTF-8")
                    val medicalExperience = URLEncoder.encode(contact.medicalExperience ?: "", "UTF-8")
                    val status = URLEncoder.encode(contact.status ?: "", "UTF-8")
                    val primaryCarer = URLEncoder.encode(contact.primaryCarer ?: "", "UTF-8")
                    val timestamp = URLEncoder.encode(contact.timestamp ?: "", "UTF-8")
                    navController.navigate(
                        "update_contact/${contact.userID}/${contact.contactID}/$profileImage/$firstname/$lastname/$contactInfo/$alertType/$medicalExperience/$status/$primaryCarer/$timestamp"
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Enhanced AsyncImage with placeholder and error handling
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(contactViewModel.ContactImage.value ?: contact.profileImage ?: R.drawable.default_profile)
                            .crossfade(true)
                            .error(R.drawable.default_profile) // Fallback if loading fails
                            .placeholder(R.drawable.default_profile) // Show while loading
                            .build(),
                        contentDescription = "Contact Profile Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "User ID: ${contact.userID}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Contact ID: ${contact.contactID}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "First Name: ${contact.firstname ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Last Name: ${contact.lastname ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Contact Info: ${contact.contact ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Alert Type: ${contact.alertType ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Medical Experience: ${contact.medicalExperience ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Status: ${contact.status ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Primary Carer: ${contact.primaryCarer ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Timestamp: ${contact.timestamp ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
        if (contacts.isEmpty()) {
            item {
                Text(
                    text = "No contacts found for user ID $userID",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }
        }
    }
}