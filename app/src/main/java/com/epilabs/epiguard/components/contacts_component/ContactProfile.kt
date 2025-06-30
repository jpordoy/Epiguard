package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.ui.components.DropdownTextField
import com.epilabs.epiguard.ui.components.EmailTextField
import com.epilabs.epiguard.ui.components.PhoneTextField
import com.epilabs.epiguard.ui.components.examples.TextFieldCustom
import com.epilabs.epiguard.ui.theme.TextboxColors
import com.epilabs.epiguard.ui.theme.TextboxTypes

@Composable
fun ContactInfoForm(
    modifier: Modifier = Modifier
) {
    var selectedGender by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Contact Information",
            style = TextboxTypes.bodyLarge400,
            color = TextboxColors.gray900,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        DropdownTextField(
            label = "Select Title",
            items = listOf("Mr", "Mrs", "Miss", "Dr", "Other"),
            selectedItem = selectedGender,
            onItemSelected = { selectedGender = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        TextFieldCustom(
            value = firstname,
            onValueChange = { firstname = it },
            label = "First Name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        TextFieldCustom(
            value = lastname,
            onValueChange = { lastname = it },
            label = "Last Name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        PhoneTextField(
            label = "Owner Phone",
            value = phone,
            countryCode = "+44",
            onValueChange = { phone = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        TextFieldCustom(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        EmailTextField()


        Button(
            onClick = { /* Handle Submit */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Submit")
        }
    }
}



@Preview(widthDp = 390)
@Composable
private fun ContactInfoFormPreview() {
    ContactInfoForm()
}
