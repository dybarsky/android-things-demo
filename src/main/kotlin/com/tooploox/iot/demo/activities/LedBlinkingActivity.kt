package com.tooploox.iot.demo.activities

import android.os.Looper
import com.tooploox.iot.demo.Blinker
import com.tooploox.iot.demo.PIN40
import com.tooploox.iot.demo.drivers.LedController

/**
 * Created by mdy on 3/22/17.
 */
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