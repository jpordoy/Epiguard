package com.epilabs.epiguard.components.seizure_component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import java.net.URLEncoder

@Composable
fun ViewSeizures(
    navController: NavController,
    userID: Int
) {
    val context = LocalContext.current
    val dbHandler = DatabaseConnector(context)
    val seizures = dbHandler.getAllSeizures(userID)

    LazyColumn {
        itemsIndexed(seizures) { _, seizure ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp),
                onClick = {
                    val seizureType = URLEncoder.encode(seizure.seizureType ?: "", "UTF-8")
                    val duration = seizure.duration?.toString() ?: ""
                    val description = URLEncoder.encode(seizure.description ?: "", "UTF-8")
                    val triggers = URLEncoder.encode(seizure.triggers ?: "", "UTF-8")
                    val medicationTaken = URLEncoder.encode(seizure.medicationTaken ?: "", "UTF-8")
                    val postSeizureSymptoms = URLEncoder.encode(seizure.postSeizureSymptoms ?: "", "UTF-8")
                    val notes = URLEncoder.encode(seizure.notes ?: "", "UTF-8")
                    val timestamp = URLEncoder.encode(seizure.timestamp ?: "", "UTF-8")
                    navController.navigate(
                        "update_seizure/$userID/${seizure.seizureID}/$seizureType/$duration/$description/$triggers/$medicationTaken/$postSeizureSymptoms/$notes/$timestamp"
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Seizure ID: ${seizure.seizureID}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "User ID: ${seizure.userId}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Timestamp: ${seizure.timestamp ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Seizure Type: ${seizure.seizureType ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Duration: ${seizure.duration?.toString() ?: "N/A"} seconds",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Description: ${seizure.description ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Triggers: ${seizure.triggers ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Medication Taken: ${seizure.medicationTaken ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Post-Seizure Symptoms: ${seizure.postSeizureSymptoms ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Notes: ${seizure.notes ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
        if (seizures.isEmpty()) {
            item {
                Text(
                    text = "No seizures found for user ID $userID",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }
        }
    }
}