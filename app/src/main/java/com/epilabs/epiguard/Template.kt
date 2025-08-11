package com.epilabs.epiguard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.components.BottomMenuContent

@Composable
fun Template(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
    ) {
        NewHeader()
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Detector", R.drawable.ic_videocam),
                BottomMenuContent("Models", R.drawable.codesandbox),
                BottomMenuContent("Contacts", R.drawable.users1),
                BottomMenuContent("Settings", R.drawable.settings)
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Position at bottom of screen
        )
    }
}



@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun TemplatePreview() {
    Template(Modifier)
}

