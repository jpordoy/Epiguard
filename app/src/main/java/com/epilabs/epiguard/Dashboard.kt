package com.epilabs.epiguard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Dashboard(navController: NavController, userId: Int) {
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
        Button(onClick = { navController.navigate("add_user_profile/$userId") }) {
            Text("Add Profile", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate("view_user_profile/$userId") }) {
            Text("View Profiles", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("add_contact/$userId") }) {
            Text("Add Contact", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate("view_contacts/$userId") }) {
            Text("View Profiles", color = Color.White)
        }
    }
}