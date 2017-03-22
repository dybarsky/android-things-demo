package com.tooploox.iot.demo

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.google.android.things.pio.PeripheralManagerService
import com.tooploox.iot.demo.drivers.LedController

/**
 * Created by mdy on 3/22/17.
 */
open class DemoActivity : Activity() {

    private val peripheralService = PeripheralManagerService()

    private var led: LedController? = null
    private var handler: Handler? = null

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
        startBlinking()
    }

    private fun initPeriphery() {
        led = LedController(peripheralService, PIN40)
    }

    private fun closePeriphery() {
        handler?.removeCallbacksAndMessages(null)
        led?.close()
    }

    private fun startBlinking() {
        val blinker = Blinker(led!!, null)
        handler = Handler(blinker)
        blinker.handler = this.handler
        blinker.scheduleCode(Blinker.CODE_ON)
    }
}