package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, Class> activityMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = (Button) findViewById(R.id.bt1);
        //String btText = bt1.getText().toString();
//        System.out.println("버튼 : " + btText);
        String tag = "MainActivity";
        //String msg = btText;
//        Log.d(tag, msg);
        Context ctx = this;
        //String text = btText;
        //Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();

        activityMap.put(R.id.bt_ex42, Ex42Activity.class);
        activityMap.put(R.id.bt_ex43, Ex43Activity.class);
        activityMap.put(R.id.bt_ex51, Ex51Activity.class);
        activityMap.put(R.id.bt_ex4_24, Ex4_24Activity.class);
        activityMap.put(R.id.bt_inflation, InflationActivity.class);
        activityMap.put(R.id.bt_newActivity, NewActivity.class);
        activityMap.put(R.id.bt_ex_10_2, Ex10_2Activity.class);
        activityMap.put(R.id.bt_ex_10_20, Ex10_20Activity.class);
        activityMap.put(R.id.lifecycle, Ex10_3Activity.class);
        activityMap.put(R.id.login, LoginActivity.class);
        activityMap.put(R.id.otherapp, CallComponentActivity.class);
        activityMap.put(R.id.customListView, CustomListViewActivity.class);
        activityMap.put(R.id.seekbar, SeekBarActivity.class);
        activityMap.put(R.id.rate, ExchangeRateActivity.class);
        activityMap.put(R.id.viewcart, ViewCartActivity.class);
        activityMap.put(R.id.tab, Ex6_17Activity.class);
        activityMap.put(R.id.sqlite, Ex12_1Activity.class);
        activityMap.put(R.id.diary, DiaryActivity.class);


    }

    public void btClick(View view) {
        Class clazz = activityMap.get(view.getId());
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String msg = intent.getStringExtra("msg");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}