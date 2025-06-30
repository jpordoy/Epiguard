package com.epilabs.epiguard.ui.components.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.components.DateButton
import com.epilabs.epiguard.ui.components.EditButton
import com.epilabs.epiguard.ui.components.GenderButton
import com.epilabs.epiguard.ui.components.LargeOutlineButton
import com.epilabs.epiguard.ui.components.LargeSolidButton
import com.epilabs.epiguard.ui.components.ReviewButton
import com.epilabs.epiguard.ui.components.SegmentedControlButton
import com.epilabs.epiguard.ui.components.SmallFilterButton
import com.epilabs.epiguard.ui.theme.EpiGuardTheme

@Composable
fun ButtonTestScreen() {
    var isMaleActive by remember { mutableStateOf(false) }
    var isUpcomingActive by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LargeSolidButton(
            text = "Update Profile",
            onClick = { /* Navigate to update */ },
            modifier = Modifier.fillMaxWidth()
        )
        LargeOutlineButton(
            text = "Create Account",
            onClick = { /* Create account */ },
            modifier = Modifier.fillMaxWidth()
        )
        SmallFilterButton(
            text = "9 Sep",
            onClick = { /* Filter by date */ },
            icon = R.drawable.sms,
            isActive = false,
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        SmallFilterButton(
            text = "Dentist",
            onClick = { /* Filter by specialty */ },
            isActive = true,
            modifier = Modifier.fillMaxWidth(0.5f)
        )

        ReviewButton(
            text = "Write a Review",
            onClick = { /* Write review */ },
            icon = R.drawable.sms,
            modifier = Modifier.fillMaxWidth()
        )
        SegmentedControlButton(
            activeText = if (isUpcomingActive) "Upcoming" else "Past",
            inactiveText = if (isUpcomingActive) "Past" else "Upcoming",
            onActiveClick = { isUpcomingActive = isUpcomingActive },
            onInactiveClick = { isUpcomingActive = !isUpcomingActive },
            isFirstActive = isUpcomingActive,
            modifier = Modifier.fillMaxWidth()
        )
        EditButton(
            text = "Edit",
            icon = R.drawable.sms,
            onClick = { /* Edit action */ },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GenderButton(
                text = "Male",
                icon = R.drawable.sms,
                onClick = { isMaleActive = true },
                isActive = isMaleActive,
                modifier = Modifier.weight(1f)
            )
            GenderButton(
                text = "Female",
                icon = R.drawable.sms,
                onClick = { isMaleActive = false },
                isActive = !isMaleActive,
                modifier = Modifier.weight(1f)
            )
        }
        DateButton(
            day = "7",
            weekday = "Mon",
            onClick = { /* Select date */ },
            hasBadge = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonTestScreenPreview() {
    EpiGuardTheme {
        ButtonTestScreen()
    }
}