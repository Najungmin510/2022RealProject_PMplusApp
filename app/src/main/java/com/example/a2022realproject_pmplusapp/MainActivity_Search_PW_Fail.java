package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

//비밀번호 찾기 실패시 관련 소스코드 작성해주시면 됩니다.


/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 :
 */

public class MainActivity_Search_PW_Fail extends AppCompatActivity {

    ImageButton gomain;
    ImageButton retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_pw_fail);


        gomain = (ImageButton)findViewById(R.id.btn_go_pm_main_ship);
        retry = (ImageButton)findViewById(R.id.btn_go_pwsearch_main);

        gomain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_PM_Main.class);
            startActivity(intent);
        });

        retry.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_Search_PW.class);
            startActivity(intent);
        });
    }
}