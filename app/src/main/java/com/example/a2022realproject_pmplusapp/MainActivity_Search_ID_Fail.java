package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageButton;

import java.util.Objects;

//아이디 찾기 실패시 화면 소스코드 작성해주심 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 :
 */


public class MainActivity_Search_ID_Fail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ImageButton gomain;
        ImageButton retry;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_id_fail);


        gomain = (ImageButton)findViewById(R.id.btn_go_pm_main_ship);
        retry = (ImageButton)findViewById(R.id.btn_replay_id);


        gomain.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), MainActivity_PM_Main.class);
            startActivity(intent);
        });

        retry.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_Search_ID.class);
            startActivity(intent);
        });
    }
}