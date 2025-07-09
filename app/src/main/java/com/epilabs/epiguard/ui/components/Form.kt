package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors

/**
 * Same UI as the original AddContactsForm, but all root‑level vertical offsets have been
 * reduced by 80 dp and the global negative offset has been removed. This lets you
 * embed the composable in any screen without the top‑bar collision.
 */
@Composable
fun AddContactsForm(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp)            .requiredHeight(1750.dp)
            .clip(RoundedCornerShape(25.dp))   // <- 25 dp radius on the whole form
            .background(AppColors.color_Gray_White)

    ) {
        /* --------------------------------------------------------------------- */
        // PROFILE IMAGE + ADD ICON
        /* --------------------------------------------------------------------- */
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 137.dp, y = 39.dp)   // 119 − 80
                .requiredWidth(103.dp)
                .requiredHeight(98.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.guy_3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(100.dp))
                    .border(BorderStroke(3.dp, AppColors.color_Gray_50), CircleShape)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 71.dp, y = 66.dp) // unchanged (relative)
                    .requiredSize(32.dp)

            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(32.dp)
                        .clip(CircleShape)
                        .background(AppColors.color_violet)
                        .border(BorderStroke(3.dp, AppColors.color_Gray_White), CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(AppColors.color_white),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 7.dp, y = 7.dp)
                        .requiredSize(18.dp)
                )
            }
        }

        /* --------------------------------------------------------------------- */
        // PLUS ICON (top‑right)
        /* --------------------------------------------------------------------- */
        Image(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = null,
            colorFilter = ColorFilter.tint(AppColors.color_white),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 217.dp, y = 114.dp)   // 194 − 80
                .requiredWidth(14.dp)
                .requiredHeight(14.dp)
        )

        /* --------------------------------------------------------------------- */
        // PERSONAL DETAILS SECTION
        /* --------------------------------------------------------------------- */
        Text(
            text = "Personal Details",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 16.dp, y = 165.dp)   // 245 − 80
        )
        Text(
            text = "FirstName",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 207.dp)   // 287 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset( y = 237.dp)   // 317 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "Bob",
            color = Color.Black,
            lineHeight = 1.69.em,
            style = TextStyle(fontSize = 13.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 251.dp)   // 331 − 80
        )
        Text(
            text = "LastName",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 313.dp)   // 393 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 343.dp)   // 423 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "Dylan",
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 357.dp)   // 437 − 80
        )

        /* --------------------------------------------------------------------- */
        // DIVIDER
        /* --------------------------------------------------------------------- */
        HorizontalDivider(
            color = Color(0xffc4c4c4),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 456.dp)   // 536 − 80
                .requiredWidth(327.dp)
        )

        /* --------------------------------------------------------------------- */
        // CONTACT DETAILS SECTION
        /* --------------------------------------------------------------------- */
        Text(
            text = "Contact Details",
            color = Color.Black,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 490.dp)   // 570 − 80
        )
        Text(
            text = "Email",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 532.dp)   // 612 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 562.dp)   // 642 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "bobdylan@gmail.com",
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 576.dp)   // 656 − 80
        )

        Text(
            text = "Contact No",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 638.dp)   // 718 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 668.dp)   // 748 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "+44 7519273204",
            color = Color.Black,
            lineHeight = 0.88.em,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 685.dp)   // 765 − 80
        )

        Text(
            text = "Alert Type",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 744.dp)   // 824 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 774.dp)   // 854 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "SMS",
            color = Color.Black,
            lineHeight = 0.88.em,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 785.dp)   // 865 − 80
                .wrapContentHeight(Alignment.CenterVertically)
        )

        Text(
            text = "Contact Status",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 850.dp)   // 930 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 880.dp)   // 960 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "Active",
            color = Color.Black,
            lineHeight = 1.em,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 895.dp)   // 975 − 80
        )
        Image(
            painter = painterResource(id = R.drawable.keyboard_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 320.dp, y = 899.dp)   // 979 − 80
                .requiredWidth(20.dp)
                .requiredHeight(10.dp)
                .clip(RoundedCornerShape(1.dp))
        )

        Text(
            text = "Contact Type",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 956.dp)   // 1036 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 986.dp)   // 1066 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "Primary Contact",
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 1000.dp)   // 1080 − 80
        )

        HorizontalDivider(
            color = Color(0xffc4c4c4),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 1068.dp)   // 1148 − 80
                .requiredWidth(327.dp)
        )

        /* --------------------------------------------------------------------- */
        // MEDICAL TRAINING SECTION
        /* --------------------------------------------------------------------- */
        Text(
            text = "Medical Training",
            color = Color.Black,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 1100.dp)   // 1180 − 80
        )
        Text(
            text = "Epilepsy First Aid",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 1144.dp)   // 1224 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 1174.dp)   // 1254 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "Yes",
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 1188.dp)   // 1268 − 80
        )
        Text(
            text = "CPR / Basic Life Support",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 1250.dp)   // 1330 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 1280.dp)   // 1360 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "False",
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 1294.dp)   // 1374 − 80
        )
        Text(
            text = "Mental Health First Aid",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 1356.dp)   // 1436 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 1386.dp)   // 1466 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )
        Text(
            text = "True",
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 1400.dp)   // 1480 − 80
        )

        /* --------------------------------------------------------------------- */
        // ADDITIONAL INFORMATION (MULTI‑LINE)
        /* --------------------------------------------------------------------- */
        Text(
            text = "Additional Information",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 1460.dp)   // 1540 − 80
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 1490.dp)   // 1570 − 80
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, Color(0xffc8c8c8)), RoundedCornerShape(8.dp))
        )

        var bio by remember { mutableStateOf("") }

        BasicTextField(
            value = bio,
            onValueChange = { bio = it },
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp, lineHeight = 1.45.em),
            singleLine = false,
            maxLines = 4,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 44.dp, y = 1490.dp)   // 1570 − 80
                .requiredWidth(287.dp)
                .requiredHeight(100.dp)
        )

        /* --------------------------------------------------------------------- */
        // SAVE BUTTON
        /* --------------------------------------------------------------------- */
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 1630.dp)   // 1710 − 80
                .requiredHeight(48.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(AppColors.color_violet)
                .clickable { /* TODO: submit action */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Save Contact",
                color = Color.White,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview(heightDp = 1750)
@Composable
private fun AddContactsFormPreview() {
    AddContactsForm()
}
