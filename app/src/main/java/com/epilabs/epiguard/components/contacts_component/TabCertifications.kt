package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun CertificationsList(modifier: Modifier = Modifier) {
    // State to track toggle status for each certification (temporary, no database)
    val certificationStates = remember {
        mutableStateMapOf(
            "Medical Training" to false,
            "Epilepsy Training" to false,
            "CPR Certification" to false,
            "First Aid Certification" to false,
            "Seizure First Aid Certification" to false,
            "Neurological Disorder Training" to false,
            "Mental Health First Aid" to false
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.color_Gray_White, RoundedCornerShape(5.dp))
            .padding(16.dp) // Matches Frame23355 padding
    ) {
        // Certification Rows
        CertificationRow(
            label = "Medical Training",
            value = "Toggle to certify",
            isChecked = certificationStates["Medical Training"] == true,
            onCheckedChange = { certificationStates["Medical Training"] = it }
        )
        CertificationRow(
            label = "Epilepsy Training",
            value = "Toggle to certify",
            isChecked = certificationStates["Epilepsy Training"] == true,
            onCheckedChange = { certificationStates["Epilepsy Training"] = it }
        )
        CertificationRow(
            label = "CPR Certification",
            value = "Toggle to certify",
            isChecked = certificationStates["CPR Certification"] == true,
            onCheckedChange = { certificationStates["CPR Certification"] = it }
        )
        CertificationRow(
            label = "First Aid Certification",
            value = "Toggle to certify",
            isChecked = certificationStates["First Aid Certification"] == true,
            onCheckedChange = { certificationStates["First Aid Certification"] = it }
        )
        CertificationRow(
            label = "Seizure First Aid Certification",
            value = "Toggle to certify",
            isChecked = certificationStates["Seizure First Aid Certification"] == true,
            onCheckedChange = { certificationStates["Seizure First Aid Certification"] = it }
        )
        CertificationRow(
            label = "Neurological Disorder Training",
            value = "Toggle to certify",
            isChecked = certificationStates["Neurological Disorder Training"] == true,
            onCheckedChange = { certificationStates["Neurological Disorder Training"] = it }
        )
        CertificationRow(
            label = "Mental Health First Aid",
            value = "Toggle to certify",
            isChecked = certificationStates["Mental Health First Aid"] == true,
            onCheckedChange = { certificationStates["Mental Health First Aid"] = it }
        )
    }
}

@Composable
fun CertificationRow(
    label: String,
    value: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Left Icon (matches ContactRow)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AppColors.color_Gray_50)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user45), // Reusing user45 icon for consistency
                contentDescription = null,
                tint = AppColors.color_violet,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }

        // Label and Value (matches ContactRow)
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

        // Switch Button (no border)
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AppColors.color_violet,
                checkedTrackColor = AppColors.color_Gray_50,
                uncheckedThumbColor = AppColors.color_violet,
                uncheckedTrackColor = AppColors.color_Gray_50,
                uncheckedBorderColor = Color.Transparent // Remove border
            ),
            modifier = Modifier
                .size(width = 51.dp, height = 30.dp) // Matches toggle size in Frame23355
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CertificationsListPreview() {
    CertificationsList()
}