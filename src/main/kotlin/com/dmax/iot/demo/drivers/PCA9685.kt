package com.dmax.iot.demo.drivers

import com.google.android.things.pio.I2cDevice
import kotlin.experimental.and
import kotlin.experimental.or

private const val PCA9685_MODE1 = 0x0
private const val PCA9685_PRESCALE = 0xFE

private const val LED0_ON_L = 0x6
private const val LED0_ON_H = 0x7
private const val LED0_OFF_L = 0x8
private const val LED0_OFF_H = 0x9

private const val LED1_ON_L = 0x0a
private const val LED1_ON_H = 0x0b
private const val LED1_OFF_L = 0x0c
private const val LED1_OFF_H = 0x0d

private const val LED2_ON_L = 0x0e
private const val LED2_ON_H = 0x0f
private const val LED2_OFF_L = 0x10
private const val LED2_OFF_H = 0x11

private const val PCA9685_25Mhz_TICKS = 25000000f

class PCA9685(val i2c: I2cDevice) {

    fun reset() {
        val value: Byte = 0x08
        println("reset")
        i2c.writeRegByte(PCA9685_MODE1, value)
    }

    fun setPwmFrequency(frequency: Byte) {
        val freq = frequency * 0.9f
        var prescale = PCA9685_25Mhz_TICKS / 4096f
        prescale /= freq
        prescale -= 1

        val oldMode = i2c.readRegByte(PCA9685_MODE1)
        val newMode = (oldMode and 0x7f) or 0x10 // sleep
        i2c.apply {
            println("new mode")
            writeRegByte(PCA9685_MODE1, newMode) // go to sleep
            println("prescale")
            writeRegByte(PCA9685_PRESCALE, prescale.toByte()) // set prescale
            println("old mode")
            writeRegByte(PCA9685_MODE1, oldMode)
//            Thread.sleep(5)
//            writeRegByte(PCA9685_MODE1, oldMode or 0xa0.toByte()) // autoincrement
        }
    }

    fun setPwmValue(on: Short, off: Short) {
        i2c.apply {

//            println("on $on, off $off")
//            val s = "${value.toString(2)} = ${h.toString(2)}.${l.toString(2)}"
//            println(s)

//            val low = 0b01111111.toByte()
//            val high = 0b11101100.toByte()
//            writeRegByte(LED0_ON_H, 0)
//            writeRegByte(LED0_ON_L, 0)
//            writeRegByte(LED0_OFF_H, 0)
//            writeRegByte(LED0_OFF_L, low)


            val low = 150
            val high = 480

            val value = high
            val value2 = low

            val l = value and 0xFF
            val h = value shr 8
            val l2 = value2 and 0xFF
            val h2 = value2 shr 8

            writeRegByte(LED0_OFF_H, h.toByte())
            writeRegByte(LED0_OFF_L, l.toByte())

            writeRegByte(LED1_OFF_H, h2.toByte())
            writeRegByte(LED1_OFF_L, l2.toByte())
        }
    }
}
