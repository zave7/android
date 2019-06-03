package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ex10_20Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_20);
    }
    public void dial(View view) {
        Uri uri = Uri.parse("tel:01012345678");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);// 암시적 인텐트
        startActivity(intent);
    }
    public void map(View view) {
        Uri uri = Uri.parse("https://www.google.co.kr/maps?q=37.4855272,126.9008118,17.17z");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);// 암시적 인텐트
        startActivity(intent);
    }
    public void send(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);// 암시적 인텐트
        intent.putExtra("sms_body", "안녕하세요?");
        intent.setData(Uri.parse("smsto:" + Uri.encode("010-7630-9585")));
        startActivity(intent);
    }
}
