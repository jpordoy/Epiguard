package com.epilabs.epiguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.ui.AppColors

// Data class for bar chart data
data class BarData(
    val day: String,
    val seizureHeight: Float, // Height for Seizure bar (0 to 1, scaled to max height)
    val notSeizureHeight: Float // Height for Not Seizure bar (0 to 1, scaled to max height)
)

@Composable
fun Component13(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 906.dp)
            .background(AppColors.color_Gray_50)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 140.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp, y = 25.dp)
                    .requiredWidth(width = 324.dp)
                    .requiredHeight(height = 35.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 289.dp, y = 0.dp)
                        .requiredSize(size = 35.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mask_group),
                        contentDescription = "pexels-christina-morillo-1181690 1",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = CircleShape)
                    )
                }
                Text(
                    text = "Accounts",
                    color = Color(0xff343c6a),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(id = R.drawable.menu1),
                    contentDescription = "Group 692",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 10.dp)
                        .requiredWidth(width = 14.dp)
                        .requiredHeight(height = 18.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 25.dp, y = 80.dp)
                    .requiredWidth(width = 325.dp)
                    .requiredHeight(height = 40.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(40.dp))
                        .background(color = Color(0xfff5f7fa))
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 19.dp, y = 12.dp)
                        .requiredWidth(width = 158.dp)
                        .requiredHeight(height = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_normal),
                        contentDescription = "magnifying-glass 1",
                        colorFilter = ColorFilter.tint(Color(0xff718ebf))
                    )
                    Text(
                        text = "Search for something",
                        color = Color(0xff8ba3cb),
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 44.dp, y = 0.dp)
                    )
                }
            }
        }
        // Four panels in a 2x2 grid
        FourPanels(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(y = 165.dp)
                .padding(horizontal = 8.dp)
                .requiredHeight(height = 185.dp) // 2 rows of 85.dp + 16.dp spacing
        )
        // Transaction table
        TransactionTable(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 25.dp, y = 403.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 199.dp)
        )
        // Bar chart
        BarChart(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 25.dp, y = 661.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 245.dp)
        )
    }
}

@Composable
fun FourPanels(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            // Panel 1: Detected
            Panel(
                iconRes = R.drawable.money_tag_1,
                iconColor = null, // No tint
                circleColor = Color(0xfffff5d9),
                title = "Detected",
                subtitle = "12",
                modifier = Modifier.weight(1f)
            )
            // Panel 2: Seizure Free
            Panel(
                iconRes = R.drawable.group,
                iconColor = null, // No tint
                circleColor = Color(0xffe7edff),
                title = "Seizure Free",
                subtitle = "12 Days",
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            // Panel 3: Expense
            Panel(
                iconRes = R.drawable._01_medical,
                iconColor = null, // No tint
                circleColor = Color(0xffffe0eb),
                title = "Expense",
                subtitle = "$3,460",
                modifier = Modifier.weight(1f)
            )
            // Panel 4: Total Saving
            Panel(
                iconRes = R.drawable._03_saving,
                iconColor = Color(0xff16dbcc), // Tint applied
                circleColor = Color(0xffdcfaf8),
                title = "Total Saving",
                subtitle = "$7,920",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Panel(
    iconRes: Int,
    iconColor: Color?,
    circleColor: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(85.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Circle with icon
            Box(
                modifier = Modifier
                    .size(50.dp) // 30.dp circle + 10.dp padding
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    colorFilter = iconColor?.let { ColorFilter.tint(it) },
                    modifier = Modifier.size(30.dp) // Icon size
                )
            }
            // Text column
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = Color(0xff718ebf),
                    style = TextStyle(fontSize = 12.sp)
                )
                Text(
                    text = subtitle,
                    color = Color(0xff232323),
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
fun TransactionTable(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Row 1: Spotify Subscription
            TransactionRow(
                iconRes = R.drawable.renew_1,
                iconColor = null, // No tint
                circleColor = Color(0xffdcfaf8),
                title = "Spotify Subscription",
                subtitle = "25 Jan 2021",
                amount = "-$150",
                amountColor = Color(0xfffe5c73)
            )
            // Row 2: Mobile Service
            TransactionRow(
                iconRes = R.drawable.group__1_,
                iconColor = null, // No tint
                circleColor = Color(0xffe7edff),
                title = "Mobile Service",
                subtitle = "25 Jan 2021",
                amount = "-$340",
                amountColor = Color(0xfffe5c73)
            )
            // Row 3: Emilly Wilson
            TransactionRow(
                iconRes = R.drawable.user45,
                iconColor = Color(0xffff82ac), // Tint applied
                circleColor = Color(0xffffe0eb),
                title = "Emilly Wilson",
                subtitle = "25 Jan 2021",
                amount = "+$780",
                amountColor = Color(0xff16dbaa)
            )
        }
    }
}

@Composable
fun TransactionRow(
    iconRes: Int,
    iconColor: Color?,
    circleColor: Color,
    title: String,
    subtitle: String,
    amount: String,
    amountColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Circle with icon
        Box(
            modifier = Modifier
                .size(50.dp) // 30.dp icon + 10.dp padding
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                colorFilter = iconColor?.let { ColorFilter.tint(it) },
                modifier = Modifier.size(30.dp) // Icon size
            )
        }
        // Text column
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color(0xff333b69),
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
            )
            Text(
                text = subtitle,
                color = Color(0xff718ebf),
                style = TextStyle(fontSize = 12.sp)
            )
        }
        // Amount
        Text(
            text = amount,
            color = amountColor,
            textAlign = TextAlign.End,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.width(60.dp)
        )
    }
}

@Composable
fun BarChart(modifier: Modifier = Modifier) {
    // Sample data based on original heights (scaled to 0-1, max height 153.dp)
    val barData = listOf(
        BarData("Sat", 1.0f, 1.0f), // 153.dp
        BarData("Sun", 0.934f, 0.934f), // 143.dp
        BarData("Mon", 0.888f, 0.888f), // 136.dp
        BarData("Tue", 0.843f, 0.843f), // 129.dp
        BarData("Wed", 0.784f, 0.784f), // 120.dp
        BarData("Thu", 0.627f, 0.627f), // 96.dp
        BarData("Fri", 0.490f, 0.490f) // 75.dp
    )
    val maxHeight = 153.dp // Max height from original chart

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Legend
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .padding(horizontal = 127.dp), // Center legend within 325.dp width
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(Color(0xff4c78ff))
                    )
                    Text(
                        text = "Seizure",
                        color = Color(0xff718ebf),
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(Color(0xffff82ac))
                    )
                    Text(
                        text = "Not Seizure",
                        color = Color(0xff718ebf),
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            }
            // Chart
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(178.dp), // Matches original chart area
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                barData.forEach { data ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        // Bar
                        Box(
                            modifier = Modifier
                                .width(25.dp)
                                .fillMaxHeight(data.seizureHeight)
                                .clip(MaterialTheme.shapes.medium)
                                .background(Color(0xff1814f3))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(MaterialTheme.shapes.small)
                                    .background(Color(0xfffc7900))
                            )
                        }
                        // Label
                        Text(
                            text = data.day,
                            color = Color(0xff718ebf),
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontSize = 12.sp),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(widthDp = 375, heightDp = 906)
@Composable
private fun Component13Preview() {
    Component13(Modifier)
}