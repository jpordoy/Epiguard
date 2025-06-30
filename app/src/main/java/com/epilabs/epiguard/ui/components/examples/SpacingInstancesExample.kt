package com.epilabs.epiguard.ui.components.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.components.CardRemindActive
import com.epilabs.epiguard.ui.components.CardRemindAdd
import com.epilabs.epiguard.ui.components.DropdownTextField
import com.epilabs.epiguard.ui.components.EmailTextField
import com.epilabs.epiguard.ui.components.InputTextField
import com.epilabs.epiguard.ui.components.MobileTextField
import com.epilabs.epiguard.ui.components.PhoneTextField
import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import com.epilabs.epiguard.ui.components.PillButtonSelectionComponent
import com.epilabs.epiguard.ui.components.Spacing

@Composable
fun SpacerFormExample() {
    var selectedGender by remember { mutableStateOf("Male") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var petType by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.medium),
        verticalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        // Row of CardRemindAdd and CardRemindActive
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            CardRemindAdd(
                text = "Add Vaccine",
                iconRes = R.drawable.plus,
                onClick = { /* Navigate to add vaccine form */ },
                modifier = Modifier.weight(1f)
            )
            CardRemindActive(
                title = "Measles Vaccine",
                date = "30.08.2025",
                iconRes = R.drawable.sms,
                onClick = { /* Navigate to event details */ },
                modifier = Modifier.weight(1f)
            )
        }

        // Spacer between groups
        Spacer(Modifier.height(Spacing.medium))

        // Textbox group with 12.dp spacing
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            InputTextField(
                label = "Owner Name",
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth()
            )

            PhoneTextField(
                label = "Owner Phone",
                value = phone,
                countryCode = "+380",
                onValueChange = { phone = it },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownTextField(
                label = "Pet Type",
                value = petType,
                onValueChange = { petType = it },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // GenderSelectionComponent
        PillButtonSelectionComponent(
            firstOption = "Male",
            secondOption = "Female",
            firstIconRes = R.drawable.user45,
            secondIconRes = R.drawable.user45,
            selectedOption = selectedGender,
            onOptionSelected = { gender -> selectedGender = gender },
            modifier = Modifier.fillMaxWidth()
        )

        // Spacer between groups
        Spacer(Modifier.height(Spacing.medium))

        // Email TextField
        EmailTextField(
            label = "Owner Email",
            value = email,
            onValueChange = { email = it },
            isFocused = email.isNotEmpty(),
            errorMessage = if (email.isNotEmpty() && !email.contains("@")) "Invalid email" else null,
            success = email.isNotEmpty() && email.contains("@"),
            modifier = Modifier.fillMaxWidth()
        )

        // Mobile TextField
        MobileTextField(
            label = "Owner Phone",
            value = phone,
            countryCode = "+380",
            onValueChange = { phone = it },
            isFocused = phone.isNotEmpty(),
            errorMessage = if (phone.isNotEmpty() && phone.length < 9) "Invalid phone number" else null,
            success = phone.length >= 9,
            modifier = Modifier.fillMaxWidth()
        )



    }
}

@Preview(showBackground = true)
@Composable
fun SpacerFormExamplePreview() {
    EpiGuardTheme {
        SpacerFormExample()
    }
}