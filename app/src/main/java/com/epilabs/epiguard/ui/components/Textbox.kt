package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.navigation.NavHostController
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.EpiGuardTheme
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes
import com.epilabs.epiguard.ui.theme.TextboxColors
import com.epilabs.epiguard.ui.theme.TextboxTypes

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    label: String = "Label",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    isFocused: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = isEnabled,
            label = {
                Text(
                    text = label,
                    color = TextboxColors.gray600,
                    style = TextboxTypes.bodyLarge400
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = TextboxColors.gray900,
                unfocusedTextColor = TextboxColors.gray900,
                focusedLabelColor = TextboxColors.gray600,
                unfocusedLabelColor = TextboxColors.primary500,
                cursorColor = TextboxColors.primary500,
                focusedIndicatorColor = TextboxColors.primary500,
                unfocusedIndicatorColor = TextboxColors.gray100,
                disabledIndicatorColor = TextboxColors.gray100,
                focusedContainerColor = TextboxColors.gray50,
                unfocusedContainerColor = TextboxColors.gray50
            ),
            textStyle = TextStyle(color = TextboxColors.gray900),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    label: String = "Phone",
    value: String = "",
    countryCode: String = "+380",
    onValueChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    isFocused: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        OutlinedTextField(
            value = "$countryCode $value",
            onValueChange = { newValue ->
                val cleaned = newValue.replace(countryCode, "").trim()
                onValueChange(cleaned)
            },
            enabled = isEnabled,
            label = {
                Text(
                    text = label,
                    color = TextboxColors.gray600,
                    style = TextboxTypes.bodyLarge400
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = TextboxColors.gray900,
                unfocusedTextColor = TextboxColors.gray900,
                focusedLabelColor = TextboxColors.gray600,
                unfocusedLabelColor = TextboxColors.primary500,
                cursorColor = TextboxColors.primary500,
                focusedIndicatorColor = TextboxColors.primary500,
                unfocusedIndicatorColor = TextboxColors.gray100,
                disabledIndicatorColor = TextboxColors.gray100,
                focusedContainerColor = TextboxColors.gray50,
                unfocusedContainerColor = TextboxColors.gray50
            ),
            textStyle = TextStyle(color = TextboxColors.gray900),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun DropdownTextField(
    modifier: Modifier = Modifier,
    label: String = "Select",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    isFocused: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = isEnabled,
            label = {
                Text(
                    text = label,
                    color = TextboxColors.gray600,
                    style = TextboxTypes.bodyLarge400
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = TextboxColors.gray900,
                unfocusedTextColor = TextboxColors.gray900,
                focusedLabelColor = TextboxColors.gray600,
                unfocusedLabelColor = TextboxColors.primary500,
                cursorColor = TextboxColors.primary500,
                focusedIndicatorColor = TextboxColors.primary500,
                unfocusedIndicatorColor = TextboxColors.gray100,
                disabledIndicatorColor = TextboxColors.gray100,
                focusedContainerColor = TextboxColors.gray50,
                unfocusedContainerColor = TextboxColors.gray50
            ),
            textStyle = TextStyle(color = TextboxColors.gray900),
            shape = RoundedCornerShape(14.dp),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.user45),
                    contentDescription = "Dropdown",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Trigger dropdown menu */ }
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview()
@Composable
private fun InputTextFieldPreview() {
    EpiGuardTheme {
        InputTextField()
    }
}

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
            .background(Color.Companion.White.copy(alpha = 0.92f))
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
            verticalAlignment = Alignment.Companion.CenterVertically
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
        contentAlignment = Alignment.Companion.Center
    ) {
        Column(
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.iconResId),
                contentDescription = item.contentDescription,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) MyColors.color_violet.copy(alpha = 0.1f)
                        else Color.Companion.Transparent
                    )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.label,
                color = if (isSelected) MyColors.color_violet else MyColors.color_grey,
                textAlign = TextAlign.Companion.Center,
                style = MyTypes.typography.labelSmall
            )
        }
    }
}