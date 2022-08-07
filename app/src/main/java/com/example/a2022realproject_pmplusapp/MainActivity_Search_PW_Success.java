package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;

//비밀번호 찾기 성공시 관련 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 :
 */

public class MainActivity_Search_PW_Success extends AppCompatActivity {

    ImageButton gologin;
    ImageButton gomain;
    TextView resultPw;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_pw_success);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        gologin = (ImageButton)findViewById(R.id.btn_go_login_searchpw_success);
        gomain = (ImageButton)findViewById(R.id.btn_go_main_searchpw_success);
        resultPw = (TextView)findViewById(R.id.text_user_pw);
        String userPw;

        gologin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity_Login.class);
            startActivity(intent);
        });

        gomain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_PM_Main.class);
            startActivity(intent);
        });

         /*

        서버와 연결해서 pw를 가져오는 소스코드 작성


         */

        resultPw.setText("서버와 연결해서 가져온 데이터를 저장");
        String UserIdData = resultPw.getText().toString();



    }
}