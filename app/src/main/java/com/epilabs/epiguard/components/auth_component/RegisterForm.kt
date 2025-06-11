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
import com.epilabs.epiguard.utils.EmailSender
import kotlinx.coroutines.launch

@Composable
fun RegisterForm(navController: NavController) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val dbHandler = DatabaseConnector(context)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            placeholder = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            placeholder = { Text("Username") },
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
            coroutineScope.launch {
                val otp = EmailSender.generateOTP()
                val emailResult = EmailSender.sendOTP(email.value, otp)
                emailResult.onSuccess {
                    val userId = dbHandler.registerUser(email.value, username.value, password.value)
                    if (userId > -1) {
                        navController.navigate("otp_verification/${email.value}/$otp")
                    } else {
                        Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                    }
                }.onFailure { exception ->
                    Toast.makeText(context, "Failed to send OTP: ${exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        }) {
            Text("Register and Send OTP", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { navController.navigate("sign_in") }) {
            Text("Already have an account? Sign In", color = Color.Blue)
        }
    }
}