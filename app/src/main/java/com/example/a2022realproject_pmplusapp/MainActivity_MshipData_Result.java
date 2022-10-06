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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//선별 관제 현황 결과 출력 소스코드 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_MshipData_Result extends AppCompatActivity {

    ImageButton gomain;
    ImageButton go_recycle;
    Toolbar toolbar;
    int count = 0;
    String rs_day1, rs_day2, rs_call, rs_sc;

    RecyclerView review;
    static RequestQueue requestQueue;
    String TAG = "data list";
    mShipAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mship_data_result);

        toolbar = (Toolbar)findViewById(R.id.toolbar_mshipdata_result);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("선별 관제 현황 조회결과");

        Intent intent = getIntent();

        rs_sc = intent.getStringExtra("prt"); //prtAgCd, 020
        rs_day1 = intent.getStringExtra("sde"); //sde
        rs_day2 = intent.getStringExtra("ede"); //ede
        rs_call = intent.getStringExtra("clsgnll"); //clsgn

        Log.d("dataintent", rs_sc);
        Log.d("dataintent", rs_day1);
        Log.d("dataintent", rs_day2);
        Log.d("dataintent", rs_call); //사용자로부터 입력받은 값이 잘 나오는지 확인

        gomain = (ImageButton)findViewById(R.id.btn_mshipdata_go_main);
        gomain.setOnClickListener(v ->{
            Intent start = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
            startActivity(start);
        });

        go_recycle = (ImageButton)findViewById(R.id.btn_mshipdata_replay_go);
        go_recycle.setOnClickListener(v->{
            Intent start = new Intent(getApplicationContext(),MainActivity_MshipData.class);
            startActivity(start);
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new mShipAdapter();
        review = findViewById(R.id.mship_recyclerview);
        review.setLayoutManager(layoutManager);
        review.setAdapter(adapter); //레이아웃 어댑터 연결

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://apis.data.go.kr/1192000/CntlVssl2/Info?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                        "&sde=" + rs_day1 + "&ede=" + rs_day2 + "&prtAgCd=" + rs_sc + "&clsgn=" + rs_call + "&pageNo=1&numOfRows=50",
                new Response.Listener<String>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse : " + response);

                        String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                        //ArrayList<Station> arrStation = new ArrayList<>();

                        mshipdata_Item mitem = new mshipdata_Item();
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
                                        mitem = new mshipdata_Item();
                                        count++; //ITEM 태그를 만난 횟수 세어주기
                                    }

                                } else if (eventType == XmlPullParser.END_TAG) {
                                    //item 태그 종료시 추가
                                    if (xpp.getName().equals("item")) {
                                        if (mitem.m_checkRecvAllData()) {
                                            adapter.addItem(mitem);
                                        }

                                    }
                                    curr_tag = "";
                                } else if (eventType == XmlPullParser.TEXT) {
                                    //태그 종류별로 기록
                                    switch (curr_tag) {
                                        case "prtAgCd":
                                            mitem.m_prtAgCd = xpp.getText();
                                            Log.d("please",mitem.m_prtAgCd); //로그를 통해 데이터가 잘 나오는지 확인
                                            break;
                                        case "prtAgNm":
                                            mitem.m_prtAgNm = xpp.getText();
                                            Log.d("please",mitem.m_prtAgNm);
                                            break;
                                        case "clsgn":
                                            mitem.m_clsgn = xpp.getText();
                                            Log.d("please",mitem.m_clsgn);
                                            break;
                                        case "vsslNm":
                                            mitem.m_vsslNm = xpp.getText();
                                            Log.d("please",mitem.m_vsslNm);
                                            break;
                                        case "vsslNltyNm":
                                            mitem.m_vsslNltyNm = xpp.getText();
                                            Log.d("please",mitem.m_vsslNltyNm);
                                            break;
                                        case "vsslKndNm":
                                            mitem.m_vsslKndNm = xpp.getText();
                                            Log.d("please",mitem.m_vsslKndNm);
                                            break;
                                        case "vsslGrtg":
                                            mitem.m_vsslGrtg = xpp.getText();
                                            Log.d("please",mitem.m_vsslGrtg);
                                            break;
                                        case "aprtfEtryptDt":
                                            mitem.m_aprtfEtryptDt= xpp.getText();
                                            Log.d("please",mitem.m_aprtfEtryptDt);
                                            break;

                                        /*item태그안의 자식태그들*/
                                        /*item 태그안의 자식 태그들 중 detail 부분의 태그들*/

                                        case "harborEntrpsNm":
                                            mitem.m_harborEntrpsNm = xpp.getText();
                                            Log.d("please",mitem.m_harborEntrpsNm);
                                            break;

                                        case "cntrlNm":
                                            mitem.m_cntrlNm = xpp.getText();
                                            Log.d("please",mitem.m_cntrlNm);
                                            break;

                                        case "cntrlOpertDt":
                                            mitem.m_cntrlOpertDt = xpp.getText();
                                            Log.d("please",mitem.m_cntrlOpertDt);
                                            break;

                                        case "fcltyCd":
                                            mitem.m_fcltyCd = xpp.getText();
                                            Log.d("please",mitem.m_fcltyCd);
                                            break;

                                        case "fcltySubCd":
                                            mitem.m_fcltySubCd = xpp.getText();
                                            Log.d("please",mitem.m_fcltySubCd);
                                            break;

                                        case "fcltyNm":
                                            mitem.m_fcltyNm= xpp.getText();
                                            Log.d("please",mitem.m_fcltyNm);
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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super. onOptionsItemSelected(item);
    }
}