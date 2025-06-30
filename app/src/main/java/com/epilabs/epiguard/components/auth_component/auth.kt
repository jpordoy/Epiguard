package com.epilabs.epiguard.components.auth_component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInForm(navController: NavController) {
    val context = LocalContext.current
    val identifier = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
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
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 122.dp)
        ) {
            Text(
                text = "Sign In",
                color = AppColors.color_Primary_500,
                textAlign = TextAlign.Left,
                lineHeight = 1.25.em,
                style = AppTypes.type_Header_Header_2
            )
            Text(
                text = "Sign in to get started.",
                color = AppColors.color_Gray_900,
                textAlign = TextAlign.Left,
                lineHeight = 1.57.em,
                style = AppTypes.type_Body_Regular_400,
                modifier = Modifier.requiredWidth(width = 345.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 246.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                OutlinedTextField(
                    value = identifier.value,
                    onValueChange = { identifier.value = it },
                    label = {
                        Text(
                            text = "Email or Username",
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
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            Box(
                modifier = Modifier
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = {
                        Text(
                            text = "Password",
                            color = AppColors.color_Gray_600,
                            lineHeight = 1.5.em,
                            style = AppTypes.type_Body_Large_400
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.eye_icon),
                            contentDescription = "visibility_off",
                            tint = AppColors.color_Gray_600,
                            modifier = Modifier.size(24.dp)
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
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 398.dp)
        ) {
            val checkedState = remember { mutableStateOf(true) }
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
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp, y = 450.dp)
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
                            try {
                                delay(500) // Ensure animation is visible
                                val userId = dbHandler.signInUser(identifier.value, password.value)
                                if (userId != null) {
                                    Toast.makeText(context, "Sign-in successful", Toast.LENGTH_SHORT).show()
                                    navController.navigate("dashboard/$userId")
                                } else {
                                    Toast.makeText(context, "Invalid credentials or unverified account", Toast.LENGTH_LONG).show()
                                }
                            } catch (e: Exception) {
                                Log.e("SignInForm", "Sign-in error: ${e.message}", e)
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                            } finally {
                                isLoading.value = false
                            }
                        }
                    }
                    .padding(horizontal = 24.dp, vertical = 18.dp)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(
                        color = AppColors.color_Gray_100,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = "Sign In",
                    color = AppColors.color_Gray_100,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Body_Large_500
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.requiredWidth(width = 345.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .requiredHeight(height = 48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppColors.color_Gray_50)
                        .border(1.dp, AppColors.color_Gray_100, RoundedCornerShape(14.dp))
                        .clickable { /* TODO: Handle Facebook login */ }
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
                        .clickable { /* TODO: Handle Google login */ }
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
            Box(
                modifier = Modifier
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
            Text(
                lineHeight = 22.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = AppColors.color_Gray_800, fontSize = 14.sp)) {
                        append("Do you have account? ")
                    }
                    withStyle(style = SpanStyle(color = AppColors.color_Primary_500, fontSize = 14.sp)) {
                        append("Sign up")
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate("register") }
            )
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun SignInFormPreview() {
    SignInForm(navController = rememberNavController())
}