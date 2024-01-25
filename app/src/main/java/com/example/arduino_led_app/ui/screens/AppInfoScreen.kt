package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.ArrowBack
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
        modifier = Modifier.padding(16.dp)
    ) {
        CustomHeader(imageVector = Icons.Filled.Info, text = "App Info")

        Spacer(modifier = Modifier.height(20.dp))
        // Symbol (Icon)
        Icon(
            imageVector = Icons.Filled.ColorLens,
            contentDescription = "Color",
            modifier = Modifier.size(60.dp)
        )

        // Opis symbolu
        Text(
            text = "Screen to choose LED color",
            fontSize = 18.sp,
            textAlign = TextAlign.Center, // Wyśrodkowany tekst
            modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            imageVector = Icons.Filled.Animation,
            contentDescription = "Color",
            modifier = Modifier.size(60.dp)
        )


        // Opis symbolu
        Text(
            text = "Screen to animate rainbow in chosen direction",
            fontSize = 18.sp,
            textAlign = TextAlign.Center, // Wyśrodkowany tekst
            modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Color",
            modifier = Modifier.size(60.dp)
        )

        // Opis symbolu
        Text(
            text = "Screen to adjust the brightness of the light and the speed of the animation",
            fontSize = 18.sp,
            textAlign = TextAlign.Center, // Wyśrodkowany tekst
            modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally)
        )
    }
}