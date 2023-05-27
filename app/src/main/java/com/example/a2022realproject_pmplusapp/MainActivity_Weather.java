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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.io.InputStreamReader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



//날씨 정보 가져오는 소스코드 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_Weather extends AppCompatActivity {

    //관건은 메인화면과 어떻게 연결을 할 것인지?, 클래스 하나 따로 만들어서 함수로 호출하기?

    static RequestQueue requestQueue;
    RecyclerView weather_re;
    weather_adapter myadapter;

    Toolbar toolbar;
    ImageButton save_weather; //버튼 클릭 시 해당 값으로 고정됨 (spinner에서 아예 고정을 시켜버리면 될 듯, 다시 클릭해서 하지 않는 이상)

    TextView weather_local; //사용자의 선택
    TextView ocean_name; //00바다 날씨정보

    String local_key;//지역 코드를 저장할 변수
    String save; //사용자가 저장하기 버튼을 누르면 저장할 변수

    String key = "NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D"; //api key

    String[] weather = {"규슈(남해) 12F00200", "규슈(서해) 12F00100","남해동부앞바다 12B20100","남해동부바깥먼바다 12B20220","남해동부안쪽먼바다 12B20210",
            "남해서부앞바다 12B10100", "대화퇴 12D00000","동중국해 12E00000","동해남부앞바다 12C10100","동해남부남쪽바깥먼바다 12C10221","동해남부남쪽안쪽먼바다 12C10211",
    "동해남부북쪽바깥먼바다 12C10222","동해남부북쪽안쪽먼바다 12C10212","동해북부앞바다 12C30100","동해북부먼바다 12C30200","동해중부앞바다 12C20100","동해중부바깥먼바다 12C20220",
            "동해중부안쪽먼바다 12C20210","서해남부앞바다 12A30100","서해남부북쪽바깥먼바다 12A30221","서해남부남쪽안쪽먼바다 12A30212","서해북부앞바다 12A10100",
    "서해북부먼바다 12A10200","서해중부바깥먼바다 12A20220","서해중부안쪽먼바다 12A20210","서해중부앞바다 12A20100","연해주 12G00000","제주도남쪽바깥먼바다 12B10420",
    "제주도남동쪽안쪽먼바다 12B10412","제주도남서쪽안쪽먼바다 12B10411","제주도앞바다","인천·경기남부앞바다 12A20102","경기북부앞바다 12A20101","부산앞바다 12B20103","울산앞바다 12C10101",
    "경남중부남해앞바다 12B20102","경남서부남해앞바다 12B20101","거제시동부앞바다 12B20104","경북남부앞바다 12C10102","경북북부앞바다 12C10103","전남북부서해앞바다 22A30103",
            "전남중부서해앞바다 22A30104","전남남부서해앞바다 22A30105","전남서부남해앞바다 12B10101","전남동부남해앞바다 12B10102","전북북부앞바다 22A30101","전북남부앞바다 22A30102",
    "충남북부앞바다 12A20103","충남남부앞바다 12A20104","강원북부앞바다 12C20103","강원중부앞바다 12C20102","강원남부앞바다 12C20101","제주도북부앞바다 12B10302",
    "제주도남부앞바다 12B10303","제주도동부앞바다 12B10301","제주도서부앞바다 12B10304","남해서부서쪽먼바다 12B10201","남해서부동쪽먼바다 12B10202"}; //사용자에게 선택지를 제공할거임.빈칸을 기준으로 뒤에 변수을 가져와 url 연결


    String TAG = "weather LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);


        requestQueue = Volley.newRequestQueue(getApplicationContext()); //volley로 데이터를 가져옴



        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main_weather);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("해상 날씨 예보 조회");

        ocean_name = (TextView) findViewById(R.id.ocean_name_weather);
        local_key = "12B20100"; //기본값

        save_weather = (ImageButton) findViewById(R.id.btn_mainweather_continue);
        save_weather.setOnClickListener(v -> {


        }); //저장하기 버튼을 눌렀을 때



        Spinner spinner = findViewById(R.id.weather_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, weather //날씨 배열을 전부 넣어주기
        );

        weather_local = (TextView) findViewById(R.id.text_weather_local);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weather_local.setText(weather[position]); //사용자가 선택한 위치에 있는 아이템을 가져옴
                String sp = weather_local.getText().toString();
                local_key = sp.substring(sp.lastIndexOf(" ") + 1); //공백을 기준으로 문자 잘라서 저장


                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/1360000/VilageFcstMsgService/getSeaFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                                "&numOfRows=5&pageNo=1&regId="+local_key,
                        new Response.Listener<String>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse : " + response);

                                String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                                //ArrayList<Station> arrStation = new ArrayList<>();

                                weather_item weatherItem = new weather_item();
                                myadapter.clearItems(); //아이템 정리

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
                                                weatherItem = new weather_item();
                                            }

                                        } else if (eventType == XmlPullParser.END_TAG) {
                                            //item 태그 종료시 추가
                                            if (xpp.getName().equals("item")) {
                                                if (weatherItem.checkRecvAllData()) {
                                                    myadapter.addItem(weatherItem);
                                                }

                                            }
                                            curr_tag = ""; //태그초기화

                                        } else if (eventType == XmlPullParser.TEXT) {
                                            //태그 종류별로 기록
                                            switch (curr_tag) {
                                                case "numEf":
                                                    weatherItem.numEf = xpp.getText();

                                                    if(weatherItem.numEf.equals("0")){
                                                        weatherItem.numEf = "오늘 오후";
                                                    } else if(weatherItem.numEf.equals("1")){
                                                        weatherItem.numEf = "내일 오전";
                                                    } else if(weatherItem.numEf.equals("2")){
                                                        weatherItem.numEf = "내일 오후";
                                                    } else if(weatherItem.numEf.equals("3")){
                                                        weatherItem.numEf = "모레 오전";
                                                    } else if(weatherItem.numEf.equals("4")){
                                                        weatherItem.numEf = "모레 오후";
                                                    }

                                                    Log.d("please",weatherItem.numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                                    break;
                                                case "rnYn":
                                                    weatherItem.rnYn = xpp.getText();

                                                    if(weatherItem.rnYn.equals("0")){
                                                        weatherItem.rnYn = "강수 없음";
                                                    } else if(weatherItem.rnYn.equals("1")){
                                                        weatherItem.rnYn = "비";
                                                    } else if(weatherItem.rnYn.equals("2")){
                                                        weatherItem.rnYn = "비/눈";
                                                    } else if(weatherItem.rnYn.equals("3")){
                                                        weatherItem.rnYn = "눈";
                                                    } else if(weatherItem.rnYn.equals("4")){
                                                        weatherItem.rnYn = "소나기";
                                                    }

                                                    Log.d("please",weatherItem.rnYn);
                                                    break;
                                                case "wf":
                                                    weatherItem.wf = xpp.getText();
                                                    Log.d("please", weatherItem.wf);
                                                    break;
                                                case "wh1":
                                                    weatherItem.wh1 = xpp.getText();
                                                    Log.d("please",weatherItem.wh1);
                                                    break;
                                                case "ws1":
                                                    weatherItem.ws1 = xpp.getText();
                                                    Log.d("please",weatherItem.ws1);
                                                    break;

                                            }
                                        }
                                        eventType = xpp.next();

                                    } //while문 종료

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println("Count : " + arrStation.size());
                                myadapter.notifyDataSetChanged();
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

                Log.d("test",local_key);

                String[] arr = sp.split(" ");
                ocean_name.setText(arr[0]); //지역정보를 넣어주기
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //아무것도 선택하지 않았을 때

            }
        });

        //어댑터 연결
        myadapter = new weather_adapter();
        weather_re = findViewById(R.id.recycler_weather);
        weather_re.setLayoutManager(layoutManager);
        weather_re.setAdapter(myadapter);


        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://apis.data.go.kr/1360000/VilageFcstMsgService/getSeaFcst?serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                        "&numOfRows=5&pageNo=1&regId="+local_key,
                new Response.Listener<String>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse : " + response);

                        String curr_tag = ""; //<tag>의 이름을 저장할 변수수
                        //ArrayList<Station> arrStation = new ArrayList<>();

                        weather_item weatherItem = new weather_item();
                        myadapter.clearItems(); //아이템 정리

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
                                        weatherItem = new weather_item();
                                    }

                                } else if (eventType == XmlPullParser.END_TAG) {
                                    //item 태그 종료시 추가
                                    if (xpp.getName().equals("item")) {
                                        if (weatherItem.checkRecvAllData()) {
                                            myadapter.addItem(weatherItem);
                                        }

                                    }
                                    curr_tag = ""; //태그초기화

                                } else if (eventType == XmlPullParser.TEXT) {
                                    //태그 종류별로 기록
                                    switch (curr_tag) {
                                        case "numEf":
                                            weatherItem.numEf = xpp.getText();

                                            if(weatherItem.numEf.equals("0")){
                                                weatherItem.numEf = "오늘 오후";
                                            } else if(weatherItem.numEf.equals("1")){
                                                weatherItem.numEf = "내일 오전";
                                            } else if(weatherItem.numEf.equals("2")){
                                                weatherItem.numEf = "내일 오후";
                                            } else if(weatherItem.numEf.equals("3")){
                                                weatherItem.numEf = "모레 오전";
                                            } else if(weatherItem.numEf.equals("4")){
                                                weatherItem.numEf = "모레 오후";
                                            }

                                            Log.d("please",weatherItem.numEf); //로그를 통해 데이터가 잘 나오는지 확인
                                            break;
                                        case "rnYn":
                                            weatherItem.rnYn = xpp.getText();

                                            if(weatherItem.rnYn.equals("0")){
                                                weatherItem.rnYn = "강수 없음";
                                            } else if(weatherItem.rnYn.equals("1")){
                                                weatherItem.rnYn = "비";
                                            } else if(weatherItem.rnYn.equals("2")){
                                                weatherItem.rnYn = "비/눈";
                                            } else if(weatherItem.rnYn.equals("3")){
                                                weatherItem.rnYn = "눈";
                                            } else if(weatherItem.rnYn.equals("4")){
                                                weatherItem.rnYn = "소나기";
                                            }

                                            Log.d("please",weatherItem.rnYn);
                                            break;
                                        case "wf":
                                            weatherItem.wf = xpp.getText();
                                            Log.d("please", weatherItem.wf);
                                            break;
                                        case "wh1":
                                            weatherItem.wh1 = xpp.getText();
                                            Log.d("please",weatherItem.wh1);
                                            break;
                                        case "ws1":
                                            weatherItem.ws1 = xpp.getText();
                                            Log.d("please",weatherItem.ws1);
                                            break;

                                    }
                                }
                                eventType = xpp.next();

                            } //while문 종료

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //System.out.println("Count : " + arrStation.size());
                            myadapter.notifyDataSetChanged();
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

