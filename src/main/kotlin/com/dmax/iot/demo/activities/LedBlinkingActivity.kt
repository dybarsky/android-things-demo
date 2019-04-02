package com.dmax.iot.demo.activities

import android.os.Looper
import com.dmax.iot.demo.Blinker
import com.dmax.iot.demo.PIN40
import com.dmax.iot.demo.drivers.LedController

open class LedBlinkingActivity : IotActivity() {

    private lateinit var led: LedController

    override fun initPeriphery() {
        led = LedController(peripheralService, PIN40)
    }

    override fun closePeriphery() {
        led.close()
    }

    override fun doAction() {
        val blinker = Blinker(led, Looper.getMainLooper())
        blinker.scheduleCode(Blinker.CODE_ON)
    }
}
