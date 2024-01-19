package com.example.arduino_led_app.di

import android.content.Context
import com.example.arduino_led_app.bluetooth.data.AndroidBluetoothController
import com.example.arduino_led_app.bluetooth.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context : Context) : BluetoothController {
        return AndroidBluetoothController(context)
    }
}