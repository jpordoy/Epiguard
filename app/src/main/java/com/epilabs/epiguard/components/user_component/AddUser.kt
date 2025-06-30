package com.epilabs.epiguard.components.user_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.epilabs.epiguard.R
import com.epilabs.epiguard.components.*
import com.epilabs.epiguard.ui.theme.*
import com.epilabs.epiguard.ui.theme.LightBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserDetailsScreen() {
    var selectedGender by remember { mutableStateOf("Male") }
    var neutered by remember { mutableStateOf(true) }
    var vaccinated by remember { mutableStateOf(false) }
    var friendlyWithDogs by remember { mutableStateOf(true) }
    var friendlyWithCats by remember { mutableStateOf(false) }
    var friendlyWithKidsUnder10 by remember { mutableStateOf(true) }
    var friendlyWithKidsOver10 by remember { mutableStateOf(true) }
    var microchipped by remember { mutableStateOf(true) }
    var purebred by remember { mutableStateOf(true) }
    // New state variables for text fields
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // Header
        HeaderSection()

        // Content
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            item {
                // Pet Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://images.unsplash.com/photo-1552053831-71594a27632d?w=200&h=200&fit=crop&crop=face",
                        contentDescription = "Pet Image",
                        modifier = Modifier
                            .size(112.dp, 115.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                // General Information Section
                Text(
                    text = "General information",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(9.dp))

                // First Name TextField
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = {
                            Text(
                                text = "First Name",
                                color = LightGray,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = PrimaryDark,
                            unfocusedTextColor = PrimaryDark,
                            focusedLabelColor = LightGray,
                            unfocusedLabelColor = PrimaryBlue,
                            cursorColor = PrimaryBlue,
                            focusedIndicatorColor = PrimaryBlue,
                            unfocusedIndicatorColor = BackgroundGray,
                            disabledIndicatorColor = BackgroundGray,
                            unfocusedContainerColor = LightBackground,
                            focusedContainerColor = LightBackground
                        ),
                        textStyle = TextStyle(color = PrimaryDark),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Last Name TextField
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = {
                            Text(
                                text = "Last Name",
                                color = LightGray,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = PrimaryDark,
                            unfocusedTextColor = PrimaryDark,
                            focusedLabelColor = LightGray,
                            unfocusedLabelColor = PrimaryBlue,
                            cursorColor = PrimaryBlue,
                            focusedIndicatorColor = PrimaryBlue,
                            unfocusedIndicatorColor = BackgroundGray,
                            disabledIndicatorColor = BackgroundGray,
                            unfocusedContainerColor = LightBackground,
                            focusedContainerColor = LightBackground
                        ),
                        textStyle = TextStyle(color = PrimaryDark),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Email TextField
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(
                                text = "Email",
                                color = LightGray,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = PrimaryDark,
                            unfocusedTextColor = PrimaryDark,
                            focusedLabelColor = LightGray,
                            unfocusedLabelColor = PrimaryBlue,
                            cursorColor = PrimaryBlue,
                            focusedIndicatorColor = PrimaryBlue,
                            unfocusedIndicatorColor = BackgroundGray,
                            disabledIndicatorColor = BackgroundGray,
                            unfocusedContainerColor = LightBackground,
                            focusedContainerColor = LightBackground
                        ),
                        textStyle = TextStyle(color = PrimaryDark),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Contact TextField
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    OutlinedTextField(
                        value = contact,
                        onValueChange = { contact = it },
                        label = {
                            Text(
                                text = "Contact",
                                color = LightGray,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = PrimaryDark,
                            unfocusedTextColor = PrimaryDark,
                            focusedLabelColor = LightGray,
                            unfocusedLabelColor = PrimaryBlue,
                            cursorColor = PrimaryBlue,
                            focusedIndicatorColor = PrimaryBlue,
                            unfocusedIndicatorColor = BackgroundGray,
                            disabledIndicatorColor = BackgroundGray,
                            unfocusedContainerColor = LightBackground,
                            focusedContainerColor = LightBackground
                        ),
                        textStyle = TextStyle(color = PrimaryDark),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Gender
                Text(
                    text = "Gender",
                    style = MaterialTheme.typography.labelMedium,
                    color = LightGray
                )

                Spacer(modifier = Modifier.height(13.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    GenderButton(
                        text = "Male",
                        isSelected = selectedGender == "Male",
                        iconRes = R.drawable.user45,
                        onClick = { selectedGender = "Male" }
                    )

                    GenderButton(
                        text = "Female",
                        isSelected = selectedGender == "Female",
                        iconRes = R.drawable.user45,
                        onClick = { selectedGender = "Female" }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Date of birth
                DropdownField(
                    label = "Date of birth",
                    value = "Feb 25, 2018"
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                // Additional Information Section
                Text(
                    text = "Additional Information",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Toggle switches
                ToggleSwitch(
                    label = "Neutered",
                    checked = neutered,
                    onCheckedChange = { neutered = it }
                )

                Spacer(modifier = Modifier.height(23.dp))

                ToggleSwitch(
                    label = "Vaccinated",
                    checked = vaccinated,
                    onCheckedChange = { vaccinated = it }
                )

                Spacer(modifier = Modifier.height(23.dp))

                ToggleSwitch(
                    label = "Friendly with dogs",
                    checked = friendlyWithDogs,
                    onCheckedChange = { friendlyWithDogs = it }
                )

                Spacer(modifier = Modifier.height(23.dp))

                ToggleSwitch(
                    label = "Friendly with cats",
                    checked = friendlyWithCats,
                    onCheckedChange = { friendlyWithCats = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                ToggleSwitch(
                    label = "Friendly with kids <10 year",
                    checked = friendlyWithKidsUnder10,
                    onCheckedChange = { friendlyWithKidsUnder10 = it }
                )

                Spacer(modifier = Modifier.height(23.dp))

                ToggleSwitch(
                    label = "Friendly with kids >10 year",
                    checked = friendlyWithKidsOver10,
                    onCheckedChange = { friendlyWithKidsOver10 = it }
                )

                Spacer(modifier = Modifier.height(23.dp))

                ToggleSwitch(
                    label = "Microchipped",
                    checked = microchipped,
                    onCheckedChange = { microchipped = it }
                )

                Spacer(modifier = Modifier.height(23.dp))

                ToggleSwitch(
                    label = "Purebred",
                    checked = purebred,
                    onCheckedChange = { purebred = it }
                )

                Spacer(modifier = Modifier.height(49.dp))

                // Pet's nursery name
                FormField(
                    label = "Pet's nursery name (optional)",
                    value = "",
                    showDivider = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Reminders section
                Text(
                    text = "Reminders",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Add vaccines, haircuts, pills, estrus, etc. and you will receive notifications for the next event.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(31.dp))

                // Reminder cards
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        AddReminderCard()
                    }
                    item {
                        ReminderCard(
                            title = "Measles vaccine",
                            date = "30.08.2018 г"
                        )
                    }
                    item {
                        ReminderCard(
                            title = "Rabies vaccine",
                            date = "30.08.2018 г"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Next button
                Button(
                    onClick = { /* Handle next action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Next",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Bottom navigation
        BottomNavigationSection()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRow() {
    EpiGuardTheme { // Use EpiGuardTheme for consistent styling
        AddUserDetailsScreen()
    }
}