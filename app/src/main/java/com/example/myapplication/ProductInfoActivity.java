package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.ktiri.dto.Product;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        TextView tvProd_no = (TextView) findViewById(R.id.tvProd_no);//하위 버전 호환성을 고려해서 다운 캐스팅을 해줘야한다.
        TextView tvProd_name = (TextView) findViewById(R.id.tvProd_name);
        TextView tvProd_price = (TextView) findViewById(R.id.tvProd_price);

        Intent intent = getIntent();
        Product product = (Product) intent.getExtras().get("productInfo"); // 리턴 타입이 번들
        tvProd_no.setText(product.getProd_no());
        tvProd_name.setText(product.getProd_name());
        tvProd_price.setText(String.valueOf(product.getProd_price()));

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.npQuantity);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setWrapSelectorWheel(true);

    }

    public void addCart(View view) {
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.npQuantity);
        final String prod_no = ((TextView) findViewById(R.id.tvProd_no)).getText().toString();
        final int quantity = numberPicker.getValue();
        //응답 받은 쿠키를 저장하기 위해
//        final SharedPreferences pref = getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = pref.edit();
        final SharedPreferences pref = getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();





        new Thread() {
            public void run() {
                String urlStr = "http://192.168.14.65/myeljstl/addcart";
                InputStream inputStream = null;

                try {
                    urlStr +="?no=" + prod_no + "&count=" + quantity;
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProductInfoActivity.this, "장바구니 넣기 성공", Toast.LENGTH_LONG).show();
                            }
                        });

                        finish();
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
