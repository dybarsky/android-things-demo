package com.tooploox.iot.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;

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

    @BindView(R.id.enable)
    Switch switcher;

    PeripheralManagerService service = new PeripheralManagerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
        initPeriphery();
    }

    private void initPeriphery() {

    }

    private void initUI() {
        setContentView(R.layout.activity);

        ButterKnife.bind(this);
        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> onEnableChecked(isChecked));
        seekBar.setOnSeekBarChangeListener(new SimpleSeekbarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onFrequencyChanged(progress);
            }
        });
    }

    private void onEnableChecked(boolean checked) {

    }

    private void onFrequencyChanged(int progress) {

    }
}
