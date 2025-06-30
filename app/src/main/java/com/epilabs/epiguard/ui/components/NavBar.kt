package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.R
import com.epilabs.epiguard.ui.theme.MyColors
import com.epilabs.epiguard.ui.theme.MyTypes

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    selectedItem: String = "Home",
    onItemSelected: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(83.dp)
            .background(MyColors.color_white),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Navigation Items
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // Detector Item
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 20.dp)
                    .requiredWidth(70.dp)
                    .requiredHeight(41.dp)
                    .clickable { onItemSelected("Detector") }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Detector Icon",
                        colorFilter = ColorFilter.tint(
                            if (selectedItem == "Detector") MyColors.color_violet else MyColors.color_grey
                        ),
                        modifier = Modifier.requiredSize(21.dp)
                    )
                    Text(
                        text = "Detector",
                        color = if (selectedItem == "Detector") MyColors.color_violet else MyColors.color_grey,
                        textAlign = TextAlign.Center,
                        style = MyTypes.typography.labelSmall,
                        modifier = Modifier.requiredWidth(60.dp)
                    )
                }
            }

            // Contacts Item
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 108.dp)
                    .requiredWidth(70.dp)
                    .requiredHeight(41.dp)
                    .clickable { onItemSelected("Contacts") }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.contacts),
                        contentDescription = "Contacts Icon",
                        colorFilter = ColorFilter.tint(
                            if (selectedItem == "Contacts") MyColors.color_violet else MyColors.color_grey
                        ),
                        modifier = Modifier.requiredSize(21.dp)
                    )
                    Text(
                        text = "Contacts",
                        color = if (selectedItem == "Contacts") MyColors.color_violet else MyColors.color_grey,
                        textAlign = TextAlign.Center,
                        style = MyTypes.typography.labelSmall
                    )
                }
            }

            // Home Item
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 197.dp)
                    .requiredWidth(70.dp)
                    .requiredHeight(41.dp)
                    .clickable { onItemSelected("Home") }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home Icon",
                        colorFilter = ColorFilter.tint(
                            if (selectedItem == "Home") MyColors.color_violet else MyColors.color_grey
                        ),
                        modifier = Modifier.requiredSize(21.dp)
                    )
                    Text(
                        text = "Home",
                        color = if (selectedItem == "Home") MyColors.color_violet else MyColors.color_grey,
                        textAlign = TextAlign.Center,
                        style = MyTypes.typography.labelSmall
                    )
                }
            }

            // Menu Item
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 285.dp)
                    .requiredWidth(70.dp)
                    .requiredHeight(41.dp)
                    .clickable { onItemSelected("Menu") }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu Icon",
                        colorFilter = ColorFilter.tint(
                            if (selectedItem == "Menu") MyColors.color_violet else MyColors.color_grey
                        ),
                        modifier = Modifier.requiredSize(21.dp)
                    )
                    Text(
                        text = "Menu",
                        color = if (selectedItem == "Menu") MyColors.color_violet else MyColors.color_grey,
                        textAlign = TextAlign.Center,
                        style = MyTypes.typography.labelSmall,
                        modifier = Modifier.requiredWidth(34.dp)
                    )
                }
            }
        }

        // Divider Line (iPhone-style swipe bar)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MyColors.color_grey)
        )
    }
}