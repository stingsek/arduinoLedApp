package com.example.arduino_led_app.utils.command

enum class VariablePrefix(val prefix: String, val maxValue: Int) {
    FUNCTION("f", 6),
    WAIT("w", Byte.MAX_VALUE.toInt()),
    BRIGHTNESS("b", Byte.MAX_VALUE.toInt()),
    RED("R", Byte.MAX_VALUE.toInt()),
    GREEN("G", Byte.MAX_VALUE.toInt()),
    BLUE("B", Byte.MAX_VALUE.toInt()),
}