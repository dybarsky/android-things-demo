package com.tooploox.iot.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.things.pio.PeripheralManagerService;
import com.tooploox.iot.demo.drivers.LedController;

/**
 * Maksym Dybarskyi | maksym.dybarskyi@tooploox.com
 * 17/03/2017 13:50
 */
public class DemoActivity extends Activity {

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
        handler.removeCallbacksAndMessages(null);
        releasePeriphery();
    }

    private void initPeriphery() {
        PeripheralManagerService service = new PeripheralManagerService();
        led = new LedController(service, DeviceDefaults.PIN40);
    }

    private void releasePeriphery() {
        led.close();
    }

    private void startBlinking() {
        Blinker blinker = new Blinker(led, null);
        handler = new Handler(blinker);
        blinker.setHandler(handler);
        blinker.scheduleCode(Blinker.CODE_ON);
    }
}
