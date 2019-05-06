package com.dmax.iot.demo.activities

import com.dmax.iot.demo.PWM0
import com.dmax.iot.demo.drivers.PwmServoController

open class PwmServoActivity : IotActivity() {

    private lateinit var pwmServo: PwmServoController

    override fun initPeriphery() {
        pwmServo = PwmServoController(peripheral, PWM0)
    }

    override fun closePeriphery() {
        pwmServo.close()
    }

    override fun doAction() {
        pwmServo.on()
        pwmServo.setAngle(90)
    }
}
