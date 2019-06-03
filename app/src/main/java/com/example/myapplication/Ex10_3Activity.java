package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Ex10_3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_3);
        Log.i("Ex10_3Activity", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ex10_3Activity", "onStart()");
    }

    @Override
    protected void onResume() { // start 다음에 호출된다.
        super.onResume();
        Log.i("Ex10_3Activity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ex10_3Activity", "onPause()");
    }

    @Override
    protected void onStop() { // pause 다음에 호출된다.
        super.onStop();
        Log.i("Ex10_3Activity", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ex10_3Activity", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ex10_3Activity", "onDestory()");
    }


}
