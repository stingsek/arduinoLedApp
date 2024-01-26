package com.example.arduino_led_app.utils.command

class CommandBuilder private constructor() {

    private var commands: MutableList<String> = mutableListOf()

    fun buildString(): String {
        val commands = commands.toMutableList()
        return appendSteeringCommands(commands.joinToString(separator = SteeringCommand.END.value))
    }

//    fun buildCharArray(): CharArray {
//        return buildString().toCharArray()
//    }
//
//    fun buildByteArray(): ByteArray{
//        return buildString().encodeToByteArray()
//    }

    fun appendFunction(functionValue: FunctionValue): CommandBuilder {
        val command = parseVariable(VariablePrefix.FUNCTION, functionValue.value)
        commands.add(command)
        return this
    }


    private fun appendVariable(variablePrefix: VariablePrefix, value: Int): CommandBuilder {
        val command = parseVariable(variablePrefix, value)
        commands.add(command)
        return this
    }

    fun appendWait(value: Int): CommandBuilder{
        appendVariable(VariablePrefix.WAIT,value)
        return this
    }

    fun appendBrightness(value: Int): CommandBuilder
    {
        appendVariable(VariablePrefix.BRIGHTNESS,value)
        return this
    }

    fun appendRGB(red: Int, green: Int, blue: Int): CommandBuilder {
        appendVariable(VariablePrefix.RED, red)
        appendVariable(VariablePrefix.GREEN, green)
        appendVariable(VariablePrefix.BLUE, blue)
        return this
    }

    private fun parseVariable(variablePrefix: VariablePrefix, value: Int): String {
        return variablePrefix.prefix + parseIntToCommandValue(value, variablePrefix.maxValue)
    }

    private fun appendSteeringCommands(command: String): String {
        return SteeringCommand.START.value + command + SteeringCommand.STOP.value
    }

    private fun parseIntToCommandValue(num: Int, maxValue: Int): String {
        if(num < 0)
            throw IllegalArgumentException("Value must be positive")
        if(num > maxValue)
            throw IllegalArgumentException("Value must be less than $maxValue")

        return String.format("%03d", num)
    }

    fun clearState()
    {
        commands.clear()
    }

    companion object {
        val instance: CommandBuilder by lazy { CommandBuilder() }
    }
}