package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
        // Navigation Items Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavBarItem(
                iconId = R.drawable.camera,
                label = "Detector",
                isSelected = selectedItem == "Detector",
                onClick = { onItemSelected("Detector") }
            )
            NavBarItem(
                iconId = R.drawable.contacts,
                label = "Contacts",
                isSelected = selectedItem == "Contacts",
                onClick = { onItemSelected("Contacts") }
            )
            NavBarItem(
                iconId = R.drawable.home,
                label = "Home",
                isSelected = selectedItem == "Home",
                onClick = { onItemSelected("Home") }
            )
            NavBarItem(
                iconId = R.drawable.menu,
                label = "Menu",
                isSelected = selectedItem == "Menu",
                onClick = { onItemSelected("Menu") }
            )
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

@Composable
fun NavBarItem(
    iconId: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .requiredWidth(70.dp)
            .requiredHeight(41.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "$label Icon",
            colorFilter = ColorFilter.tint(
                if (isSelected) MyColors.color_violet else MyColors.color_grey
            ),
            modifier = Modifier.requiredSize(21.dp)
        )
        Text(
            text = label,
            color = if (isSelected) MyColors.color_violet else MyColors.color_grey,
            textAlign = TextAlign.Center,
            style = MyTypes.typography.labelSmall
        )
    }
}
