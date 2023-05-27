package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    private FirebaseAuth mAuth; //firebase 인스턴스 선언
    private FirebaseUser currentUser;
    private static final String TAG = "SignActivity";

    ImageButton goLogin; // 로그인 버튼
    EditText userID;
    EditText userPW;
    Button MemberConversion; //회원가입 버튼
    Button searchpw; //비밀번호 찾기 버튼
    Button searchid; //아이디 찾기 버튼
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        goLogin = (ImageButton) findViewById(R.id.btn_go_login_login);
        MemberConversion = (Button)findViewById(R.id.btn_go_member);
        searchid = (Button)findViewById(R.id.btn_findID);
        searchpw = (Button)findViewById(R.id.btn_findPW);

        userID = (EditText)findViewById(R.id.et_Login_ID);
        userPW = (EditText)findViewById(R.id.et_Login_PW);

        toolbar = (Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);
        getSupportActionBar().setTitle("");


        MemberConversion.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_Membership.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //이전 활동 지워주기
            startActivity(intent);

        }); //람다식(익명함수)으로 작성, 회원가입 버튼 클릭 시  화면 전환

        searchid.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity_Search_ID.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //이전 활동 지워주기
            startActivity(intent);

        });

        searchpw.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), MainActivity_Search_PW.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //이전 활동 지워주기
            startActivity(intent);

        });

        mAuth = FirebaseAuth.getInstance(); // firebase 인스턴스 초기화 해주고

        if(mAuth.getCurrentUser() == null){ //로그인이 안되어있는 상황일때만 버튼 활성화
            goLogin.setOnClickListener(onClickListener);
        } else{
            login("이미 로그인 된 상태입니다. 로그아웃 후 이용해주세요.");

        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_go_login_login) {
                LoginUp();
            }
        }
    };

    private void LoginUp(){ //회원가입 관리 함수

        String email = ((EditText)findViewById(R.id.et_Login_ID)).getText().toString();
        String password  = ((EditText)findViewById(R.id.et_Login_PW)).getText().toString();

        if( email.length() > 0 && password.length() > 0){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "로그인 성공");
                                FirebaseUser user = mAuth.getCurrentUser();
                                login("로그인이 완료되었습니다.");

                                Intent intent = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //이전 활동 지워주기
                                startActivity(intent);

                            } else{
                                if(task.getException() != null){
                                    login(task.getException().toString());
                                }
                            }
                        }
                    });
        } else {
            login("빈칸이 없도록 입력해주세요.");
        }


    }

    private void login(String msg){
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