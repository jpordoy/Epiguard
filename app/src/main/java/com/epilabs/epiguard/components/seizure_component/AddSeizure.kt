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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.SeizureModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddSeizureForm(
    navController: NavController,
    userID: Int
) {
    val context = LocalContext.current
    val timestamp = remember {
        mutableStateOf(
            TextFieldValue(
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            )
        )
    }
    val seizureType = remember { mutableStateOf(TextFieldValue()) }
    val duration = remember { mutableStateOf(TextFieldValue()) }
    val description = remember { mutableStateOf(TextFieldValue()) }
    val triggers = remember { mutableStateOf(TextFieldValue()) }
    val medicationTaken = remember { mutableStateOf(TextFieldValue()) }
    val postSeizureSymptoms = remember { mutableStateOf(TextFieldValue()) }
    val notes = remember { mutableStateOf(TextFieldValue()) }
    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Seizure",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = timestamp.value,
            onValueChange = { timestamp.value = it },
            placeholder = { Text("Enter Timestamp (e.g., YYYY-MM-DD HH:MM:SS)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = seizureType.value,
            onValueChange = { seizureType.value = it },
            placeholder = { Text("Enter Seizure Type (e.g., Tonic-Clonic)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = duration.value,
            onValueChange = { duration.value = it },
            placeholder = { Text("Enter Duration (seconds)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = { Text("Enter Description") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = false,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = triggers.value,
            onValueChange = { triggers.value = it },
            placeholder = { Text("Enter Triggers (e.g., Stress)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = medicationTaken.value,
            onValueChange = { medicationTaken.value = it },
            placeholder = { Text("Enter Medication Taken (e.g., Levetiracetam 500mg)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = postSeizureSymptoms.value,
            onValueChange = { postSeizureSymptoms.value = it },
            placeholder = { Text("Enter Post-Seizure Symptoms (e.g., Fatigue)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = notes.value,
            onValueChange = { notes.value = it },
            placeholder = { Text("Enter Additional Notes") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = false,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (userID == -1) {
                Toast.makeText(context, "Invalid user ID", Toast.LENGTH_SHORT).show()
                return@Button
            }
            if (timestamp.value.text.isBlank()) {
                Toast.makeText(context, "Timestamp is required", Toast.LENGTH_SHORT).show()
                return@Button
            }
            try {
                val newSeizure = SeizureModel(
                    seizureID = 0, // Ignored during insert due to AUTOINCREMENT
                    userId = userID,
                    timestamp = timestamp.value.text,
                    seizureType = seizureType.value.text.takeIf { it.isNotBlank() },
                    duration = duration.value.text.toIntOrNull(),
                    description = description.value.text.takeIf { it.isNotBlank() },
                    triggers = triggers.value.text.takeIf { it.isNotBlank() },
                    medicationTaken = medicationTaken.value.text.takeIf { it.isNotBlank() },
                    postSeizureSymptoms = postSeizureSymptoms.value.text.takeIf { it.isNotBlank() },
                    notes = notes.value.text.takeIf { it.isNotBlank() }
                )
                val result = dbHandler.insertSeizure(newSeizure)
                if (result > 0) {
                    Toast.makeText(context, "Seizure Added to Database", Toast.LENGTH_SHORT).show()
                    navController.navigate("dashboard/$userID")
                } else {
                    Toast.makeText(context, "Failed to Add Seizure", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Add Seizure to Database", color = Color.White)
        }
    }
}