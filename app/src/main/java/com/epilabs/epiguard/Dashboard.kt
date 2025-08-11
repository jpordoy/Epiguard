package com.epilabs.epiguard

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.database.UserDAO
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.components.BottomMenuContent
import com.epilabs.epiguard.ui.components.Feature
import com.epilabs.epiguard.ui.theme.AquaBlue
import com.epilabs.epiguard.ui.theme.Beige1
import com.epilabs.epiguard.ui.theme.Beige2
import com.epilabs.epiguard.ui.theme.Beige3
import com.epilabs.epiguard.ui.theme.BlueViolet1
import com.epilabs.epiguard.ui.theme.BlueViolet2
import com.epilabs.epiguard.ui.theme.BlueViolet3
import com.epilabs.epiguard.ui.theme.ButtonBlue
import com.epilabs.epiguard.ui.theme.DarkerButtonBlue
import com.epilabs.epiguard.ui.theme.DeepBlue
import com.epilabs.epiguard.ui.theme.LightGreen1
import com.epilabs.epiguard.ui.theme.LightGreen2
import com.epilabs.epiguard.ui.theme.LightGreen3
import com.epilabs.epiguard.ui.theme.OrangeYellow1
import com.epilabs.epiguard.ui.theme.OrangeYellow2
import com.epilabs.epiguard.ui.theme.OrangeYellow3
import com.epilabs.epiguard.ui.theme.TextWhite


@Composable
fun NewHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(AppColors.color_white)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .requiredHeight(height = 140.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 25.dp)
                    .padding(horizontal = 16.dp)
                    .requiredHeight(height = 35.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 320.dp, y = 0.dp)
                        .requiredSize(size = 35.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mask_group),
                        contentDescription = "pexels-christina-morillo-1181690 1",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = "EpiGuard",
                    color = Color(0xff343c6a),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier
                        .width(350.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.menu1),
                    contentDescription = "Group 692",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 0.dp, y = 10.dp)
                        .requiredWidth(width = 14.dp)
                        .requiredHeight(height = 18.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 80.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .requiredHeight(height = 40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.color_Gray_50)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 19.dp, y = 12.dp)
                        .requiredWidth(width = 158.dp)
                        .requiredHeight(height = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_normal),
                        contentDescription = "magnifying-glass 1",
                        colorFilter = ColorFilter.tint(Color(0xff718ebf)),
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = "Search for something",
                        color = Color(0xff8ba3cb),
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .offset(x = 24.dp, y = 0.dp)
                    )
                }
            }
        }
    }
}
// Data class for half-circle progress bar
data class ProgressBarData(
    val percentage: Float, // 0.0f to 100.0f
    val centerText: String,
    val title: String,
    val goal: String,
    val remaining: String
)

// Composable for half-circle progress bar (Seizure Detection Rate)
@Composable
fun HalfCircleProgressBar(
    data: ProgressBarData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp) // Fixed height to align with SeizureFreeDaysDisplay
            .padding(8.dp) // Reduced outer padding for tighter fit
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.color_white)
            .padding(12.dp), // Reduced inner padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = data.title,
            color = AppColors.color_Gray_900,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 1.dp)
        )




        // Divider
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 8.dp),
            color = AppColors.color_Gray_50
        )

        // Gauge
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp), // Reduced height for proportionality
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                val radius = width * 0.4f // Adjust radius to fit
                Offset(width / 2f, height) // Center at bottom

                // Background arc (full semi-circle)
                drawArc(
                    color = AppColors.color_Gray_50,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    style = Stroke(width = 12.dp.toPx()), // Reduced stroke width
                    topLeft = Offset(width / 2f - radius, height - radius),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
                )

                // Progress arc
                drawArc(
                    color = AppColors.color_violet,
                    startAngle = 180f,
                    sweepAngle = (data.percentage / 100f) * 180f,
                    useCenter = false,
                    style = Stroke(width = 12.dp.toPx()),
                    topLeft = Offset(width / 2f - radius, height - radius),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
                )
            }

            // Percentage below gauge
            Text(
                text = "${data.percentage.toInt()}%",
                color = AppColors.color_Gray_900,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .offset(y = 18.dp)
            )

            // Descriptive text below percentage
            Text(
                text = "Seizures Detected",
                color = AppColors.color_Gray_900,
                style = TextStyle(fontSize = 12.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .offset(y = 50.dp)
            )
        }
    }
}

// Composable for seizure-free days display
@Composable
fun SeizureFreeDaysDisplay(
    days: Int,
    goal: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp) // Fixed height to align with HalfCircleProgressBar
            .padding(8.dp) // Reduced outer padding for tighter fit
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.color_Gray_White)
            .padding(12.dp), // Reduced inner padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Seizure Free",
            color = AppColors.color_Gray_900,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .size(80.dp) // Reduced size for proportionality
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                size.width / 2
                drawArc(
                    color = AppColors.color_Gray_50,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 6.dp.toPx()) // Reduced stroke width
                )
                drawArc(
                    color = AppColors.color_violet,
                    startAngle = -90f,
                    sweepAngle = (days.toFloat() / goal) * 360f,
                    useCenter = false,
                    style = Stroke(width = 6.dp.toPx())
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$days",
                    color = AppColors.color_Gray_900,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                )
                Text(
                    text = "Days",
                    color = AppColors.color_Gray_900,
                    style = TextStyle(fontSize = 12.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(AppColors.color_violet)
            )
            Text(
                text = "Goal: $goal Days",
                color = AppColors.color_Gray_900,
                style = TextStyle(fontSize = 10.sp),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun SeizureStackedBarChart(
    data: List<Pair<Int, Int>> = listOf( // Default to 10 bars of sample data
        Pair(2, 5), Pair(1, 6), Pair(3, 4), Pair(2, 5), Pair(1, 6),
        Pair(3, 4), Pair(2, 5), Pair(6, 6), Pair(7, 4), Pair(7, 5)
    ), // List of (seizureDays, seizureFreeDays) per period
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(270.dp)
            .padding(horizontal = 10.dp) // Reduced inner padding
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.color_Gray_White)
            .padding(horizontal = 6.dp, vertical = 7.5.dp), // Consistent padding



    ) {
        Text(
            text = "Seizure Days Tracking",
            color = Color(0xff343c6a),
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .zIndex(1f)

        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 0.dp, y = 31.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
            )
            // Legend
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 16.dp, y = 17.dp) // Left-aligned for scrollable content
                    .requiredHeight(15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredHeight(15.dp)
                ) {
                    Text(
                        text = "Seizure Days",
                        color = Color(0xff718ebf),
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 135.dp, y = 0.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 115.dp, y = 2.dp)
                            .requiredSize(12.dp)
                            .clip(CircleShape)
                            .background(AppColors.color_Error_500) // Blue for seizure days
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 245.dp, y = 0.dp)
                        .requiredWidth(77.dp)
                        .requiredHeight(15.dp)
                ) {
                    Text(
                        text = "Seizure-Free Days",
                        color = Color(0xff718ebf),
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 18.dp, y = 0.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 0.dp, y = 2.dp)
                            .requiredSize(12.dp)
                            .clip(CircleShape)
                            .background(AppColors.color_violet) // Orange for seizure-free days
                    )
                }
            }
            // Chart area
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 18.dp, y = 43.dp)
            ) {
                // Y-axis labels (0 to 10)
                listOf(10, 8, 6, 4, 2, 0).forEachIndexed { index, value ->
                    Text(
                        text = "$value",
                        color = Color(0xff718ebf),
                        textAlign = TextAlign.End,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 0.dp, y = (index * 28.5).dp) // 171.dp / 6 = 28.5.dp per step
                    )
                }
                // Grid lines
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 11.dp)
                        .requiredHeight(142.5.dp) // 171.dp - 28.5.dp for labels
                        .width(100.dp)
                ) {
                    repeat(7) { index ->
                        HorizontalDivider(
                            modifier = Modifier
                                .requiredHeight(1.dp)
                                .offset(x = 0.dp, y = (index * 21.5).dp),
                            color = Color(0xfff3f3f5)
                        )
                    }
                }
                // Bars in LazyRow for horizontal scrolling
                LazyRow(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 30.dp, y = 11.dp)
                        .requiredHeight(142.5.dp)
                ) {
                    items(7) { index ->
                        val (seizureDays, seizureFreeDays) = data.getOrElse(index) { Pair(0, 0) }
                        val maxHeight = 150.dp // Max bar height
                        val seizureHeight = (seizureDays.toFloat() / 10 * maxHeight.value).dp
                        val seizureFreeHeight = (seizureFreeDays.toFloat() / 10 * maxHeight.value).dp

                        Box(
                            modifier = Modifier
                                .requiredWidth(44.dp) // Spacing per bar
                                .requiredHeight(177.5.dp)
                        ) {
                            // Seizure days bar
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .offset(x = 5.dp, y = (maxHeight - seizureHeight))
                                    .requiredWidth(7.dp)
                                    .requiredHeight(seizureHeight)
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(AppColors.color_Error_500) // Blue
                            )
                            // Seizure-free days bar
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .offset(x = 15.dp, y = (maxHeight - seizureFreeHeight))
                                    .requiredWidth(7.dp)
                                    .requiredHeight(seizureFreeHeight)
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(AppColors.color_violet) // Orange
                            )
                            // Day label
                            Text(
                                text = when (index % 7) {
                                    0 -> "Mon"
                                    1 -> "Tue"
                                    2 -> "Wed"
                                    3 -> "Thu"
                                    4 -> "Fri"
                                    5 -> "Sat"
                                    6 -> "Sun"
                                    else -> "W${index + 1}"
                                },
                                color = Color(0xff718ebf),
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 12.sp),
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .offset(x = 5.dp, y = (maxHeight + 13.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

// Dashboard composable with charts in the correct order
@ExperimentalFoundationApi
@Composable
fun Dashboard(navController: NavController, userId: Int) {
    val context = LocalContext.current
    val firstName = remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    // Fetch firstName from database
    LaunchedEffect(userId) {
        if (userId != -1) {
            val dbConnector = DatabaseConnector(context)
            val db = dbConnector.readableDatabase
            firstName.value = UserDAO.getUserFirstName(db, userId)
            db.close()
        } else {
            Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
            navController.navigate("sign_in")
        }
    }

    Box(modifier = Modifier.background(AppColors.color_Gray_50).fillMaxWidth()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp) // Space for BottomMenu
                .fillMaxWidth()
        ) {
            NewHeader()
            GreetingText(firstName = firstName.value)
            ChipSection(chips = listOf("Add Device", "Seizure Detector", "Live Feed"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Seizure Detector",
                        imageId = R.drawable.epiguard,
                        darkColor = BlueViolet1,
                        mediumColor = BlueViolet2,
                        lightColor = BlueViolet3,
                        route = "IpMjpegDetector/$userId"
                    ),
                    Feature(
                        title = "Wireless Camera",
                        imageId = R.drawable.analytics,
                        darkColor = LightGreen1,
                        mediumColor = LightGreen2,
                        lightColor = LightGreen3,
                        route = "ip_webcam"
                    ),
                    Feature(
                        title = "Seizure Detector Lab",
                        imageId = R.drawable.ai,
                        darkColor = OrangeYellow1,
                        mediumColor = OrangeYellow2,
                        lightColor = OrangeYellow3,
                        route = "ip_webcam_connector/$userId"
                    ),
                    Feature(
                        title = "Contacts and Alerts",
                        imageId = R.drawable.family,
                        darkColor = Beige1,
                        mediumColor = Beige2,
                        lightColor = Beige3,
                        route = "upload_video/$userId"
                    )
                ),
                navController = navController,
                userId = userId
            )
            // Row for HalfCircleProgressBar and SeizureFreeDaysDisplay
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 7.5.dp), // Consistent padding
                horizontalArrangement = Arrangement.SpaceBetween // Space charts to left and right
            ) {
                HalfCircleProgressBar(
                    data = ProgressBarData(
                        percentage = 72f,
                        centerText = "Seizure\nEvents",
                        title = "Seizure Detection Rate",
                        goal = "$100",
                        remaining = "$28"
                    ),
                    modifier = Modifier
                        .weight(1f) // 50% of screen width
                        .fillMaxWidth() // Ensure full width within weight
                )
                SeizureFreeDaysDisplay(
                    days = 42,
                    goal = 60,
                    modifier = Modifier
                        .weight(1f) // 50% of screen width
                        .fillMaxWidth() // Ensure full width within weight
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp), // Consistent padding
                horizontalArrangement = Arrangement.SpaceBetween // Space charts to left and right
            ) {            // Stacked bar chart below
                SeizureStackedBarChart(
                    data = listOf(
                        Pair(2, 5), // Week 1: 2 seizure days, 5 seizure-free
                        Pair(1, 6), // Week 2
                        Pair(3, 4),  // Week 3
                        Pair(6, 6),
                        Pair(7, 4),
                        Pair(7, 5),
                        Pair(1, 6), // Week 2


                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Detector", R.drawable.ic_videocam),
                BottomMenuContent("Models", R.drawable.codesandbox),
                BottomMenuContent("Contacts", R.drawable.users1),
                BottomMenuContent("Settings", R.drawable.settings)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// UserHeader composable
@Composable
fun UserHeader(modifier: Modifier = Modifier, name: String = "Fulano") {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
            .requiredHeight(52.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xff8a19d6))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.guy1),
                    contentDescription = "User profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column {
                Text(
                    text = name,
                    color = Color(0xff2c2c2c),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Administrador",
                    color = Color(0xff8c8c8c),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable._d_notification_bell_icon_illustration_png),
                contentDescription = "Notifications",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(24.dp)
            )
            Badge(
                containerColor = Color(0xffff4d4d),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 4.dp, y = (-4).dp)
            )
        }
    }
}

// GreetingText composable
@Composable
fun GreetingText(modifier: Modifier = Modifier, firstName: String? = null) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Hi ${firstName ?: "Guest 👋"}",
            color = Color.Black,
            fontSize = 20.sp
        )
        Text(
            text = "Welcome back to your EpiGuard Dashboard!",
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

// SearchBar composable
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
            .requiredHeight(height = 47.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                tint = Color(0xffaeaeae),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Buscar...",
                color = Color(0xffaeaeae),
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

// BottomMenu composable
@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember { mutableIntStateOf(initialSelectedItemIndex) }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().background(DeepBlue).padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) { selectedItemIndex = index }
        }
    }
}

// BottomMenuItem composable
@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onItemClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(if (isSelected) activeHighlightColor else Color.Transparent).padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(text = item.title, color = if (isSelected) activeTextColor else inactiveTextColor)
    }
}

// ChipSection composable
@Composable
fun ChipSection(chips: List<String>) {
    var selectedChipIndex by remember { mutableIntStateOf(0) }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable { selectedChipIndex = it }
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue)
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

// CurrentMeditation composable
@Composable
fun CurrentMeditation() {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.blue_violet_gradient)
            .fillMaxWidth()
            .requiredHeight(120.dp)
            .clipToBounds()
    ) {
        Image(
            painter = painterResource(id = R.drawable.heroimage),
            contentDescription = "AI Assistant",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(width = 190.dp, height = 190.dp)
                .padding(end = 5.dp)
                .zIndex(1f)
                .clipToBounds()
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(IntrinsicSize.Max)
                .padding(start = 15.dp, end = 8.dp, top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "New AI Test Lab!",
                style = TextStyle(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp
                ),
                color = TextWhite,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Upload seizure videos & \ntest AI models for epilepsy care.",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                ),
                color = TextWhite.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(120.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.color_Gray_White)
                    .padding(horizontal = 8.dp)
                    .zIndex(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Try Now",
                    tint = AppColors.color_violet,
                    modifier = Modifier.size(11.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Try Now",
                    color = AppColors.color_violet,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// FeatureSection composable
@Composable
fun FeatureSection(features: List<Feature>, navController: NavController, userId: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.labelMedium
        )
        // First row: Seizure Detector, Tracker and Analytics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.5.dp)
        ) {
            FeatureItem(
                feature = features[0],
                navController = navController,
                userId = userId,
                modifier = Modifier.weight(1f)
            )
            FeatureItem(
                feature = features[1],
                navController = navController,
                userId = userId,
                modifier = Modifier.weight(1f)
            )
        }
        // Second row: Test Lab, Contacts and Alerts
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.5.dp)
        ) {
            FeatureItem(
                feature = features[2],
                navController = navController,
                userId = userId,
                modifier = Modifier.weight(1f)
            )
            FeatureItem(
                feature = features[3],
                navController = navController,
                userId = userId,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// FeatureItem composable
@Composable
fun FeatureItem(
    feature: Feature,
    navController: NavController,
    userId: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BoxWithConstraints(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
            .clickable {
                if (userId != -1 || feature.route == "model_classification/$userId") {
                    navController.navigate(feature.route)
                } else {
                    Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                    navController.navigate("sign_in")
                }
            }
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())
        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)
        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColoredPath, color = feature.mediumColor)
            drawPath(path = lightColoredPath, color = feature.lightColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = feature.imageId),
                    contentDescription = "${feature.title} image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(125.dp)
                )
                Text(
                    text = feature.title,
                    style = MaterialTheme.typography.labelMedium,
                    lineHeight = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(widthDp = 390, heightDp = 1420)
@Composable
private fun DashboardScreenPreview() {
    Dashboard(navController = NavController(LocalContext.current), userId = 1)
}