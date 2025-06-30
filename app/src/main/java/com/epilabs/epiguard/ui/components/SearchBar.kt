package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.examples.QuickAction
import com.epilabs.epiguard.ui.theme.AppShadows

@Composable
fun TopNavigation(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .clip(shape = RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp))
            .background(color = AppColors.color_Gray_White)
            .shadow(
                elevation = AppShadows.effect_switch_shadow,
                shape = RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp)
            )
    ) {
        NavBar88Pt(title = "My Contacts")
        Spacer(modifier = Modifier.height(8.dp)) // Default spacing, replace with Spacing.small if defined
        QuickAction(
            modifier = Modifier.fillMaxWidth(),
            iconR80850 = true,
            iconL80725 = true,
            label808100 = "Button",
            buttonHeight = 48.dp
        )
    }
}

@Preview(widthDp = 375, heightDp = 200) // Increased height to accommodate content
@Composable
private fun TopNavigationPreview() {
    TopNavigation()
}