package com.epilabs.epiguard

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.NavController
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.database.UserDAO
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes
import com.epilabs.epiguard.ui.components.BottomNav
import com.epilabs.epiguard.ui.components.Component1
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.theme.AppShadows

@Composable
fun Dashboard(modifier: Modifier = Modifier, navController: NavController, userId: Int) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val firstName = remember { mutableStateOf<String?>(null) }

    // Fetch firstName from database
    LaunchedEffect(userId) {
        if (userId != -1) {
            val dbConnector = DatabaseConnector(context)
            val db = dbConnector.readableDatabase
            firstName.value = UserDAO.getUserFirstName(db, userId)
            db.close()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 844.dp)
            .background(color = AppColors.color_Gray_50)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 16.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .background(color = AppColors.color_Gray_50)
                .padding(bottom = 80.dp)
        ) {
            Component1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
         
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .height(40.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Hi ${firstName.value ?: "Guest \uD83D\uDC4B"}",
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .weight(1f)
                            )

                        }
                        Text(
                            text = "Welcome back to your EpiGuard Dashboard!",
                            color = Color.Black,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 163.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 163.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banner4),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(12.dp))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = (-40).dp, y = (-55).dp)
                            .requiredSize(size = 154.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffd9d9d9).copy(alpha = 0.2f))
                    )
                    Text(
                        text = "Meet Sera \nyour AI assistant",
                        color = AppColors.color_white,
                        style = AppTypes.type_Body_Large_400,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 11.dp, y = 31.dp)
                    )
                    Text(
                        text = "Ask me anything about EpiGuard.\nI’m here to help.",
                        color = AppColors.color_white,
                        lineHeight = 12.5.em,
                        style = AppTypes.type_Body_Regular_400,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 11.dp, y = 93.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 68.dp, y = 151.dp)
                            .requiredSize(size = 83.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffd9d9d9).copy(alpha = 0.2f))
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        modifier = Modifier
                            .align(alignment = Alignment.BottomStart)
                            .offset(x = 141.dp, y = (-6).dp)
                    ) {
                        ActiveYes()
                        Badge()
                        Badge()
                        Badge()
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Categories",
                        color = AppColors.color_violet,
                        lineHeight = 9.38.em,
                        style = AppTypes.type_Body_Regular_400
                    )
                    Text(
                        text = "See All",
                        color = AppColors.color_Gray_600,
                        textAlign = TextAlign.Center,
                        lineHeight = 10.71.em,
                        style = AppTypes.type_Typography_Body_Small
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Box 1: btn1
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = AppColors.color_white)
                                    .clickable { navController.navigate("model_classification/$userId") }
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.codesandbox),
                                            contentDescription = "Classify",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "btn1",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_Regular_400
                            )
                        }
                        // Box 2: btn2
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = AppColors.color_white)
                                    .clickable {
                                        if (userId != -1) {
                                            navController.navigate("add_contact/$userId")
                                        } else {
                                            Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                                            navController.navigate("sign_in")
                                        }
                                    }
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.face_detection),
                                            contentDescription = "Contact",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "btn2",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                        // Box 3: btn3
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = AppColors.color_white)
                                    .clickable {
                                        if (userId != -1) {
                                            navController.navigate("view_contacts/$userId")
                                        } else {
                                            Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                                            navController.navigate("sign_in")
                                        }
                                    }
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.diary),
                                            contentDescription = "Contacts",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "btn3",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }

                        // Box 4: btn4
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 62.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = AppColors.color_white)
                                    .clickable {
                                        if (userId != -1) {
                                            navController.navigate("upload_video/$userId")
                                        } else {
                                            Toast.makeText(context, "Invalid user ID. Please sign in again.", Toast.LENGTH_SHORT).show()
                                            navController.navigate("sign_in")
                                        }
                                    }
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .requiredSize(size = 62.dp)
                                        .padding(all = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 35.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.evaluation),
                                            contentDescription = "Upload",
                                            modifier = Modifier
                                                .requiredWidth(width = 35.dp)
                                                .requiredHeight(height = 35.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = (-34).dp, y = (-34).dp)
                                        .requiredSize(size = 68.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = Color.White.copy(alpha = 0.2f))
                                )
                            }
                            Text(
                                text = "Upload Video",
                                color = AppColors.color_Gray_600,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.5.em,
                                style = AppTypes.type_Body_small_400
                            )
                        }
                    }

                    // Component3 from Code 2
                    Component3(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Nearby Medical Centers",
                        color = AppColors.color_violet,
                        lineHeight = 9.38.em,
                        style = AppTypes.type_Body_Regular_400
                    )
                    Text(
                        text = "See All",
                        color = AppColors.color_Gray_600,
                        textAlign = TextAlign.Center,
                        lineHeight = 10.71.em,
                        style = AppTypes.type_Body_Regular_400
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Card(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = AppColors.color_Gray_600
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .requiredHeight(height = 252.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(1.dp, AppColors.color_Gray_50, RoundedCornerShape(8.dp))
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 252.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mayo),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 121.dp)
                                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Sunrise Health Clinic",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 10.71.em,
                                            style = AppTypes.type_Body_small_400,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.sms),
                                                contentDescription = "vuesax/linear/location",
                                                modifier = Modifier.requiredSize(size = 14.dp)
                                            )
                                            Text(
                                                text = "123 Oak Street, CA 98765",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_Regular_400
                                            )
                                        }
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "5.0",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_small_400
                                            )
                                            Image(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "Frame 1000000929"
                                            )
                                        }
                                        Text(
                                            text = "(58 Reviews)",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = AppColors.color_Gray_600
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.Start),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.camera),
                                            contentDescription = "vuesax/bold/routing",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "2.5 km/40min",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mail),
                                            contentDescription = "vuesax/bold/hospital",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "Hospital",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.sms),
                                contentDescription = "Frame 1000001036",
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .offset(x = (-8).dp, y = (-8).dp)
                                    .clip(shape = RoundedCornerShape(52.dp))
                                    .padding(all = 6.dp)
                                    .background(color = Color.White)
                            )
                        }
                    }
                    Card(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = AppColors.color_Gray_600
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .requiredHeight(height = 252.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(1.dp, AppColors.color_Gray_50, RoundedCornerShape(8.dp))
                            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 252.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.og),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 121.dp)
                                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Sunrise Health Clinic",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 10.71.em,
                                            style = AppTypes.type_Body_small_400,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.sms),
                                                contentDescription = "vuesax/linear/location",
                                                modifier = Modifier.requiredSize(size = 14.dp)
                                            )
                                            Text(
                                                text = "123 Oak Street, CA 98765",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_Regular_400
                                            )
                                        }
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "5.0",
                                                color = AppColors.color_Gray_600,
                                                lineHeight = 12.5.em,
                                                style = AppTypes.type_Body_small_400
                                            )
                                            Image(
                                                painter = painterResource(id = R.drawable.users),
                                                contentDescription = "Frame 1000000929"
                                            )
                                        }
                                        Text(
                                            text = "(58 Reviews)",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = AppColors.color_Gray_600
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.Start),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.camera),
                                            contentDescription = "vuesax/bold/routing",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "2.5 km/40min",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mail),
                                            contentDescription = "vuesax/bold/hospital",
                                            modifier = Modifier.requiredSize(size = 16.dp)
                                        )
                                        Text(
                                            text = "Hospital",
                                            color = AppColors.color_Gray_600,
                                            lineHeight = 12.5.em,
                                            style = AppTypes.type_Body_small_400
                                        )
                                    }
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.sms),
                                contentDescription = "Frame 1000001036",
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .offset(x = (-8).dp, y = (-8).dp)
                                    .clip(shape = RoundedCornerShape(52.dp))
                                    .padding(all = 6.dp)
                                    .background(color = Color.White)
                            )
                        }
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            /* Your main screen content here */
            BottomNav(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun ActiveYes(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 30.dp)
            .requiredHeight(height = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(40.dp))
                .background(color = AppColors.color_white)
        )
    }
}

@Composable
fun Component3(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            modifier = Modifier.fillMaxSize()
        ) {
            // Image section (~80% height)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // ~80% of 230.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.room),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                )


                Text(
                    text = "Living room",
                    color = AppColors.color_Gray_700,
                    lineHeight = 1.71.em,
                    style = AppTypes.type_Body_small_400,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 16.dp, y = 198.dp)
                )
            }

            // White strip at the bottom (~20% height)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.color_white)
                    .clip(RoundedCornerShape(10.dp))

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(10.dp))

                ) {
                    // Left side: Two rows of text
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
                    ) {
                        Text(
                            text = "Bedroom",
                            color = AppColors.color_Gray_700,
                            lineHeight = 1.71.em,
                            style = AppTypes.type_Body_small_400, // Bolder style
                            modifier = Modifier // Smaller, non-bold text
                                .weight(2f)
                        )
                        Text(
                            text = "Camera Feed Active",
                            color = AppColors.color_Gray_700,
                            lineHeight = 1.43.em,
                            style = AppTypes.type_Body_small_400,
                            modifier = Modifier // Smaller, non-bold text

                        )
                    }
                    // Right side: Three circular icons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(AppColors.color_Gray_700)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.skip_backward),
                                contentDescription = "Icon 1",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center),

                                contentScale = ContentScale.Fit,

                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(AppColors.color_Gray_700)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = "Icon 2",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center),
                                contentScale = ContentScale.Fit,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(AppColors.color_Gray_700)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.full_screen),
                                contentDescription = "Icon 3",
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(Alignment.Center),
                                contentScale = ContentScale.Fit,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun DashboardPreview() {
    Dashboard(Modifier, navController = NavController(LocalContext.current), userId = 1)
}