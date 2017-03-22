package com.tooploox.iot.demo.drivers

import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManagerService;

/**
 * Created by mdy on 3/22/17.
 */
class LedController(peripheral: PeripheralManagerService, pin: String) {

    private val gpio: Gpio = peripheral.openGpio(pin)

    init {
        gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
    }

    fun on() { gpio.value = true }

    fun off() { gpio.value = false }

    fun close() { gpio.close() }
}