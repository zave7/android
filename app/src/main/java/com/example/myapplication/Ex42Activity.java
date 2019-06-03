package com.example.myapplication;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ex42Activity extends AppCompatActivity {
    int clickCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex42);
        Button bt1 = findViewById(R.id.bt1);
        //final int clickCnt = 0;
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //clickCnt++;
//                clickCnt++;
//                String text = String.valueOf(clickCnt);
//                Context context = Ex42Activity.this;
//                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
//                Button bt2 = findViewById(R.id.bt2);
//                if(bt2.getVisibility() == View.VISIBLE) {
//                    bt2.setVisibility(View.GONE);//영역도 사라짐
//                } else if(bt2.getVisibility() == View.GONE) {
//                    bt2.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
    }

    public void btClick(View view) {
        int viewId = view.getId();
        Log.i("Ex42Activity", "지금 클릭한 뷰 객체의 id는 " + viewId);
        if(viewId == R.id.bt1) {
            Log.i("Ex42Activity", "지금 클릭한 뷰 객체의 id값이 bt1입니다 ");
        }
        clickCnt++;
        String text = String.valueOf(clickCnt);
        Context context = Ex42Activity.this;
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        Button bt2 = findViewById(R.id.bt2);
        if (bt2.getVisibility() == View.VISIBLE) {
            bt2.setVisibility(View.GONE);//영역도 사라짐
        } else if (bt2.getVisibility() == View.GONE) {
            bt2.setVisibility(View.VISIBLE);
        }
    }
}
