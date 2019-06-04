package com.example.myapplication;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

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
        final TextView tvResult = (TextView) findViewById(R.id.tvResult);
        new Thread() {
            public void run() {
                final int start = seekBar.getProgress();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = start; i <= 100; i++) {
                                seekBar.setProgress(i);
                                tvResult.setText("진행률 : " + i + "%");
                                SystemClock.sleep(100);
                            }
                        }
                    });

                }

        }.start();

    }
}
