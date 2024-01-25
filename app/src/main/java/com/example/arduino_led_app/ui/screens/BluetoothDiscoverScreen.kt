package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.arduino_led_app.bluetooth.domain.BluetoothDevice
import com.example.arduino_led_app.presentation.BluetoothUiState
import com.example.arduino_led_app.presentation.BluetoothViewModel
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.arduino_led_app.ui.composables.CustomHeader

@Composable
fun BluetoothDiscoverScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClick: (BluetoothDevice) -> Unit
) {
    val buttonColor = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA5237))
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        BluetoothDeviceList(
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices,
            onClick = onDeviceClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = onStartScan, colors = buttonColor,
            ) {
                Text(text = "Start scan")
            }
            Button(onClick = onStopScan,  colors = buttonColor) {
                Text(text = "Stop scan")
            }
            Button(onClick = onStartServer,  colors = buttonColor) {
                Text(text = "Start server")
            }
        }
    }
}

@Composable
fun BluetoothDeviceList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onClick: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            CustomHeader("Paired Devices",Icons.Filled.Bluetooth)
        }
        items(pairedDevices) { device ->
            BluetoothDeviceItem(device = device, onClick = onClick)
        }

        item {
            CustomHeader("Scanned Devices",Icons.Filled.Bluetooth)
        }
        items(scannedDevices) { device ->
            BluetoothDeviceItem(device = device, onClick = onClick)
        }
    }
}

@Composable
fun BluetoothDeviceItem(
    device: BluetoothDevice,
    onClick: (BluetoothDevice) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(device) }
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Bluetooth,
                contentDescription = "Ikona Bluetooth"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = device.name ?: "(No name)",
                fontWeight = FontWeight.Medium
            )
        }
        Divider()
    }
}

