package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.widget.ImageButton;

import java.util.Objects;

//로그인 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

작성하는 곳
사용자의 화면 전환(로그인 화면) -> 사용자의 입력 -> 사용자의 버튼 클릭 -> 입력이 안된 곳이 있는지 확인 (if, else사용)
-> 만약 전부 입력이 완료되었다면 서버로 데이터 전송 -> 서버로부터 오는 응답에 따라 로그인 성공, 실패 여부 결정
->로그인 성공 시 메인 화면으로 전환되로록 하고 실패시 토스트 메세지로 아이디, 비밀번호를 다시 확인해주세요 문구를 출력

코드 작성자 : 나정민
 */


public class MainActivity_Login extends AppCompatActivity {
    Button MemberConversion; //회원가입 버튼
    ImageButton goLogin; // 로그인 버튼
    Button searchpw; //비밀번호 찾기 버튼
    Button searchid; //아이디 찾기 버튼
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        MemberConversion = (Button)findViewById(R.id.btn_go_member);
        goLogin = (ImageButton) findViewById(R.id.btn_go_login_login);
        searchid = (Button)findViewById(R.id.btn_findID);
        searchpw = (Button)findViewById(R.id.btn_findPW);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);

        goLogin.setOnClickListener(v -> {
            //로그인 버튼 클릭 시 서버로 데이터를 전송하는 코드 작성
        });


        MemberConversion.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_Membership.class);
            startActivity(intent);

        }); //람다식(익명함수)으로 작성, 회원가입 버튼 클릭 시  화면 전환

        searchid.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity_Search_ID.class);
            startActivity(intent);

        });

        searchpw.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), MainActivity_Search_PW.class);
            startActivity(intent);

        });


    }
}