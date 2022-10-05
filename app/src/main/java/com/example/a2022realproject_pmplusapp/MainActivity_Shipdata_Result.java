package com.example.a2022realproject_pmplusapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class MainActivity_Shipdata_Result extends AppCompatActivity {

    ImageButton replay_shipdata;
    ImageButton no_replay_main;
    Button test;
    String rs_day1, rs_day2, rs_call, rs_sc;

    RecyclerView review;
    static RequestQueue requestQueue;
    String TAG = "data list";
    ShipAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shipdata_result);


        Intent intent = getIntent();
        //인텐트 문제인 듯? 이 부분을 수정해보기
        rs_sc = intent.getStringExtra("A"); //prtAgCd, 020
        rs_day1 = intent.getStringExtra("B"); //sde
        rs_day2 = intent.getStringExtra("B2"); //ede
        rs_call = intent.getStringExtra("C"); //clsgn

        //문제가 지금 null값 나오고 있음

        Log.d("dataintent","intentdata" + rs_sc);
        Log.d("dataintent","intentdata" + rs_day1);
        Log.d("dataintent","intentdata" + rs_day2);
        Log.d("dataintent","intentdata" + rs_call);


        replay_shipdata = (ImageButton)findViewById(R.id.btn_shipdata_replay_go);
        no_replay_main = (ImageButton)findViewById(R.id.btn_shipdata_go_main);
        test = (Button)findViewById(R.id.btn_shiptest);

        replay_shipdata.setOnClickListener(v->{
            Intent re_start = new Intent(getApplicationContext(),MainActivity_ShipData.class);
            startActivity(re_start);
        });

       no_replay_main.setOnClickListener(v->{
           Intent no_start = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
           startActivity(no_start);
       });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ShipAdapter();
        review = findViewById(R.id.ship_recyclerview);
        review.setLayoutManager(layoutManager);
        review.setAdapter(adapter); //레이아웃 어댑터 연결

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        test.setOnClickListener(new View.OnClickListener(){

            final int STEP_NONE = 0;
            final int STEP_Item = 1;
            final int STEP_Detail = 2;

            int step = STEP_NONE;
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1192000/VsslEtrynd2/Info?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&sde="+rs_day1+"&ede="+rs_day2+"&prtAgCd="+rs_sc+"&clsgn="+rs_call+"&pageNo=1&numOfRows=3",
                        new Response.Listener<String>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse : " + response);

                        String curr_tag = "";
                        //ArrayList<Station> arrStation = new ArrayList<>();
                        shipdata_Item ditem = new shipdata_Item();
                        adapter.clearItems(); //아이템 정리

                        try{
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            factory.setNamespaceAware(true);
                            XmlPullParser xpp = factory.newPullParser();

                            xpp.setInput( new StringReader(response) );
                            int eventType = xpp.getEventType();

                            while (eventType != XmlPullParser.END_DOCUMENT) { //문서의 끝일때까지 돌건데
                                if(eventType == XmlPullParser.START_DOCUMENT) { //이벤트타입이 START라면 XML데이터를 시작해
                                    //System.out.println("Start document");

                                } else if(eventType == XmlPullParser.START_TAG) { //START 태그를 기억할거야 (대표 태그)
                                    curr_tag = xpp.getName(); //대표태그를 currtag에서 기억할건데

                                   //ditem = new shipdata_Item(); //일단 값들 저장할 함수불러와 ****

                                    if(xpp.getName().equals("item")){ //만약 시작태그가 item하고 같다면 item에 있는 데이터를 파싱해

                                        step = STEP_Item; //item을 파싱하기 위해서 step에 step_item 저장

                                    } else if(xpp.getName().equals("detail")){
                                        step = STEP_Detail; //item을 파싱하기 위해서 step에 step_detail 저장

                                    } else{ //아무것도 없다면 그냥 0값 저장
                                        step = STEP_NONE;
                                    }

                                } else if(eventType == XmlPullParser.END_TAG) { //만약 태그의 끝 부분에 도달했다면

                                    if(xpp.getName().equals("item")){ //아이템에 있는 태그들을 가져올거야
                                        if(ditem.checkRecvAllData()){
                                            adapter.addItem(ditem);
                                        }
                                        //arrStation.add(station);
                                    }

                                    curr_tag = "";

                                    //태그안을 돌면서 데이터 파싱할거
                                } else if(eventType == XmlPullParser.TEXT) { // 시작 태그와 종료 태그 사이의 텍스트. " ex) <item>TEXT</item>"
                                    //태그 종류별로 기록

                                    if(step == STEP_Item){
                                        switch(curr_tag) //대표 목록 태그를 가져와서 text형태로 저장할거야
                                        { //이 정보들을 가져와 (여긴 item 태그에 속하는 데이터들)
                                            case "prtAgCd":
                                                ditem.prtAgCd = xpp.getText();
                                                Log.d(TAG, "onResponse : " + ditem.prtAgCd);
                                                break;

                                            case "prtAgNm":
                                                ditem.prtAgNm = xpp.getText();
                                                Log.d(TAG, "onResponse : " + ditem.prtAgNm);
                                                break;
                                            case "clsgn":
                                                ditem.clsgn = xpp.getText();
                                                break;
                                            case "vsslNm":
                                                ditem.vsslNm = xpp.getText();
                                                break;
                                            case "vsslNltyNm":
                                                ditem.vsslNltyNm = xpp.getText();
                                                break;
                                            case "vsslKndNm":
                                                ditem.vsslKndNm = xpp.getText();
                                                break;
                                            case "etryptPurpsNm":
                                                ditem.etryptPurpsNm = xpp.getText();
                                                break;
                                            case "dstnPrtNm":
                                                ditem.dstnPrtNm = xpp.getText();
                                                break;
                                        }
                                    } else if(step == STEP_Detail) { //만약 DETAIL 태그라면 그와 관련된 데이터들을 가져와

                                        switch (curr_tag) {
                                            case "etryptDt":
                                                ditem.etryptDt = xpp.getText();
                                                Log.d(TAG, "onResponse : " + ditem.etryptDt);
                                                break;
                                            case "tkoffDT":
                                                ditem.tkoffDT = xpp.getText();
                                                Log.d(TAG, "onResponse : " + ditem.tkoffDT);
                                                break;
                                            case "ibobprtNm":
                                                ditem.ibobprtNm = xpp.getText();
                                                break;
                                            case "laidupFcltyNm":
                                                ditem.laidupFcltyNm = xpp.getText();
                                                break;
                                            case "ldadngFrghtClCd":
                                                ditem.ldadngFrghtClCd = xpp.getText();
                                                break;
                                            case "grtg":
                                                ditem.grtg= xpp.getText();
                                                break;
                                            case "satmntEntrpsNm":
                                                ditem.satmntEntrpsNm = xpp.getText();
                                                break;
                                        }
                                    }
                                }
                                eventType = xpp.next();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "수신완료", Toast.LENGTH_LONG).show();
                    }
            },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse : " + error.toString());
                            }
                        })
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //요청 객체가 하나 만들어짐
                        Map<String, String> params = new HashMap<String, String>();
                        //요청 큐에 넣어주면 된다

                        return super.getParams();
                    }
                };
                request.setShouldCache(false);
                requestQueue.add(request);
                }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super. onOptionsItemSelected(item);
    }

  }
