package com.epilabs.epiguard.components.seizure_component

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.SeizureModel

@Composable
fun UpdateSeizureForm(
    navController: NavController,
    userID: Int,
    seizureID: Int,
    seizureType: String?,
    duration: String?,
    description: String?,
    triggers: String?,
    medicationTaken: String?,
    postSeizureSymptoms: String?,
    notes: String?,
    timestamp: String?
) {
    val context = LocalContext.current
    val seizureTypeState = remember { mutableStateOf(seizureType ?: "") }
    val durationState = remember { mutableStateOf(duration ?: "") }
    val descriptionState = remember { mutableStateOf(description ?: "") }
    val triggersState = remember { mutableStateOf(triggers ?: "") }
    val medicationTakenState = remember { mutableStateOf(medicationTaken ?: "") }
    val postSeizureSymptomsState = remember { mutableStateOf(postSeizureSymptoms ?: "") }
    val notesState = remember { mutableStateOf(notes ?: "") }
    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Update Seizure",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "User ID: $userID",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Seizure ID: $seizureID",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Timestamp: ${timestamp ?: "N/A"}",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = seizureTypeState.value,
            onValueChange = { seizureTypeState.value = it },
            placeholder = { Text("Enter seizure type (e.g., Tonic-Clonic)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = durationState.value,
            onValueChange = { durationState.value = it },
            placeholder = { Text("Enter duration (seconds)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it },
            placeholder = { Text("Enter description") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = false,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = triggersState.value,
            onValueChange = { triggersState.value = it },
            placeholder = { Text("Enter triggers (e.g., Stress)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = medicationTakenState.value,
            onValueChange = { medicationTakenState.value = it },
            placeholder = { Text("Enter medication taken (e.g., Levetiracetam 500mg)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = postSeizureSymptomsState.value,
            onValueChange = { postSeizureSymptomsState.value = it },
            placeholder = { Text("Enter post-seizure symptoms (e.g., Fatigue)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = notesState.value,
            onValueChange = { notesState.value = it },
            placeholder = { Text("Enter additional notes") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = false,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            try {
                val updatedSeizure = SeizureModel(
                    seizureID = seizureID,
                    userId = userID,
                    timestamp = timestamp ?: "", // Keep original timestamp
                    seizureType = seizureTypeState.value.takeIf { it.isNotBlank() },
                    duration = durationState.value.toIntOrNull(),
                    description = descriptionState.value.takeIf { it.isNotBlank() },
                    triggers = triggersState.value.takeIf { it.isNotBlank() },
                    medicationTaken = medicationTakenState.value.takeIf { it.isNotBlank() },
                    postSeizureSymptoms = postSeizureSymptomsState.value.takeIf { it.isNotBlank() },
                    notes = notesState.value.takeIf { it.isNotBlank() }
                )
                val result = dbHandler.updateSeizure(updatedSeizure)
                if (result > 0) {
                    Toast.makeText(context, "Seizure Updated", Toast.LENGTH_SHORT).show()
                    navController.navigate("dashboard/$userID")
                } else {
                    Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Update Seizure", color = Color.White)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            val result = dbHandler.deleteSeizure(seizureID)
            if (result > 0) {
                Toast.makeText(context, "Seizure Deleted", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard/$userID")
            } else {
                Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Delete Seizure", color = Color.White)
        }
    }
}