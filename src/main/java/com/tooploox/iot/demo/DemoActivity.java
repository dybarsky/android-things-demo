package com.tooploox.iot.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.google.android.things.pio.PeripheralManagerService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Maksym Dybarskyi | maksym.dybarskyi@tooploox.com
 * 17/03/2017 13:50
 */
public class DemoActivity extends Activity {

    @BindView(R.id.frequency)
    SeekBar seekBar;

    PeripheralManagerService service = new PeripheralManagerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);
        ButterKnife.bind(this);
        seekBar.setOnSeekBarChangeListener(new SimpleSeekbarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onFrequencyChanged(progress);
            }
        });
    }

    @OnCheckedChanged(R.id.enable)
    void onEnableChecked(boolean checked) {

    }

    void onFrequencyChanged(int progress) {

    }
}
