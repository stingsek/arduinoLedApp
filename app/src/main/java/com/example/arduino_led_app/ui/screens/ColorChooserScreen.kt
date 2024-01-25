package com.example.arduino_led_app.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.ui.composables.CustomHeader
import com.example.arduino_led_app.ui.composables.CustomSwitch
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorChooserScreen(onCommandChange: (String) -> Unit
)
{
    val controller = rememberColorPickerController()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        CustomHeader(text = "Choose Color", imageVector = Icons.Filled.ColorLens)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp)
    ) {


        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
                .padding(10.dp),
            controller = controller,
            onColorChanged = {
                val (red, green, blue) = it.color.toRgbInt()
                Log.d("Color", "Red: $red,Green: $green,Blue: $blue,")
            }

        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(12.dp)),
                controller = controller
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            CustomSwitch(description = "Pulse")
        }

    }



}

@Stable
fun Color.toRgbInt(): Triple<Int, Int, Int> {

    val redInt = (red * 255.0f).toInt()
    val greenInt = (green * 255.0f).toInt()
    val blueInt = (blue * 255.0f).toInt()

    return Triple(redInt, greenInt, blueInt)
}

