package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arduino_led_app.ui.composables.CustomHeader

@Composable
fun AppInfoScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight() // Fill the available height
    ) {
        CustomHeader(imageVector = Icons.Filled.Info, text = "App Info")

        Spacer(modifier = Modifier.weight(1f)) // Flexible spacer

        // First Icon and Description
        Icon(
            imageVector = Icons.Filled.ColorLens,
            contentDescription = "Color",
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = "Screen to choose LED color",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Flexible spacer

        // Second Icon and Description
        Icon(
            imageVector = Icons.Filled.Animation,
            contentDescription = "Animation",
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = "Screen to animate rainbow in chosen direction",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Flexible spacer

        // Third Icon and Description
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Settings",
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = "Screen to adjust the brightness of the light and the speed of the animation",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Flexible spacer
    }
}