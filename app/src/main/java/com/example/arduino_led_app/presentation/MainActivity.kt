package com.example.arduino_led_app.presentation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arduino_led_app.databinding.ActivityMainBinding
import com.example.arduino_led_app.presentation.BluetoothViewModel
import com.example.arduino_led_app.presentation.components.DeviceScreen
import com.example.arduino_led_app.ui.theme.ArduinoLedAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val bluetoothManager by lazy{
        applicationContext.getSystemService(BluetoothManager::class.java)
    }

    //initiate a connection, a scan and paired and scanned devices view
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {/* Not needed */} //if the user enabled permission after showing the dialog use this result

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        {
                perms ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[android.Manifest.permission.BLUETOOTH_CONNECT] == true
            } else {
                true
            }
            if(canEnableBluetooth && !isBluetoothEnabled)
            {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.BLUETOOTH_SCAN,
                    android.Manifest.permission.BLUETOOTH_CONNECT
                )
            )
        }

        setContent{
            ArduinoLedAppTheme{
                val viewModel = hiltViewModel<BluetoothViewModel>()
                val state by viewModel.state.collectAsState()


                Surface(color=MaterialTheme.colorScheme.background) {
                DeviceScreen(
                    state = state,
                    onStartScan = viewModel::startScan,
                    onStopScan = viewModel::stopScan
                )

                }

            }
        }

    }
}