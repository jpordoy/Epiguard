package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.components.GenderButton
import com.epilabs.epiguard.ui.theme.EpiGuardTheme

@Composable
fun PillButtonSelectionComponent(
    modifier: Modifier = Modifier,
    firstOption: String = "Male",
    secondOption: String = "Female",
    firstIconRes: Int = R.drawable.user45,
    secondIconRes: Int = R.drawable.user45,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GenderButton(
            text = firstOption,
            isSelected = selectedOption == firstOption,
            iconRes = firstIconRes,
            onClick = { onOptionSelected(firstOption) },
        )
        GenderButton(
            text = secondOption,
            isSelected = selectedOption == secondOption,
            iconRes = secondIconRes,
            onClick = { onOptionSelected(secondOption) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PillButtonSelectionComponentPreview() {
    EpiGuardTheme {
        PillButtonSelectionComponent(
            firstOption = "Male",
            secondOption = "Female",
            firstIconRes = R.drawable.user45,
            secondIconRes = R.drawable.user45,
            selectedOption = "Male",
            onOptionSelected = {}
        )
    }
}