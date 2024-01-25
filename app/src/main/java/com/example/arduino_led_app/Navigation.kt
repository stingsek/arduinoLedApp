package com.example.arduino_led_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.arduino_led_app.bluetooth.domain.BluetoothDevice
import com.example.arduino_led_app.presentation.BluetoothUiState
import com.example.arduino_led_app.presentation.BluetoothViewModel
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
               onDeviceClick: (BluetoothDevice) -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
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
            HomeScreen(navController)
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

//        navigation(
//            startDestination = "bluetooth_discover_screen",
//            route = "color_chooser_screen"
//        )
//        {
//            composable(route = "bluetooth_discover") {
//                val viewModel = it.sharedViewModel<BluetoothViewModel>(navController = navController)
//
////
//            }
//
//        }
//        navigation(
//            startDestination = "color_chooser",
//            route = "led_controller"
//        )
//        {
//
//        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T
{
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry =  remember(this)
    {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)

}