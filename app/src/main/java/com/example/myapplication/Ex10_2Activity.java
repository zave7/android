package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Ex10_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_2);

        Button btScore = (Button) findViewById(R.id.btScore);
        btScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingbarScore);
                float rating = ratingBar.getRating();
//                Toast.makeText(Ex10_2Activity.this, "별점 = " + rating, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(Ex10_2Activity.this, Ex10_2ResultActivity.class);
                intent.putExtra("title", "어벤져스엔드게임");
                intent.putExtra("rating", rating);
                //startActivity(intent);
                startActivityForResult(intent, 0);

            }
        });

    }
    public void close(View view) {
        finish();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            float scoreAvg = data.getFloatExtra("scoreAvg", 0);
            Toast.makeText(this, "영화 평점은 " + scoreAvg + "점 입니다", Toast.LENGTH_LONG).show();
        }
    }
}
