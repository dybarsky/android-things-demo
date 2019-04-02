package com.dmax.iot.demo.activities

import android.app.Activity
import android.os.Bundle
import com.google.android.things.pio.PeripheralManager

abstract class IotActivity : Activity() {

    protected val peripheralService: PeripheralManager by lazy {
        PeripheralManager.getInstance() ?: throw IllegalStateException("Can't obtain peripheral manager")
    }

    abstract fun initPeriphery()
    abstract fun closePeriphery()
    abstract fun doAction()

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
        doAction()
    }
}
