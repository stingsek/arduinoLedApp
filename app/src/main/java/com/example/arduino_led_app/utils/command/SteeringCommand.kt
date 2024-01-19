package com.example.arduino_led_app.utils.command

enum class SteeringCommand(val value: String) {
    START("?"),
    STOP("!"),
    END(".")
}