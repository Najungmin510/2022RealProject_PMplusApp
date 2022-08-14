package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//앱 실행 시 실행되는 로딩화면입니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

작성하는 곳
실행 시 화면으로 설정해둠, 앱 실행 시 2초간 화면이 보여지고 메인 화면으로 넘어감

코드 작성자 : 나정민
 */

public class MainActivity_loding extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loding);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
                startActivity(intent);

                finish();
            }
        },2000);
    }
}