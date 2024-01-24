package com.example.arduino_led_app.ui.screens

sealed class Screen(val route: String) {
    data object BluetoothDiscoverScreen: Screen("bluetooth_discover_screen")

    data object SplashScreen: Screen("splash_screen")
}
