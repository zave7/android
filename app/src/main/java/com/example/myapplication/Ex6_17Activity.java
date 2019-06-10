package com.example.myapplication;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")//warnning 무시 어노테이션
public class Ex6_17Activity extends TabActivity {//extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6_17);
        TabHost tabHost = this.getTabHost(); // xml에서 정의했던 tabhost를 찾는 작업

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("SONG");//탭을 1개 추가하는 작업
        tabSpec1.setIndicator("음악별");//탭 라벨설정
        tabSpec1.setContent(R.id.song);//탭이 클릭되었을때 보여줄 위젯 등록
        tabHost.addTab(tabSpec1); // 탭 호스트에 탭 설정

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("SINGER");//탭을 1개 추가하는 작업
        tabSpec2.setIndicator("가수별");//탭 라벨설정
        tabSpec2.setContent(R.id.singer);//탭이 클릭되었을때 보여줄 위젯 등록
        tabHost.addTab(tabSpec2); // 탭 호스트에 탭 설정

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("ALBUM");//탭을 1개 추가하는 작업
        tabSpec3.setIndicator("음악별");//탭 라벨설정
        tabSpec3.setContent(R.id.album);//탭이 클릭되었을때 보여줄 위젯 등록
        tabHost.addTab(tabSpec3); // 탭 호스트에 탭 설정

    }
}
