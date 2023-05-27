package com.example.a2022realproject_pmplusapp;
import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton; //버튼도 동일함
import android.widget.Toast; //토스트 메세지를 이용하여, 회원가입이 완료되었습니다 메세지를 출력

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
회원가입 소스코드 작성하시면 됩니다.
순서

사용자 입력 값 받아오기 -> 서버 연결 -> 만약 입력하지 않은채로 버튼을 누르면 토스트 메세지 출력 ->
전부 입력했다면 회원가입 버튼 클릭시 이 데이터를 서버로 전송 -> 전송 완료 시 회원가입 성공이라는 토스트 메세지 출력
->로그인창으로 화면 전환

코드작성 : 나정민

 */


public class MainActivity_Membership extends AppCompatActivity {

    private FirebaseAuth mAuth; //firebase 인스턴스 선언
    private static final String TAG = "SignActivity";

    EditText userid; //아이디 입력받은 것 저장할 변수
    EditText userpass; //비밀번호 입력받은 것 저장할 변수
    EditText username; //사용자 이름 입력받은 것 저장할 변수
    EditText useremail; //사용자 이메일 입력받은 것 저장할 변수
    ImageButton MemberShipBtn; //버튼 값을 저장할 변수
    ServiceApi service;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_membership);

        mAuth = FirebaseAuth.getInstance(); // firebase 인스턴스 초기화 해주고

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
        MemberShipBtn.setOnClickListener(onClickListener);

    }

    @Override
    public void onStart(){ //로그인 활동 초기화 할 때 사용자가 현재 로그인 되어있는지 확인하는 메소드
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_go_membership:
                    signUp();
                    break;
            }
        }
    };

    private void signUp(){ //회원가입 관리 함수
        String name = ((EditText)findViewById(R.id.et_username)).getText().toString();
        String id = ((EditText)findViewById(R.id.et_userID)).getText().toString();
        String email = ((EditText)findViewById(R.id.et_userEM)).getText().toString();
        String password  = ((EditText)findViewById(R.id.et_userPW)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && name.length() > 0 && id.length() > 0 ){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "이메일을 성공적으로 생성했습니다.");
                                FirebaseUser user = mAuth.getCurrentUser();
                                signup_success("회원가입이 완료되었습니다.");

                                Intent intent = new Intent(getApplicationContext(),MainActivity_Success_membership.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            } else{
                                if(task.getException() != null){
                                    signup_success(task.getException().toString());
                                }
                            }
                        }
                    });
        } else {
            signup_success("빈칸이 없도록 입력해주세요.");
        }


    }

    private void signup_success(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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



