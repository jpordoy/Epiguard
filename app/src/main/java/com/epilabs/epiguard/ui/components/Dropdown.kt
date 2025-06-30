package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.TextboxColors
import com.epilabs.epiguard.ui.theme.TextboxTypes

@Composable
fun DropdownTextField(
    modifier: Modifier = Modifier,
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val dropdownIcon = if (expanded) R.drawable.keyboard_arrow_up else R.drawable.keyboard_arrow_down

    val horizontalPadding = 15.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = label,
                    color = TextboxColors.gray600,
                    style = TextboxTypes.bodyLarge400
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = dropdownIcon),
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier
                        .height(24.dp)
                        .clickable { expanded = !expanded }
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
                unfocusedContainerColor = TextboxColors.gray50
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown with horizontal padding
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding)
                .background(TextboxColors.gray50)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            color = TextboxColors.gray900,
                            style = TextboxTypes.bodyLarge400
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
