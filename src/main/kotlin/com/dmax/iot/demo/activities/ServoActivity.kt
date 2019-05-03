package com.dmax.iot.demo.activities

import com.dmax.iot.demo.PWM0
import com.dmax.iot.demo.drivers.ServoController

open class ServoActivity : IotActivity() {

    private lateinit var servo: ServoController

    override fun initPeriphery() {
        servo = ServoController(peripheral, PWM0)
    }

    override fun closePeriphery() {
        servo.close()
    }

    override fun doAction() {
        servo.on()
        servo.setAngle(90)
    }
}
