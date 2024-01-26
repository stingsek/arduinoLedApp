package com.example.arduino_led_app.utils

import android.Manifest
import android.content.Context
import android.os.Build

fun hasBluetoothConnectPermission(context: Context): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        return hasPermission(Manifest.permission.BLUETOOTH_CONNECT, context)

    return hasPermission(Manifest.permission.BLUETOOTH, context) &&
            hasPermission(Manifest.permission.BLUETOOTH_ADMIN, context)
}

fun hasBluetoothScanPermission(context: Context): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        return hasPermission(Manifest.permission.BLUETOOTH_SCAN, context)

    return hasPermission(Manifest.permission.BLUETOOTH, context) &&
            hasPermission(Manifest.permission.BLUETOOTH_ADMIN, context)
}

fun hasPermission(permission: String, context: Context): Boolean {
    return context.checkSelfPermission(permission) == android.content.pm.PackageManager.PERMISSION_GRANTED
}
