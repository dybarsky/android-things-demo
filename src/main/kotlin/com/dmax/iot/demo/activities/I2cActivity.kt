package com.dmax.iot.demo.activities

import com.google.android.things.pio.I2cDevice
import java.io.IOException

private const val I2C_DEVICE_NAME: String = "i2c"
private const val I2C_ADDRESS: Int = 0x00


open class I2cActivity : IotActivity() {

    private var i2c: I2cDevice? = null

    override fun initPeriphery() {
        i2c = try {
            peripheral.openI2cDevice(I2C_DEVICE_NAME, I2C_ADDRESS)
        } catch (_: IOException) {
            null
        }
    }

    override fun closePeriphery() {
        try {
            i2c?.close()
        } catch (_: IOException) {}
    }

    override fun doAction() {
        i2c?.
    }
}
