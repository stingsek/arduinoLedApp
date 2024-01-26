package com.example.arduino_led_app.utils.command

enum class VariablePrefix(val prefix: String, val maxValue: Int) {
    FUNCTION("f", 6),
    WAIT("w", 255),
    BRIGHTNESS("b", 255),
    RED("R", 255),
    GREEN("G", 255),
    BLUE("B", 255),
}