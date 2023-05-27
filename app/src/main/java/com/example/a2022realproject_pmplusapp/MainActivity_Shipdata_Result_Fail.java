package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.Objects;

//선박 입출항 현황 정보 불러오기 실패시 or 관련된 정보가 없을 때를 대비한것으로, 관련 소스코드를 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_Shipdata_Result_Fail extends AppCompatActivity {

    ImageButton  gomain;
    ImageButton  goreplay;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_shipdata_fail);

        toolbar = (Toolbar)findViewById(R.id.toolbar_shipdata_search_fail);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);
        getSupportActionBar().setTitle("선박 입출항 현황 조회결과");

        gomain = (ImageButton)findViewById(R.id.btn_go_pm_main_ship);

        gomain.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
            startActivity(intent);
        });

        goreplay = (ImageButton)findViewById(R.id.btn_replay_shipdata);

        goreplay.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_ShipData.class);
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