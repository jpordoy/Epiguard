package com.epilabs.epiguard.components.user_component


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.UserProfileModel
import java.net.URLEncoder



@Composable
fun ViewUserProfiles(navController: NavController) {
    val context = LocalContext.current
    val dbHandler = DatabaseConnector(context)
    val userList: List<UserProfileModel> = dbHandler.getAllUserProfiles()


    LazyColumn {
        itemsIndexed(userList) { index, user ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp),
                onClick = {
                    val profileImage = user.profileImage?.let { URLEncoder.encode(it, "UTF-8") } ?: ""
                    val fullName = user.fullName?.let { URLEncoder.encode(it, "UTF-8") } ?: ""
                    val phone = user.phone?.let { URLEncoder.encode(it, "UTF-8") } ?: ""
                    val dateOfBirth = user.dateOfBirth?.let { URLEncoder.encode(it, "UTF-8") } ?: ""
                    val bio = user.bio?.let { URLEncoder.encode(it, "UTF-8") } ?: ""

                    navController.navigate(
                        "update_user_profile/${user.userId}/${user.profileId}/$profileImage/$fullName/$phone/$dateOfBirth/$bio"
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Full Name: ${user.fullName}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Phone: ${user.phone}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "DOB: ${user.dateOfBirth}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Bio: ${user.bio}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}