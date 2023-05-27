package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

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
    private FirebaseAuth mAuth; //firebase 인스턴스 선언
    private FirebaseUser currentUser;
    private static final String TAG = "Search_pw_Activity";


    EditText userId;
    EditText userEmail;
    ImageButton searchpw;
    String ID;
    String Email;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_pw);

        toolbar = (Toolbar)findViewById(R.id.toolbar_search_pw);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);
        getSupportActionBar().setTitle("비밀번호 찾기");

        searchpw = (ImageButton)findViewById(R.id.btn_go_pw_result);
        searchpw.setOnClickListener(onClickListener); //비밀번호 찾기 버튼 클릭시..

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_go_pw_result) {
                Search_pw_Up();
            }
        }
    };

    private void Search_pw_Up(){ //비밀번호 찾기 함수

        String email = ((EditText)findViewById(R.id.et_Login_pw_email_search)).getText().toString();
        String id  = ((EditText)findViewById(R.id.et_Login_pw_name)).getText().toString();

        mAuth = FirebaseAuth.getInstance();

        if( email.length() > 0 && id.length() > 0){
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                pw_msg("이메일을 전송했습니다. 확인해주세요.");
                                Intent intent = new Intent(getApplicationContext(),MainActivity_Login.class);
                                startActivity(intent);

                            } else {
                                Intent intent = new Intent(getApplicationContext(),MainActivity_Search_PW_Fail.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    });
        } else {
            pw_msg("빈칸이 없도록 입력해주세요.");
        }

    }

    private void pw_msg(String msg){
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