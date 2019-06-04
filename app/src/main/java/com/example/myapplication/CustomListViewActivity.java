package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktiri.dto.Product;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {
    private ArrayList<Product> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);



        final MyListView myListView = new MyListView(this);
//        Product product = new Product();
//        product.setProd_no("001");
//        product.setProd_price(1500);
//        product.setProd_name("아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("002");
//        product.setProd_price(30000);
//        product.setProd_name("아이스 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("003");
//        product.setProd_price(600);
//        product.setProd_name("바나나 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("004");
//        product.setProd_price(250);
//        product.setProd_name("초코 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("005");
//        product.setProd_price(250);
//        product.setProd_name("초코 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("006");
//        product.setProd_price(250);
//        product.setProd_name("초코 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("007");
//        product.setProd_price(250);
//        product.setProd_name("초코 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("008");
//        product.setProd_price(250);
//        product.setProd_name("초코 아메리카노");
//        data.add(product);
//
//        product = new Product();
//        product.setProd_no("009");
//        product.setProd_price(250);
//        product.setProd_name("초코 아메리카노");
//        data.add(product);

        //웹서버 URL
        new Thread() {
            @Override
            public void run() {
                String urlStr = "http://192.168.14.52/myeljstl/productlistjson";
                InputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
//                    GET방식 요청
//                    urlStr += "?opt=add";
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

//                    POST방식 요청
//                    URL url = new URL(urlStr);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("POST");
//                    conn.setDoOutput(true); // 요청 전달 데이터를 message body에 쓰기 허용
//                    DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
//                    dataOutputStream.writeBytes("opt=add"); // massage body에 쓰기

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

                    ObjectMapper objectMapper = new ObjectMapper();//JSCKSON API json오브젝트와 DTO간의 매핑
                    //응답내용을 문자열의 구성이 JSONArray 포맷이면
                    //DTO클래스배열 타입으로 매핑
                    //List<Product> list = Arrays.asList(objectMapper.readValue(str, Product[].class));
                    data = objectMapper.readValue(str, new TypeReference<ArrayList<Product>>() {
                    });

                    final MyAdapter myAdapter = new MyAdapter(CustomListViewActivity.this, data); // 얘가 데이터를 받아 관리한다.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myListView.setAdapter(myAdapter); //main 스레드가 아닌 사용자정의 스레드에서는 위젯 myListView에 접근할 수 없다
                        }
                    });

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
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.list);
        linearLayout.addView(myListView);

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Toast.makeText(CustomListViewActivity.this, data.get(index).getProd_name(), Toast.LENGTH_LONG);
                //직렬화 가능한 객체, 기본형 데이터 들만 Put extra로 전달 가능!!!! Implement Serialize
                Intent intent = new Intent(CustomListViewActivity.this, ProductInfoActivity.class);
                intent.putExtra("productInfo", data.get(index));
                startActivity(intent);
                return false;
            }
        });
    }
}
