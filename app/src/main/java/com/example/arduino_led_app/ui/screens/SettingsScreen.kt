package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.ui.composables.CustomHeader
import com.example.arduino_led_app.ui.composables.CustomSlider
import com.example.arduino_led_app.utils.command.CommandBuilder

@Composable
fun SettingsScreen(onCommandChange: (String) -> Unit = {})
{
    val brightness = remember { mutableIntStateOf(0) }
    val speed = remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        CustomHeader(text = "Settings", imageVector = Icons.Filled.Settings)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomSlider(Icons.Filled.Brightness6,"Brightness", brightness)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomSlider(Icons.Filled.Speed,"Speed", speed)
        }

        LaunchedEffect(brightness.intValue, speed.intValue)
        {
            onCommandChange(buildCommandToSend(brightness.intValue, speed.intValue))
        }


    }

}

private fun buildCommandToSend(brightness: Int, speed: Int): String {
    return CommandBuilder.instance.apply {
        clearState()
        appendWait(convertRange(100 - speed,100,255))
        appendBrightness(convertRange(brightness,100,255))
    }.buildString()
}

fun convertRange(value: Int, oldMax: Int, newMax: Int): Int {
    return (value.toDouble() / oldMax * newMax).toInt()
}
