package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.zIndex
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes

// Data model for a contact
data class Contact(
    val name: String,
    val reviews: String,
    val distance: String,
    val emailaddress: String,
    val carer: String,
    val profileImageRes: Int
)

@Composable
fun ViewContact(
    name: String,
    reviews: String,
    distance: String,
    emailaddress: String,
    carer: String,
    profileImageRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(120.dp)
            .clickable(onClick = onClick)
    ) {
        // Card background with all corners curved
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(110.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(16.dp))
                .background(color = AppColors.color_white) // Using Brush for gradient
        ) {
            // Profile Photo
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
                    .requiredSize(60.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MyColors.color_white)
            ) {
                Image(
                    painter = painterResource(id = profileImageRes),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(
                            BorderStroke(2.dp, Brush.linearGradient(listOf(MyColors.color_violet, MyColors.color_yellow))),
                            CircleShape
                        )
                )
            }

            // Text and Stars
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 104.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    color = MyColors.color_Gray_600,
                    style = MyTypes.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = emailaddress,
                    color = MyColors.color_Gray_600,
                    style = MyTypes.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Stars
                    Box(
                        modifier = Modifier
                            .requiredHeight(12.dp)
                            .background(MyColors.color_white)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "Half star",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .requiredWidth(6.dp)
                                .requiredHeight(11.dp)
                        )
                    }
                    Text(
                        text = reviews,
                        color = MyColors.color_grey,
                        style = MyTypes.typography.headlineSmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Location, Price, and Experience
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 8.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Experience
                Row(
                    modifier = Modifier
                        .requiredHeight(20.dp)
                        .clip(CircleShape)
                        .background(MyColors.color_violet)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "wallet",
                        colorFilter = ColorFilter.tint(MyColors.color_yellow),
                        modifier = Modifier.requiredSize(16.dp)
                    )
                    Text(
                        text = carer,
                        color = MyColors.color_grey,
                        lineHeight = 1.45.em,
                        style = MyTypes.typography.bodySmall,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                // Location
                Row(
                    modifier = Modifier
                        .requiredWidth(78.dp)
                        .requiredHeight(20.dp)
                        .clip(CircleShape)
                        .background(MyColors.color_light_grey_2)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.materialsymbolsverifiedrounded),
                        contentDescription = "map pin",
                        colorFilter = ColorFilter.tint(MyColors.color_white),
                        modifier = Modifier.requiredSize(16.dp)
                    )
                    Text(
                        text = distance,
                        color = MyColors.color_violet,
                        style = MyTypes.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

// Composable to display a list of contacts
@Composable
fun ContactList(modifier: Modifier = Modifier) {
    // Sample list of contacts
    val contacts = listOf(
        Contact(
            name = "Jamie Pordoy",
            reviews = "07519273204",
            distance = "Active",
            emailaddress = "jamiepordoy@hotmail.com",
            carer = "Primary Care Contact",
            profileImageRes = R.drawable.guy2
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Outer padding for the list
        verticalArrangement = Arrangement.spacedBy(16.dp) // Increased spacing to 16.dp for visibility
    ) {
        contacts.forEach { contact ->
            ViewContact(
                name = contact.name,
                reviews = contact.reviews,
                distance = contact.distance,
                emailaddress = contact.emailaddress,
                carer = contact.carer,
                profileImageRes = contact.profileImageRes,
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* Handle click */ }
            )
        }

        
    }
}

@Preview(widthDp = 359) // Increased height for multiple items with spacing
@Composable
fun ContactListPreview() {
    ContactList()
}