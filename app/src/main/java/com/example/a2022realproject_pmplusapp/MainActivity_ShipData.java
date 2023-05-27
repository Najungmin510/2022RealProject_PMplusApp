package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

//선박 입출항 현황 데이터를 서버로부터 가져오는 소스코드를 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_ShipData extends AppCompatActivity {


    Toolbar  toolbar;
    TextView shipcode;
    EditText shipday1;
    EditText shipday2;
    EditText codecall;

    ImageButton gofind;

    String[] items = {"000 해양수산부","010 해양수산부-해양수산부","020 부산", "021 감천", "022 부산신항","030 인천","031 평택",
            "032 인천연안", "034 용기포", "035 연평도", "040 서울", "050경인","090 불개항-불개항청","200 동해","201 삼척", "202 묵호",
            "203 속초","204 옥계","206 호산","300 대산","301 보령","302 태안","303 당진화력","304 보령출장소","500 군산", "501 장항", "502 고정",
            "503 상왕등도", "601 흑산도","602 가거항리", "610 목포", "611 완도","616 대불분실", "617 북항분실", "620 여수", "621 여천",
            "622 광양","626 월내","627 중흥", "629 중흥", "630 여수", "631 거문도", "700 포항", "701 포항신항", "702 영일만항", "703 후포",
            "704 울릉도동", "705 울릉사동", "810 마산", "811 삼천포", "812 옥포", "813 장승포", "814 진해", "815 통영",
            "816 고현", "817 하동", "818 국도", "820 울산", "821 온산", "822 마포", "900 제주", "901 서귀포", "902 추자",
            "903 화순", "951 기타항-북한", "998 기타-통계기타항", "BO1 수산물품질관리원", "F99 수산물품질관리원", "FMC 수산물품질관리원",
            "G13 수산물품질관리원","I02 수산물품질관리원","IO3 수산물품질관리원","J06 수산물품질관리원","J10 수산물품질관리원","J14 수산물품질관리원",
            "M07 수산물품질관리원","P05 수산물품질관리원","P12 수산물품질관리원","S04 수산물품질관리원","T11 수산물품질관리원","WO8 수산물품질관리원",
            "Y09 수산물품질관리원"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ship_data);


        toolbar = (Toolbar)findViewById(R.id.toolbar_main_ship_data); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before);
        getSupportActionBar().setTitle("선박 입출항 조회");


        Spinner spinner = findViewById(R.id.spinner);
        shipcode = findViewById(R.id.et_prtAgCd_ship);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item, items
        );

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shipcode.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //shipcode = (EditText)findViewById(R.id.et_prtAgCd_ship); //청코드, 필수

        shipday1 = (EditText)findViewById(R.id.et_sde_ship); //시작날짜

        shipday2 = (EditText)findViewById(R.id.et_ede_ship); //종료날짜, 필수

        codecall = (EditText)findViewById(R.id.et_clsgn_ship); //호출부호


        gofind = (ImageButton)findViewById(R.id.btn_data_search);

        gofind.setOnClickListener(v -> { //조회 버튼이 눌렸을 때 입력받은 값을 결과화면으로 보내고 화면 전환

            String data = shipcode.getText().toString();
            String Shipcode = data.substring(0,3);
            String Shipday1 = shipday1.getText().toString().trim();
            String Shipday2 = shipday2.getText().toString().trim();
            String Codecall = codecall.getText().toString().trim(); //호출부호


            Intent intent = new Intent(MainActivity_ShipData.this, MainActivity_Shipdata_Result.class);

            intent.putExtra("prt",Shipcode); // 020
            intent.putExtra("sde",Shipday1);
            intent.putExtra("ede",Shipday2);
            intent.putExtra("clsgnll",Codecall);

            startActivity(intent);
       });

        //데이터 출력하는 건 결과 화면에서..
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