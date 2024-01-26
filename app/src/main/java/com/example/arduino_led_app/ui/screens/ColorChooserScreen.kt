package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.ui.composables.CustomHeader
import com.example.arduino_led_app.ui.composables.CustomSwitch
import com.example.arduino_led_app.utils.command.CommandBuilder
import com.example.arduino_led_app.utils.command.FunctionValue
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorChooserScreen(onCommandChange: (String) -> Unit = {}) {
    val controller = rememberColorPickerController()
    val pulse = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomHeader(text = "Choose Color", imageVector = Icons.Filled.ColorLens)
        }

        Spacer(modifier = Modifier.weight(0.1f)) // Dynamic spacer for upper part

        // Color Picker
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3.5f), // Adjusted weight for color picker
            controller = controller
        )

        // Alpha Tile directly below Color Picker
        AlphaTile(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .shadow(4.dp, RoundedCornerShape(12.dp)),
            controller = controller
        )

        // Custom Switch directly below Alpha Tile
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomSwitch(description = "Pulse", switchState = pulse)
        }

        Spacer(modifier = Modifier.weight(1f)) // Dynamic spacer for lower part
    }

    LaunchedEffect(pulse.value, controller.selectedColor.value) {
        val (red, green, blue) = controller.selectedColor.value.toRgbInt()
        // onCommandChange(buildCommandToSend(red, green, blue, pulse.value))
    }
}


private fun buildCommandToSend(r: Int, g: Int, b : Int, pulse: Boolean): String
{
    return CommandBuilder.instance.apply {
        clearState()
        appendFunction(if (pulse) FunctionValue.PULSE else FunctionValue.FILL)
        appendRGB(r, g, b)
    }.buildString()
}

@Stable
fun Color.toRgbInt(): Triple<Int, Int, Int> {

    val redInt = (red * 255.0f).toInt()
    val greenInt = (green * 255.0f).toInt()
    val blueInt = (blue * 255.0f).toInt()

    return Triple(redInt, greenInt, blueInt)
}

