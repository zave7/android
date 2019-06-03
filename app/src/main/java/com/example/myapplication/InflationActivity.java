package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class InflationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inflation);

        final LinearLayout root = (LinearLayout)findViewById(R.id.root);

        Button makeBtn = (Button) findViewById(R.id.btCb);
        Button removeBtn = (Button) findViewById(R.id.btCbRemove);

        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test inflation", " make 메서드 진입");
                //부분 XML 전개
                LayoutInflater inflater =
                        (LayoutInflater) getSystemService(
                                Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.activity_sub_1, root, true);
                //view 제거
                //root.removeView(findViewById(R.id.sub_1));

                //전개된 view 사라지기
                //findViewById().setVisibility(View.GONE);
                Log.i("test inflation", " make 메서드 종료");
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test inflation", " remove 메서드 진입");
                //view 제거
                root.removeView(findViewById(R.id.sub_1));

                //전개된 view 사라지기
                //findViewById().setVisibility(View.GONE);
                Log.i("test inflation", " remove 메서드 종료");
            }
        });
    }
}
