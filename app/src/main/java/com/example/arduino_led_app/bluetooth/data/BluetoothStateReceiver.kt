package com.example.arduino_led_app.bluetooth.data

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class BluetoothStateReceiver(
    private val onStateChanged: (isConnected: Boolean, android.bluetooth.BluetoothDevice) -> Unit
): BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val device = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(
                android.bluetooth.BluetoothDevice.EXTRA_DEVICE,
                android.bluetooth.BluetoothDevice::class.java
            )
        } else {
            intent?.getParcelableExtra(android.bluetooth.BluetoothDevice.EXTRA_DEVICE)
        }
        when(intent?.action){
            android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED -> {
                onStateChanged(true, device ?: return)
            }
            BluetoothDevice.ACTION_ACL_DISCONNECTED ->
            {
                onStateChanged(false, device ?: return)
            }
        }
    }

}