package com.epilabs.epiguard.components.auth_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors

@Composable
fun Confirmation(navController: NavController) {
    Box(
        modifier = Modifier
            .requiredWidth(width = 390.dp)
            .requiredHeight(height = 844.dp)
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.sticker),
            contentDescription = "Successmark",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 164.dp, y = 258.dp)
                .requiredSize(size = 62.dp)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 75.dp, y = 343.dp)
                .requiredWidth(width = 226.dp)
                .requiredHeight(height = 81.dp)
        ) {
            Text(
                text = "Sign Up Completed!",
                color = AppColors.color_Gray_600,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 9.dp, y = 0.dp)
            )
            Text(
                text = "Your account has been created successfully.",
                color = Color(0xff555555),
                textAlign = TextAlign.Center,
                lineHeight = 10.71.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 39.dp)
                    .requiredWidth(width = 340.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 28.dp, y = 458.dp)
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 56.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = AppColors.color_Primary_500)
                .clickable {
                    navController.navigate("sign_in") {
                        popUpTo("confirmation") { inclusive = true }
                    }
                }
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Back to Sign In",
                color = AppColors.color_Gray_100,
                lineHeight = 1.5.em,
            )
        }
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun ConfirmationPreview() {
    Confirmation(navController = rememberNavController())
}