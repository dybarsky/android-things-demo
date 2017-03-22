package com.tooploox.iot.demo;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.things.pio.PeripheralManagerService;

/**
 * Maksym Dybarskyi | maksym.dybarskyi@tooploox.com
 * 17/03/2017 13:50
 */
public class DemoActivity extends Activity {

    PeripheralManagerService service = new PeripheralManagerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPeriphery();
    }

    private void initPeriphery() {
        
    }
}
