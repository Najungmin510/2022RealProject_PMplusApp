package com.example.a2022realproject_pmplusapp;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton; //버튼도 동일함
import android.widget.Toast; //토스트 메세지를 이용하여, 회원가입이 완료되었습니다 메세지를 출력

import java.util.Objects;

//import io.socket.client.Socket; //이하동일


/*
회원가입 소스코드 작성하시면 됩니다.
순서

사용자 입력 값 받아오기 -> 서버 연결 -> 만약 입력하지 않은채로 버튼을 누르면 토스트 메세지 출력 ->
전부 입력했다면 회원가입 버튼 클릭시 이 데이터를 서버로 전송 -> 전송 완료 시 회원가입 성공이라는 토스트 메세지 출력
->로그인창으로 화면 전환

 */


public class MainActivity_Membership extends AppCompatActivity {

    EditText userid; //아이디 입력받은 것 저장할 변수
    EditText userpass; //비밀번호 입력받은 것 저장할 변수
    EditText username; //사용자 이름 입력받은 것 저장할 변수
    EditText useremail; //사용자 이메일 입력받은 것 저장할 변수
    ImageButton MemberShipBtn; //버튼 값을 저장할 변수
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_membership);

        toolbar = (Toolbar)findViewById(R.id.toolbar_membership);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);
        getSupportActionBar().setTitle("회원가입");

        username = (EditText) findViewById(R.id.et_username);
        username.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)}); //이름은 최대 5글자
        username.getText().toString();


        userid = (EditText) findViewById(R.id.et_userID); //각 아이디(각 데이터가 가지는 고유값)를 찾아서 연결
        userid.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)}); //아이디 글자수 제한
        userid.getText().toString();


        userpass = (EditText) findViewById(R.id.et_userPW); //이하 동일
        userpass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)}); //비밀번호 글자수 제한
        userpass.getText().toString(); //글자를 가져옴


        useremail = (EditText) findViewById(R.id.et_userEM);
        useremail.getText().toString();

        MemberShipBtn = (ImageButton) findViewById(R.id.btn_go_membership); //회원가입 버튼


        useremail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                MemberShipBtn.setOnClickListener(v -> {
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                        Toast.makeText(getApplicationContext(), "이메일 형식에 맞춰주세요.", Toast.LENGTH_SHORT).show();

                    } else if (useremail.length() == 0){
                        Toast.makeText(getApplicationContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }

                    else if(username.length() == 0){
                        Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(userid.length() == 0){
                        Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(userpass.length() == 0){
                        Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(username.length() < 1){
                        Toast.makeText(getApplicationContext(), "이름을 두글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(userid.length() < 1){
                        Toast.makeText(getApplicationContext(), "아이디를 두글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(userpass.length() < 4){
                        Toast.makeText(getApplicationContext(), "비밀번호를 다섯글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(useremail.length() == 0 && userid.length() == 0 && userpass.length() == 0 && username.length() == 0){
                        Toast.makeText(getApplicationContext(), "정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }

                    else{

                        ServerConnect thread = new ServerConnect();
                        thread.start(userid, userpass, username, useremail); //작성한 자료들을 서버로 보내기 위해 SERVER CONNECT로 데이터 넘겨줌
                        //넘겨줌과 동시에 화면으로 전환

                        Intent intent = new Intent(getApplicationContext(), MainActivity_Success_membership.class);
                        startActivity(intent);

                        //서버로부터 ok 신호가 오면 그 때 회원가입이 완료되었습니다 토스트메세지 출력
                    }
                });

            }// afterTextChanged()..
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super. onOptionsItemSelected(item);
    }

}



