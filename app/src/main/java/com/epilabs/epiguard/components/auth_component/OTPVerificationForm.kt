package com.epilabs.epiguard.components.auth_component

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPVerificationForm(navController: NavController, email: String, expectedOTP: String) {
    val context = LocalContext.current
    val otpDigits = remember {
        List(6) { mutableStateOf("") }
    }
    val dbHandler = DatabaseConnector(context)
    val coroutineScope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 852.dp)
            .background(color = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 81.dp)
        ) {
            Text(
                text = "Enter OTP",
                color = AppColors.color_Primary_600,
                lineHeight = 1.25.em,
                style = AppTypes.type_Header_Header_2
            )
            Text(
                text = "Enter the OTP code we just sent\nyou on your registered Email/Phone number: $email",
                color = AppColors.color_Gray_700,
                textAlign = TextAlign.Center,
                lineHeight = 1.57.em,
                style = AppTypes.type_Body_Regular_400,
                modifier = Modifier.requiredWidth(width = 345.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 213.dp)
                .requiredWidth(width = 344.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .requiredWidth(width = 344.dp)
                    .requiredHeight(height = 70.dp)
            ) {
                val focusRequesters = remember { List(6) { FocusRequester() } }

                otpDigits.forEachIndexed { index, digitState ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .requiredHeight(height = 70.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(color = AppColors.color_Gray_50)
                            .border(
                                border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        OutlinedTextField(
                            value = digitState.value,
                            onValueChange = { newValue ->
                                if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                    digitState.value = newValue
                                    if (newValue.isNotEmpty() && index < otpDigits.size - 1) {
                                        focusRequesters[index + 1].requestFocus()
                                    }
                                    if (index == otpDigits.size - 1 && newValue.isNotEmpty()) {
                                        // Auto-submit OTP
                                        coroutineScope.launch {
                                            isLoading.value = true
                                            delay(500)
                                            val otpInput = otpDigits.joinToString("") { it.value }
                                            if (otpInput == expectedOTP) {
                                                if (dbHandler.verifyUser(email)) {
                                                    Toast.makeText(
                                                        context,
                                                        "Verification successful",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    navController.navigate("confirmation")
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Verification failed",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Invalid OTP",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                            isLoading.value = false
                                        }
                                    }
                                } else if (newValue.length == 6 && newValue.all { it.isDigit() }) {
                                    newValue.forEachIndexed { i, char ->
                                        if (i < otpDigits.size) {
                                            otpDigits[i].value = char.toString()
                                        }
                                    }
                                    // Auto-submit OTP
                                    coroutineScope.launch {
                                        isLoading.value = true
                                        delay(500)
                                        val otpInput = newValue
                                        if (otpInput == expectedOTP) {
                                            if (dbHandler.verifyUser(email)) {
                                                Toast.makeText(
                                                    context,
                                                    "Verification successful",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navController.navigate("confirmation")
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Verification failed",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Invalid OTP",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                        isLoading.value = false
                                    }
                                }
                            },
                            textStyle = TextStyle(
                                color = AppColors.color_Gray_900,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .focusRequester(focusRequesters[index])
                                .onKeyEvent { event ->
                                    if (event.key == Key.Backspace && digitState.value.isEmpty() && index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                        true
                                    } else {
                                        false
                                    }
                                }
                                .semantics { contentDescription = "OTP digit ${index + 1}" },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = AppColors.color_Gray_900,
                                unfocusedTextColor = AppColors.color_Gray_900,
                                cursorColor = AppColors.color_Primary_500,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = AppColors.color_Gray_50,
                                focusedContainerColor = AppColors.color_Gray_50
                            )
                        )
                    }
                }
            }
            // Optional: Add hint for pasting
            Text(
                text = "Paste 6-digit OTP into any field or type digits",
                color = AppColors.color_Gray_700,
                style = AppTypes.type_Body_small_400,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 335.dp)
                .requiredWidth(width = 345.dp)
                .requiredHeight(height = 60.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = AppColors.color_Primary_500)
                    .clickable(enabled = !isLoading.value) {
                        isLoading.value = true
                        coroutineScope.launch {
                            delay(500) // Ensure loading animation is visible
                            val otpInput = otpDigits.joinToString("") { it.value }
                            if (otpInput == expectedOTP) {
                                if (dbHandler.verifyUser(email)) {
                                    Toast.makeText(
                                        context,
                                        "Verification successful",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate("confirmation")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Verification failed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(context, "Invalid OTP", Toast.LENGTH_LONG).show()
                            }
                            isLoading.value = false
                        }
                    }
                    .padding(horizontal = 24.dp, vertical = 18.dp)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(
                        color = AppColors.color_Gray_White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = "Verify OTP",
                    color = AppColors.color_Gray_White,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Body_Large_500
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 24.dp, y = 39.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 14.dp)
                    .blur(radius = 44.dp)
                    .background(color = AppColors.color_Primary_500)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 411.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                lineHeight = 20.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                        append("Didn’t get OTP? ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF6E62FF), fontSize = 14.sp)) {
                        append("Resend OTP")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Handle Resend OTP */ }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 137.dp, y = 748.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 32.dp)
                    .requiredHeight(height = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .background(color = AppColors.color_Primary_100)
            )
            Box(
                modifier = Modifier
                    .requiredWidth(width = 32.dp)
                    .requiredHeight(height = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .background(color = AppColors.color_Primary_500)
            )
            Box(
                modifier = Modifier
                    .requiredWidth(width = 32.dp)
                    .requiredHeight(height = 4.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .background(color = AppColors.color_Primary_100)
            )
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun OTPVerificationFormPreview() {
    OTPVerificationForm(
        navController = rememberNavController(),
        email = "test@example.com",
        expectedOTP = "123456"
    )
}