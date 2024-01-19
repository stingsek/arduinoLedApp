package com.example.arduino_led_app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arduino_led_app.ui.screens.BluetoothDiscoverScreen
import com.example.arduino_led_app.ui.screens.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.BluetoothDiscoverScreen.route) {
        composable(route = Screen.BluetoothDiscoverScreen.route) {
            BluetoothDiscoverScreen()
        }
    }
}