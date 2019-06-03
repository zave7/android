package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ktiri.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.list);
        final ArrayList<Product> data = new ArrayList<>();

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







        MyAdapter myAdapter = new MyAdapter(this, data); // 얘가 데이터를 받아 관리한다.
        MyListView myListView = new MyListView(this);
        myListView.setAdapter(myAdapter);

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
