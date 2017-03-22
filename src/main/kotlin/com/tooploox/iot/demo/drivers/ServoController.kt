package com.tooploox.iot.demo.drivers

import com.google.android.things.pio.PeripheralManagerService
import com.google.android.things.pio.Pwm

/**
 * Created by mdy on 3/22/17.
 */
class ServoController(peripheral: PeripheralManagerService, pin: String) {

    companion object {
        private const val FREQUENCY = 50.0
        private const val DUTY_CYCLE_MIN_MS = 0.75
        private const val DUTY_CYCLE_MAX_MS = 2.4
        private const val PULSE_PERIOD_MS = 20.0 // 1000/50Hz
        private const val ONE_DEGREE_MS = (DUTY_CYCLE_MAX_MS - DUTY_CYCLE_MIN_MS) / 180
    }

    private val pwm: Pwm = peripheral.openPwm(pin)

    init {
        pwm.setPwmFrequencyHz(FREQUENCY)
        pwm.setPwmDutyCycle(0.0)
    }

    fun on() { pwm.setEnabled(true) }

    fun off() { pwm.setEnabled(false) }

    fun close() { pwm.close() }

    fun setAngle(angle: Int) {
        if (angle !in 0..180) return

        val dutyCycleInMillis = DUTY_CYCLE_MIN_MS + angle * ONE_DEGREE_MS
        val dutyCycleInPercents = dutyCycleInMillis * 100 / PULSE_PERIOD_MS
        pwm.setPwmDutyCycle(dutyCycleInPercents)
    }
}