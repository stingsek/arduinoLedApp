package com.example.arduino_led_app.bluetooth.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>
    val isConnected: StateFlow<Boolean>
    val errors: SharedFlow<String>

    //launching the server
    fun startBluetoothServer(): Flow<ConnectionResult>

    //connects to device that has a launched server
    fun connectToDevice(device: BluetoothDevice) : Flow<ConnectionResult>


    suspend fun trySendCommand(command : String) : String?

    fun closeConnection()
    fun startDiscovery()
    fun stopDiscovery()
    fun release()
}