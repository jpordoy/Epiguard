package com.epilabs.epiguard.components.raw_data_component

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

@Composable
fun ViewRawData(
    navController: NavController,
    userID: Int
) {
    val context = LocalContext.current
    val dbHandler = DatabaseConnector(context)
    val rawDataList = dbHandler.getAllRawData(userID)

    LazyColumn {
        itemsIndexed(rawDataList) { _, rawData ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Raw Data ID: ${rawData.rawDataId}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "User ID: ${rawData.userId}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Seizure ID: ${rawData.seizureID?.toString() ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Timestamp: ${rawData.timestamp ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Classification Result: ${rawData.classificationResult ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Number of Classified Timesteps: ${rawData.numberOfClassifiedTimesteps}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Predicted Class: ${rawData.predictedClass ?: "N/A"}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
        if (rawDataList.isEmpty()) {
            item {
                Text(
                    text = "No raw data found for user ID $userID",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }
        }
    }
}