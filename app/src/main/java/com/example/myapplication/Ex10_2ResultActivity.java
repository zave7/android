package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Ex10_2ResultActivity extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ex10_2_result);

                Intent intent = getIntent();
                String title = intent.getStringExtra("title");
                float score = intent.getFloatExtra("rating", 0);

                TextView textViewSub = findViewById(R.id.subject);
        TextView textViewRat = findViewById(R.id.rating);

        textViewSub.setText("영화 제목 : " + title);
        textViewRat.setText("별점 : " + String.valueOf(score));
    }
    public void close(View view) {

        Intent intent = new Intent(this, Ex10_2Activity.class);
        intent.putExtra("scoreAvg", 4.3F);
        setResult(RESULT_OK, intent);
        finish();
    }
}
