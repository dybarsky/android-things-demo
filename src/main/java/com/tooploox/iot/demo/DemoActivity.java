package com.tooploox.iot.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

/**
 * Maksym Dybarskyi | maksym.dybarskyi@tooploox.com
 * 17/03/2017 13:50
 */
public class DemoActivity extends Activity {

    private static class LedController {

        private Gpio gpio;

        LedController(PeripheralManagerService service, String pin) throws IOException {
            this.gpio = service.openGpio(pin);
            this.gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        }

        void on() throws IOException {
            gpio.setValue(true);
        }

        void off() throws IOException {
            gpio.setValue(false);
        }

        void close() throws IOException {
            gpio.close();
            gpio = null;
        }
    }

    private class Blinker implements Handler.Callback {

        static final int ON = 0x01;
        static final int OFF = 0x02;
        static final int DELAY = 500; // millis

        @Override
        public boolean handleMessage(Message msg) {
            int nextCode = OFF;
            try {
                switch (msg.what) {
                    case ON:
                        nextCode = OFF;
                        led.on();
                        break;
                    case OFF:
                        nextCode = ON;
                        led.off();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                scheduleCode(nextCode);
            }
            return false;
        }

        void scheduleCode(int code) {
            handler.sendEmptyMessageDelayed(code, DELAY);
        }
    }

    private LedController led;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPeriphery();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startBlinking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePeriphery();
    }

    private void initPeriphery() {
        try {
            PeripheralManagerService service = new PeripheralManagerService();
            led = new LedController(service, DeviceDefaults.PIN40);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void releasePeriphery() {
        try {
            led.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startBlinking() {
        Blinker blinker = new Blinker();
        handler = new Handler(blinker);
        blinker.scheduleCode(Blinker.ON);
    }
}
