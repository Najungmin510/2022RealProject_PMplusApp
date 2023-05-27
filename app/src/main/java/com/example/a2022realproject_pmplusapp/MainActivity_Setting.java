package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

//설정 버튼 클릭했을 시 보이는 버튼들에 대한 처리 소스코드를 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_Setting extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton go_user_inform;
    Button camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);

        toolbar = (Toolbar)findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_black_24dp);
        getSupportActionBar().setTitle("환경 설정");

        go_user_inform = (ImageButton)findViewById(R.id.btn_go_setting_user_infrom);
        go_user_inform.setOnClickListener(ClickListener);


    }

    View.OnClickListener ClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_go_setting_user_infrom) { //버튼 클릭 시 세팅 함수 실행하도록 함
                check();
            }
        }
    };


    public void check(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Toast.makeText(this,"로그인 후 이용해주세요.",Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(),MainActivity_setting_user_inform_detail.class);
            startActivity(intent); //사용자 정보 설정으로 이동
        }
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