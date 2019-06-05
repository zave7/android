package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginResultActivity.class);//명시적 인텐트
        final String id = ((EditText) findViewById(R.id.etid)).getText().toString();
        final String pass = ((EditText) findViewById(R.id.etpass)).getText().toString();

        //응답 받은 쿠키를 저장하기 위해
        final SharedPreferences pref = getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();

        new Thread() {
            public void run() {
                String urlStr = "http://192.168.14.52/myeljstl/login";
                InputStream inputStream = null;

                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true); //요청데이터를 출력허용
                    conn.setRequestMethod("POST");

                    DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
                    dataOutputStream.writeBytes("id=" + id + "&pass=" + pass); // massage body에 쓰기
                    //요청헤더에 쿠키 추가
                    String jsession_cookie = pref.getString("JSESSIONID", null); // 해당 이름의 프리퍼런스가 없으면 null로 대입
//                    if(jsession_cookie != null) { //요청시에 이전에 로그인을 했는지 판단
//                        Log.i("LoginActivity", "이미 로그인 성공된 상태");
//                        conn.setRequestProperty("Cookie", jsession_cookie); //요청 헤더 설정 작업
//                    } else {
//                        Log.i("LoginActivity", "로그인 안된 상태");
//                    }

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) { //정상응답
//                        inputStream = conn.getInputStream();
//                        StringBuffer sb = new StringBuffer();
//                        int readValue = -1;
//                        while ((readValue = inputStream.read()) != -1) {
//                            sb.append((char)readValue);
//                        }

                        Log.i("LoginActivity", conn.getHeaderField("Set-cookie")); //응답받으면 쿠키값이 전달되어있을 것으로 예상
//                        Log.i("LoginActivity", sb.toString());
//                        if(sb.toString().trim().equals("1")) { // 로그인이 성공된 경우
                            List<String> cookies = conn.getHeaderFields().get("Set-cookie");
                            if(cookies != null) {
                                for (String cookie : cookies) {
                                    String cookieNameValue = cookie.split(";\\s*")[0];
                                    String cookieName = cookie.split("=")[0];
                                    editor.putString(cookieName, cookieNameValue);
                                    editor.apply(); //xml파일에 쓰기 작업을 비동기 처리
                                }
                            }
//                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        intent.putExtra("id", id);
        intent.putExtra("pass", pass);
        startActivity(intent);

    }
}
