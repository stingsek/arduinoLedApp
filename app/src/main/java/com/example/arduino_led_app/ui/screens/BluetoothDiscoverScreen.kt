package com.example.arduino_led_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arduino_led_app.bluetooth.domain.BluetoothDevice
import com.example.arduino_led_app.ui.composables.CustomHeader
import com.example.arduino_led_app.ui.presentation.BluetoothUiState
import com.example.arduino_led_app.ui.theme.RedOrange

@Composable
fun BluetoothDiscoverScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClick: (BluetoothDevice) -> Unit
) {
    val buttonColor = ButtonDefaults.buttonColors(containerColor = RedOrange)
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
                contentDescription = "Bluetooth Icon"
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

