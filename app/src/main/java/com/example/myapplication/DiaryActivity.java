package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class DiaryActivity extends AppCompatActivity {

    class MySQLOpenHelper extends SQLiteOpenHelper {
        public MySQLOpenHelper(Context context, String name) {
            //DB생성
            super(context, name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //테이블 생성
            String sql = "CREATE TABLE diary (diary_dt char(10) primary key, diary_content varchar2(400));";
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //테이블 삭제
            String sql = "DROP TABLE IF EXISTS diary";
            sqLiteDatabase.execSQL(sql);
        }
    }

    DatePicker datePicker;
    EditText editDiary;
    Button btnWrite;
    String date;

    private DiaryActivity.MySQLOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setTitle("간단 일기장");
        helper = new DiaryActivity.MySQLOpenHelper(this, "testDB");

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        editDiary = findViewById(R.id.editDiary);
        btnWrite = findViewById(R.id.btnWrite);

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date = Integer.toString(year) + "/" +
                        Integer.toString(monthOfYear + 1) + "/" +
                        Integer.toString(dayOfMonth);
                findDiary(date);
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    String str = editDiary.getText().toString();
                    if(!"".equals(str.trim())) {
                        insert(str);
                        Toast.makeText(DiaryActivity.this, "저장됨", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DiaryActivity.this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    public void insert(String content) {
        //입력
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        String date = (datePicker.getYear()+"/"+(datePicker.getMonth() + 1)+"/"+datePicker.getDayOfMonth()).trim();
        Log.i("insert date", date+"!!");
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM diary WHERE diary_dt = ?", new String[] {date});
        if(cursor.moveToNext()) {
            sqLiteDatabase.execSQL("UPDATE diary SET diary_content = ? WHERE diary_dt = ?", new String[] {content, date});

        } else {
            sqLiteDatabase.execSQL("INSERT INTO diary VALUES (?, ?)", new String[] {date, content});

        }
        cursor.close();
        sqLiteDatabase.close();
    }

    public String select(String date) {
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT diary_content FROM diary WHERE diary_dt = ?", new String[] {date});
        String result = "";
        if(cursor.moveToFirst()) {//첫행으로 이동
            result = cursor.getString(0);
            Log.i("select result", "결과:" + cursor.getString(0));
        }
        cursor.close();
        sqLiteDatabase.close();
        Log.i("select result", result);
        return result;
    }

    public void findDiary(String date) {
        String str = select(date);
        Log.i("changed", date);
        Log.i("changed", str + "!!");
        if(!"".equals(str)) {
            btnWrite.setText("수정하기");
            editDiary.setText(str);
        } else {
            editDiary.setText("");
            editDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        btnWrite.setEnabled(true);
    }
}
