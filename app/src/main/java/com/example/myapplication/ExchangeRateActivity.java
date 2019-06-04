package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateActivity extends AppCompatActivity {

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            public void run() {
                String urlStr = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=Hg8tQpSqFP9E3xmOjXVugTnazlgtFDhW&searchdate=20190529&data=AP01";
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    int responseCode = con.getResponseCode(); //응답상태코드값
                    if(responseCode == 200) { //정상응답된 경우
                        is = con.getInputStream();
                        byte[] buf = new byte[1024];
                        int readLength = -1; //읽은 바이트수
                        StringBuffer sb = new StringBuffer();
                        while( (readLength = is.read(buf)) != -1 ){
                            sb.append( new String(buf, 0, readLength));
                        }
                        String str = sb.toString(); //응답결과
                        Log.i("ExchangeRateActivity", str);

//                        final StringBuffer strResult = new StringBuffer();
//                        JSONArray jsonArr = new JSONArray(str);
//
//                        //스피너를 위한 문자열배열
//                        final String[] arr = new String[jsonArr.length()];
//
//                        for(int i=0; i<jsonArr.length(); i++){
//                            JSONObject obj =  jsonArr.getJSONObject(i);
//
//                            //"국가 통화명" 중 국가만 얻기
//                            String cur_nm = obj.getString("cur_nm").split("\\s")[0];
//                            String cur_unit = obj.getString("cur_unit");//통화코드
//                            String deal_bas_r = obj.getString("deal_bas_r");//매매기준율
//
//                            strResult.append(cur_nm + " " + cur_unit +"\t\t" + deal_bas_r + "\n");
//                            arr[i] = cur_nm + " " + cur_unit +"\t\t" + deal_bas_r;
//                        }
//                        runOnUiThread(new Thread(){
//                            @Override
//                            public void run() {
//                                TextView tvResult = (TextView)findViewById(R.id.tvResult);
//                                tvResult.setText(strResult.toString());
//
//
//                                Spinner spinner1 = findViewById(R.id.spinner1);
//                                ArrayAdapter adapter =
//                                        new ArrayAdapter(MainActivity.this,
//                                                android.R.layout.simple_list_item_1,
//                                                arr);
//                                spinner1.setAdapter(adapter);
//
//
//                                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                    @Override
//                                    public void onItemSelected(AdapterView<?> adapterView,
//                                                               View view,
//                                                               int index, long l) {
//                                        TextView tv_spinner_result = (TextView)findViewById(R.id.tv_spinner_result);
//                                        tv_spinner_result.setText(
//                                                "대한민국:" + arr[index].split("\\s",3)[2]+"원");
//                                    }
//
//                                    @Override
//                                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                                    }
//                                });
//                            }
//                        });

                       ObjectMapper mapper = new ObjectMapper();
                       final List<com.kitri.dto.ExchangeRate> listExchangeRate = mapper.readValue(str, new TypeReference<ArrayList<com.kitri.dto.ExchangeRate>>(){});
                       final StringBuffer strResult = new StringBuffer(); //textview에 보여줄 목록
                       final String[]arr = new String[listExchangeRate.size()]; //spinner에 보여줄 목록
                       for(int i=0; i<listExchangeRate.size(); i++){
                           com.kitri.dto.ExchangeRate exchageRate = listExchangeRate.get(i);
                           String nation = exchageRate.getCur_nm().split("\\s")[0];//국가
                           String cur_unit = exchageRate.getCur_unit(); //통화단위
                           String deal_bas_r = exchageRate.getDeal_bas_r(); //매매기준율

                           strResult.append(nation + " " + cur_unit +"\t\t" + deal_bas_r + "\n");

                           arr[i] = nation +"\t" + cur_unit;
                       }
                       runOnUiThread(new Thread() {
                           @Override
                           public void run() {
                               TextView tvResult = (TextView)findViewById(R.id.tvResult);
                                tvResult.setText(strResult.toString());
//
//
                                Spinner spinner1 = findViewById(R.id.spinner1);
                                ArrayAdapter adapter =
                                        new ArrayAdapter(ExchangeRateActivity.this,
                                                android.R.layout.simple_list_item_1,
                                                arr);
                                spinner1.setAdapter(adapter);


                               spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                   @Override
                                   public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                                       com.kitri.dto.ExchangeRate exchangeRate = listExchangeRate.get(index);
                                       TextView tv_spinner_result = (TextView)findViewById(R.id.tv_spinner_result);
                                       tv_spinner_result.setText("대한민국:" + exchangeRate.getDeal_bas_r() + "원");
                                   }

                                   @Override
                                   public void onNothingSelected(AdapterView<?> adapterView) {

                                   }
                               });
//
                           }
                       });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
                } finally{
                    if(is != null){
                        try {
                            is.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }

        }.start();
    }
}
