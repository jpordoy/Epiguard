package com.epilabs.epiguard.ui.components.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epilabs.epiguard.ui.components.NavBar88Pt

@Preview
@Composable
fun LayoutExampleScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        NavBar88Pt(title = "My Profile")
        // Rest of the screen
        Text("User Profile Details here...", modifier = Modifier.padding(16.dp))
    }
}