package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.widget.ImageButton;


//회원가입 성공 시 관련 소스코드를 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */
public class MainActivity_Success_membership extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton gomain;
    ImageButton goLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_success_membership);

        gomain = (ImageButton)findViewById(R.id.btn_go_main);
        goLogin =(ImageButton)findViewById(R.id.btn_go_login);

       gomain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_PM_Main.class);
           startActivity(intent);

        });

        goLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
            startActivity(intent);
        });



    }
}