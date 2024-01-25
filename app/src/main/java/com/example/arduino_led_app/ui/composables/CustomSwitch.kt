package com.example.arduino_led_app.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.ui.theme.Orange
import com.example.arduino_led_app.ui.theme.RedOrange

data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)


@Composable
fun CustomSwitch(description: String = "", scale: Float = 1.2f, leftIcon: ImageVector = Icons.Default.Close, rightIcon: ImageVector = Icons.Default.Check,colorDifference: Boolean = true, colors : List<Color> = listOf(Orange, RedOrange, Color.White, Color.Black)) {
    var switch by remember {
        mutableStateOf(
            ToggleableInfo(
                isChecked = false,
                text = "Rainbow Direction"
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(2.dp)
            .scale(scale)
    ) {
        Text(text = description, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Switch(
            colors = SwitchDefaults.colors(
                checkedThumbColor = colors[0],
                checkedTrackColor= colors[1],
                checkedBorderColor= colors[2],
                checkedIconColor= colors[3],
                uncheckedThumbColor = if(colorDifference) colors[2] else colors[0],
                uncheckedTrackColor= if(colorDifference) colors[3] else colors[1],
                uncheckedBorderColor= if(colorDifference) colors[0] else colors[2],
                uncheckedIconColor= if(colorDifference) colors[1] else colors[3],
            ),
            checked = switch.isChecked,
            onCheckedChange = { isChecked ->
                switch = switch.copy(isChecked = isChecked)
            },
            thumbContent = {
                Icon(
                    imageVector = if (switch.isChecked) {
                        rightIcon
                    } else {
                        leftIcon
                    },
                    contentDescription = null
                )
            }
        )
    }
}
