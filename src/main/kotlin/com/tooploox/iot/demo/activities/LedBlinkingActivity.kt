package com.tooploox.iot.demo.activities

import android.os.Handler
import com.tooploox.iot.demo.Blinker
import com.tooploox.iot.demo.PIN40
import com.tooploox.iot.demo.drivers.LedController

/**
 * Created by mdy on 3/22/17.
 */
open class LedBlinkingActivity : IotActivity() {

    private var led: LedController? = null
    private var handler: Handler? = null

    override fun initPeriphery() {
        led = LedController(peripheralService, PIN40)
    }

    override fun closePeriphery() {
        handler?.removeCallbacksAndMessages(null)
        led?.close()
    }

    override fun doAction() {
        val blinker = Blinker(led!!, null)
        handler = Handler(blinker)
        blinker.handler = this.handler
        blinker.scheduleCode(Blinker.CODE_ON)
    }
}