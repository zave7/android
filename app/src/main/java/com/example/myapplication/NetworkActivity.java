package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktiri.dto.Product;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        new Thread() {
            @Override
            public void run() {
                String urlStr = "http://192.168.14.52/androidweb/";
                InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
                try {
//                    GET방식 요청
//                    urlStr += "?opt=add";
//                    URL url = new URL(urlStr);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");

//                    POST방식 요청
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true); // 요청 전달 데이터를 message body에 쓰기 허용
                    DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
                    dataOutputStream.writeBytes("opt=add"); // massage body에 쓰기

                    inputStream = conn.getInputStream();
                    byte[] buffer = new byte[1024];
            byteArrayOutputStream = new ByteArrayOutputStream(buffer.length);
                    int readLength = -1;
                    while((readLength = inputStream.read(buffer)) != -1) {
//                        String str = new String(buffer, 0, readLength);
//                        JSONObject jsonObject = new JSONObject();
                        //Log.i("NetworkActivity", "서버가 보내준 응답 결과 : " + str);
                        byteArrayOutputStream.write(buffer, 0, readLength);
                    }
                    byte[] byteData = null;
                    byteData = byteArrayOutputStream.toByteArray();
                    String str = new String(byteData, 0, byteData.length);

//                    JSONObject jsonObject = new JSONObject(str);
//                    int status = jsonObject.getInt("status");
//                    String msg = jsonObject.getString("msg");
//                    Log.i("NetworkActivity", "서버가 보내준 [status] : " + status);
//                    Log.i("NetworkActivity", "서버가 보내준 [msg] : " + msg);
//                    Log.i("NetworkActivity", "서버가 보내준 [method] : " + jsonObject.getString("method"));

//                    JSONArray jsonArray = new JSONArray(str);
//                    Log.i("NetworkActivity", "서버가 보내준 상품종류" + jsonArray.length());
//                    for(int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                        Log.i("NetworkActivity", "- 상품번호 : " + jsonObject.getString("prod_no"));
//                        Log.i("NetworkActivity", "- 상품명 : " + jsonObject.getString("prod_name"));
//                        Log.i("NetworkActivity", "- 가격 : " + jsonObject.getInt("prod_price"));
//                    }
                    ObjectMapper objectMapper = new ObjectMapper();

                    List<Product> list = Arrays.asList(objectMapper.readValue(str, Product[].class));

                    Log.i("NetworkActivity", "서버가 보내준 상품종류 : " + list.size());
                    for(int i =0; i < list.size(); i++) {
                        Product product = list.get(i);
                        Log.i("NetworkActivity", "상품번호 : " + product.getProd_no());
                        Log.i("NetworkActivity", "상품명 : " + product.getProd_name());
                        Log.i("NetworkActivity", "가격 : " + product.getProd_price());

                    }
                } catch (MalformedURLException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }     finally {
                    if(inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();


    }
}
