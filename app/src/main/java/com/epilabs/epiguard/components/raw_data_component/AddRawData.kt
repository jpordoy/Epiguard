package com.epilabs.epiguard.components.raw_data_component

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
import com.epilabs.epiguard.models.RawDataModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddRawDataForm(
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
    val seizureID = remember { mutableStateOf(TextFieldValue()) }
    val classificationResult = remember { mutableStateOf(TextFieldValue()) }
    val numberOfClassifiedTimesteps = remember { mutableStateOf(TextFieldValue()) }
    val predictedClass = remember { mutableStateOf(TextFieldValue()) }
    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Raw Data",
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
            value = seizureID.value,
            onValueChange = { seizureID.value = it },
            placeholder = { Text("Enter Seizure ID (optional)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = classificationResult.value,
            onValueChange = { classificationResult.value = it },
            placeholder = { Text("Enter Classification Result (e.g., Seizure)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = numberOfClassifiedTimesteps.value,
            onValueChange = { numberOfClassifiedTimesteps.value = it },
            placeholder = { Text("Enter Number of Classified Timesteps") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = predictedClass.value,
            onValueChange = { predictedClass.value = it },
            placeholder = { Text("Enter Predicted Class (e.g., Tonic-Clonic)") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (userID == -1) {
                Toast.makeText(context, "Invalid user ID", Toast.LENGTH_SHORT).show()
                return@Button
            }
            if (timestamp.value.text.isBlank() || classificationResult.value.text.isBlank() ||
                numberOfClassifiedTimesteps.value.text.isBlank() || predictedClass.value.text.isBlank()
            ) {
                Toast.makeText(context, "Required fields are missing", Toast.LENGTH_SHORT).show()
                return@Button
            }
            try {
                val newRawData = RawDataModel(
                    rawDataId = 0, // Ignored during insert due to AUTOINCREMENT
                    userId = userID,
                    seizureID = seizureID.value.text.toIntOrNull(),
                    timestamp = timestamp.value.text,
                    classificationResult = classificationResult.value.text,
                    numberOfClassifiedTimesteps = numberOfClassifiedTimesteps.value.text.toInt(),
                    predictedClass = predictedClass.value.text
                )
                val result = dbHandler.insertRawData(newRawData)
                if (result > 0) {
                    Toast.makeText(context, "Raw Data Added to Database", Toast.LENGTH_SHORT).show()
                    navController.navigate("dashboard/$userID")
                } else {
                    Toast.makeText(context, "Failed to Add Raw Data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Add Raw Data to Database", color = Color.White)
        }
    }
}