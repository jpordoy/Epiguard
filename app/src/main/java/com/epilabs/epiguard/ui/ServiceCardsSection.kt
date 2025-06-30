package com.epilabs.epiguard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ServiceCardsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            ServiceCard(
                title = "Book an Appointment",
                subtitle = "Find a Doctor or specialist",
                backgroundColor = Color(0xFFF9F5FF),
                iconBackgroundColor = Color(0xFFC6D4F1),
                iconBorderColor = Color(0xFFA0B6EA),
                icon = "📋",
                modifier = Modifier.weight(1f)
            )

            ServiceCard(
                title = "Appointment with QR",
                subtitle = "Queuing without the hustle",
                backgroundColor = Color(0xFFEDFCF2),
                iconBackgroundColor = Color(0xFFD3F8DF),
                iconBorderColor = Color(0xFFAAF0C4),
                icon = "📱",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            ServiceCard(
                title = "Request Consultation",
                subtitle = "Talk to specialist",
                backgroundColor = Color(0xFFFEF6EE),
                iconBackgroundColor = Color(0xFFFEE4E2),
                iconBorderColor = Color(0xFFF9DBAF),
                icon = "💬",
                modifier = Modifier.weight(1f)
            )

            ServiceCard(
                title = "Locate a Pharmacy",
                subtitle = "Purchase Medicines",
                backgroundColor = Color(0xFFFEF3F2),
                iconBackgroundColor = Color(0xFFFEE4E2),
                iconBorderColor = Color(0xFFFECDCA),
                icon = "🏥",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ServiceCard(
    title: String,
    subtitle: String,
    backgroundColor: Color,
    iconBackgroundColor: Color,
    iconBorderColor: Color,
    icon: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(164.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        iconBackgroundColor,
                        RoundedCornerShape(8.dp)
                    )
                    .border(
                        1.dp,
                        iconBorderColor,
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    fontSize = 20.sp
                )
            }

            // Text content
            Column {
                Text(
                    text = title,
                    color = Color(0xFF18181B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp,
                    letterSpacing = 0.2.sp
                )
                Text(
                    text = subtitle,
                    color = Color(0xFF71717A),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    letterSpacing = 0.2.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun ServiceCardsSectionPreview() {
    ServiceCardsSection()
}
