package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun ContactAboutForm(modifier: Modifier = Modifier) {
    // State to hold editable values (temporary, no database)
    var status by remember { mutableStateOf("Active") }
    var contactType by remember { mutableStateOf("Phone") }
    var alertType by remember { mutableStateOf("Mobile/SMS") }
    var bio by remember { mutableStateOf("Enter your bio here") }

    // State to control dialog visibility and current field being edited
    var showDialog by remember { mutableStateOf(false) }
    var currentField by remember { mutableStateOf("") }
    var currentValue by remember { mutableStateOf("") }
    var onUpdate: (String) -> Unit by remember { mutableStateOf({}) }

    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.color_Gray_White, RoundedCornerShape(5.dp))
            .padding(16.dp) // Matches Frame23355 padding
    ) {
        // Status Row
        AboutRow(
            iconRes = R.drawable.user45,
            label = "Status",
            value = status,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Status"
                currentValue = status
                onUpdate = { newValue -> status = newValue }
                showDialog = true
            }
        )

        // Contact Type Row
        AboutRow(
            iconRes = R.drawable.bell,
            label = "Contact Type",
            value = contactType,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Contact Type"
                currentValue = contactType
                onUpdate = { newValue -> contactType = newValue }
                showDialog = true
            }
        )

        // Alert Type Row
        AboutRow(
            iconRes = R.drawable.lock,
            label = "Alert Type",
            value = alertType,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Alert Type"
                currentValue = alertType
                onUpdate = { newValue -> alertType = newValue }
                showDialog = true
            }
        )

        // Bio Row (uses AboutRow, edited via dialog)
        AboutRow(
            iconRes = R.drawable.user45,
            label = "Bio",
            value = bio,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Bio"
                currentValue = bio
                onUpdate = { newValue -> bio = newValue }
                showDialog = true
            }
        )
    }

    // Show dialog when triggered
    if (showDialog) {
        EditFieldDialog1(
            label = currentField,
            initialValue = currentValue,
            isMultiLine = currentField == "Bio", // Multi-line for Bio
            onUpdate = { newValue ->
                onUpdate(newValue)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun AboutRow(
    iconRes: Int,
    label: String,
    value: String,
    endIcon: Int? = null,
    onEditClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Left Icon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AppColors.color_Gray_50)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = AppColors.color_violet,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }

        // Label and Value
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                color = Color(0xff181d27),
                lineHeight = 1.5.em,
                style = AppTypes.type_Body_small_400
            )
            Text(
                text = value,
                color = AppColors.color_Gray_600,
                lineHeight = 1.45.em,
                style = AppTypes.type_Body_small_400,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // End Icon
        endIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = "Edit",
                tint = AppColors.color_Gray_600,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onEditClick?.invoke() }
            )
        }
    }
}

@Composable
fun EditFieldDialog1(
    label: String,
    initialValue: String,
    isMultiLine: Boolean = false, // Controls multi-line for Bio
    onUpdate: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var textValue by remember { mutableStateOf(initialValue) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit $label", style = AppTypes.type_Header_Header_2) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = label,
                    color = AppColors.color_Gray_600,
                    style = AppTypes.type_Body_small_400
                )
                BasicTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    textStyle = TextStyle(
                        color = AppColors.color_Gray_900,
                        fontSize = AppTypes.type_Body_small_400.fontSize,
                        lineHeight = 1.45.em
                    ),
                    singleLine = !isMultiLine,
                    maxLines = if (isMultiLine) 4 else 1, // 4 lines for Bio
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isMultiLine) 100.dp else 40.dp) // Fixed height for Bio
                        .background(AppColors.color_Gray_50, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onUpdate(textValue) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.color_Primary_500,
                    contentColor = AppColors.color_white
                )
            ) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.color_Gray_100,
                    contentColor = AppColors.color_Gray_600
                )
            ) {
                Text("Cancel")
            }
        },
        containerColor = AppColors.color_Gray_White,
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ContactAboutFormPreview() {
    ContactAboutForm()
}