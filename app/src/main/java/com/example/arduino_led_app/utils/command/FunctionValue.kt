package com.example.arduino_led_app.utils.command

enum class FunctionValue(val value: Int) {
    CLEAR(1),
    FILL(2),
    RAINBOW_LEFT(3),
    RAINBOW_RIGHT(4),
    PULSE(5),
    PULSE_RAINBOW(6),
}