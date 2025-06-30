package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.ComboBoxCustom
import com.epilabs.epiguard.ui.components.PhoneTextField
import com.epilabs.epiguard.ui.components.TextFieldCustom

@Composable
fun MyProfilePersonalData(modifier: Modifier = Modifier) {
    // State for ComboBox
    var selectedTitle1 by remember { mutableStateOf<String?>(null) } // Updated to use placeholder
    val titleOptions1 = listOf("Mr", "Mrs", "Miss", "Dr", "Other")
    // State for text fields
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var medicalInfo by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppColors.color_pale_grey)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = AppColors.color_white)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "My Personal Data",
                        color = AppColors.color_black,
                        lineHeight = 10.em,
                        style = AppTypes.type_Typography_Body_Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Details about my personal data",
                        color = AppColors.color_grey,
                        lineHeight = 11.67.em,
                        style = AppTypes.type_Typography_Body_Small,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 159.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 100.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.guy_3),
                                contentDescription = "profile image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                                    .clip(shape = RoundedCornerShape(12.dp))
                                    .border(
                                        border = BorderStroke(2.dp, AppColors.color_white),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Upload Photo",
                                    color = AppColors.color_Gray_600,
                                    style = AppTypes.type_Typography_Body_Small,
                                    modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
                                )
                            }
                            Text(
                                text = "Format should be in .jpeg .png at least 800x800px and less than 5MB",
                                color = AppColors.color_grey,
                                textAlign = TextAlign.Center,
                                lineHeight = 14.em,
                                style = AppTypes.type_Typography_Body_Small,
                                modifier = Modifier.requiredWidth(width = 190.dp)
                            )
                        }
                    }
                }

                // ComboBox 1: Title
                ComboBoxCustom(
                    iconResId = R.drawable.ic_person, // Updated to match TextFieldCustom
                    label = "Title",
                    placeholder = "Select Title",
                    options = titleOptions1,
                    selectedOption = selectedTitle1,
                    onOptionSelected = { selectedTitle1 = it },
                    modifier = Modifier.fillMaxWidth()
                )

                // TextField: Name (replacing TextBoxCustom)
                TextFieldCustom(
                    value = name,
                    onValueChange = { name = it },
                    label = "First Name",
                    modifier = Modifier.fillMaxWidth()
                )

                // PhoneTextField
                PhoneTextField(
                    label = "Owner Phone",
                    value = phone,
                    countryCode = "+44",
                    onValueChange = { phone = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = AppColors.color_white)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "About Contact",
                        color = AppColors.color_black,
                        lineHeight = 10.em,
                        style = AppTypes.type_Typography_Body_Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                // Replaced TypeDoubleIconStatePlaceholderLabelTrueHintFalseDestructiveFalse
                TextFieldCustom(
                    value = medicalInfo,
                    onValueChange = { medicalInfo = it },
                    label = "Medical or Epilepsy Information",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            BottomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun TextBoxCustom(
    iconResId: Int,
    name: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = label,
            color = AppColors.color_Gray_600,
            lineHeight = 1.33.em,
            style = AppTypes.type_Typography_Body_Small,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 17.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 44.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = AppColors.color_Gray_50)
                .border(
                    border = BorderStroke(1.dp, AppColors.color_Gray_100),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(all = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "icon",
                tint = AppColors.color_violet,
                modifier = Modifier
                    .requiredSize(size = 20.dp)
                    .offset(x = 3.5.dp)
            )
            Text(
                text = name,
                color = AppColors.color_black,
                lineHeight = 1.43.em,
                style = AppTypes.type_Typography_Body_Medium,
                modifier = Modifier.weight(weight = 1f)
            )
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 95.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColors.color_white)
                .border(
                    border = BorderStroke(1.dp, AppColors.color_light_grey_border)
                )
                .padding(bottom = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(74.04.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            ) {
                Box(
                    modifier = Modifier.requiredSize(size = 32.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 32.dp)
                            .clip(shape = CircleShape)
                            .background(color = AppColors.color_Primary_100)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sms),
                        contentDescription = "left icon",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 6.5.dp, y = 7.dp)
                            .requiredSize(size = 18.dp)
                    )
                }
                Text(
                    text = "Personal Data",
                    color = AppColors.color_black,
                    style = AppTypes.type_Typography_Navbar_Title,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier.requiredSize(size = 32.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 32.dp)
                            .clip(shape = CircleShape)
                            .background(color = AppColors.color_Gray_100)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sms),
                        contentDescription = "right icon",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 8.dp, y = 8.dp)
                            .requiredSize(size = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Handle update action */ },
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.color_violet,
            contentColor = AppColors.color_white
        )
    ) {
        Text(
            text = "Update",
            style = AppTypes.type_Typography_Label_Large
        )
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun MyProfilePersonalDataPreview() {
    MyProfilePersonalData()
}