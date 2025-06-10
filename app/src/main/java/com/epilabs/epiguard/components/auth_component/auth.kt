package com.epilabs.epiguard.components.auth_component

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

@Composable
fun SignInForm(navController: NavController) {
    val context = LocalContext.current
    val identifier = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign In",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = identifier.value,
            onValueChange = { identifier.value = it },
            placeholder = { Text("Email or Username") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val userId = dbHandler.signInUser(identifier.value, password.value)
            if (userId != null) {
                Toast.makeText(context, "Sign-in successful", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard/$userId")
            } else {
                Toast.makeText(context, "Invalid credentials or unverified account", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Sign In", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Don't have an account? Register", color = Color.Blue)
        }
    }
}