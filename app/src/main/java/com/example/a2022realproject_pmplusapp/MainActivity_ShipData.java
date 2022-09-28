package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

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
    EditText shipcode;
    EditText shipday1;
    EditText shipday2;
    EditText codecall;

    ImageButton gofind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ship_data);

        boolean Itsture = false; //클래스로부터 데이터가 잘 받아져왔는지 신호를 받기 위한 변수, 조회된데이터가 없다면 false 아니면 true



        toolbar = (Toolbar)findViewById(R.id.toolbar_main_ship_data); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before);
        getSupportActionBar().setTitle("선박 입출항 조회");




        shipcode = (EditText)findViewById(R.id.et_prtAgCd_ship); //청코드, 필수
        String Shipcode = shipcode.getText().toString().trim(); //청코드

        shipday1 = (EditText)findViewById(R.id.et_sde_ship); //시작날짜
        String Shipday1 = shipday1.getText().toString().trim();

        shipday2 = (EditText)findViewById(R.id.et_ede_ship); //종료날짜, 필수
        String Shipday2 = shipday2.getText().toString().trim();

        codecall = (EditText)findViewById(R.id.et_clsgn_ship); //호출부호
        String Codecall = codecall.getText().toString().trim(); //호출부호






        gofind = (ImageButton)findViewById(R.id.btn_data_search);

        gofind.setOnClickListener(v -> { //조회 버튼이 눌렸을 때 입력받은 값을 결과화면으로 보내고 화면 전환

            Intent intent = new Intent(MainActivity_ShipData.this, MainActivity_Shipdata_Result.class);

            intent.putExtra("청코드",Shipcode);
            intent.putExtra("입항날짜",Shipday1);
            intent.putExtra("출항날짜",Shipday2);
            intent.putExtra("호출부호",Codecall);

            shipdata_to_xml_re ship = new shipdata_to_xml_re( Shipcode, Shipday1, Shipday2, Codecall );

                   Intent parsing_start = new Intent(getApplicationContext(),MainActivity_Shipdata_Result.class);
                   startActivity(parsing_start);
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