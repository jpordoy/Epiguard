package com.epilabs.epiguard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.BackgroundGray
import com.epilabs.epiguard.ui.theme.BorderGray
import com.epilabs.epiguard.ui.theme.LightBackground
import com.epilabs.epiguard.ui.theme.LightGray
import com.epilabs.epiguard.ui.theme.PrimaryBlue
import com.epilabs.epiguard.ui.theme.PrimaryDark
import com.epilabs.epiguard.ui.theme.EpiGuardTheme

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.92f))
            .padding(horizontal = 14.dp, vertical = 14.dp)
    ) {
        Column {
            // Time
            Text(
                text = "9:41",
                modifier = Modifier.padding(start = 18.dp),
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.17).sp
            )

            Spacer(modifier = Modifier.height(17.dp))

            // Header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* Handle back navigation */ },
                    tint = Color.Black
                )

                Text(
                    text = "Add pet detail",
                    color = PrimaryDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.36.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Skip",
                    color = PrimaryBlue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.13.sp,
                    modifier = Modifier.clickable { /* Handle skip */ }
                )
            }
        }
    }

    // Divider
    HorizontalDivider(
        color = BackgroundGray,
        thickness = 1.dp
    )
}

@Preview(showBackground = true)
@Composable
fun HeaderSectionPreview() {
    EpiGuardTheme {
        HeaderSection()
    }
}

@Composable
fun FormField(
    label: String,
    value: String,
    showDivider: Boolean = true
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = LightGray
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = PrimaryDark
        )

        if (showDivider) {
            Spacer(modifier = Modifier.height(22.dp))
            HorizontalDivider(
                color = BackgroundGray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormFieldPreview() {
    EpiGuardTheme {
        FormField(
            label = "Pet's name",
            value = "Troy"
        )
    }
}

@Composable
fun DropdownField(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = LightGray
        )

        Spacer(modifier = Modifier.height(9.dp))

        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = PrimaryDark
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown",
                modifier = Modifier.size(16.dp),
                tint = PrimaryDark
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        HorizontalDivider(
            color = BackgroundGray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(22.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownFieldPreview() {
    EpiGuardTheme {
        DropdownField(
            label = "Species of your pet",
            value = "Dog"
        )
    }
}

@Composable
fun GenderButton(
    text: String,
    isSelected: Boolean,
    iconRes: Int,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) PrimaryBlue else Color.Transparent
    val textColor = if (isSelected) Color.White else PrimaryDark
    val borderColor = if (isSelected) Color.Transparent else BorderGray

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(backgroundColor)
            .border(
                width = if (isSelected) 0.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 31.dp, vertical = 4.dp)
            .then(
                if (isSelected) {
                    Modifier.shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(25.dp)
                    )
                } else Modifier
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                color = textColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.13.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenderButtonPreview() {
    EpiGuardTheme {
        GenderButton(
            text = "Male",
            isSelected = true,
            iconRes = R.drawable.user45,
            onClick = {}
        )
    }
}

@Composable
fun ToggleSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = PrimaryDark
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryBlue,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = LightBackground,
                uncheckedBorderColor = BorderGray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToggleSwitchPreview() {
    EpiGuardTheme {
        ToggleSwitch(
            label = "Neutered",
            checked = true,
            onCheckedChange = {}
        )
    }
}

@Composable
fun AddReminderCard() {
    Box(
        modifier = Modifier
            .size(120.dp, 144.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundGray)
            .clickable { /* Handle add reminder */ }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.sms),
            contentDescription = "Add Reminder",
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),
            tint = LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddReminderCardPreview() {
    EpiGuardTheme {
        AddReminderCard()
    }
}

@Composable
fun ReminderCard(
    title: String,
    date: String
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(144.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = title,
                color = PrimaryDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = date,
                color = LightGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderCardPreview() {
    EpiGuardTheme {
        ReminderCard(
            title = "Measles vaccine",
            date = "30.08.2018 г"
        )
    }
}

@Composable
fun BottomNavigationSection() {
    Column {
        HorizontalDivider(
            color = BackgroundGray,
            thickness = 1.dp
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.92f))
                .padding(horizontal = 39.dp, vertical = 7.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    BottomNavItem(
                        iconRes = R.drawable.sms,
                        label = "Search",
                        isSelected = false
                    )

                    BottomNavItem(
                        iconRes = R.drawable.sms,
                        label = "Appointments",
                        isSelected = false
                    )

                    BottomNavItem(
                        iconRes = R.drawable.sms,
                        label = "Explore",
                        isSelected = false
                    )

                    BottomNavItem(
                        iconRes = R.drawable.sms,
                        label = "Profile",
                        isSelected = true
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationSectionPreview() {
    EpiGuardTheme {
        BottomNavigationSection()
    }
}

@Composable
fun BottomNavItem(
    iconRes: Int,
    label: String,
    isSelected: Boolean
) {
    val textColor = if (isSelected) PrimaryBlue else LightGray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.4.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavItemPreview() {
    EpiGuardTheme {
        BottomNavItem(
            iconRes = R.drawable.sms,
            label = "Profile",
            isSelected = true
        )
    }
}