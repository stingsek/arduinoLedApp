package com.example.arduino_led_app.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arduino_led_app.ui.theme.Orange
import com.example.arduino_led_app.ui.theme.RedOrange

@Composable
fun CustomRadioButtons(onRadioButtonSelected: (String) -> Unit) {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(isChecked = true, text = "Left"),
            ToggleableInfo(isChecked = false, text = "Right"),
            ToggleableInfo(isChecked = false, text = "Pulse"),
        )
    }

    val textWidth = 50.dp
    val radioButtonScale = 1.5f  // Scale factor for RadioButton
    val textSize = 18.sp  // Text size

    radioButtons.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable {
                    radioButtons.replaceAll {
                        it.copy(isChecked = it.text == info.text)

                    }
                    onRadioButtonSelected(info.text)
                }
                .padding(end = 16.dp)
        ) {
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = Orange,
                    unselectedColor = Color.Black
                ),
                selected = info.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(isChecked = it.text == info.text)
                    }
                    onRadioButtonSelected(info.text)
                },
                modifier = Modifier.scale(radioButtonScale)  // Scaling the RadioButton
            )
            Box(modifier = Modifier.width(textWidth)) {
                Text(
                    text = info.text,
                    fontSize = textSize  // Increasing the Text size
                )
            }
        }
    }
}