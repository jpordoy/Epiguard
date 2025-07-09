package com.epilabs.epiguard.ui.components.examples

import com.epilabs.epiguard.ui.components.CardRemindAdd
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.components.CardRemindActive
import com.epilabs.epiguard.ui.theme.EpiGuardTheme

@Composable
fun EventListScreen() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardRemindAdd(
            text = "Add Camera",
            iconRes = R.drawable.sms, // Replace with actual icon
            onClick = { /* Handle vaccine event */ }
        )
    }
}

@Composable
fun EventReminderScreen() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardRemindActive(
            title = "Measles Vaccine",
            date = "30.08.2025",
            iconRes = R.drawable.sms,
            onClick = { /* Navigate to event details */ }
        )
        CardRemindActive(
            title = "Rabies Vaccine",
            date = "15.09.2025",
            iconRes = R.drawable.sms,
            onClick = { /* Navigate to event details */ }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EventListScreenPreview() {
    EpiGuardTheme {
        EventReminderScreen()
        EventListScreen()

    }
}