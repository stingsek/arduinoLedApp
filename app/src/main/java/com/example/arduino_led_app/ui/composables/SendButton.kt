package com.example.arduino_led_app.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.ui.theme.Orange
import com.example.arduino_led_app.ui.theme.RedOrange

@Composable
fun SendButton(onClickSend: () -> Unit = {}) {
    ExtendedFloatingActionButton(
        shape = RoundedCornerShape(50),
                onClick = onClickSend,
        containerColor = RedOrange,
        contentColor = Color.Black
        )
     {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = "Send Icon",
            modifier = Modifier.size(ButtonDefaults.IconSize) // Możesz także dostosować rozmiar ikony
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("Send")
    }
}