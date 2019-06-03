package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ex43Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex43);
        TextView input1 = findViewById(R.id.input1);
        TextView input2 = findViewById(R.id.input2);
        Button sum = findViewById(R.id.sum);
        Button minus = findViewById(R.id.minus);
        Button multipily = findViewById(R.id.multiply);
        Button nanoo = findViewById(R.id.nanoo);
        Button namerge = findViewById(R.id.namerge);
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator(view.getId());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button)view;
                calculator(view.getId());
            }
        });
        multipily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button)view;
                calculator(view.getId());
            }
        });
        nanoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button)view;
                calculator(view.getId());
            }
        });
        namerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button)view;
                calculator(view.getId());
            }
        });
    }

    public void calculator(int execute) {
        Context context = Ex43Activity.this;
        if("".equals(((EditText)findViewById(R.id.input1)).getText().toString()) ||
                "".equals(((EditText)findViewById(R.id.input2)).getText().toString())) {
            Log.i("result", "이프문");
            Toast.makeText(context, "입력해주세요", Toast.LENGTH_SHORT);
            return;
        }
        Log.i("result", "이프문통과");
        EditText e1 = findViewById(R.id.input1);
        EditText e2 = findViewById(R.id.input2);
        double num1 = Double.parseDouble(((EditText)findViewById(R.id.input1)).getText().toString());
        double num2 = Double.parseDouble(((EditText)findViewById(R.id.input2)).getText().toString());

            double resultNum = 0;
            switch(execute) {
                case R.id.sum: resultNum = num1 + num2;
                    break;
                case R.id.minus: resultNum = num1 - num2;
                    break;
                case R.id.multiply: resultNum = num1 * num2;
                    break;
                case R.id.nanoo: if(num2 == 0) {
                    Toast.makeText(context, "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT);
                    return;
                }
                    resultNum = num1 / num2;
                    break;
                case R.id.namerge: resultNum = num1 % num2;
                    break;
                default :
                    Toast.makeText(context, "예상치 못한 오류", Toast.LENGTH_SHORT);
                    return;
        }
        Log.i("result", ""+resultNum);
        TextView result = findViewById(R.id.result);
        result.setText(String.valueOf(resultNum));
    }
}
