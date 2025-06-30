package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes
import com.epilabs.epiguard.R

@Composable
fun NavBar88Pt(
    modifier: Modifier = Modifier,
    title: String = "My Pets",
    iconResId: Int? = null, // Optional icon resource ID
    onBackClick: () -> Unit = {},
    onIconClick: () -> Unit = {} // Optional click handler for the icon
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MyColors.color_white)
            .height(56.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Back Arrow
        Image(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "Back",
            colorFilter = ColorFilter.tint(MyColors.color_violet),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(24.dp)
                .clickable { onBackClick() }
        )

        // Title
        Text(
            text = title,
            color = MyColors.color_black,
            style = MyTypes.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp) // Avoid overlap with back arrow and icon
        )

        // Optional Icon (e.g., on the right)
        iconResId?.let { resId ->
            Image(
                painter = painterResource(id = resId),
                contentDescription = "Action Icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(24.dp)
                    .clickable { onIconClick() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavBar88PtPreview() {
    NavBar88Pt(title = "My Pets")
}