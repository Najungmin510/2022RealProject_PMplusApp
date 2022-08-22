package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Objects;


//선별 관제 현황 데이터를 서버로부터 가져오는 소스코드를 작성하시면 됩니다.
//Node.js 서버와 연결되는 소스코드를 포함하여 작성해주세요.


/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

작성하는 곳


코드 작성자 : 나정민
 */
public class MainActivity_MshipData extends AppCompatActivity {

    ImageButton go;
    EditText a;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mship_data);

        toolbar = (Toolbar)findViewById(R.id.toolbar_mshipdata); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before);
        getSupportActionBar().setTitle("선박 관제현황 조회");



    }
}