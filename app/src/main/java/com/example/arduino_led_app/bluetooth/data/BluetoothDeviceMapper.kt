package com.example.arduino_led_app.bluetooth.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.arduino_led_app.bluetooth.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain():
        BluetoothDeviceDomain
{
    return BluetoothDeviceDomain(name = name, address = address)
}