package com.epilabs.epiguard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PromotionCardsSection() {
    val promotionItems = listOf(
        PromotionItem(
            title = "Prevent the spread\nof COVID-19 Virus",
            actionText = "Find out now →",
            backgroundColor = Color(0xFF254EDB)
        ),
        PromotionItem(
            title = "Heart Health\nCheck-up",
            actionText = "Book now →",
            backgroundColor = Color(0xFFF04438)
        ),
        PromotionItem(
            title = "Mental Health\nSupport",
            actionText = "Learn more →",
            backgroundColor = Color(0xFF16B364)
        )
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(promotionItems) { item ->
            PromotionCard(item = item)
        }
    }
}

@Composable
private fun PromotionCard(item: PromotionItem) {
    Card(
        modifier = Modifier
            .width(312.dp)
            .height(104.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            item.backgroundColor,
                            item.backgroundColor.copy(alpha = 0.8f)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.2.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = item.actionText,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.2.sp
                )
            }

            // Decorative elements (simplified)
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .offset(x = 250.dp, y = (-10).dp)
                    .background(
                        Color.White.copy(alpha = 0.1f),
                        RoundedCornerShape(50)
                    )
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = 270.dp, y = 30.dp)
                    .background(
                        Color.White.copy(alpha = 0.1f),
                        RoundedCornerShape(50)
                    )
            )
        }
    }
}

private data class PromotionItem(
    val title: String,
    val actionText: String,
    val backgroundColor: Color
)

@Preview
@Composable
fun PromotionCardsSectionPreview() {
    PromotionCardsSection()
}
