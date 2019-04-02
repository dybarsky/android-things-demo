package com.dmax.iot.demo.drivers

import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager
import java.io.IOException

/**
 * Created by mdy on 3/22/17.
 */
class LedController(peripheral: PeripheralManager, pin: String) {

    private val gpio: Gpio? = try {
        peripheral.openGpio(pin)
    } catch (_: IOException) {
        null
    }

    init {
        gpio?.apply {
            setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
            setActiveType(Gpio.ACTIVE_LOW)
        }
    }

    fun on() {
        gpio?.value = true
    }

    fun off() {
        gpio?.value = false
    }

    fun close() {
        try {
            gpio?.close()
        } catch (_: IOException) {}
    }
}
