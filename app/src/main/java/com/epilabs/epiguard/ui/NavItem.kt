package com.epilabs.epiguard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.*

data class NavItem(
    val route: String,
    val label: String,
    val iconResId: Int,
    val contentDescription: String
)

@Composable
fun BottomNavigation(
    navController: NavHostController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
    val navItems = listOf(
        NavItem("search", "Search", R.drawable.sms, "Search Icon"),
        NavItem("appointments", "Appointments", R.drawable.sms, "icon clock"),
        NavItem("explore", "Explore", R.drawable.sms, "Vector"),
        NavItem("profile", "Profile", R.drawable.sms, "icon profile")
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(83.dp)
            .background(Color.White.copy(alpha = 0.92f))
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                NavigationItem(
                    item = item,
                    isSelected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.iconResId),
                contentDescription = item.contentDescription,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) MyColors.color_violet.copy(alpha = 0.1f)
                        else Color.Transparent
                    )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.label,
                color = if (isSelected) MyColors.color_violet else MyColors.color_grey,
                textAlign = TextAlign.Center,
                style = MyTypes.typography.labelSmall
            )
        }
    }
}