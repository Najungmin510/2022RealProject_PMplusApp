package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;
import java.io.InputStreamReader;


//날씨 정보 가져오는 소스코드 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_Weather extends AppCompatActivity {


    Toolbar toolbar;
    ImageButton save_weather;
    Button search_weather;
    EditText user_search_weather; //사용자의 검색, 요청 변수, DB와 연결
    TextView ocean_name; //00바다 날씨정보
    TextView degree; //온도
    TextView sky; //날씨하늘형태
    TextView wind_detail; //풍속
    TextView wave_height; //파고
    TextView rain; //강수형태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);

        toolbar = (Toolbar)findViewById(R.id.toolbar_main_weather);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("해상 날씨 예보 조회");

        user_search_weather = (EditText)findViewById(R.id.et_weather_user_inform_write);


        ocean_name = (TextView) findViewById(R.id.ocean_name_weather);
        degree = (TextView)findViewById(R.id.degree_ocean_weather);
        sky = (TextView)findViewById(R.id.sky_weather);
        wind_detail = (TextView)findViewById(R.id.wind_weather_detail);
        wave_height = (TextView)findViewById(R.id.wave_height_detail);
        rain = (TextView)findViewById(R.id.rain_weather_detail);



        search_weather=(Button)findViewById(R.id.btn_weather_search);

        search_weather.setOnClickListener(v->{

            String searchdata = user_search_weather.getText().toString(); //사용자가 입력한 값을 string타입으로 변환
            // String code = null; //이 변수에 DB에서 받아온 값을 저장할 것

                /*
                DB와 연결하여 사용자가 입력한 값와 일치하는 관서코드를 안드로이드로 가져오는 코드 작성
                작성 : 홍혜린
                 */

        });



        save_weather = (ImageButton)findViewById(R.id.btn_mainweather_continue);
        save_weather.setOnClickListener(v->{
            /* 버튼 클릭 시 db와 연결되어 그 정보를 저장하도록 함
            작성 : 홍혜린
            */

        });
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

