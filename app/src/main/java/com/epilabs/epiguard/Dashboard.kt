package com.epilabs.epiguard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.widget.Toast

@Composable
fun Dashboard(navController: NavController, userId: Int) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome, User $userId",
            color = Color.Black,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("add_user_profile/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Profile", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("view_user_profile/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("My Profile", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("add_contact/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Contact", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("view_contacts/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Contacts", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("add_seizure/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Seizure", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("view_seizures/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Seizures", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("add_raw_data/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Raw Data", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("view_raw_data/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Raw Data", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (userId != -1) {
                    navController.navigate("seizure_detector/$userId")
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Detect Seizure", color = Color.White)
        }
    }
}