package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ktiri.dto.Product;

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

    }
}
