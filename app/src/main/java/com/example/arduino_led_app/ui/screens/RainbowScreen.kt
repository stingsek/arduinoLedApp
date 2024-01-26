package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.R
import com.example.arduino_led_app.ui.composables.CustomHeader
import com.example.arduino_led_app.ui.composables.CustomRadioButtons
import com.example.arduino_led_app.ui.composables.CustomSwitch
import com.example.arduino_led_app.utils.command.CommandBuilder
import com.example.arduino_led_app.utils.command.FunctionValue

@Composable
fun RainbowScreen(onCommandChange: (String) -> Unit = {}
) {
    val direction = remember {
        mutableStateOf(false) // false -> left, true -> right
    }
    val pulse = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        CustomHeader(text = "Rainbow", imageVector = Icons.Filled.Animation)

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box()
        {
            Image(
                painter = painterResource(id = R.drawable.ic_rainbow_no_background),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            CustomRadioButtons { selectedText ->
                when (selectedText) {
                    "Left" -> Unit
                    "Right" -> Unit
                    "Pulse" -> Unit
                    else -> Unit
                }
            }
        }

        LaunchedEffect(pulse.value, direction.value)
        {
//        onCommandChange(buildCommandToSend(direction.value, pulse.value))
        }


    }

}
    private fun buildCommandToSend(direction: Boolean, pulse: Boolean): String {
        return CommandBuilder.instance.apply {
            clear()
            if (pulse) {
                appendFunction(FunctionValue.PULSE)
            }
        }.buildString()
    }
