package com.example.arduino_led_app.bluetooth.domain

import androidx.compose.runtime.MutableState

data class BluetoothDevice(
    val name: String?,
    val address: String
)