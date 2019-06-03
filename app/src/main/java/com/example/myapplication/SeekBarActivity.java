package com.example.myapplication;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class SeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
    }

    public void init(View view) {
        SeekBar seekBar = (SeekBar) findViewById(R.id.sb);
        seekBar.setProgress(0);
    }

    public void progress(View view) {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.sb);
        new Thread() {
            public void run() {
                int start = seekBar.getProgress();
                for (int i = start; i <= 100; i++)
                seekBar.setProgress(i);
                SystemClock.sleep(100);
                }
        }.start();

    }
}
