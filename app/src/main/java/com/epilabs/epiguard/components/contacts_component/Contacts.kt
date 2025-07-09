package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.epilabs.epiguard.R
import com.epilabs.epiguard.database.ContactDAO
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.ContactModel
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.NavBar

// Static sample data for preview
val sampleContacts = listOf(
    ContactModel(
        contactID = 1,
        userID = 0,
        firstname = "John",
        lastname = "Doe",
        contact = "123-456-7890",
        email = "john.doe@example.com",
        profileImage = null,
        alertType = "Primary",
        about = "Emergency contact",
        epilepsyFirstAid = "Trained",
        cPR = "Certified",
        mentalHealthFirstAid = "Not trained",
        status = "Active",
        primaryCarer = "Yes",
        timestamp = "2025-07-08",
        relationship = "Friend"
    ),
    ContactModel(
        contactID = 2,
        userID = 0,
        firstname = "Jane",
        lastname = "Smith",
        contact = "987-654-3210",
        email = "jane.smith@example.com",
        profileImage = null,
        alertType = "Secondary",
        about = "Family member",
        epilepsyFirstAid = "Not trained",
        cPR = "Not certified",
        mentalHealthFirstAid = "Trained",
        status = "Active",
        primaryCarer = "No",
        timestamp = "2025-07-08",
        relationship = "Sister"
    ),
    ContactModel(
        contactID = 3,
        userID = 0,
        firstname = "Alice",
        lastname = "Johnson",
        contact = "555-123-4567",
        email = "alice.johnson@example.com",
        profileImage = null,
        alertType = "Primary",
        about = "Close friend",
        epilepsyFirstAid = "Trained",
        cPR = "Certified",
        mentalHealthFirstAid = "Trained",
        status = "Active",
        primaryCarer = "Yes",
        timestamp = "2025-07-08",
        relationship = "Partner"
    )
)

@Composable
fun ContactCard(contact: ContactModel, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White)
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredSize(size = 89.dp)
        ) {
            val painter = contact.profileImage?.let {
                rememberAsyncImagePainter(it)
            } ?: painterResource(id = R.drawable.guy_3)

            Image(
                painter = painter,
                contentDescription = "Profile",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .fillMaxWidth()
                    .requiredSize(size = 89.dp)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .padding(all = 10.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(9.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(weight = 1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "Phone",
                        modifier = Modifier
                            .requiredWidth(width = 14.dp)
                            .requiredHeight(height = 13.dp)
                    )
                    Text(
                        text = "Call",
                        color = Color(0xff8997a9),
                        lineHeight = 8.em,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(weight = 1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = "Email",
                        modifier = Modifier
                            .requiredWidth(width = 14.dp)
                            .requiredHeight(height = 13.dp)
                    )
                    Text(
                        text = "Email",
                        color = Color(0xff8997a9),
                        lineHeight = 8.em,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(color = Color(0xfff4f6f9))
                        .padding(horizontal = 6.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = contact.primaryCarer,
                        color = Color(0xff478ff1),
                        lineHeight = 9.6.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.Top)
            ) {
                Text(
                    text = "${contact.firstname} ${contact.lastname}",
                    color = Color(0xff122d4d),
                    lineHeight = 5.65.em,
                    style = TextStyle(fontSize = 17.sp),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Status",
                        modifier = Modifier
                            .requiredWidth(width = 8.dp)
                            .requiredHeight(height = 10.dp)
                    )
                    Text(
                        text = "Contact Status: ${contact.status}",
                        color = Color(0xff415770),
                        lineHeight = 9.6.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                          .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.users),
                            contentDescription = "Relationship",
                            modifier = Modifier
                                .requiredWidth(width = 13.dp)
                                .requiredHeight(height = 9.dp)
                        )
                        Text(
                            text = "Relationship:",
                            color = Color(0xff122d4d),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                        Text(
                            text = contact.relationship ?: "Unknown",
                            color = Color(0xff0066ff),
                            lineHeight = 8.73.em,
                            style = TextStyle(fontSize = 11.sp),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ViewContactScreen(
    userID: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedItem = remember { mutableStateOf("Contacts") }
    val dbHandler = DatabaseConnector(context)
    val contacts = remember { mutableStateOf<List<ContactModel>>(emptyList()) }

    // Fetch contacts when the composable is first created
    LaunchedEffect(Unit) {
        val db = dbHandler.writableDatabase
        contacts.value = ContactDAO.getAllContacts(db, userID)
        db.close()
    }

    Column(
        modifier = modifier
            .requiredHeight(height = 812.dp)
            .background(AppColors.color_Gray_50)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White.copy(alpha = 0.1f))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 375.dp)
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .requiredSize(size = 20.dp)
                            .clickable { navController.popBackStack() }
                    )
                    Text(
                        text = "View Contacts",
                        color = Color(0xff122d4d),
                        textAlign = TextAlign.Center,
                        lineHeight = 5.33.em,
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Add",
                        colorFilter = ColorFilter.tint(AppColors.color_violet),
                        modifier = Modifier
                            .requiredSize(size = 20.dp)
                            .clickable { navController.navigate("add_contact/$userID") }
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
        ) {
            InputChip(
                label = {
                    Text(
                        text = "All",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = true,
                onClick = { }
            )
            InputChip(
                label = {
                    Text(
                        text = "Primary",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = false,
                onClick = { }
            )
            InputChip(
                label = {
                    Text(
                        text = "Secondary",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = false,
                onClick = { }
            )
            InputChip(
                label = {
                    Text(
                        text = "Status",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.69.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = AppColors.color_violet,
                    selectedContainerColor = AppColors.color_violet
                ),
                selected = false,
                onClick = { }
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
            ) {
                items(contacts.value) { contact ->
                    ContactCard(contact = contact)
                }
            }
        }

        NavBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(83.dp),
            selectedItem = selectedItem.value
        )
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun ViewContactScreenPreview() {
    val contactsState = remember { mutableStateOf(sampleContacts) }
    ViewContactScreen(
        userID = 0,
        navController = rememberNavController(),
        modifier = Modifier
    )
    // Override contacts state for preview
    LaunchedEffect(Unit) {
        contactsState.value = sampleContacts
    }
}