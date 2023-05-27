package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.Objects;


// 선별 관제 현황 정보 가져오기 실패 시 화면에 대한 소스코드를 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳
서버로부터 받은 결과값이 fail일 경우 해당화면으로 전환됨 -> 툴바, 메인화면으로(pm_main), 재조회 버튼이 존재
-> 각 버튼 클릭 시 해당 화면으로 이동

코드 작성자 : 나정민
 */

public class MainActivity_MshipData_Result_Fail extends AppCompatActivity {

    ImageButton replay;
    ImageButton gomain;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mship_data_result_fail);


        gomain = (ImageButton)findViewById(R.id.btn_go_pm_main_mship);
        replay = (ImageButton)findViewById(R.id.btn_replay_mshipdata);

        toolbar = (Toolbar)findViewById(R.id.toolbar_mshipdata_search_fail);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);
        getSupportActionBar().setTitle("선박 관제현황 조회결과");


        gomain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_PM_Main.class);
            startActivity(intent);
        });

        replay.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_MshipData.class);
            startActivity(intent);
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