package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//다른 앱의 화면 띄우기
public class CallComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_component);
    }

    public void callComponent(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.bapplication", "com.example.bapplication.BActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 새로운 태스크로 화면 관리???
        intent.setComponent(componentName); // 이렇게 하면 다른 화면의 앱을 띄울 수 있다.
        intent.putExtra("msg", "MyApplcation 에서 전달하는 데이터입니다");
        startActivity(intent);
    }
}
