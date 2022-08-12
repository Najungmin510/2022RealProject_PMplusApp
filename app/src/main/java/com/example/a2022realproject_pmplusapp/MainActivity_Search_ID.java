package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import java.util.Objects;
import java.util.regex.Pattern;


// 아이디 찾기 화면 코드 작성 하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳
사용자의 화면 전환(아이디 찾기 화면으로) -> 사용자의 입력 -> 사용자의 버튼 클릭 -> 입력이 안된 곳이 있는지 확인 (if, else사용)
-> 만약 전부 입력이 완료되었다면 서버로 데이터 전송 -> 서버로부터 오는 응답에 따라 각 관련된 화면으로 이동 ( 관련 아이디 출력 or 정보 없음 화면)

코드 작성자 : 나정민

 */

public class MainActivity_Search_ID extends AppCompatActivity {

    ImageButton searchId; //아이디 찾기 버튼
    EditText username; //사용자가 입력한 이름
    EditText useremail; //사용자가 입력한 이메일


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_id);


        username = (EditText)findViewById(R.id.et_Login_id_search);
        useremail = (EditText)findViewById(R.id.et_Logini_email_search);
        searchId = (ImageButton)findViewById(R.id.btn_go_id_result);
        Pattern pattern = Patterns.EMAIL_ADDRESS; //각 변수들 선언


        String inputname = username.getText().toString();
        String inputemail = useremail.getText().toString(); //string 형식으로 사용자가 입력한 데이터를 가져옴


        searchId.setOnClickListener(v->{ //버튼 클릭 시

            if(inputname.trim().equals("") || inputemail.trim().equals("")){
                Toast inform = Toast.makeText(this.getApplicationContext(),"빈칸 없이 모두 입력해주세요.",Toast.LENGTH_SHORT);
                inform.show();
                // 둘 다 빈칸일 경우

            } else if(inputname.trim().length() < 2 ){
                Toast inform = Toast.makeText(this.getApplicationContext(),"이름을 2글자 이상으로 입력해주세요.",Toast.LENGTH_SHORT);
                inform.show();
                //사용자 이름이 2글자 미만일 경우

            } else if(pattern.matcher(inputemail).matches()){
                Toast inform = Toast.makeText(this.getApplicationContext(),"이메일 형식에 맞춰 입력해주세요.",Toast.LENGTH_SHORT);
                inform.show();
                //이메일 형식이 아닐 경우
            }

            else { //모든 조건을 만족할 경우


            /*
            서버로 데이터를 보내는 소스코드 작성
            화면 전환은 중첩 if 문 사용해야 할 것 같음
            만약 서버로부터 받은 데이터 값이 true라면 서버로부터 전달받은 id를 string으로 변환 후 화면 전환 하여 이를 EditText에 출력
            false라면 실패 화면으로 넘어감
            */
                Intent intent = new Intent(getApplicationContext(),MainActivity_Search_ID_Success.class);
                startActivity(intent);
            }

        });

    }
}