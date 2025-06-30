package com.epilabs.epiguard.components.auth_component

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.epilabs.epiguard.R
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.utils.EmailSender
import com.epilabs.epiguard.utils.ValidationUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val dbHandler = DatabaseConnector(context)
    val coroutineScope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val validationError = remember { mutableStateOf<String?>(null) }

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val checkedState = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 852.dp)
            .background(color = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 122.dp)
        ) {
            Text(
                text = "Create An Account",
                color = AppColors.color_Primary_500,
                textAlign = TextAlign.Left,
                lineHeight = 1.25.em,
                style = AppTypes.type_Header_Header_2
            )
            Text(
                text = "Sign up to EpiGuard to get started.",
                color = AppColors.color_Gray_900,
                textAlign = TextAlign.Left,
                lineHeight = 1.57.em,
                style = AppTypes.type_Body_Regular_400,
                modifier = Modifier.requiredWidth(width = 345.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 246.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = {
                        Text(
                            text = "Username",
                            color = AppColors.color_Gray_600,
                            lineHeight = 1.5.em,
                            style = AppTypes.type_Body_Large_400
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = AppColors.color_Gray_900,
                        unfocusedTextColor = AppColors.color_Gray_900,
                        focusedLabelColor = AppColors.color_Gray_600,
                        unfocusedLabelColor = AppColors.color_Primary_500,
                        cursorColor = AppColors.color_Primary_500,
                        focusedIndicatorColor = AppColors.color_Primary_500,
                        unfocusedIndicatorColor = AppColors.color_Gray_100,
                        disabledIndicatorColor = AppColors.color_Gray_100,
                        unfocusedContainerColor = AppColors.color_Gray_50,
                        focusedContainerColor = AppColors.color_Gray_50
                    ),
                    textStyle = TextStyle(color = AppColors.color_Gray_900),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            Box(
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            text = "Email",
                            color = AppColors.color_Gray_600,
                            lineHeight = 1.5.em,
                            style = AppTypes.type_Body_Large_400
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = AppColors.color_Gray_900,
                        unfocusedTextColor = AppColors.color_Gray_900,
                        focusedLabelColor = AppColors.color_Gray_600,
                        unfocusedLabelColor = AppColors.color_Primary_500,
                        cursorColor = AppColors.color_Primary_500,
                        focusedIndicatorColor = AppColors.color_Primary_500,
                        unfocusedIndicatorColor = AppColors.color_Gray_100,
                        disabledIndicatorColor = AppColors.color_Gray_100,
                        unfocusedContainerColor = AppColors.color_Gray_50,
                        focusedContainerColor = AppColors.color_Gray_50
                    ),
                    textStyle = TextStyle(color = AppColors.color_Gray_900),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            Box(
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                var passwordVisible by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            text = "Password",
                            color = AppColors.color_Gray_600,
                            lineHeight = 1.5.em,
                            style = AppTypes.type_Body_Large_400
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(id = if (passwordVisible) R.drawable.eye_icon else R.drawable.eye_icon),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = AppColors.color_Gray_600,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = AppColors.color_Gray_900,
                        unfocusedTextColor = AppColors.color_Gray_900,
                        focusedLabelColor = AppColors.color_Gray_600,
                        unfocusedLabelColor = AppColors.color_Primary_500,
                        cursorColor = AppColors.color_Primary_500,
                        focusedIndicatorColor = AppColors.color_Primary_500,
                        unfocusedIndicatorColor = AppColors.color_Gray_100,
                        disabledIndicatorColor = AppColors.color_Gray_100,
                        unfocusedContainerColor = AppColors.color_Gray_50,
                        focusedContainerColor = AppColors.color_Gray_50
                    ),
                    textStyle = TextStyle(color = AppColors.color_Gray_900),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            if (validationError.value != null) {
                Text(
                    text = validationError.value!!,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 470.dp)
        ) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColors.color_Primary_500,
                    uncheckedColor = AppColors.color_Gray_600,
                    checkmarkColor = AppColors.color_Gray_White
                )
            )
            Text(
                modifier = Modifier.offset(y = 14.dp),
                lineHeight = 22.sp,
                fontSize = 12.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = AppColors.color_Gray_800)) {
                        append("I’m agree to The ")
                    }
                    withStyle(style = SpanStyle(color = AppColors.color_Primary_500)) {
                        append("Terms of Service")
                    }
                    withStyle(style = SpanStyle(color = AppColors.color_Gray_800)) {
                        append(" and ")
                    }
                    withStyle(style = SpanStyle(color = AppColors.color_Primary_500)) {
                        append("Privacy Policy")
                    }
                }
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 520.dp)
                .requiredWidth(width = 345.dp)
                .requiredHeight(height = 126.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = AppColors.color_Primary_500)
                    .clickable(
                        enabled = !isLoading.value && checkedState.value
                    ) {
                        coroutineScope.launch {
                            Log.d("RegisterForm", "Starting registration for email=$email, username=$username")
                            isLoading.value = true
                            validationError.value = null

                            if (!ValidationUtils.isValidEmail(email, dbHandler)) {
                                validationError.value = "Invalid email format or already registered"
                                Log.w("RegisterForm", "Email validation failed")
                            } else if (!ValidationUtils.isValidUsername(username, dbHandler)) {
                                validationError.value = "Username must be 3+ characters, alphanumeric, or already taken"
                                Log.w("RegisterForm", "Username validation failed")
                            } else if (!ValidationUtils.isValidPassword(password)) {
                                validationError.value = "Password must be 8+ characters with uppercase and number"
                                Log.w("RegisterForm", "Password validation failed")
                            } else {
                                Log.d("RegisterForm", "Validation passed, generating OTP")
                                val otp = EmailSender.generateOTP()
                                Log.d("RegisterForm", "OTP generated: $otp")
                                val emailResult = EmailSender.sendOTP(email, otp)
                                emailResult.onSuccess {
                                    Log.d("RegisterForm", "OTP sent successfully, attempting to register user")
                                    val userId = dbHandler.registerUser(email, username, password)
                                    Log.d("RegisterForm", "Registration attempt completed: userId=$userId")
                                    if (userId > -1) {
                                        Log.d("RegisterForm", "User registered successfully, navigating to OTP verification")
                                        val emailToPass = email // Store email before clearing
                                        email = ""
                                        username = ""
                                        password = ""
                                        navController.navigate("otp_verification/$emailToPass/$otp")
                                    } else {
                                        Log.e("RegisterForm", "Registration failed: userId=$userId")
                                        validationError.value = "Registration failed: Unable to save user"
                                    }
                                }.onFailure { exception ->
                                    Log.e("RegisterForm", "OTP send failed: ${exception.message}")
                                    validationError.value = "Failed to send OTP: ${exception.message}"
                                }
                            }
                            isLoading.value = false
                        }
                    }
                    .padding(horizontal = 24.dp, vertical = 18.dp)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(
                        color = AppColors.color_Gray_White,
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = "Create Account",
                    color = AppColors.color_Gray_100,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Body_Large_500
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 39.dp)
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 14.dp)
                    .blur(radius = 44.dp)
                    .background(color = AppColors.color_Primary_500)
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = 76.dp)
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 22.dp)
            ) {
                Text(
                    text = "Or",
                    color = AppColors.color_Gray_900,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.57.em,
                    style = AppTypes.type_Body_Regular_400,
                    modifier = Modifier.align(alignment = Alignment.TopCenter)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 192.dp, y = 14.dp)
                        .requiredWidth(width = 153.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 14.dp)
                        .requiredWidth(width = 153.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = 104.dp)
                    .requiredWidth(width = 345.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .requiredHeight(height = 48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppColors.color_Gray_50)
                        .border(1.dp, AppColors.color_Gray_100, RoundedCornerShape(14.dp))
                        .clickable { /* Handle Facebook login */ }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                    ) {
                        CompositionLocalProvider(LocalContentColor provides Color.Unspecified) {
                            Icon(
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = "Facebook",
                                modifier = Modifier.size(24.dp),
                                tint = null
                            )
                        }
                        Text(
                            text = "Facebook",
                            color = AppColors.color_Gray_900,
                            style = AppTypes.type_Body_Large_500,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .requiredHeight(height = 48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppColors.color_Gray_50)
                        .border(1.dp, AppColors.color_Gray_100, RoundedCornerShape(14.dp))
                        .clickable { /* Handle Google login */ }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                    ) {
                        CompositionLocalProvider(LocalContentColor provides Color.Unspecified) {
                            Icon(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = "Google",
                                modifier = Modifier.size(24.dp),
                                tint = null
                            )
                        }
                        Text(
                            text = "Google",
                            color = AppColors.color_Gray_900,
                            style = AppTypes.type_Body_Large_500,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
        Text(
            lineHeight = 22.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = AppColors.color_Gray_800, fontSize = 14.sp)) {
                    append("Do you have an account? ")
                }
                withStyle(style = SpanStyle(color = AppColors.color_Primary_500, fontSize = 14.sp)) {
                    append("Sign In")
                }
            },
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .offset(y = (-150).dp)
                .clickable { navController.navigate("sign_in") }
        )
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun RegisterFormPreview() {
    RegisterForm(navController = rememberNavController())
}