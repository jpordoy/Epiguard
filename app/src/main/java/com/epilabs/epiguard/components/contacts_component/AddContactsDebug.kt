package com.epilabs.epiguard.components.contacts_component

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.epilabs.epiguard.R
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.ContactModel
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.components.NavBar88Pt
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.theme.TextboxColors
import com.epilabs.epiguard.ui.theme.TextboxTypes
import com.epilabs.epiguard.viewmodel.ContactViewModel

/**
 * AddContactDebugForm – now with a tappable circular profile image:
 *  • Shows placeholder `guy_3.png` on load.
 *  • On tap, launches image picker (same Intent you already used).
 *  • Selected image replaces the placeholder (URI handled via ViewModel).
 *  • Image URL is stored but no longer shown as a TextField.
 */
@Composable
fun AddContactDebugForm(
    navController: NavController,
    userID: Int,
    imageLauncher: (Intent) -> Unit,
    contactViewModel: ContactViewModel
) {
    val context = LocalContext.current

    /*──────── State ───────*/
    val firstname = remember { mutableStateOf(TextFieldValue()) }
    val lastname = remember { mutableStateOf(TextFieldValue()) }
    val contact = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val alertType = remember { mutableStateOf(TextFieldValue()) }
    val about = remember { mutableStateOf(TextFieldValue()) }
    val epilepsyFirstAid = remember { mutableStateOf(TextFieldValue()) }
    val cPR = remember { mutableStateOf(TextFieldValue()) }
    val mentalHealthFirstAid = remember { mutableStateOf(TextFieldValue()) }
    val status = remember { mutableStateOf(TextFieldValue()) }
    val primaryCarer = remember { mutableStateOf(TextFieldValue()) }
    val relationship = remember { mutableStateOf(TextFieldValue()) }
    val timestamp = remember { mutableStateOf(TextFieldValue()) }

    val isLoading = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val dbHandler = DatabaseConnector(context)
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            NavBar88Pt(title = "Contacts", onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            NavBar(selectedItem = "Contacts") { destination ->
                when (destination) {
                    "Home" -> navController.navigate("dashboard/$userID")
                    "Detector" -> navController.navigate("detector/$userID")
                    "Contacts" -> {}
                    "Menu" -> navController.navigate("menu/$userID")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .background(AppColors.color_light_grey_border)
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(30.dp)
            ) {
                Text(
                    text = "Add New Contact",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.color_Gray_900,
                    style = AppTypes.type_Body_Regular_400,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    val painter = contactViewModel.ContactImage.value?.let {
                        rememberAsyncImagePainter(it)
                    } ?: painterResource(id = R.drawable.guy_3)

                    Image(
                        painter = painter,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(3.dp, AppColors.color_Gray_50, CircleShape)
                            .clickable {
                                imageLauncher(Intent(Intent.ACTION_PICK).apply { type = "image/*" })
                            }
                    )

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-8).dp, y = (-8).dp)
                            .zIndex(1f)
                            .clip(CircleShape)
                            .background(AppColors.color_violet)
                            .border(2.dp, AppColors.color_Gray_White, CircleShape)
                            .clickable {
                                imageLauncher(Intent(Intent.ACTION_PICK).apply { type = "image/*" })
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "Add Photo",
                            colorFilter = ColorFilter.tint(AppColors.color_white),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tap above to upload a profile image",
                    color = AppColors.color_Gray_900,
                    style = AppTypes.type_Body_Regular_400,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(30.dp)
            ) {
                @Composable
                fun LabeledTextField(
                    label: String,
                    value: TextFieldValue,
                    onValueChange: (TextFieldValue) -> Unit,
                    placeholder: String,
                    singleLine: Boolean = true,
                    maxLines: Int = 1
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 40.dp)
                    ) {
                        OutlinedTextField(
                            value = value,
                            onValueChange = onValueChange,
                            placeholder = { Text(placeholder, fontSize = 15.sp) },
                            label = {
                                Text(
                                    text = label,
                                    color = TextboxColors.gray600,
                                    style = TextboxTypes.bodyLarge400
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = TextboxColors.gray900,
                                unfocusedTextColor = TextboxColors.gray900,
                                focusedLabelColor = TextboxColors.gray600,
                                unfocusedLabelColor = TextboxColors.primary500,
                                cursorColor = TextboxColors.primary500,
                                focusedIndicatorColor = TextboxColors.primary500,
                                unfocusedIndicatorColor = TextboxColors.gray100,
                                disabledIndicatorColor = TextboxColors.gray100,
                                focusedContainerColor = TextboxColors.gray50,
                                unfocusedContainerColor = AppColors.color_Gray_White
                            ),
                            textStyle = TextStyle(fontSize = 15.sp),
                            singleLine = singleLine,
                            maxLines = maxLines,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                }

                // ─── Fields ────────────────────────────────────────────────
                LabeledTextField("First Name", firstname.value, { firstname.value = it }, "Enter First Name")
                LabeledTextField("Last Name", lastname.value, { lastname.value = it }, "Enter Last Name")

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f).height(1.dp), color = Color.Gray)
                    Text("Contact Details", modifier = Modifier.padding(horizontal = 8.dp), style = AppTypes.type_Body_Regular_400, color = AppColors.color_Gray_600)
                    HorizontalDivider(modifier = Modifier.weight(1f).height(1.dp), color = Color.Gray)
                }

                LabeledTextField("Contact No", contact.value, { contact.value = it }, "Enter Contact no")
                LabeledTextField("Email", email.value, { email.value = it }, "Enter email")
                LabeledTextField("Alert Type", alertType.value, { alertType.value = it }, "Enter Alert Type")
                LabeledTextField("Status", status.value, { status.value = it }, "Enter Status")
                LabeledTextField("Primary Carer", primaryCarer.value, { primaryCarer.value = it }, "Enter Primary Carer")

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f).height(1.dp), color = Color.Gray)
                    Text("Epilepsy Related Training", modifier = Modifier.padding(horizontal = 8.dp), style = AppTypes.type_Body_Regular_400, color = AppColors.color_Gray_900)
                    HorizontalDivider(modifier = Modifier.weight(1f).height(1.dp), color = Color.Gray)
                }

                LabeledTextField("Epilepsy First Aid", epilepsyFirstAid.value, { epilepsyFirstAid.value = it }, "Enter Epilepsy First Aid")
                LabeledTextField("CPR", cPR.value, { cPR.value = it }, "Enter CPR")
                LabeledTextField("Mental Health First Aid", mentalHealthFirstAid.value, { mentalHealthFirstAid.value = it }, "Enter Mental Health First Aid")
                LabeledTextField("About", about.value, { about.value = it }, "Enter About", singleLine = false, maxLines = 3)
                LabeledTextField("relationship", relationship.value, { relationship.value = it }, "Enter relationship")
                LabeledTextField("Timestamp", timestamp.value, { timestamp.value = it }, "Enter Timestamp")

                Spacer(Modifier.height(24.dp))

                /*──────── Save Button ───────*/
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppColors.color_Primary_500)
                        .clickable(enabled = !isLoading.value) {
                            scope.launch {
                                isLoading.value = true
                                try {
                                    val model = ContactModel(
                                        contactID = 0,
                                        userID = userID,
                                        firstname = firstname.value.text,
                                        lastname = lastname.value.text,
                                        contact = contact.value.text,
                                        email = email.value.text,
                                        profileImage = contactViewModel.ContactImage.value, // Include the image path
                                        alertType = alertType.value.text,
                                        about = about.value.text,
                                        epilepsyFirstAid = epilepsyFirstAid.value.text,
                                        cPR = cPR.value.text,
                                        mentalHealthFirstAid = mentalHealthFirstAid.value.text,
                                        status = status.value.text,
                                        primaryCarer = primaryCarer.value.text,
                                        relationship = relationship.value.text,
                                        timestamp = timestamp.value.text

                                    )
                                    val success = dbHandler.insertContact(model)
                                    if (success != -1L) {
                                        Toast.makeText(context, "Contact Added!", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
                                    } else {
                                        Toast.makeText(context, "Save failed", Toast.LENGTH_SHORT).show()
                                    }
                                } finally {
                                    isLoading.value = false
                                }
                            }
                        }
                        .padding(horizontal = 24.dp, vertical = 18.dp)
                ) {
                    if (isLoading.value) {
                        CircularProgressIndicator(
                            color = AppColors.color_Gray_100,
                            strokeWidth = 4.dp,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(
                        text = "Add Contact",
                        color = AppColors.color_Gray_100,
                        style = AppTypes.type_Body_Large_500
                    )
                }
            }
        }
    }
}


// ╔══════════════════════╗
// ║ Preview – Design tab ║
// ╚══════════════════════╝

@Preview(showBackground = true, device = "spec:width=411dp,height=900dp,dpi=420")
@Composable
fun AddContactDebugFormPreview() {
    val context = LocalContext.current
    val nav = rememberNavController()

    // Start with placeholder resource URI
    val placeholderUri = "android.resource://${context.packageName}/${R.drawable.guy_3}"

    val dummyVM = remember {
        ContactViewModel().apply { updateContactImage(placeholderUri) }
    }

    AddContactDebugForm(
        navController = nav,
        userID = 0,
        imageLauncher = {},
        contactViewModel = dummyVM
    )
}
