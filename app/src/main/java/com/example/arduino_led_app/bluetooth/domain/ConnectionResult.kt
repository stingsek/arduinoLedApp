package com.example.arduino_led_app.bluetooth.domain

sealed interface ConnectionResult
{
    data object ConnectionEstablished: ConnectionResult
    data class Error(val message: String): ConnectionResult
}