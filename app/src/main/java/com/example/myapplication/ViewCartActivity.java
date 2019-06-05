package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktiri.dto.Product;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        final SharedPreferences pref = getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();

        new Thread() {
            public void run() {
                String urlStr = "http://192.168.14.65/myeljstl/viewcart";
                InputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true); //요청데이터를 출력허용
                    conn.setRequestMethod("GET");

//                    DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
//                    dataOutputStream.writeBytes("no=" + prod_no + "&quantity=" + quantity); // massage body에 쓰기
//                    요청헤더에 쿠키 추가
                    String jsession_cookie = pref.getString("JSESSIONID", null); // 해당 이름의 프리퍼런스가 없으면 null로 대입
                    if(jsession_cookie != null) { //요청시에 이전에 로그인을 했는지 판단
                        Log.i("LoginActivity", "이미 로그인 성공된 상태");
                        conn.setRequestProperty("Cookie", jsession_cookie); //요청 헤더 설정 작업
                    } else {
                        Log.i("LoginActivity", "로그인 안된 상태");
                    }

                    //응답얻기
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) { //정상응답
                        List<String> cookies = conn.getHeaderFields().get("Set-cookie");
                        if(cookies != null) {
                            //JSESSIONID 쿠키없이 요청했을 경우
                            // 서버가 응답하는 응답헤더에는 쿠키가 있다

                            //JSESSIONID 쿠키가 요청헤더에 추가됐을경우
                            //서버가 응답하는 응답헤더에는 쿠키가 없다
                            for (String cookie : cookies) {
                                String cookieNameValue = cookie.split(";\\s*")[0];
                                String cookieName = cookie.split("=")[0];
                                editor.putString(cookieName, cookieNameValue);
                                editor.apply(); //xml파일에 쓰기 작업을 비동기 처리
                            }
                        }

                        inputStream = conn.getInputStream(); //응답결과 입력 스트림
                        byte[] buffer = new byte[1024];
                        byteArrayOutputStream = new ByteArrayOutputStream(buffer.length);
                        int readLength = -1;
                        while ((readLength = inputStream.read(buffer)) != -1) {
//                        String str = new String(buffer, 0, readLength);
//                        JSONObject jsonObject = new JSONObject();
                            //Log.i("NetworkActivity", "서버가 보내준 응답 결과 : " + str);
                            byteArrayOutputStream.write(buffer, 0, readLength);
                        }

                        byte[] byteData = null;
                        byteData = byteArrayOutputStream.toByteArray();
                        //응답내용 문자열
                        String str = new String(byteData, 0, byteData.length);
                        Log.i("json", str);
                        ObjectMapper objectMapper = new ObjectMapper();

                       JsonNode jsonNode = objectMapper.readTree(str).get(0);
                        final String prod_no = jsonNode.get("prod_no").toString().replace("\"", "");
                        final String prod_name = jsonNode.get("prod_name").textValue();
                        final String prod_price = jsonNode.get("prod_price").toString();
                        final String prod_quantity = jsonNode.get("quantity").toString();

                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView cartProd_no = findViewById(R.id.cartProd_no);
                                TextView cartProd_name = findViewById(R.id.cartProd_name);
                                TextView cartProd_price = findViewById(R.id.cartProd_price);
                                TextView cartProd_quantity = findViewById(R.id.cartProd_quantity);
                                cartProd_no.setText("상품 번호 : " + prod_no);
                                cartProd_name.setText("상품 번호 : " + prod_name);
                                cartProd_price.setText("상품 번호 : " + prod_price);
                                cartProd_quantity.setText("상품 번호 : " + prod_quantity);

                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
