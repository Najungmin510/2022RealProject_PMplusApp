package com.example.a2022realproject_pmplusapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import java.util.Objects;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class MainActivity_Shipdata_Result extends AppCompatActivity {

    ImageButton replay_shipdata;
    ImageButton no_replay_main;
    Toolbar toolbar;
    Button test;
    String rs_day1, rs_day2, rs_call, rs_sc;
    int count = 0; //몇개의 결과가 조회되었는지 셀 변수

    RecyclerView review;
    static RequestQueue requestQueue;
    String TAG = "data list";
    ShipAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shipdata_result);

        toolbar = (Toolbar)findViewById(R.id.toolbar_shipdata_result);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("선박 입출항 현황 조회결과");



        Intent intent = getIntent();
        //인텐트 문제인 듯? 이 부분을 수정해보기
        rs_sc = intent.getStringExtra("prt"); //prtAgCd, 020
        rs_day1 = intent.getStringExtra("sde"); //sde
        rs_day2 = intent.getStringExtra("ede"); //ede
        rs_call = intent.getStringExtra("clsgnll"); //clsgn

        //문제가 지금 null값 나오고 있음

        Log.d("dataintent", rs_sc);
        Log.d("dataintent", rs_day1);
        Log.d("dataintent", rs_day2);
        Log.d("dataintent", rs_call); //사용자로부터 입력받은 값이 잘 나오는지 확인


        replay_shipdata = (ImageButton) findViewById(R.id.btn_shipdata_replay_go);
        no_replay_main = (ImageButton) findViewById(R.id.btn_shipdata_go_main);


        replay_shipdata.setOnClickListener(v -> {
            Intent re_start = new Intent(getApplicationContext(), MainActivity_ShipData.class);
            startActivity(re_start);
        });

        no_replay_main.setOnClickListener(v -> {
            Intent no_start = new Intent(getApplicationContext(), MainActivity_PM_Main.class);
            startActivity(no_start);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ShipAdapter();
        review = findViewById(R.id.ship_recyclerview);
        review.setLayoutManager(layoutManager);
        review.setAdapter(adapter); //레이아웃 어댑터 연결

        requestQueue = Volley.newRequestQueue(getApplicationContext()); //volley로 데이터를 가져올거


                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1192000/VsslEtrynd2/Info?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&sde=" + rs_day1 + "&ede=" + rs_day2 + "&prtAgCd=" + rs_sc + "&clsgn=" + rs_call + "&pageNo=1&numOfRows=50",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                shipdata_Item ditem = new shipdata_Item();
                                adapter.clearItems(); //아이템 정리

                                try {
                                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                                    factory.setNamespaceAware(true);
                                    XmlPullParser xpp = factory.newPullParser();

                                    xpp.setInput(new StringReader(response));
                                    int eventType = xpp.getEventType();

                                    while (eventType != XmlPullParser.END_DOCUMENT) {
                                        if (eventType == XmlPullParser.START_DOCUMENT) {
                                            //System.out.println("Start document");

                                        } else if (eventType == XmlPullParser.START_TAG) {
                                            //시작하는 tag 기억


                                            curr_tag = xpp.getName();
                                            if (xpp.getName().equals("item")) { //item 태그를 기준으로 돌거임 <item> ~ </item> 이 1회 도는거임
                                                ditem = new shipdata_Item();
                                                count++; //ITEM 태그를 만난 횟수 세어주기
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (ditem.checkRecvAllData()) {
                                                    adapter.addItem(ditem);
                                                }

                                            }
                                            curr_tag = "";
                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "prtAgCd":
                                                    ditem.prtAgCd = xpp.getText();
                                                    Log.d("please",ditem.prtAgCd); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;
                                                case "prtAgNm":
                                                    ditem.prtAgNm = xpp.getText();
                                                    Log.d("please",ditem.prtAgNm);
                                                    break;
                                                case "clsgn":
                                                    ditem.clsgn = xpp.getText();
                                                    Log.d("please",ditem.clsgn);
                                                    break;
                                                case "vsslNm":
                                                    ditem.vsslNm = xpp.getText();
                                                    Log.d("please",ditem.vsslNm);
                                                    break;
                                                case "vsslNltyNm":
                                                    ditem.vsslNltyNm = xpp.getText();
                                                    Log.d("please",ditem.vsslNltyNm);
                                                    break;
                                                case "vsslKndNm":
                                                    ditem.vsslKndNm = xpp.getText();
                                                    Log.d("please",ditem.vsslKndNm);
                                                    break;
                                                case "etryptPurpsNm":
                                                    ditem. etryptPurpsNm = xpp.getText();
                                                    Log.d("please",ditem.etryptPurpsNm);
                                                    break;
                                                case "dstnPrtNm":
                                                    ditem.dstnPrtNm= xpp.getText();
                                                    Log.d("please",ditem.dstnPrtNm);
                                                    break;

                                                /*item태그안의 자식태그들*/
                                                /*item 태그안의 자식 태그들 중 detail 부분의 태그들*/

                                                case "etryptDt":
                                                    ditem.etryptDt = xpp.getText();
                                                    Log.d("please",ditem.etryptDt);
                                                    break;

                                                case "tkoffDt":
                                                    ditem.tkoffDT = xpp.getText();
                                                    Log.d("please",ditem.tkoffDT);
                                                    break;

                                                case "ibobprtNm":
                                                    ditem.ibobprtNm = xpp.getText();
                                                    Log.d("please",ditem.ibobprtNm);
                                                    break;

                                                case "laidupFcltyNm":
                                                    ditem.laidupFcltyNm = xpp.getText();
                                                    Log.d("please",ditem.laidupFcltyNm);
                                                    break;

                                                case "ldadngFrghtClCd":
                                                    ditem. ldadngFrghtClCd = xpp.getText();
                                                    Log.d("please",ditem.ldadngFrghtClCd);
                                                    break;

                                                case "grtg":
                                                    ditem.grtg= xpp.getText();
                                                    Log.d("please",ditem.grtg);
                                                    break;

                                                case "satmntEntrpsNm":
                                                    ditem.satmntEntrpsNm = xpp.getText();
                                                    Log.d("please",ditem.satmntEntrpsNm);
                                                    break;


                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), count+"개의 결과가 조회되었어요.", Toast.LENGTH_LONG).show();
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse : " + error.toString());
                            }
                        }
                ) {
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super. onOptionsItemSelected(item);
    }

  }
