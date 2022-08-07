package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.EditText;

import java.util.Objects;

//비밀번호 찾기 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 :
 */

public class MainActivity_Search_PW extends AppCompatActivity {

    Toolbar toolbar;
    EditText userId;
    EditText userEmail;
    ImageButton searchpw;
    String ID;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_pw);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);

        userId = (EditText)findViewById(R.id.et_Login_pw_name);
        ID = userId.getText().toString();

        userEmail = (EditText)findViewById(R.id.et_user_email_pw);
        Email = userEmail.getText().toString();

        /*

        입력받은 아이디, 이메일을 서버로 보내는 소스코드 작성

         */

        searchpw = (ImageButton)findViewById(R.id.btn_go_pw_result);
        searchpw.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_Search_PW_Fail.class);
            startActivity(intent);
        });



    }
}