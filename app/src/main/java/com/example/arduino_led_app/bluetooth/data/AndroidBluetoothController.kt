package com.example.arduino_led_app.bluetooth.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.IntentFilter
import androidx.core.content.ContextCompat.RECEIVER_EXPORTED
import androidx.core.content.ContextCompat.registerReceiver
import com.example.arduino_led_app.bluetooth.domain.BluetoothController
import com.example.arduino_led_app.bluetooth.BluetoothDeviceDomain
import com.example.arduino_led_app.utils.hasBluetoothConnectPermission
import com.example.arduino_led_app.utils.hasBluetoothScanPermission
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("MissingPermission")
class AndroidBluetoothController(
    private val context: Context
) : BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    // Initiate a connection, a scan, and paired and scanned devices view
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val _scannedDevices = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>
        get() = _pairedDevices.asStateFlow()


    private val foundDeviceReceiver = FoundDeviceReceiver { bluetoothDevice ->
        _scannedDevices.update { devices ->
            val newDevice = bluetoothDevice.toBluetoothDeviceDomain()
            if (newDevice in devices) devices else devices + newDevice
        }
    }

    init {
        updatePairedDevices()
    }

    override fun startDiscovery() {
        if (!hasBluetoothScanPermission(context))
            return

        registerReceiver(
            context,
            foundDeviceReceiver,
            IntentFilter(BluetoothDevice.ACTION_FOUND),
            RECEIVER_EXPORTED
        )

        updatePairedDevices()
        bluetoothAdapter?.startDiscovery()
    }

    override fun stopDiscovery() {
        if (!hasBluetoothScanPermission(context))
            return

        bluetoothAdapter?.cancelDiscovery()
    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
    }

    private fun updatePairedDevices() {
        if (!hasBluetoothConnectPermission(context))
            return

        bluetoothAdapter
            ?.bondedDevices?.map { it.toBluetoothDeviceDomain() }
            ?.also { devices -> _pairedDevices.update { devices } }
    }
}
