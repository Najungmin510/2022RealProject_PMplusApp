package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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

    TextView prtAgCd; //항구청코드
    TextView prtAgNm; //항만명
    TextView clsgn; //호출부호
    TextView vsslKndNm; //선박종류명
    TextView vsslNm; //선박한글명
    TextView vsslGrtg; //선박총토수
    TextView vsslNltyNm; //선박국적명
    TextView aprtfEtryptDt; //기항지입항일시
    TextView harborEntrpsNm; //항만업체명
    TextView cntrlNm; //관제구분명
    TextView cntrlOpertDt;//관제작업일시
    TextView fcltyCd; //시설코드
    TextView fcltySubCd; //시설서브코드
    TextView fcltyNm; //시설한글명

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mship_data_result);

        toolbar = (Toolbar)findViewById(R.id.toolbar_mshipdata_result);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("선별 관제 현황 조회결과");

        gomain = (ImageButton)findViewById(R.id.btn_mshipdata_go_main);
        gomain.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
            startActivity(intent);
        });

        go_recycle = (ImageButton)findViewById(R.id.btn_mshipdata_replay_go);
        go_recycle.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_MshipData.class);
            startActivity(intent);
        });





    }
}