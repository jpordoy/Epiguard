package com.epilabs.epiguard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.epilabs.epiguard.R
import com.epilabs.epiguard.components.contacts_component.Frame23355
import com.epilabs.epiguard.ui.components.NavBar
import com.epilabs.epiguard.ui.components.NavBar88Pt



@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onBottomNavClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            NavBar88Pt(
                title = "Contact Profile",
                iconResId = R.drawable.settings1,
                onBackClick = onBackClick,
                onIconClick = onSettingsClick
            )
        },
        bottomBar = {
            NavBar(
                selectedItem = "Home",
                onItemSelected = onBottomNavClick
            )
        },
        containerColor = Color(0xFFF9F8FD)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ProfileTabs()
        }
    }
}

@Composable
fun ProfileTabs(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F8FD))
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Stylethree(
            coins1690 = false,
            email1694 = true,
            phoneNum1698 = true
        )

        TotalTab3TypeTextStyleBorder(
            selectedTab = selectedTab,
            onTabSelected = { index -> selectedTab = index }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(AppColors.color_white)
                .border(
                    border = BorderStroke(1.dp, AppColors.color_light_grey_border),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 25.dp, vertical = 16.dp)
        ) {
            when (selectedTab) {
                0 -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp) // Padding below Frame23355
                ) {
                    Frame23355()
                }
                1 -> Text("Reviews Content", modifier = Modifier.align(Alignment.Center))
                2 -> Text("Info Content", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun Stylethree(
    modifier: Modifier = Modifier,
    coins1690: Boolean = false,
    email1694: Boolean = false,
    phoneNum1698: Boolean = false
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.color_pale_grey)
            .padding(horizontal = 25.dp, vertical = 12.dp)
    ) {
        TypeDefaultSize96()

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Albert Flores",
                color = AppColors.color_Gray_900,
                textAlign = TextAlign.Center,
                lineHeight = 1.44.em,
                style = AppTypes.type_Header_Header_2,
                modifier = Modifier.fillMaxWidth()
            )
            if (email1694) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "albertflores@mail.com",
                        color = AppColors.color_Gray_900,
                        lineHeight = 1.43.em,
                        style = AppTypes.type_Body_small_400
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sms),
                        contentDescription = "check_circle",
                        colorFilter = ColorFilter.tint(AppColors.color_Primary_500),
                        modifier = Modifier.requiredSize(18.dp)
                    )
                }
            }
            if (phoneNum1698) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "(209) 555-0104",
                        color = AppColors.color_Gray_900,
                        lineHeight = 1.43.em,
                        style = AppTypes.type_Body_small_400
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sms),
                        contentDescription = "check_circle",
                        colorFilter = ColorFilter.tint(AppColors.color_Primary_500),
                        modifier = Modifier.requiredSize(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TypeDefaultSize96(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.requiredSize(96.dp)
    ) {
        TypeDefault()
    }
}

@Composable
fun TypeDefault(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(226.dp))
            .background(Color(0xFFF9F8FD))
    ) {
        Image(
            painter = painterResource(id = R.drawable.guy2),
            contentDescription = "replace here",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun TotalTab3TypeTextStyleBorder(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.color_Gray_100)
            .border(
                BorderStroke(1.dp, AppColors.color_light_grey_border),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
    ) {
        TabItem("Products", selectedTab == 0, { onTabSelected(0) }, 20, Modifier.weight(1f))
        TabItem("Reviews", selectedTab == 1, { onTabSelected(1) }, 20, Modifier.weight(1f))
        TabItem("Info", selectedTab == 2, { onTabSelected(2) }, null, Modifier.weight(1f))
    }
}

@Composable
fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    count: Int? = null,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(28.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) AppColors.color_Primary_500 else AppColors.color_Gray_100)
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) AppColors.color_white else AppColors.color_Gray_600,
            textAlign = TextAlign.Center,
            lineHeight = 1.43.em,
            style = AppTypes.type_Typography_Body_Small,
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
        )
        if (count != null) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(AppColors.color_Error_500)
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = count.toString(),
                    color = AppColors.color_white,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.33.em,
                    style = AppTypes.type_Typography_Body_Small,
                    modifier = Modifier
                        .size(width = 15.dp, height = 18.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Preview(widthDp = 375, heightDp = 700)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}