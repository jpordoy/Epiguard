package com.epilabs.epiguard.ui.components.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.AppColors // Adjust to your theme package
import com.epilabs.epiguard.ui.AppTypes // Adjust to your theme package
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isFocused: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        label = {
            Text(
                text = label,
                color = AppColors.color_Gray_600,
                style = AppTypes.type_Typography_Body_Small
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = AppColors.color_black,
            unfocusedTextColor = AppColors.color_black,
            focusedLabelColor = AppColors.color_Gray_600,
            unfocusedLabelColor = AppColors.color_violet, // Matches your primary color
            cursorColor = AppColors.color_violet,
            focusedIndicatorColor = AppColors.color_violet,
            unfocusedIndicatorColor = AppColors.color_Gray_100,
            disabledIndicatorColor = AppColors.color_Gray_100,
            focusedContainerColor = AppColors.color_Gray_50,
            unfocusedContainerColor = AppColors.color_Gray_50
        ),
        textStyle = TextStyle(color = AppColors.color_black, fontSize = AppTypes.type_Typography_Body_Medium.fontSize),
        shape = RoundedCornerShape(5.dp), // Matches ComboBoxCustom
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(35.dp) // Matches PhoneTextField height
    )
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun TextFieldCustomPreview() {
    var text by remember { mutableStateOf("") }
    TextFieldCustom(
        value = text,
        onValueChange = { text = it },
        label = "Name",
        modifier = Modifier.padding(8.dp)
    )
}