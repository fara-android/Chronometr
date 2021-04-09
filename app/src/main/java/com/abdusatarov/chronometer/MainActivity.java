package com.abdusatarov.chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffSet = 0;
    private boolean isPlaying = false;
    private ToggleButton toggleButton;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        toggleButton = findViewById(R.id.toggle);
        resetBtn = findViewById(R.id.reset_btn);
        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b){
                    chronometer.setBase(SystemClock.elapsedRealtime()- pauseOffSet);
                    chronometer.start();
                    isPlaying = true;
                }
                else {
                    chronometer.stop();
                    pauseOffSet = SystemClock.elapsedRealtime()- chronometer.getBase();
                    isPlaying = false;
                }

            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying||!isPlaying){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    pauseOffSet = 0;
                    if (!isPlaying){
                    chronometer.start();
                    isPlaying = true;
                    }
                }
            }
        });
    }
}