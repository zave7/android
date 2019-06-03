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


public class Ex55Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex55);
    }
    public void clickNum(View view) {

        Context context = this;
        EditText editText1 = findViewById(R.id.gridNum1);
        EditText editText2 = findViewById(R.id.gridNum2);
        Button button = (Button) view;
        String value = button.getText().toString();
        if(editText1.isFocused()) {
            editText1.setText(editText1.getText().toString()+value);
        } else if(editText2.isFocused()) {
            editText2.setText(editText2.getText().toString()+value);
        } else {
            Toast.makeText(context, "입력할 폼을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }


    }
    public void execute(View view) {
        Context context = this;
        EditText editText1 = findViewById(R.id.gridNum1);
        EditText editText2 = findViewById(R.id.gridNum2);
        if("".equals(editText1.getText().toString()) ||
                "".equals(editText2.getText().toString())) {
            Log.i("result", "유효성체크");
            Toast.makeText(context, "입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView textView = findViewById(R.id.gridresult);

        double num1 = Double.parseDouble(editText1.getText().toString());
        double num2 = Double.parseDouble(editText2.getText().toString());
        double result = 0;
        switch(view.getId()) {
            case R.id.plusss: result = num1 + num2;
                break;
            case R.id.minusss: result = num1 - num2;
                break;
            case R.id.mull: result = num1 * num2;
                break;
            case R.id.divv: if(num2 == 0) {
                Toast.makeText(context, "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                return;
            }
                result = num1 / num2;
                break;
            default :
                Toast.makeText(context, "예상치 못한 오류", Toast.LENGTH_SHORT);
                return;
        }
        textView.setText("결과 : " + String.valueOf(result));
    }
}
