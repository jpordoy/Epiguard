package com.epilabs.epiguard.components.contacts_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.epilabs.epiguard.ui.AppColors
import com.epilabs.epiguard.ui.AppTypes

@Composable
fun UserProfileNew(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF9F8FD))
    ) {
        ProfileTabs1()
    }
}

@Composable
fun ProfileTabs1(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F8FD))
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Stylethree1(
            coins1690 = false,
            email1694 = true,
            phoneNum1698 = true
        )

        TotalTab3TypeTextStyleBorder1(
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
                        .padding(bottom = 16.dp)
                ) {
                    Frame23355() //CertificationsList()
                }
                1 -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    CertificationsList()
                }
                2 -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    ContactAboutForm()
                }
            }
        }
    }
}


@Composable
fun Stylethree1(
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
        TypeDefaultSize961()

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
                    Image(
                        painter = painterResource(id = R.drawable.mail1),
                        contentDescription = "check_circle",
                        colorFilter = ColorFilter.tint(AppColors.color_Primary_500),
                        modifier = Modifier.requiredSize(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Added padding between Text and Image
                    Text(
                        text = "albertflores@mail.com",
                        color = AppColors.color_Gray_900,
                        lineHeight = 1.43.em,
                        style = AppTypes.type_Body_small_400
                    )

                }
            }
            if (phoneNum1698) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "check_circle",
                        colorFilter = ColorFilter.tint(AppColors.color_Primary_500),
                        modifier = Modifier.requiredSize(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Added padding between Text and Image

                    Text(
                        text = "(209) 555-0104",
                        color = AppColors.color_Gray_900,
                        lineHeight = 1.43.em,
                        style = AppTypes.type_Body_small_400
                    )

                }
            }
        }
    }
}

@Composable
fun TypeDefaultSize961(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.requiredSize(96.dp)
    ) {
        TypeDefault1()
    }
}

@Composable
fun TypeDefault1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(226.dp))
            .background(Color(0xFFF9F8FD))
    ) {
        Image(
            painter = painterResource(id = R.drawable.guy2),
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun TotalTab3TypeTextStyleBorder1(
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
        TabItem1("Details", selectedTab == 0, { onTabSelected(0) }, 20, Modifier.weight(1f))
        TabItem1("Info", selectedTab == 1, { onTabSelected(1) }, 20, Modifier.weight(1f))
        TabItem1("About", selectedTab == 2, { onTabSelected(2) }, 20, Modifier.weight(1f))
    }
}

@Composable
fun TabItem1(
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

@Preview(widthDp = 375)
@Composable
private fun UserProfileNewPreview() {
    UserProfileNew()
}