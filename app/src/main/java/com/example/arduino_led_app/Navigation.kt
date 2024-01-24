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
import com.example.arduino_led_app.presentation.BluetoothUiState
import com.example.arduino_led_app.presentation.BluetoothViewModel
import com.example.arduino_led_app.ui.screens.BluetoothDiscoverScreen
import com.example.arduino_led_app.ui.screens.Screen

@Composable
fun Navigation(state: BluetoothUiState,
               onDisconnect: () -> Unit,
               onSendCommand : (String) -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.BluetoothDiscoverScreen.route) {
        navigation(
            startDestination = "bluetooth_discover",
            route = "color_chooser"
        )
        {
            composable(route = "bluetooth_discover") {
                val viewModel = it.sharedViewModel<BluetoothViewModel>(navController = navController)

//
            }

        }
        navigation(
            startDestination = "color_chooser",
            route = "led_controller"
        )
        {

        }
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