package com.example.a2022realproject_pmplusapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

//Intent 이용해서 사용자가 선택한 값 메인으로 보내주기
//로컬 키 총 9개임
//새로고침 추가해야함 밑에 데이터 안나옴 ㅠ... 왜안나옴????

public class MainActivity_pmmain_weather extends AppCompatActivity {


    String[] weather_seoul = {"서울 11B10101", "인천 11B20201", "수원 11B20601", "성남 11B20605", "안양 11B20602", "광명 11B10103",
            "과천 11B10102", "평택 11B20606", "오산 11B20603", "의왕 11B20609", "용인 11B20612", "군포 11B20610", "안성 11B20611", "화성 11B20604",
            "양평 11B20503", "구리 11B20501", "남양주 11B20502", "하남 11B20504", "이천 11B20701", "여주 11B20703", "광주 11B20702", "의정부 11B20301",
            "고양 11B20302", "파주 11B20305", "양주 11B20304", "동두천 11B20401", "연천 11B20402", "포천 11B20403", "가평 11B20404", "강화 11B20101"
            , "김포 11B20102", "시흥 11B20202", "부천 11B20204", "안산 11B20203", "백령도 11A00101"};

    String[] weather_busan = {"부산 11H20201", "울산 11H20101", "김해 11H20304", "양산 11H20102", "창원 11H20301", "밀양 11H20601",
            "함안 11H20603", "창녕 11H20604", "의령 11H20602", "진주 11H20701", "하동 11H20704", "사천 11H20402", "거창 11H20502", "합천 11H20503",
            "산청 11H20703", "함양 11H20501", "통영 11H20401", "거제 11H20403", "고성 11H20404", "남해 11H20405"};

    String[] weather_daegu = {"대구 11H10701", "영천 11H10702", "경산 11H10703", "청도 11H10704", "칠곡 11H10705", "김천 11H10601", "구미 11H10602", "군위 11H10603", "고령 11H10604",
            "성주 11H10605", "안동 11H10501", "의성 11H10502", "청송 11H10503", "상주 11H10302", "문경 11H10301", "예천 11H10303", "영주 11H10401", "봉화 11H10402", "영양 11H10403",
            "울진 11H10101", "영덕 11H10102", "포항 11H10201", "경주 11H10202", "울릉도 11E00101", "독도 11E00102"};

    String[] weather_gwangju = {"광주 11F20501", "나주 11F20503", "장성 11F20502", "담양 11F20504", "화순 11F20505", "영광 21F20102", "함평 21F20101", "목포 21F20801", "무안 21F20804",
            "영암 21F20802", "진도 21F20201", "신안 21F20803", "흑산도 11F20701", "순천 11F20603", "순천시 11F20405", "광양 11F20402", "구례 11F20601",
            "곡성 11F20602", "완도 11F20301", "강진 11F20303", "장흥 11F20304", "해남 11F20302", "여수 11F20401", "고흥 11F20403", "보성 11F20404"};

    String[] weather_bukdo = {"전주 11F10201", "익산 11F10202", "군산 21F10501", "정읍 11F10203", "김제 21F10502", "남원 11F10401", "고창 21F10601",
            "무주 11F10302", "부안 21F10602", "순창 11F10403", "완주 11F10204", "임실 11F10402", "장수 11F10301", "진안 11F10303"}; //전라북도

    String[] weather_daejeon = {"대전 11C20401", "세종 11C20404", "공주 11C20402", "논산 11C20602", "계릉 11C20403", "금산 11C20601", "천안 11C20301", "아산 11C20302", "예산 11C20303",
            "서산 11C20101", "태안 11C20102", "당진 11C20103", "홍성 11C20104", "보령 11C20201", "서천 11C20202", "청양 11C20502", "부여 11C20501"};

    String[] weather_chungcheon = {"청주 11C10301", "증평 11C10304", "괴산 11C10303", "진천 11C10102", "충주 11C10101", "음성 11C10103",
            "제천 11C10201", "단양 11C10202", "보은 11C10302", "옥천 11C10403", "영동 11C10402", "추풍령 11C10401"};

    String[] weather_gangwon = {"철원 11D10101", "화천 11D10102", "인제 11D10201", "양구 11D10202", "춘천 11D10301", "홍천 11D10302", "원주 11D10401",
            "횡성 11D10402", "영월 11D10501", "정선 11D10502", "평창 11D10503", "대관령 11D20201", "속초 11D20401", "고성 11D20402", "양양 11D20403", "강릉 11D20501",
            "동해 11D20601", "삼척 11D20602", "태백 11D20301"};

    String[] weather_jeju = {"제주 11G00201", "서귀포 11G00401", "성산 11G00101", "고산 11G00501", "성판악 11G00302", "이어도 11G00601", "추자도 11G00800"};


    TextView seoul;
    TextView busan;
    TextView daegu;
    TextView gwangju;
    TextView daejeon;
    TextView bukdo;
    TextView chungcheon;
    TextView gangwon;
    TextView jeju;

    String local_key_seoul = "11B10101"; //기본값 서울
    String local_key_busan = "11H20201"; //기본값 부산
    String local_key_daegu = "11H10701"; // 기본값 대구
    String local_key_gwangju = "11F20501"; //기본값 광주
    String local_key_daejeon = "11C20401"; //기본값 대전
    String local_key_bukdo = "11F10201"; // 기본값 전주
    String local_key_chungcheon = "11C10301"; //기본값 청주
    String local_key_gangwon = "11D10101"; //기본값 철원
    String local_key_jeju = "11G00201"; //기본값 제주

    String TAG = "weather mini LIST";

    static RequestQueue requestQueue;

    SeoulAdapter SeoulAdapter;
    RecyclerView reseoul;

    BusanAdapter BusanAdapter;
    RecyclerView rebusan;

    BukdoAdapter BukdoAdapter;
    RecyclerView rebukdo;

    ChungcheonAdapter ChungcheonAdapter;
    RecyclerView rechungcheon;

    DaeguAdapter DaeguAdapter;
    RecyclerView redaegu;

    DaejeonAdapter DaejeonAdapter;
    RecyclerView redaejeon;

    GangwonAdapter GangwonAdapter;
    RecyclerView regangwon;

    GwangjuAdapter GwangjuAdapter;
    RecyclerView regwangju;

    JejuAdapter JejuAdapter;
    RecyclerView rejeju;

    Toolbar toolbar;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pmmain_weather);

        toolbar = (Toolbar) findViewById(R.id.toolbar_miniweather); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before);
        getSupportActionBar().setTitle("육상 날씨 조회");


        //서울 부분 데이터 추출
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        SeoulAdapter = new SeoulAdapter(); //어댑터를 불러오고
        reseoul = findViewById(R.id.recycler_mini_seoul);
        reseoul.setLayoutManager(layoutManager);
        reseoul.setAdapter(SeoulAdapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext()); //volley로 데이터를 가져옴

        Spinner spinner = findViewById(R.id.spinner_miniweather_seoul);
        seoul = findViewById(R.id.text_mini_seoul);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_seoul
        );

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seoul.setText(weather_seoul[position]);
                String sp = seoul.getText().toString();
                local_key_seoul = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                //api 코드
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_seoul,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                seoul_item SeoulItem = new seoul_item();
                                SeoulAdapter.clearItems(); //아이템 정리

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
                                                SeoulItem = new seoul_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (SeoulItem.checkRecvAllData()) {
                                                    SeoulAdapter.addItem(SeoulItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    SeoulItem.s_numEf = xpp.getText();

                                                    switch (SeoulItem.s_numEf) {
                                                        case "0":
                                                            SeoulItem.s_numEf = "오늘 오후";
                                                            break;
                                                        case "1":
                                                            SeoulItem.s_numEf = "내일 오전";
                                                            break;
                                                        case "2":
                                                            SeoulItem.s_numEf = "내일 오후";
                                                            break;
                                                        case "3":
                                                            SeoulItem.s_numEf = "모레 오전";
                                                            break;
                                                        case "4":
                                                            SeoulItem.s_numEf = "모레 오후";
                                                            break;
                                                    }
                                                    Log.d("please", SeoulItem.s_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    SeoulItem.s_rnYn = xpp.getText();

                                                    switch (SeoulItem.s_rnYn) {
                                                        case "0":
                                                            SeoulItem.s_rnYn = "강수 없음";
                                                            break;
                                                        case "1":
                                                            SeoulItem.s_rnYn = "비";
                                                            break;
                                                        case "2":
                                                            SeoulItem.s_rnYn = "비/눈";
                                                            break;
                                                        case "3":
                                                            SeoulItem.s_rnYn = "눈";
                                                            break;
                                                        case "4":
                                                            SeoulItem.s_rnYn = "소나기";
                                                            break;
                                                    }

                                                    Log.d("please", SeoulItem.s_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    SeoulItem.s_wf = xpp.getText();
                                                    Log.d("please", SeoulItem.s_wf);
                                                    break;

                                                case "rnSt":
                                                    SeoulItem.s_rnSt = xpp.getText();

                                                    if (SeoulItem.s_rnSt.equals("0")) {
                                                        SeoulItem.s_rnSt = "강수 확률 없음";
                                                    } else if (SeoulItem.s_rnSt.equals("1")) {
                                                        SeoulItem.s_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", SeoulItem.s_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    SeoulItem.s_ta = xpp.getText();
                                                    Log.d("please", SeoulItem.s_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) { //아무것도 선택 안되었을 때

                //api 코드
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11B10101",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                seoul_item SeoulItem = new seoul_item();
                                SeoulAdapter.clearItems(); //아이템 정리

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
                                                SeoulItem = new seoul_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (SeoulItem.checkRecvAllData()) {
                                                    SeoulAdapter.addItem(SeoulItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    SeoulItem.s_numEf = xpp.getText();

                                                    switch (SeoulItem.s_numEf) {
                                                        case "0":
                                                            SeoulItem.s_numEf = "오늘 오후";
                                                            break;
                                                        case "1":
                                                            SeoulItem.s_numEf = "내일 오전";
                                                            break;
                                                        case "2":
                                                            SeoulItem.s_numEf = "내일 오후";
                                                            break;
                                                        case "3":
                                                            SeoulItem.s_numEf = "모레 오전";
                                                            break;
                                                        case "4":
                                                            SeoulItem.s_numEf = "모레 오후";
                                                            break;
                                                    }
                                                    Log.d("please", SeoulItem.s_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    SeoulItem.s_rnYn = xpp.getText();

                                                    switch (SeoulItem.s_rnYn) {
                                                        case "0":
                                                            SeoulItem.s_rnYn = "강수 없음";
                                                            break;
                                                        case "1":
                                                            SeoulItem.s_rnYn = "비";
                                                            break;
                                                        case "2":
                                                            SeoulItem.s_rnYn = "비/눈";
                                                            break;
                                                        case "3":
                                                            SeoulItem.s_rnYn = "눈";
                                                            break;
                                                        case "4":
                                                            SeoulItem.s_rnYn = "소나기";
                                                            break;
                                                    }

                                                    Log.d("please", SeoulItem.s_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    SeoulItem.s_wf = xpp.getText();
                                                    Log.d("please", SeoulItem.s_wf);
                                                    break;

                                                case "rnSt":
                                                    SeoulItem.s_rnSt = xpp.getText();

                                                    if (SeoulItem.s_rnSt.equals("0")) {
                                                        SeoulItem.s_rnSt = "강수 확률 없음";
                                                    } else if (SeoulItem.s_rnSt.equals("1")) {
                                                        SeoulItem.s_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", SeoulItem.s_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    SeoulItem.s_ta = xpp.getText();
                                                    Log.d("please", SeoulItem.s_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //부산 날씨 가져오는 코드 : 기본값 부산날씨
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        BusanAdapter = new BusanAdapter();
        rebusan = findViewById(R.id.recycler_mini_busan); //리사이클러뷰를 적용할 레이아웃 설정
        rebusan.setLayoutManager(layoutManager2);
        rebusan.setAdapter(BusanAdapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext()); //volley로 데이터를 가져옴


        //부산 부분 데이터 추출
        Spinner spinner2 = findViewById(R.id.spinner_miniweather_busan);
        busan = findViewById(R.id.text_mini_busan);

        ArrayAdapter<String> two_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_busan
        );

        spinner2.setAdapter(two_adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                busan.setText(weather_busan[position]);
                String sp = busan.getText().toString();
                local_key_busan = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장


                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_busan, //부산에 대한 로컬키 넣어주고
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                busan_item BusanItem = new busan_item();
                                BusanAdapter.clearItems(); //아이템 정리

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
                                                BusanItem = new busan_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (BusanItem.checkRecvAllData()) {
                                                    BusanAdapter.addItem(BusanItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    BusanItem.busan_numEf = xpp.getText();

                                                    switch (BusanItem.busan_numEf) {
                                                        case "0":
                                                            BusanItem.busan_numEf = "오늘 오후";
                                                            break;
                                                        case "1":
                                                            BusanItem.busan_numEf = "내일 오전";
                                                            break;
                                                        case "2":
                                                            BusanItem.busan_numEf = "내일 오후";
                                                            break;
                                                        case "3":
                                                            BusanItem.busan_numEf = "모레 오전";
                                                            break;
                                                        case "4":
                                                            BusanItem.busan_numEf = "모레 오후";
                                                            break;
                                                    }
                                                    Log.d("please", BusanItem.busan_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    BusanItem.busan_rnYn = xpp.getText();

                                                    switch (BusanItem.busan_rnYn) {
                                                        case "0":
                                                            BusanItem.busan_rnYn = "강수 없음";
                                                            break;
                                                        case "1":
                                                            BusanItem.busan_rnYn = "비";
                                                            break;
                                                        case "2":
                                                            BusanItem.busan_rnYn = "비/눈";
                                                            break;
                                                        case "3":
                                                            BusanItem.busan_rnYn = "눈";
                                                            break;
                                                        case "4":
                                                            BusanItem.busan_rnYn = "소나기";
                                                            break;
                                                    }

                                                    Log.d("please", BusanItem.busan_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    BusanItem.busan_wf = xpp.getText();
                                                    Log.d("please", BusanItem.busan_wf);
                                                    break;

                                                case "rnSt":
                                                    BusanItem.busan_rnSt = xpp.getText();

                                                    if (BusanItem.busan_rnSt.equals("0")) {
                                                        BusanItem.busan_rnSt = "강수 확률 없음";
                                                    } else if (BusanItem.busan_rnSt.equals("1")) {
                                                        BusanItem.busan_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", BusanItem.busan_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    BusanItem.busan_ta = xpp.getText();
                                                    Log.d("please", BusanItem.busan_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                BusanAdapter.notifyDataSetChanged();
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
                //api 코드
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11H20201", //부산에 대한 로컬키 넣어주고
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                busan_item BusanItem = new busan_item();
                                BusanAdapter.clearItems(); //아이템 정리

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
                                                BusanItem = new busan_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (BusanItem.checkRecvAllData()) {
                                                    BusanAdapter.addItem(BusanItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    BusanItem.busan_numEf = xpp.getText();

                                                    switch (BusanItem.busan_numEf) {
                                                        case "0":
                                                            BusanItem.busan_numEf = "오늘 오후";
                                                            break;
                                                        case "1":
                                                            BusanItem.busan_numEf = "내일 오전";
                                                            break;
                                                        case "2":
                                                            BusanItem.busan_numEf = "내일 오후";
                                                            break;
                                                        case "3":
                                                            BusanItem.busan_numEf = "모레 오전";
                                                            break;
                                                        case "4":
                                                            BusanItem.busan_numEf = "모레 오후";
                                                            break;
                                                    }
                                                    Log.d("please", BusanItem.busan_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    BusanItem.busan_rnYn = xpp.getText();

                                                    switch (BusanItem.busan_rnYn) {
                                                        case "0":
                                                            BusanItem.busan_rnYn = "강수 없음";
                                                            break;
                                                        case "1":
                                                            BusanItem.busan_rnYn = "비";
                                                            break;
                                                        case "2":
                                                            BusanItem.busan_rnYn = "비/눈";
                                                            break;
                                                        case "3":
                                                            BusanItem.busan_rnYn = "눈";
                                                            break;
                                                        case "4":
                                                            BusanItem.busan_rnYn = "소나기";
                                                            break;
                                                    }

                                                    Log.d("please", BusanItem.busan_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    BusanItem.busan_wf = xpp.getText();
                                                    Log.d("please", BusanItem.busan_wf);
                                                    break;

                                                case "rnSt":
                                                    BusanItem.busan_rnSt = xpp.getText();

                                                    if (BusanItem.busan_rnSt.equals("0")) {
                                                        BusanItem.busan_rnSt = "강수 확률 없음";
                                                    } else if (BusanItem.busan_rnSt.equals("1")) {
                                                        BusanItem.busan_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", BusanItem.busan_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    BusanItem.busan_ta = xpp.getText();
                                                    Log.d("please", BusanItem.busan_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                BusanAdapter.notifyDataSetChanged();
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
                //api 코드

            }
        });


        //대구 부분 데이터 추출 , 기본값 : 대구
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        DaeguAdapter = new DaeguAdapter();
        redaegu = findViewById(R.id.recycler_mini_daegu); //리사이클러뷰를 적용할 레이아웃 설정, 대구 파트
        redaegu.setLayoutManager(layoutManager3);
        redaegu.setAdapter(DaeguAdapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext()); //volley로 데이터를 가져옴

        Spinner spinner3 = findViewById(R.id.spinner_miniweather_daegu);
        daegu = findViewById(R.id.text_mini_daegu);

        ArrayAdapter<String> three_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_daegu
        );

        spinner3.setAdapter(three_adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //데이터 선택 시
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daegu.setText(weather_daegu[position]);
                String sp = daegu.getText().toString();
                local_key_daegu = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_daegu,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                daegu_item DaeguItem = new daegu_item();
                                DaeguAdapter.clearItems(); //아이템 정리

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
                                                DaeguItem = new daegu_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (DaeguItem.checkRecvAllData()) {
                                                    DaeguAdapter.addItem(DaeguItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    DaeguItem.daegu_numEf = xpp.getText();

                                                    if (DaeguItem.daegu_numEf.equals("0")) {
                                                        DaeguItem.daegu_numEf = "오늘 오후";
                                                    } else if (DaeguItem.daegu_numEf.equals("1")) {
                                                        DaeguItem.daegu_numEf = "내일 오전";
                                                    } else if (DaeguItem.daegu_numEf.equals("2")) {
                                                        DaeguItem.daegu_numEf = "내일 오후";
                                                    } else if (DaeguItem.daegu_numEf.equals("3")) {
                                                        DaeguItem.daegu_numEf = "모레 오전";
                                                    } else if (DaeguItem.daegu_numEf.equals("4")) {
                                                        DaeguItem.daegu_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", DaeguItem.daegu_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    DaeguItem.daegu_rnYn = xpp.getText();

                                                    if (DaeguItem.daegu_rnYn.equals("0")) {
                                                        DaeguItem.daegu_rnYn = "강수 없음";
                                                    } else if (DaeguItem.daegu_rnYn.equals("1")) {
                                                        DaeguItem.daegu_rnYn = "비";
                                                    } else if (DaeguItem.daegu_rnYn.equals("2")) {
                                                        DaeguItem.daegu_rnYn = "비/눈";
                                                    } else if (DaeguItem.daegu_rnYn.equals("3")) {
                                                        DaeguItem.daegu_rnYn = "눈";
                                                    } else if (DaeguItem.daegu_rnYn.equals("4")) {
                                                        DaeguItem.daegu_rnYn = "소나기";
                                                    }

                                                    Log.d("please", DaeguItem.daegu_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    DaeguItem.daegu_wf = xpp.getText();
                                                    Log.d("please", DaeguItem.daegu_wf);
                                                    break;

                                                case "rnSt":
                                                    DaeguItem.daegu_rnSt = xpp.getText();

                                                    if (DaeguItem.daegu_rnSt.equals("0")) {
                                                        DaeguItem.daegu_rnSt = "강수 확률 없음";
                                                    } else if (DaeguItem.daegu_rnSt.equals("1")) {
                                                        DaeguItem.daegu_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", DaeguItem.daegu_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    DaeguItem.daegu_ta = xpp.getText();
                                                    Log.d("please", DaeguItem.daegu_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) { //아무것도 선택되지 않았을 시, 그에 대한 코드 (기본값 넣어주기)
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11H10701",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                daegu_item DaeguItem = new daegu_item();
                                DaeguAdapter.clearItems(); //아이템 정리

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
                                                DaeguItem = new daegu_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (DaeguItem.checkRecvAllData()) {
                                                    DaeguAdapter.addItem(DaeguItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    DaeguItem.daegu_numEf = xpp.getText();

                                                    if (DaeguItem.daegu_numEf.equals("0")) {
                                                        DaeguItem.daegu_numEf = "오늘 오후";
                                                    } else if (DaeguItem.daegu_numEf.equals("1")) {
                                                        DaeguItem.daegu_numEf = "내일 오전";
                                                    } else if (DaeguItem.daegu_numEf.equals("2")) {
                                                        DaeguItem.daegu_numEf = "내일 오후";
                                                    } else if (DaeguItem.daegu_numEf.equals("3")) {
                                                        DaeguItem.daegu_numEf = "모레 오전";
                                                    } else if (DaeguItem.daegu_numEf.equals("4")) {
                                                        DaeguItem.daegu_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", DaeguItem.daegu_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    DaeguItem.daegu_rnYn = xpp.getText();

                                                    if (DaeguItem.daegu_rnYn.equals("0")) {
                                                        DaeguItem.daegu_rnYn = "강수 없음";
                                                    } else if (DaeguItem.daegu_rnYn.equals("1")) {
                                                        DaeguItem.daegu_rnYn = "비";
                                                    } else if (DaeguItem.daegu_rnYn.equals("2")) {
                                                        DaeguItem.daegu_rnYn = "비/눈";
                                                    } else if (DaeguItem.daegu_rnYn.equals("3")) {
                                                        DaeguItem.daegu_rnYn = "눈";
                                                    } else if (DaeguItem.daegu_rnYn.equals("4")) {
                                                        DaeguItem.daegu_rnYn = "소나기";
                                                    }

                                                    Log.d("please", DaeguItem.daegu_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    DaeguItem.daegu_wf = xpp.getText();
                                                    Log.d("please", DaeguItem.daegu_wf);
                                                    break;

                                                case "rnSt":
                                                    DaeguItem.daegu_rnSt = xpp.getText();

                                                    if (DaeguItem.daegu_rnSt.equals("0")) {
                                                        DaeguItem.daegu_rnSt = "강수 확률 없음";
                                                    } else if (DaeguItem.daegu_rnSt.equals("1")) {
                                                        DaeguItem.daegu_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", DaeguItem.daegu_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    DaeguItem.daegu_ta = xpp.getText();
                                                    Log.d("please", DaeguItem.daegu_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //광주 부분 데이터 추출
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        GwangjuAdapter = new GwangjuAdapter();
        regwangju = findViewById(R.id.recycler_mini_gwangju); //리사이클러뷰를 적용할 레이아웃 설정, 광주 파트
        regwangju.setLayoutManager(layoutManager4);
        regwangju.setAdapter(DaeguAdapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext()); //volley로 데이터를 가져옴

        Spinner spinner4 = findViewById(R.id.spinner_miniweather_gwangju);
        gwangju = findViewById(R.id.text_mini_gwangju);

        ArrayAdapter<String> four_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_gwangju
        );

        spinner4.setAdapter(four_adapter);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gwangju.setText(weather_gwangju[position]);
                String sp = gwangju.getText().toString();
                local_key_gwangju = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장


                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_gwangju,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                gwangju_item GwangjuItem = new gwangju_item();
                                GwangjuAdapter.clearItems(); //아이템 정리

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
                                                GwangjuItem = new gwangju_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (GwangjuItem.checkRecvAllData()) {
                                                    GwangjuAdapter.addItem(GwangjuItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    GwangjuItem.gwangju_numEf = xpp.getText();

                                                    switch (GwangjuItem.gwangju_numEf) {
                                                        case "0":
                                                            GwangjuItem.gwangju_numEf = "오늘 오후";
                                                            break;
                                                        case "1":
                                                            GwangjuItem.gwangju_numEf = "내일 오전";
                                                            break;
                                                        case "2":
                                                            GwangjuItem.gwangju_numEf = "내일 오후";
                                                            break;
                                                        case "3":
                                                            GwangjuItem.gwangju_numEf = "모레 오전";
                                                            break;
                                                        case "4":
                                                            GwangjuItem.gwangju_numEf = "모레 오후";
                                                            break;
                                                    }
                                                    Log.d("please", GwangjuItem.gwangju_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    GwangjuItem.gwangju_rnYn = xpp.getText();

                                                    if (GwangjuItem.gwangju_rnYn.equals("0")) {
                                                        GwangjuItem.gwangju_rnYn = "강수 없음";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("1")) {
                                                        GwangjuItem.gwangju_rnYn = "비";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("2")) {
                                                        GwangjuItem.gwangju_rnYn = "비/눈";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("3")) {
                                                        GwangjuItem.gwangju_rnYn = "눈";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("4")) {
                                                        GwangjuItem.gwangju_rnYn = "소나기";
                                                    }

                                                    Log.d("please", GwangjuItem.gwangju_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    GwangjuItem.gwangju_wf = xpp.getText();
                                                    Log.d("please", GwangjuItem.gwangju_wf);
                                                    break;

                                                case "rnSt":
                                                    GwangjuItem.gwangju_rnSt = xpp.getText();

                                                    if (GwangjuItem.gwangju_rnSt.equals("0")) {
                                                        GwangjuItem.gwangju_rnSt = "강수 확률 없음";
                                                    } else if (GwangjuItem.gwangju_rnSt.equals("1")) {
                                                        GwangjuItem.gwangju_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", GwangjuItem.gwangju_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    GwangjuItem.gwangju_ta = xpp.getText();
                                                    Log.d("please", GwangjuItem.gwangju_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) { // 광주 지역 부분 기본값 출력하기

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11F20501",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                gwangju_item GwangjuItem = new gwangju_item();
                                GwangjuAdapter.clearItems(); //아이템 정리

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
                                                GwangjuItem = new gwangju_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (GwangjuItem.checkRecvAllData()) {
                                                    GwangjuAdapter.addItem(GwangjuItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    GwangjuItem.gwangju_numEf = xpp.getText();

                                                    switch (GwangjuItem.gwangju_numEf) {
                                                        case "0":
                                                            GwangjuItem.gwangju_numEf = "오늘 오후";
                                                            break;
                                                        case "1":
                                                            GwangjuItem.gwangju_numEf = "내일 오전";
                                                            break;
                                                        case "2":
                                                            GwangjuItem.gwangju_numEf = "내일 오후";
                                                            break;
                                                        case "3":
                                                            GwangjuItem.gwangju_numEf = "모레 오전";
                                                            break;
                                                        case "4":
                                                            GwangjuItem.gwangju_numEf = "모레 오후";
                                                            break;
                                                    }
                                                    Log.d("please", GwangjuItem.gwangju_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    GwangjuItem.gwangju_rnYn = xpp.getText();

                                                    if (GwangjuItem.gwangju_rnYn.equals("0")) {
                                                        GwangjuItem.gwangju_rnYn = "강수 없음";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("1")) {
                                                        GwangjuItem.gwangju_rnYn = "비";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("2")) {
                                                        GwangjuItem.gwangju_rnYn = "비/눈";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("3")) {
                                                        GwangjuItem.gwangju_rnYn = "눈";
                                                    } else if (GwangjuItem.gwangju_rnYn.equals("4")) {
                                                        GwangjuItem.gwangju_rnYn = "소나기";
                                                    }

                                                    Log.d("please", GwangjuItem.gwangju_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    GwangjuItem.gwangju_wf = xpp.getText();
                                                    Log.d("please", GwangjuItem.gwangju_wf);
                                                    break;

                                                case "rnSt":
                                                    GwangjuItem.gwangju_rnSt = xpp.getText();

                                                    if (GwangjuItem.gwangju_rnSt.equals("0")) {
                                                        GwangjuItem.gwangju_rnSt = "강수 확률 없음";
                                                    } else if (GwangjuItem.gwangju_rnSt.equals("1")) {
                                                        GwangjuItem.gwangju_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", GwangjuItem.gwangju_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    GwangjuItem.gwangju_ta = xpp.getText();
                                                    Log.d("please", GwangjuItem.gwangju_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //대전 부분 데이터 추출

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DaejeonAdapter = new DaejeonAdapter(); //어댑터 객체를 하나 새롭게 만들어주고
        redaejeon = findViewById(R.id.recycler_mini_daejeon); // 대상은 대구 날씨 파트 부분 레이어
        redaejeon.setLayoutManager(layoutManager5);
        redaejeon.setAdapter(DaejeonAdapter); //대전 어댑터를 적용시켜줌

        Spinner spinner5 = findViewById(R.id.spinner_miniweather_daejeon);
        daejeon = findViewById(R.id.text_mini_daejeon);

        ArrayAdapter<String> five_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_daejeon
        );

        spinner5.setAdapter(five_adapter);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daejeon.setText(weather_daejeon[position]);
                String sp = daejeon.getText().toString();
                local_key_daejeon = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_daejeon,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                daejeon_item DaejeonItem = new daejeon_item();
                                DaejeonAdapter.clearItems(); //아이템 정리

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
                                                DaejeonItem = new daejeon_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (DaejeonItem.checkRecvAllData()) {
                                                    DaejeonAdapter.addItem(DaejeonItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    DaejeonItem.daejeon_numEf = xpp.getText();

                                                    if (DaejeonItem.daejeon_numEf.equals("0")) {
                                                        DaejeonItem.daejeon_numEf = "오늘 오후";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("1")) {
                                                        DaejeonItem.daejeon_numEf = "내일 오전";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("2")) {
                                                        DaejeonItem.daejeon_numEf = "내일 오후";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("3")) {
                                                        DaejeonItem.daejeon_numEf = "모레 오전";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("4")) {
                                                        DaejeonItem.daejeon_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", DaejeonItem.daejeon_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    DaejeonItem.daejeon_rnYn = xpp.getText();

                                                    if (DaejeonItem.daejeon_rnYn.equals("0")) {
                                                        DaejeonItem.daejeon_rnYn = "강수 없음";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("1")) {
                                                        DaejeonItem.daejeon_rnYn = "비";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("2")) {
                                                        DaejeonItem.daejeon_rnYn = "비/눈";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("3")) {
                                                        DaejeonItem.daejeon_rnYn = "눈";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("4")) {
                                                        DaejeonItem.daejeon_rnYn = "소나기";
                                                    }

                                                    Log.d("please", DaejeonItem.daejeon_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    DaejeonItem.daejeon_wf = xpp.getText();
                                                    Log.d("please", DaejeonItem.daejeon_wf);
                                                    break;

                                                case "rnSt":
                                                    DaejeonItem.daejeon_rnSt = xpp.getText();

                                                    if (DaejeonItem.daejeon_rnSt.equals("0")) {
                                                        DaejeonItem.daejeon_rnSt = "강수 확률 없음";
                                                    } else if (DaejeonItem.daejeon_rnSt.equals("1")) {
                                                        DaejeonItem.daejeon_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", DaejeonItem.daejeon_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    DaejeonItem.daejeon_ta = xpp.getText();
                                                    Log.d("please", DaejeonItem.daejeon_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11C20401",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                daejeon_item DaejeonItem = new daejeon_item();
                                DaejeonAdapter.clearItems(); //아이템 정리

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
                                                DaejeonItem = new daejeon_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (DaejeonItem.checkRecvAllData()) {
                                                    DaejeonAdapter.addItem(DaejeonItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    DaejeonItem.daejeon_numEf = xpp.getText();

                                                    if (DaejeonItem.daejeon_numEf.equals("0")) {
                                                        DaejeonItem.daejeon_numEf = "오늘 오후";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("1")) {
                                                        DaejeonItem.daejeon_numEf = "내일 오전";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("2")) {
                                                        DaejeonItem.daejeon_numEf = "내일 오후";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("3")) {
                                                        DaejeonItem.daejeon_numEf = "모레 오전";
                                                    } else if (DaejeonItem.daejeon_numEf.equals("4")) {
                                                        DaejeonItem.daejeon_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", DaejeonItem.daejeon_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    DaejeonItem.daejeon_rnYn = xpp.getText();

                                                    if (DaejeonItem.daejeon_rnYn.equals("0")) {
                                                        DaejeonItem.daejeon_rnYn = "강수 없음";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("1")) {
                                                        DaejeonItem.daejeon_rnYn = "비";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("2")) {
                                                        DaejeonItem.daejeon_rnYn = "비/눈";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("3")) {
                                                        DaejeonItem.daejeon_rnYn = "눈";
                                                    } else if (DaejeonItem.daejeon_rnYn.equals("4")) {
                                                        DaejeonItem.daejeon_rnYn = "소나기";
                                                    }

                                                    Log.d("please", DaejeonItem.daejeon_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    DaejeonItem.daejeon_wf = xpp.getText();
                                                    Log.d("please", DaejeonItem.daejeon_wf);
                                                    break;

                                                case "rnSt":
                                                    DaejeonItem.daejeon_rnSt = xpp.getText();

                                                    if (DaejeonItem.daejeon_rnSt.equals("0")) {
                                                        DaejeonItem.daejeon_rnSt = "강수 확률 없음";
                                                    } else if (DaejeonItem.daejeon_rnSt.equals("1")) {
                                                        DaejeonItem.daejeon_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", DaejeonItem.daejeon_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    DaejeonItem.daejeon_ta = xpp.getText();
                                                    Log.d("please", DaejeonItem.daejeon_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //전라북도 부분 데이터 추출

        LinearLayoutManager layoutManager6 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        BukdoAdapter = new BukdoAdapter();
        rebukdo = findViewById(R.id.recycler_mini_bukbo);
        rebukdo.setLayoutManager(layoutManager6);
        rebukdo.setAdapter(BukdoAdapter);

        Spinner spinner6 = findViewById(R.id.spinner_miniweather_bukdo);
        bukdo = findViewById(R.id.text_mini_bukdo);

        ArrayAdapter<String> six_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_bukdo
        );

        spinner6.setAdapter(six_adapter);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bukdo.setText(weather_bukdo[position]);
                String sp = bukdo.getText().toString();
                local_key_bukdo = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_bukdo,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                bukdo_item BukdoItem = new bukdo_item();
                                BukdoAdapter.clearItems(); //아이템 정리

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
                                                BukdoItem = new bukdo_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (BukdoItem.checkRecvAllData()) {
                                                    BukdoAdapter.addItem(BukdoItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    BukdoItem.buk_numEf = xpp.getText();

                                                    if (BukdoItem.buk_numEf.equals("0")) {
                                                        BukdoItem.buk_numEf = "오늘 오후";
                                                    } else if (BukdoItem.buk_numEf.equals("1")) {
                                                        BukdoItem.buk_numEf = "내일 오전";
                                                    } else if (BukdoItem.buk_numEf.equals("2")) {
                                                        BukdoItem.buk_numEf = "내일 오후";
                                                    } else if (BukdoItem.buk_numEf.equals("3")) {
                                                        BukdoItem.buk_numEf = "모레 오전";
                                                    } else if (BukdoItem.buk_numEf.equals("4")) {
                                                        BukdoItem.buk_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", BukdoItem.buk_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    BukdoItem.buk_rnYn = xpp.getText();

                                                    if (BukdoItem.buk_rnYn.equals("0")) {
                                                        BukdoItem.buk_rnYn = "강수 없음";
                                                    } else if (BukdoItem.buk_rnYn.equals("1")) {
                                                        BukdoItem.buk_rnYn = "비";
                                                    } else if (BukdoItem.buk_rnYn.equals("2")) {
                                                        BukdoItem.buk_rnYn = "비/눈";
                                                    } else if (BukdoItem.buk_rnYn.equals("3")) {
                                                        BukdoItem.buk_rnYn = "눈";
                                                    } else if (BukdoItem.buk_rnYn.equals("4")) {
                                                        BukdoItem.buk_rnYn = "소나기";
                                                    }

                                                    Log.d("please", BukdoItem.buk_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    BukdoItem.buk_wf = xpp.getText();
                                                    Log.d("please", BukdoItem.buk_wf);
                                                    break;

                                                case "rnSt":
                                                    BukdoItem.buk_rnSt = xpp.getText();

                                                    if (BukdoItem.buk_rnSt.equals("0")) {
                                                        BukdoItem.buk_rnSt = "강수 확률 없음";
                                                    } else if (BukdoItem.buk_rnSt.equals("1")) {
                                                        BukdoItem.buk_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", BukdoItem.buk_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    BukdoItem.buk_ta = xpp.getText();
                                                    Log.d("please", BukdoItem.buk_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) {

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11F10201",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                bukdo_item BukdoItem = new bukdo_item();
                                BukdoAdapter.clearItems(); //아이템 정리

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
                                                BukdoItem = new bukdo_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (BukdoItem.checkRecvAllData()) {
                                                    BukdoAdapter.addItem(BukdoItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    BukdoItem.buk_numEf = xpp.getText();

                                                    if (BukdoItem.buk_numEf.equals("0")) {
                                                        BukdoItem.buk_numEf = "오늘 오후";
                                                    } else if (BukdoItem.buk_numEf.equals("1")) {
                                                        BukdoItem.buk_numEf = "내일 오전";
                                                    } else if (BukdoItem.buk_numEf.equals("2")) {
                                                        BukdoItem.buk_numEf = "내일 오후";
                                                    } else if (BukdoItem.buk_numEf.equals("3")) {
                                                        BukdoItem.buk_numEf = "모레 오전";
                                                    } else if (BukdoItem.buk_numEf.equals("4")) {
                                                        BukdoItem.buk_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", BukdoItem.buk_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    BukdoItem.buk_rnYn = xpp.getText();

                                                    if (BukdoItem.buk_rnYn.equals("0")) {
                                                        BukdoItem.buk_rnYn = "강수 없음";
                                                    } else if (BukdoItem.buk_rnYn.equals("1")) {
                                                        BukdoItem.buk_rnYn = "비";
                                                    } else if (BukdoItem.buk_rnYn.equals("2")) {
                                                        BukdoItem.buk_rnYn = "비/눈";
                                                    } else if (BukdoItem.buk_rnYn.equals("3")) {
                                                        BukdoItem.buk_rnYn = "눈";
                                                    } else if (BukdoItem.buk_rnYn.equals("4")) {
                                                        BukdoItem.buk_rnYn = "소나기";
                                                    }

                                                    Log.d("please", BukdoItem.buk_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    BukdoItem.buk_wf = xpp.getText();
                                                    Log.d("please", BukdoItem.buk_wf);
                                                    break;

                                                case "rnSt":
                                                    BukdoItem.buk_rnSt = xpp.getText();

                                                    if (BukdoItem.buk_rnSt.equals("0")) {
                                                        BukdoItem.buk_rnSt = "강수 확률 없음";
                                                    } else if (BukdoItem.buk_rnSt.equals("1")) {
                                                        BukdoItem.buk_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", BukdoItem.buk_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    BukdoItem.buk_ta = xpp.getText();
                                                    Log.d("please", BukdoItem.buk_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //충청북도 부분 데이터 추출

        LinearLayoutManager layoutManager7 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ChungcheonAdapter = new ChungcheonAdapter();
        rechungcheon = findViewById(R.id.recycler_mini_chungcheon);
        rechungcheon.setLayoutManager(layoutManager7);
        rechungcheon.setAdapter(ChungcheonAdapter);

        Spinner spinner7 = findViewById(R.id.spinner_miniweather_chungcheon);
        chungcheon = findViewById(R.id.text_mini_chungcheon);

        ArrayAdapter<String> seven_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_chungcheon
        );

        spinner7.setAdapter(seven_adapter);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chungcheon.setText(weather_chungcheon[position]);
                String sp = chungcheon.getText().toString();
                local_key_chungcheon = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_chungcheon,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                chungcheon_item chungcheonItem = new chungcheon_item();
                                ChungcheonAdapter.clearItems(); //아이템 정리

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
                                                chungcheonItem = new chungcheon_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (chungcheonItem.checkRecvAllData()) {
                                                    ChungcheonAdapter.addItem(chungcheonItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    chungcheonItem.ch_numEf = xpp.getText();

                                                    if (chungcheonItem.ch_numEf.equals("0")) {
                                                        chungcheonItem.ch_numEf = "오늘 오후";
                                                    } else if (chungcheonItem.ch_numEf.equals("1")) {
                                                        chungcheonItem.ch_numEf = "내일 오전";
                                                    } else if (chungcheonItem.ch_numEf.equals("2")) {
                                                        chungcheonItem.ch_numEf = "내일 오후";
                                                    } else if (chungcheonItem.ch_numEf.equals("3")) {
                                                        chungcheonItem.ch_numEf = "모레 오전";
                                                    } else if (chungcheonItem.ch_numEf.equals("4")) {
                                                        chungcheonItem.ch_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", chungcheonItem.ch_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    chungcheonItem.ch_rnYn = xpp.getText();

                                                    if (chungcheonItem.ch_rnYn.equals("0")) {
                                                        chungcheonItem.ch_rnYn = "강수 없음";
                                                    } else if (chungcheonItem.ch_rnYn.equals("1")) {
                                                        chungcheonItem.ch_rnYn = "비";
                                                    } else if (chungcheonItem.ch_rnYn.equals("2")) {
                                                        chungcheonItem.ch_rnYn = "비/눈";
                                                    } else if (chungcheonItem.ch_rnYn.equals("3")) {
                                                        chungcheonItem.ch_rnYn = "눈";
                                                    } else if (chungcheonItem.ch_rnYn.equals("4")) {
                                                        chungcheonItem.ch_rnYn = "소나기";
                                                    }

                                                    Log.d("please", chungcheonItem.ch_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    chungcheonItem.ch_wf = xpp.getText();
                                                    Log.d("please", chungcheonItem.ch_wf);
                                                    break;

                                                case "rnSt":
                                                    chungcheonItem.ch_rnSt = xpp.getText();

                                                    if (chungcheonItem.ch_rnSt.equals("0")) {
                                                        chungcheonItem.ch_rnSt = "강수 확률 없음";
                                                    } else if (chungcheonItem.ch_rnSt.equals("1")) {
                                                        chungcheonItem.ch_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", chungcheonItem.ch_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    chungcheonItem.ch_ta = xpp.getText();
                                                    Log.d("please", chungcheonItem.ch_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11C10301",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                chungcheon_item chungcheonItem = new chungcheon_item();
                                ChungcheonAdapter.clearItems(); //아이템 정리

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
                                                chungcheonItem = new chungcheon_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (chungcheonItem.checkRecvAllData()) {
                                                    ChungcheonAdapter.addItem(chungcheonItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    chungcheonItem.ch_numEf = xpp.getText();

                                                    if (chungcheonItem.ch_numEf.equals("0")) {
                                                        chungcheonItem.ch_numEf = "오늘 오후";
                                                    } else if (chungcheonItem.ch_numEf.equals("1")) {
                                                        chungcheonItem.ch_numEf = "내일 오전";
                                                    } else if (chungcheonItem.ch_numEf.equals("2")) {
                                                        chungcheonItem.ch_numEf = "내일 오후";
                                                    } else if (chungcheonItem.ch_numEf.equals("3")) {
                                                        chungcheonItem.ch_numEf = "모레 오전";
                                                    } else if (chungcheonItem.ch_numEf.equals("4")) {
                                                        chungcheonItem.ch_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", chungcheonItem.ch_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    chungcheonItem.ch_rnYn = xpp.getText();

                                                    if (chungcheonItem.ch_rnYn.equals("0")) {
                                                        chungcheonItem.ch_rnYn = "강수 없음";
                                                    } else if (chungcheonItem.ch_rnYn.equals("1")) {
                                                        chungcheonItem.ch_rnYn = "비";
                                                    } else if (chungcheonItem.ch_rnYn.equals("2")) {
                                                        chungcheonItem.ch_rnYn = "비/눈";
                                                    } else if (chungcheonItem.ch_rnYn.equals("3")) {
                                                        chungcheonItem.ch_rnYn = "눈";
                                                    } else if (chungcheonItem.ch_rnYn.equals("4")) {
                                                        chungcheonItem.ch_rnYn = "소나기";
                                                    }

                                                    Log.d("please", chungcheonItem.ch_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    chungcheonItem.ch_wf = xpp.getText();
                                                    Log.d("please", chungcheonItem.ch_wf);
                                                    break;

                                                case "rnSt":
                                                    chungcheonItem.ch_rnSt = xpp.getText();

                                                    if (chungcheonItem.ch_rnSt.equals("0")) {
                                                        chungcheonItem.ch_rnSt = "강수 확률 없음";
                                                    } else if (chungcheonItem.ch_rnSt.equals("1")) {
                                                        chungcheonItem.ch_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", chungcheonItem.ch_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    chungcheonItem.ch_ta = xpp.getText();
                                                    Log.d("please", chungcheonItem.ch_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //강원도 부분 데이터 추출

        LinearLayoutManager layoutManager8 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        GangwonAdapter = new GangwonAdapter();
        regangwon = findViewById(R.id.recycler_mini_gangwon);
        regangwon.setLayoutManager(layoutManager8);
        regangwon.setAdapter(GangwonAdapter);

        Spinner spinner8 = findViewById(R.id.spinner_miniweather_gangwon);
        gangwon = findViewById(R.id.text_mini_gangwon);

        ArrayAdapter<String> eight_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_gangwon
        );

        spinner8.setAdapter(eight_adapter);
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gangwon.setText(weather_gangwon[position]);
                String sp = gangwon.getText().toString();
                local_key_gangwon = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_gangwon,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                gangwon_item GangwonItem = new gangwon_item();
                                GangwonAdapter.clearItems(); //아이템 정리

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
                                                GangwonItem = new gangwon_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (GangwonItem.checkRecvAllData()) {
                                                    GangwonAdapter.addItem(GangwonItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    GangwonItem.gangwon_numEf = xpp.getText();

                                                    if (GangwonItem.gangwon_numEf.equals("0")) {
                                                        GangwonItem.gangwon_numEf = "오늘 오후";
                                                    } else if (GangwonItem.gangwon_numEf.equals("1")) {
                                                        GangwonItem.gangwon_numEf = "내일 오전";
                                                    } else if (GangwonItem.gangwon_numEf.equals("2")) {
                                                        GangwonItem.gangwon_numEf = "내일 오후";
                                                    } else if (GangwonItem.gangwon_numEf.equals("3")) {
                                                        GangwonItem.gangwon_numEf = "모레 오전";
                                                    } else if (GangwonItem.gangwon_numEf.equals("4")) {
                                                        GangwonItem.gangwon_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", GangwonItem.gangwon_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    GangwonItem.gangwon_rnYn = xpp.getText();

                                                    if (GangwonItem.gangwon_rnYn.equals("0")) {
                                                        GangwonItem.gangwon_rnYn = "강수 없음";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("1")) {
                                                        GangwonItem.gangwon_rnYn = "비";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("2")) {
                                                        GangwonItem.gangwon_rnYn = "비/눈";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("3")) {
                                                        GangwonItem.gangwon_rnYn = "눈";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("4")) {
                                                        GangwonItem.gangwon_rnYn = "소나기";
                                                    }

                                                    Log.d("please", GangwonItem.gangwon_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    GangwonItem.gangwon_wf = xpp.getText();
                                                    Log.d("please", GangwonItem.gangwon_wf);
                                                    break;

                                                case "rnSt":
                                                    GangwonItem.gangwon_rnSt = xpp.getText();

                                                    if (GangwonItem.gangwon_rnSt.equals("0")) {
                                                        GangwonItem.gangwon_rnSt = "강수 확률 없음";
                                                    } else if (GangwonItem.gangwon_rnSt.equals("1")) {
                                                        GangwonItem.gangwon_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", GangwonItem.gangwon_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    GangwonItem.gangwon_ta = xpp.getText();
                                                    Log.d("please", GangwonItem.gangwon_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11D10101",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                gangwon_item GangwonItem = new gangwon_item();
                                GangwonAdapter.clearItems(); //아이템 정리

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
                                                GangwonItem = new gangwon_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (GangwonItem.checkRecvAllData()) {
                                                    GangwonAdapter.addItem(GangwonItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    GangwonItem.gangwon_numEf = xpp.getText();

                                                    if (GangwonItem.gangwon_numEf.equals("0")) {
                                                        GangwonItem.gangwon_numEf = "오늘 오후";
                                                    } else if (GangwonItem.gangwon_numEf.equals("1")) {
                                                        GangwonItem.gangwon_numEf = "내일 오전";
                                                    } else if (GangwonItem.gangwon_numEf.equals("2")) {
                                                        GangwonItem.gangwon_numEf = "내일 오후";
                                                    } else if (GangwonItem.gangwon_numEf.equals("3")) {
                                                        GangwonItem.gangwon_numEf = "모레 오전";
                                                    } else if (GangwonItem.gangwon_numEf.equals("4")) {
                                                        GangwonItem.gangwon_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", GangwonItem.gangwon_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    GangwonItem.gangwon_rnYn = xpp.getText();

                                                    if (GangwonItem.gangwon_rnYn.equals("0")) {
                                                        GangwonItem.gangwon_rnYn = "강수 없음";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("1")) {
                                                        GangwonItem.gangwon_rnYn = "비";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("2")) {
                                                        GangwonItem.gangwon_rnYn = "비/눈";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("3")) {
                                                        GangwonItem.gangwon_rnYn = "눈";
                                                    } else if (GangwonItem.gangwon_rnYn.equals("4")) {
                                                        GangwonItem.gangwon_rnYn = "소나기";
                                                    }

                                                    Log.d("please", GangwonItem.gangwon_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    GangwonItem.gangwon_wf = xpp.getText();
                                                    Log.d("please", GangwonItem.gangwon_wf);
                                                    break;

                                                case "rnSt":
                                                    GangwonItem.gangwon_rnSt = xpp.getText();

                                                    if (GangwonItem.gangwon_rnSt.equals("0")) {
                                                        GangwonItem.gangwon_rnSt = "강수 확률 없음";
                                                    } else if (GangwonItem.gangwon_rnSt.equals("1")) {
                                                        GangwonItem.gangwon_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", GangwonItem.gangwon_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    GangwonItem.gangwon_ta = xpp.getText();
                                                    Log.d("please", GangwonItem.gangwon_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


        //제주도 부분 데이터 추출

        LinearLayoutManager layoutManager9 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        JejuAdapter = new JejuAdapter();
        rejeju = findViewById(R.id.recycler_mini_jeju);
        rejeju.setLayoutManager(layoutManager9);
        rejeju.setAdapter(JejuAdapter);

        Spinner spinner9 = findViewById(R.id.spinner_miniweather_jeju);
        jeju = findViewById(R.id.text_mini_jeju);

        ArrayAdapter<String> nine_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather_jeju
        );

        spinner9.setAdapter(nine_adapter);
        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jeju.setText(weather_jeju[position]);
                String sp = jeju.getText().toString();
                local_key_jeju = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=" + local_key_jeju,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                jeju_item JejuItem = new jeju_item();
                                JejuAdapter.clearItems(); //아이템 정리

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
                                                JejuItem = new jeju_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (JejuItem.checkRecvAllData()) {
                                                    JejuAdapter.addItem(JejuItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    JejuItem.jeju_numEf = xpp.getText();

                                                    if (JejuItem.jeju_numEf.equals("0")) {
                                                        JejuItem.jeju_numEf = "오늘 오후";
                                                    } else if (JejuItem.jeju_numEf.equals("1")) {
                                                        JejuItem.jeju_numEf = "내일 오전";
                                                    } else if (JejuItem.jeju_numEf.equals("2")) {
                                                        JejuItem.jeju_numEf = "내일 오후";
                                                    } else if (JejuItem.jeju_numEf.equals("3")) {
                                                        JejuItem.jeju_numEf = "모레 오전";
                                                    } else if (JejuItem.jeju_numEf.equals("4")) {
                                                        JejuItem.jeju_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", JejuItem.jeju_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    JejuItem.jeju_rnYn = xpp.getText();

                                                    if (JejuItem.jeju_rnYn.equals("0")) {
                                                        JejuItem.jeju_rnYn = "강수 없음";
                                                    } else if (JejuItem.jeju_rnYn.equals("1")) {
                                                        JejuItem.jeju_rnYn = "비";
                                                    } else if (JejuItem.jeju_rnYn.equals("2")) {
                                                        JejuItem.jeju_rnYn = "비/눈";
                                                    } else if (JejuItem.jeju_rnYn.equals("3")) {
                                                        JejuItem.jeju_rnYn = "눈";
                                                    } else if (JejuItem.jeju_rnYn.equals("4")) {
                                                        JejuItem.jeju_rnYn = "소나기";
                                                    }

                                                    Log.d("please", JejuItem.jeju_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    JejuItem.jeju_wf = xpp.getText();
                                                    Log.d("please", JejuItem.jeju_wf);
                                                    break;

                                                case "rnSt":
                                                    JejuItem.jeju_rnSt = xpp.getText();

                                                    if (JejuItem.jeju_rnSt.equals("0")) {
                                                        JejuItem.jeju_rnSt = "강수 확률 없음";
                                                    } else if (JejuItem.jeju_rnSt.equals("1")) {
                                                        JejuItem.jeju_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", JejuItem.jeju_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    JejuItem.jeju_ta = xpp.getText();
                                                    Log.d("please", JejuItem.jeju_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
            public void onNothingSelected(AdapterView<?> parent) { //제주도 아무것도 선택되지 않았을 때

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId=11G00201",
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                jeju_item JejuItem = new jeju_item();
                                JejuAdapter.clearItems(); //아이템 정리

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
                                                JejuItem = new jeju_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (JejuItem.checkRecvAllData()) {
                                                    JejuAdapter.addItem(JejuItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    JejuItem.jeju_numEf = xpp.getText();

                                                    if (JejuItem.jeju_numEf.equals("0")) {
                                                        JejuItem.jeju_numEf = "오늘 오후";
                                                    } else if (JejuItem.jeju_numEf.equals("1")) {
                                                        JejuItem.jeju_numEf = "내일 오전";
                                                    } else if (JejuItem.jeju_numEf.equals("2")) {
                                                        JejuItem.jeju_numEf = "내일 오후";
                                                    } else if (JejuItem.jeju_numEf.equals("3")) {
                                                        JejuItem.jeju_numEf = "모레 오전";
                                                    } else if (JejuItem.jeju_numEf.equals("4")) {
                                                        JejuItem.jeju_numEf = "모레 오후";
                                                    }
                                                    Log.d("please", JejuItem.jeju_numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;

                                                case "rnYn":
                                                    JejuItem.jeju_rnYn = xpp.getText();

                                                    if (JejuItem.jeju_rnYn.equals("0")) {
                                                        JejuItem.jeju_rnYn = "강수 없음";
                                                    } else if (JejuItem.jeju_rnYn.equals("1")) {
                                                        JejuItem.jeju_rnYn = "비";
                                                    } else if (JejuItem.jeju_rnYn.equals("2")) {
                                                        JejuItem.jeju_rnYn = "비/눈";
                                                    } else if (JejuItem.jeju_rnYn.equals("3")) {
                                                        JejuItem.jeju_rnYn = "눈";
                                                    } else if (JejuItem.jeju_rnYn.equals("4")) {
                                                        JejuItem.jeju_rnYn = "소나기";
                                                    }

                                                    Log.d("please", JejuItem.jeju_rnYn);
                                                    break;

                                                case "wf": //날씨
                                                    JejuItem.jeju_wf = xpp.getText();
                                                    Log.d("please", JejuItem.jeju_wf);
                                                    break;

                                                case "rnSt":
                                                    JejuItem.jeju_rnSt = xpp.getText();

                                                    if (JejuItem.jeju_rnSt.equals("0")) {
                                                        JejuItem.jeju_rnSt = "강수 확률 없음";
                                                    } else if (JejuItem.jeju_rnSt.equals("1")) {
                                                        JejuItem.jeju_rnSt = "강수 확률 있음";
                                                    }

                                                    Log.d("please", JejuItem.jeju_rnSt);
                                                    break;
                                                case "ta": //예상기온
                                                    JejuItem.jeju_ta = xpp.getText();
                                                    Log.d("please", JejuItem.jeju_ta);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                SeoulAdapter.notifyDataSetChanged();
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
        });


    } //oncreate
        @Override
        public boolean onOptionsItemSelected (MenuItem item){ //뒤로가기 했을 때
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
                default:
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

    }
