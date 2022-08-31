package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageButton;
import android.widget.EditText;

//비밀번호 찾기 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳
사용자로부터 값을 입력받음 -> 값들이 모두 입력되었는지 검사 -> 입력이 안되었다면 toast 메세지를 통해 사용자에게 안내 ->
정보가 전부 입력되었다면 서버로 데이터 전송

코드 작성자 : 나정민,
 */

public class MainActivity_Search_PW extends AppCompatActivity {


    EditText userId;
    EditText userEmail;
    ImageButton searchpw;
    String ID;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_pw);


        userId = (EditText)findViewById(R.id.et_Login_pw_name);
        ID = userId.getText().toString();

        userEmail = (EditText)findViewById(R.id.et_Login_pw_email_search);
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