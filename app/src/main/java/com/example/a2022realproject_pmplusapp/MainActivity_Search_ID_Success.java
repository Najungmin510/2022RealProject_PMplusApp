package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

//아이디 찾기 성공 시, 관련 데이터를 출력하도록 코드를 구성할 것

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_Search_ID_Success extends AppCompatActivity {

    TextView resultText; //id 결과값
    ImageButton goLoginMain;
    ImageButton goMainPM;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_id_success);

        resultText = (TextView)findViewById(R.id.et_result_text_ID); //텍스트값 가져오기
        goLoginMain = (ImageButton)findViewById(R.id.btn_go_login_searchid_success);
        goMainPM = (ImageButton)findViewById(R.id.btn_go_pmmain_search_id_success);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //디스플레이에 적용
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_black_24dp); //툴바에 이미지 선언

        goLoginMain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity_Login.class);
            startActivity(intent);
        });

        goMainPM.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
            startActivity(intent);
        });

        /*

        서버와 연결해서 id를 가져오는 소스코드 작성


         */

        resultText.setText("서버와 연결해서 가져온 데이터를 저장");
        String UserIdData = resultText.getText().toString();

    }
}