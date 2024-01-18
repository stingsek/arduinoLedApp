package com.example.arduino_led_app.presentation

import com.example.arduino_led_app.bluetooth.domain.BluetoothDevice

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList()
) {
}