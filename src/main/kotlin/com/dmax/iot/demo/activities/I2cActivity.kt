package com.dmax.iot.demo.activities

import com.dmax.iot.demo.drivers.PCA9685
import com.google.android.things.pio.I2cDevice
import java.io.IOException

private const val I2C_ADDRESS: Int = 0x40

open class I2cActivity : IotActivity() {

    private companion object {
        /*
        min servo time = 500 us
        max servo time = 2400 us
        ticks per second = 4096 * 50 = 204800
        min ticks = 204800 / 1000000 * 500 = 103
        max ticks = 204800 / 1000000 * 2400 = 492
        one degree time = (492 - 103) / 180 = ~2
        */

        private const val MIN_TIME = 103
        private const val MAX_TIME = 492
        private const val ONE_DEGREE_TIME = 2
    }

    private var i2c: I2cDevice? = null

    override fun initPeriphery() {
        peripheral.i2cBusList.firstOrNull()?.let {
            i2c = try {
                peripheral.openI2cDevice(it, I2C_ADDRESS)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

    override fun closePeriphery() {
        try {
            i2c?.close()
        } catch (_: IOException) {}
    }

    override fun doAction() {
        i2c?.let {
            PCA9685(it).apply {
                reset()
                setPwmFrequency(50)
                setPwmValue(0, 0)
            }
        }
    }
}
