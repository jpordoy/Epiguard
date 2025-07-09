package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.epilabs.epiguard.R
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.viewmodel.ContactViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.layout.ContentScale
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

    Scaffold(
        topBar = {
            TopBar(onBackClick = { navController.popBackStack() })
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                item {
                    // Banner with profile image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(R.drawable.rectangle)
                                .crossfade(true)
                                .error(R.drawable.default_profile)
                                .placeholder(R.drawable.default_profile)
                                .build(),
                            contentDescription = "Banner Background",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                itemsIndexed(contacts) { _, contact ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F6F9)),
                        elevation = CardDefaults.cardElevation(0.dp),
                        onClick = {
                            val profileImage = URLEncoder.encode(contact.profileImage ?: "", "UTF-8")
                            val firstname = URLEncoder.encode(contact.firstname ?: "", "UTF-8")
                            val lastname = URLEncoder.encode(contact.lastname ?: "", "UTF-8")
                            val contactInfo = URLEncoder.encode(contact.contact ?: "", "UTF-8")
                            val email = URLEncoder.encode(contact.email ?: "", "UTF-8")
                            val alertType = URLEncoder.encode(contact.alertType ?: "", "UTF-8")
                            val about = URLEncoder.encode(contact.about ?: "", "UTF-8")
                            val epilepsyFirstAid = URLEncoder.encode(contact.epilepsyFirstAid ?: "", "UTF-8")
                            val cPR = URLEncoder.encode(contact.cPR ?: "", "UTF-8")
                            val mentalHealthFirstAid = URLEncoder.encode(contact.mentalHealthFirstAid ?: "", "UTF-8")
                            val status = URLEncoder.encode(contact.status ?: "", "UTF-8")
                            val primaryCarer = URLEncoder.encode(contact.primaryCarer ?: "", "UTF-8")
                            val timestamp = URLEncoder.encode(contact.timestamp ?: "", "UTF-8")
                            navController.navigate(
                                "update_contact/${contact.userID}/${contact.contactID}/$profileImage/$firstname/$lastname/$contactInfo/$alertType/$about/$epilepsyFirstAid/$cPR/$mentalHealthFirstAid/$status/$primaryCarer/$timestamp"
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Profile image centered near bottom of banner
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(contactViewModel.ContactImage.value ?: contact.profileImage ?: R.drawable.default_profile)
                                    .crossfade(true)
                                    .error(R.drawable.default_profile)
                                    .placeholder(R.drawable.default_profile)
                                    .build(),
                                contentDescription = "Contact Profile Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .border(2.dp, Color.White, RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "${contact.firstname ?: "N/A"} ${contact.lastname ?: "N/A"}",
                                color = Color(0xFF101828),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = (-0.5).sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Contact Info: ${contact.contact ?: "N/A"}",
                                color = Color(0xFF7A5AF8),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.5).sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Email: ${contact.email ?: "N/A"}",
                                color = Color(0xFF7A5AF8),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.5).sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            ContactDetailRow(label = "User ID", value = contact.userID.toString())
                            ContactDetailRow(label = "Contact ID", value = contact.contactID.toString())
                            ContactDetailRow(label = "Email", value = contact.email.toString())
                            ContactDetailRow(label = "Alert Type", value = contact.alertType ?: "N/A")
                            ContactDetailRow(label = "About", value = contact.about ?: "N/A")
                            ContactDetailRow(label = "epilepsyFirstAid", value = contact.epilepsyFirstAid ?: "N/A")
                            ContactDetailRow(label = "cPR", value = contact.cPR ?: "N/A")
                            ContactDetailRow(label = "mentalHealthFirstAid", value = contact.mentalHealthFirstAid ?: "N/A")
                            ContactDetailRow(label = "Status", value = contact.status ?: "N/A")
                            ContactDetailRow(label = "Primary Carer", value = contact.primaryCarer ?: "N/A")
                            ContactDetailRow(label = "Timestamp", value = contact.timestamp ?: "N/A")
                        }
                    }
                }
                if (contacts.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No contacts found for user ID $userID",
                                color = Color.Red,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun TopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF6E62FF))
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color.White, CircleShape)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Back",
                tint = Color(0xFF6E62FF),
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = "My Contacts",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.5).sp
        )
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
private fun ContactDetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label: ",
            color = Color(0xFF344054),
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            color = Color(0xFF4F5464),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
    }
}