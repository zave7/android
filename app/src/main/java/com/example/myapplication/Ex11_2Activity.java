package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class Ex11_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex11_2);
        final ListView listView = (ListView)findViewById(R.id.lv);
        final String[] arr = {"아메리카노", "아이스아메리카노", "두두"};

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
                android.R.layout.simple_list_item_single_choice,
//                android.R.layout.simple_list_item_multiple_choice,
                arr);


        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                Toast.makeText(Ex11_2Activity.this, arr[index], Toast.LENGTH_LONG).show();
                //listView.setItemChecked(index, true);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
