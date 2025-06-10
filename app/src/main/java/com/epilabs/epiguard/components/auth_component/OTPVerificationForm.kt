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
fun OTPVerificationForm(navController: NavController, email: String, expectedOTP: String) {
    val context = LocalContext.current
    val otpInput = remember { mutableStateOf("") }
    val dbHandler = DatabaseConnector(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter OTP",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "An OTP has been sent to $email",
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = otpInput.value,
            onValueChange = { otpInput.value = it },
            placeholder = { Text("Enter OTP") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (otpInput.value == expectedOTP) {
                if (dbHandler.verifyUser(email)) {
                    Toast.makeText(context, "Verification successful", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                } else {
                    Toast.makeText(context, "Verification failed", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Invalid OTP", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Verify OTP", color = Color.White)
        }
    }
}