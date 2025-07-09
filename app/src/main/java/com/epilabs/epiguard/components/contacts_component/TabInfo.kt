package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun Frame23355(modifier: Modifier = Modifier) {
    // State to hold editable values (temporary, since no database)
    var name by remember { mutableStateOf("Jamie Pordoy") }
    var contactNo by remember { mutableStateOf("07519273204") }
    var username by remember { mutableStateOf("JamiePordoy186") }
    var carerStatus by remember { mutableStateOf("Primary Carer") }
    var alertType by remember { mutableStateOf("Mobile/SMS") }

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
            .padding(16.dp)
    ) {
        // Name Row
        ContactRow(
            iconRes = R.drawable.user45,
            label = "Name",
            value = name,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Name"
                currentValue = name
                onUpdate = { newValue -> name = newValue }
                showDialog = true
            }
        )

        // Contact No Row
        ContactRow(
            iconRes = R.drawable.bell,
            label = "Contact No",
            value = contactNo,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Contact No"
                currentValue = contactNo
                onUpdate = { newValue -> contactNo = newValue }
                showDialog = true
            }
        )


        // Username Row
        ContactRow(
            iconRes = R.drawable.lock,
            label = "Carer Status",
            value = carerStatus,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "carerStatus"
                currentValue = carerStatus
                onUpdate = { newValue -> carerStatus = newValue }
                showDialog = true
            }
        )

        // Username Row
        ContactRow(
            iconRes = R.drawable.lock,
            label = "Username",
            value = username,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "Username"
                currentValue = username
                onUpdate = { newValue -> username = newValue }
                showDialog = true
            }
        )

        // Username Row
        ContactRow(
            iconRes = R.drawable.lock,
            label = "Alert Type",
            value = alertType,
            endIcon = R.drawable.keyboard_arrow_down,
            onEditClick = {
                currentField = "alertType"
                currentValue = alertType
                onUpdate = { newValue -> alertType = newValue }
                showDialog = true
            }
        )
    }

    // Show dialog when triggered
    if (showDialog) {
        EditFieldDialog(
            label = currentField,
            initialValue = currentValue,
            onUpdate = { newValue ->
                onUpdate(newValue)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun ContactRow(
    iconRes: Int,
    label: String,
    value: String,
    endIcon: Int? = null,
    endToggle: Boolean = false,
    onEditClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
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

        // Optional End Icon or Toggle
        endIcon?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "Edit",
                colorFilter = ColorFilter.tint(AppColors.color_Gray_600),
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onEditClick?.invoke() }
            )
        }

        if (endToggle) {
            Box(
                modifier = Modifier
                    .size(width = 51.dp, height = 30.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(AppColors.color_Gray_50)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 5.dp)
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(AppColors.color_Gray_600)
                )
            }
        }
    }
}

@Composable
fun EditFieldDialog(
    label: String,
    initialValue: String,
    onUpdate: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit $label", style = AppTypes.type_Header_Header_2) },
        text = {
            var textValue by remember { mutableStateOf(initialValue) }
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppColors.color_Gray_50, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {},
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
fun Frame23355Preview() {
    Frame23355()
}