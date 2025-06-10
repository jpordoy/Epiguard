package com.epilabs.epiguard.components.user_component

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
import com.epilabs.epiguard.viewmodel.ProfileViewModel
import java.net.URLEncoder

@Composable
fun ViewUserProfiles(
    navController: NavController,
    userId: Int,
    profileViewModel: ProfileViewModel
) {
    val context = LocalContext.current
    val dbHandler = DatabaseConnector(context)
    val profile = dbHandler.getUserProfileWithUserDetails(userId)

    LazyColumn {
        item {
            profile?.let { userProfile ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp),
                    onClick = {
                        val profileImage = URLEncoder.encode(userProfile.profileImage ?: "", "UTF-8")
                        val fullName = URLEncoder.encode(userProfile.fullName ?: "", "UTF-8")
                        val phone = URLEncoder.encode(userProfile.phone ?: "", "UTF-8")
                        val dateOfBirth = URLEncoder.encode(userProfile.dateOfBirth ?: "", "UTF-8")
                        val bio = URLEncoder.encode(userProfile.bio ?: "", "UTF-8")
                        navController.navigate(
                            "update_user_profile/${userProfile.userId}/${userProfile.profileId}/$profileImage/$fullName/$phone/$dateOfBirth/$bio"
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
                                .data(profileViewModel.profileImage.value ?: userProfile.profileImage ?: R.drawable.default_profile)
                                .crossfade(true)
                                .error(R.drawable.default_profile) // Fallback if loading fails
                                .placeholder(R.drawable.default_profile) // Show while loading
                                .build(),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "User ID: ${userProfile.userId}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Profile ID: ${userProfile.profileId}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Email: ${userProfile.email}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Password: ${"•".repeat(8)}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Full Name: ${userProfile.fullName ?: "N/A"}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Phone: ${userProfile.phone ?: "N/A"}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "DOB: ${userProfile.dateOfBirth ?: "N/A"}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Bio: ${userProfile.bio ?: "N/A"}",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            } ?: Text(
                text = "No profile found for user ID $userId",
                modifier = Modifier.padding(16.dp),
                color = Color.Red
            )
        }
    }
}