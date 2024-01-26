package com.example.arduino_led_app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arduino_led_app.bluetooth.domain.BluetoothDevice
import com.example.arduino_led_app.ui.presentation.BluetoothUiState
import com.example.arduino_led_app.ui.screens.AppInfoScreen
import com.example.arduino_led_app.ui.screens.BluetoothDiscoverScreen
import com.example.arduino_led_app.ui.screens.ColorChooserScreen
import com.example.arduino_led_app.ui.screens.HomeScreen
import com.example.arduino_led_app.ui.screens.RainbowScreen
import com.example.arduino_led_app.ui.screens.Screen
import com.example.arduino_led_app.ui.screens.SettingsScreen
import com.example.arduino_led_app.ui.screens.SplashScreen

@Composable
fun Navigation(state: BluetoothUiState,
               onStartScan: () -> Unit,
               onStopScan: () -> Unit,
               onStartServer: () -> Unit,
               onDeviceClick: (BluetoothDevice) -> Unit,
               onSendClicked: (String) -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.ColorChooserScreen.route)
        {
            ColorChooserScreen()
        }
        composable(route = Screen.BluetoothDiscoverScreen.route)
        {
            BluetoothDiscoverScreen(
                state = state,
                onStartScan = onStartScan,
                onStopScan = onStopScan,
                onStartServer = onStartServer,
                onDeviceClick = onDeviceClick
            )
        }
        composable(route = Screen.HomeScreen.route)
        {
            HomeScreen(onSendClicked)
        }
        composable(route = Screen.RainbowScreen.route){
            RainbowScreen()
        }
        composable(route = Screen.SettingsScreen.route){
            SettingsScreen()
        }
        composable(route = Screen.AppInfoScreen.route){
            AppInfoScreen()
        }

    }
}
