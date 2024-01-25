package com.example.arduino_led_app.ui.screens

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object BluetoothDiscoverScreen: Screen("bluetooth_discover_screen")
    data object SplashScreen: Screen("splash_screen")
    data object ColorChooserScreen: Screen("Color")
    data object RainbowScreen: Screen("Rainbow")
    data object SettingsScreen: Screen("Settings")
}
