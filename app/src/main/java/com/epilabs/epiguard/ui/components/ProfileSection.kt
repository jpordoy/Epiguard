package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes

@Composable
fun Section(
    modifier: Modifier = Modifier,
    name: String = "Ingredia Nutrisha",
    accountNumber: String = "A/N: 0987654321"
) {
    Box(
        modifier = modifier
            .requiredWidth(386.dp)
            .requiredHeight(200.dp)
    ) {
        // Gradient Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.linearGradient(
                        0.3f to MyColors.color_gradient_start,
                        1f to MyColors.color_gradient_end,
                        start = Offset(39.2f, 253.13f),
                        end = Offset(446.31f, -142.19f)
                    )
                )
        )

        // Circular Image
        Image(
            painter = painterResource(id = R.drawable.guy2),
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 14.dp, y = 13.dp)
                .requiredSize(173.dp)
                .clip(CircleShape)
        )

        // Text (Name and Account Number)
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 201.dp, y = 20.dp) // Aligned with button's start (201.dp)
                .requiredWidth(185.dp) // 386.dp - 201.dp
        ) {
            Text(
                text = name,
                color = MyColors.color_white,
                style = MyTypes.typography.bodyLarge.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = accountNumber,
                color = Color(0xFFB2C6FF),
                style = MyTypes.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 22.dp)
            )
        }

        // Edit Profile Button
        TextButton(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(start = 201.dp, top = 113.dp, end = 14.dp, bottom = 45.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(171.dp)
                    .requiredHeight(42.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MyColors.color_white)
            ) {
                Text(
                    text = "EDIT PROFILE",
                    color = MyColors.color_violet,
                    textAlign = TextAlign.Center,
                    style = MyTypes.typography.labelLarge.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )
            }
        }
    }
}



@Composable
fun ProfileSectionWhite(
    name: String,
    breed: String,
    profileImageRes: Int,
    overlayIconRes: Int = R.drawable.sms,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredWidth(386.dp)
            .requiredHeight(279.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .requiredWidth(386.dp)
                .requiredHeight(279.dp)
        ) {
            // Card background with flat top and curved bottom corners
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .requiredWidth(386.dp)
                    .requiredHeight(279.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
                    .background(Color.White)
            )




            // Photo Circle — dynamic resource
            ComponentPhotoCircle(
                profileImageRes = profileImageRes,
                overlayIconRes = overlayIconRes,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 135.dp, y = 45.dp)
            )

            // Name
            Text(
                text = name,
                color = MyColors.color_black,
                textAlign = TextAlign.Center,
                style = MyTypes.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 68.dp)
                    .fillMaxWidth()
            )

            // Breed
            Text(
                text = breed,
                color = MyColors.color_grey,
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = MyTypes.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ComponentPhotoCircle(
    profileImageRes: Int,
    overlayIconRes: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.requiredSize(117.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MyColors.color_grey)
        )
        Image(
            painter = painterResource(id = profileImageRes),
            contentDescription = "Profile picture",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}

@Preview(widthDp = 386, heightDp = 279)
@Composable
fun ProfileSectionWhitePreview() {
    ProfileSectionWhite(
        name = "Jamie Pordoy",
        breed = "Primary Care Contact",
        profileImageRes = R.drawable.guy2, // Replace with actual drawable
        overlayIconRes = R.drawable.sms
    )
}


@Preview(widthDp = 386, heightDp = 200)
@Composable
private fun SectionPreview() {
    Section()
}