package com.tooploox.iot.demo.activities

import android.app.Activity
import android.os.Bundle
import com.google.android.things.pio.PeripheralManagerService
import com.tooploox.iot.demo.PWM0
import com.tooploox.iot.demo.drivers.ServoController

/**
 * Created by mdy on 3/22/17.
 */
open class ServoActivity : Activity() {

    private val peripheralService = PeripheralManagerService()

    private var servo: ServoController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPeriphery()
    }

    override fun onDestroy() {
        super.onDestroy()
        closePeriphery()
    }

    override fun onStart() {
        super.onStart()
        startMoving()
    }

    private fun initPeriphery() {
        servo = ServoController(peripheralService, PWM0)
    }

    private fun closePeriphery() {
        servo?.close()
    }

    private fun startMoving() {
        servo?.on()
        servo?.setAngle(90)
    }
}