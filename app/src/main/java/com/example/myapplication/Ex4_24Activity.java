package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Ex4_24Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_24);

        CheckBox cb1 = findViewById(R.id.cb1);//
        CheckBox cb2 = findViewById(R.id.cb2);//
        CheckBox cb3 = findViewById(R.id.cb3);//

        MycheckedChangeListener myListener = new MycheckedChangeListener();

        cb1.setOnCheckedChangeListener(myListener);
        cb2.setOnCheckedChangeListener(myListener);
        cb3.setOnCheckedChangeListener(myListener);


        final RadioButton rb1 = findViewById(R.id.gender1);
        final RadioButton rb2 = findViewById(R.id.gender2);
        RadioGroup rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.gender1) {
                    Log.i("Ex4_24Activity", rb1.getText() + "이 선택 되었습니다");
                } else if(i == R.id.gender2) {
                    Log.i("Ex4_24Activity", rb2.getText() + "가 선택 되었습니다");
                }
            }
        });


    }

    class MycheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.i("Ex4_24Activity", "checked 속성값이 변경됨!" + b);
            Log.i("Ex4_24Activity", "텍스트!!" + compoundButton.getText());
        }
    }

}
