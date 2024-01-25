package com.example.arduino_led_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arduino_led_app.bluetooth.BluetoothDeviceDomain
import com.example.arduino_led_app.bluetooth.domain.BluetoothController
import com.example.arduino_led_app.bluetooth.domain.BluetoothDevice
import com.example.arduino_led_app.bluetooth.domain.ConnectionResult
import com.example.arduino_led_app.presentation.BluetoothUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
) : ViewModel() {

    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.scannedDevices,
        bluetoothController.pairedDevices,
        _state
    )
    {
        scannedDevices, pairedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices,
            commands = if(state.isConnected) state.commands else emptyList()
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),_state.value)

    private var deviceConnectionJob: Job? = null

    init {
        bluetoothController.isConnected.onEach { isConnected ->
            _state.update {
                it.copy(
                    isConnected = isConnected
                )
            }
        }.launchIn(viewModelScope)

        bluetoothController.errors.onEach { error ->
            _state.update { it.copy(
                errorMessage = error
            ) }
        }.launchIn(viewModelScope)

    }

    fun connectToDevice(device: BluetoothDeviceDomain)
    {
        _state.update { it.copy(
            isConnecting = true
        ) }
        deviceConnectionJob = bluetoothController
            .connectToDevice(device)
            .listen()

    }

    fun disconnectFromDevice()
    {
        deviceConnectionJob?.cancel()
        bluetoothController.closeConnection()
        _state.update { it.copy(
            isConnecting = false,
            isConnected = false
        )
        }
    }

    fun waitForIncomingConnections()
    {
        _state.update { it.copy(
            isConnecting = true,
        ) }
       deviceConnectionJob = bluetoothController
           .startBluetoothServer()
           .listen()
    }

    fun sendCommand(command : String)
    {
        viewModelScope.launch {
            val bluetoothCommand = bluetoothController.trySendCommand(command)

            if(bluetoothCommand != null)
            {
                _state.update {
                    it.copy(
                        commands = it.commands + bluetoothCommand
                    )
                }
            }
        }
    }


    fun startScan()
    {
        bluetoothController.startDiscovery()
    }

    fun stopScan()
    {
        bluetoothController.stopDiscovery()
    }

    private fun Flow<ConnectionResult>.listen() : Job
    {
        return onEach {result ->
            when(result)
            {
                ConnectionResult.ConnectionEstablished ->
                {
                    _state.update {
                        it.copy(
                            isConnected = true,
                            isConnecting = false,
                            errorMessage = null
                        )
                    }

                }
                is ConnectionResult.TransferSucceeded ->
                {
                    _state.update { it.copy(
                        commands = it.commands + result.command
                    ) }
                }
                is ConnectionResult.Error -> {
                    _state.update {
                        it.copy(
                            isConnected = false,
                            isConnecting = false,
                            errorMessage = result.message
                        )
                    }

                }
            }

        }.catch { throwable ->
            bluetoothController.closeConnection()
            _state.update {
                it.copy(
                    isConnected = false,
                    isConnecting = false,
                    errorMessage = throwable.message
                )
            }

        }.launchIn(viewModelScope)


    }
    override fun onCleared()
    {
        super.onCleared()
        bluetoothController.release()

    }
}