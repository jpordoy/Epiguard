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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

    Box(modifier = Modifier.background(AppColors.color_Gray_50).fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp) // Space for BottomMenu
        ) {
            UserHeader()
            GreetingText(firstName = firstName.value)
            PercentageCircleRow()
            //SearchBar()
            ChipSection(chips = listOf("Add Device", "Seizure Detector", "Live Feed",))
            CurrentMeditation()
            FeatureSection(features = listOf(
                Feature(
                    title = "Seizure Detector",
                    imageId = R.drawable.epiguard, // Replace with actual resource
                    darkColor = BlueViolet1,
                    mediumColor = BlueViolet2,
                    lightColor = BlueViolet3,
                    route = "model_classification/$userId"
                ),
                Feature(
                    title = "Tracker and Analytics",
                    imageId = R.drawable.analytics, // Replace with actual resource
                    darkColor = LightGreen1,
                    mediumColor = LightGreen2,
                    lightColor = LightGreen3,
                    route = "add_contact/$userId"
                ),
                Feature(
                    title = "Test Lab",
                    imageId = R.drawable.untitled_design, // Replace with actual resource
                    darkColor = OrangeYellow1,
                    mediumColor = OrangeYellow2,
                    lightColor = OrangeYellow3,
                    route = "view_contacts/$userId"
                ),
                Feature(
                    title = "Contacts and Alerts",
                    imageId = R.drawable.family, // Replace with actual resource
                    darkColor = Beige1,
                    mediumColor = Beige2,
                    lightColor = Beige3,
                    route = "upload_video/$userId"
                )
            ), navController = navController, userId = userId)
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Detector", R.drawable.ic_bubble),
            BottomMenuContent("Ai Lab", R.drawable.ic_moon),
            BottomMenuContent("Contacts", R.drawable.ic_music),
            BottomMenuContent("Settings", R.drawable.ic_profile)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

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

@Composable
fun PercentageCircleRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PercentageCircle(percentage = 100, label = "Seizures Detected")
        PercentageCircle(percentage = 100, label = "Seizure Free")
        PercentageCircle(percentage = 100, label = "Text 3")
    }
}

@Composable
fun PercentageCircle(percentage: Int, label: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(90.dp) // consistent size
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = Color.LightGray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10.dp.toPx())
            )
            drawArc(
                color = ButtonBlue, // Or any app color
                startAngle = -90f,
                sweepAngle = (percentage / 100f) * 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10.dp.toPx())
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$percentage%",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}


@Composable
fun CurrentMeditation() {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.blue_violet_gradient)
            .fillMaxWidth()
            .requiredHeight(120.dp) // Total height including padding
            .clipToBounds() // Allow image overflow
    ) {
        Image(
            painter = painterResource(id = R.drawable.heroimage), // Your AI bot drawable
            contentDescription = "AI Assistant",
            contentScale = ContentScale.Fit, // Preserve aspect ratio
            modifier = Modifier
                .align(Alignment.TopEnd) // Align top-right
                .size(width = 190.dp, height = 190.dp) // Match/exceed visible height
                .padding(end = 5.dp) // Match horizontal padding
                .zIndex(1f)
                .clipToBounds() // Allow top overflow
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(IntrinsicSize.Max)
                .padding(start = 15.dp, end = 8.dp, top = 20.dp, bottom = 20.dp) ,// Match inner padding
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
            Spacer(modifier = Modifier.height(12.dp)) // Space between text and button

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(120.dp) // fixed width to help center the content
                    .height(36.dp) // increase height slightly for better tap area
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.color_Gray_White)
                    .padding(horizontal = 8.dp) // internal padding to center nicely
                    .zIndex(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Try Now",
                    tint = AppColors.color_violet,
                    modifier = Modifier
                        .size(11.dp) // icon size matches text height roughly
                )
                Spacer(modifier = Modifier.width(6.dp)) // spacing between icon and text
                Text(
                    text = "Try Now",
                    color = AppColors.color_violet,
                    fontSize = 14.sp // ensure it's balanced with icon
                )
            }


        }
    }
}


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
                    modifier = Modifier.size(110.dp)
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
@Preview(widthDp = 390, heightDp = 920)
@Composable
private fun DashboardScreenPreview() {
    Dashboard(navController = NavController(LocalContext.current), userId = 1)
}