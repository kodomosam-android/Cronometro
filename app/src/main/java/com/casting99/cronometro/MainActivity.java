package com.casting99.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    private long pauseOfset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.cronometro);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "Fim", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void startCronometro(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOfset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseCronometro(View v){
        if(running){
            chronometer.stop();
            pauseOfset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetCronometro(View v){

        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOfset = 0;

    }
}
