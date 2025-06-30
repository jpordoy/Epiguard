package com.epilabs.epiguard.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.components.GenderButton

@Composable
fun MyNewScreen() {
    var isMaleSelected by remember { mutableStateOf(true) }
    var isFemaleSelected by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GenderButton(
            text = "Male",
            isSelected = isMaleSelected,
            iconRes = R.drawable.user45, // Replace with actual male icon resource
            onClick = {
                isMaleSelected = true
                isFemaleSelected = false
            }
        )
        GenderButton(
            text = "Female",
            isSelected = isFemaleSelected,
            iconRes = R.drawable.user45, // Replace with actual female icon resource
            onClick = {
                isMaleSelected = false
                isFemaleSelected = true
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyNewScreenPreview() {
    EpiGuardTheme {
        MyNewScreen()
    }
}