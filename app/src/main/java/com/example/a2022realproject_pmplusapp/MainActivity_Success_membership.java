package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


//회원가입 성공 시 관련 소스코드를 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 :
 */
public class MainActivity_Success_membership extends AppCompatActivity {

    Button gomain;
    Button goLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_success_membership);

      //  gomain = (Button)findViewById(R.id.btn_goMain);
     //   goLogin =(Button)findViewById(R.id.btn_goLogin);

      // gomain.setOnClickListener(v -> {
           // Intent intent = new Intent(getApplicationContext(), MainActivity_PM_Main.class); //나중에 작성 할 PM 메인화면 넣으면 됨
           // startActivity(intent);

     //   }); //람다식(익명함수)으로 작성, 회원가입 버튼 클릭 시 성공 화면으로 이동

       // goLogin.setOnClickListener(v -> {
           // Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
           // startActivity(intent);
     //   });



    }
}