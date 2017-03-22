package com.tooploox.iot.demo

import android.os.Handler
import android.os.Message
import com.tooploox.iot.demo.drivers.LedController

class Blinker(val ledController: LedController, var handler: Handler?) : Handler.Callback {

    companion object {
        const val CODE_ON = 0x01
        const val CODE_OFF = 0x02
        private const val DELAY = 500L // millis
    }

    fun scheduleCode(code: Int) {
        handler?.sendEmptyMessageDelayed(code, DELAY)
    }

    override fun handleMessage(msg: Message?): Boolean {
        return when(msg?.what) {
            CODE_ON -> {
                ledController.on()
                scheduleCode(CODE_OFF)
                true
            }
            CODE_OFF -> {
                ledController.off()
                scheduleCode(CODE_ON)
                true
            }
            else -> false
        }
    }
}