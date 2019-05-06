package com.dmax.iot.demo.activities

import com.dmax.iot.demo.PWM0
import com.dmax.iot.demo.drivers.I2cServoController

open class I2cServoActivity : IotActivity() {

    private lateinit var pwmServo: I2cServoController

    override fun initPeriphery() {
        pwmServo = I2cServoController(peripheral, PWM0)
    }

    override fun closePeriphery() {
        pwmServo.close()
    }

    override fun doAction() {
        pwmServo.on()
        pwmServo.setAngle(90)
    }
}
