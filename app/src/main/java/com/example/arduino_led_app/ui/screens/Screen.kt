package com.example.arduino_led_app.ui.screens

sealed class Screen(val route: String) {
    object BluetoothDiscoverScreen: Screen("bluetooth_discover_screen")
}
