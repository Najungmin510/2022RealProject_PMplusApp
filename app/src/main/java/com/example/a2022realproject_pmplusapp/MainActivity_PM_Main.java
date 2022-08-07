package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import java.util.Objects;

//메인화면 소스코드 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳
어플 접속 시 처음으로 보여지는 화면 (지정완료함) -> 맨 위에는 툴바 mini날씨정보(all 날씨정보와 연동되도록 할 것),
아래에는 공지사항과 챗봇으로 이동하기 버튼이 존재하며 이들을 클릭 시 각 해당화면으로 이동 -> 툴바에 있는 메뉴 버튼 클릭 시 네비게이션 드로우가 적용되도록 할 것

코드 작성자 : 나정민
 */

public class MainActivity_PM_Main extends AppCompatActivity {

    Toolbar toolbar; //툴바
    ImageButton miniweather; //날씨 정보
    ImageButton gochatbot; //챗봇으로 이동
    ImageButton gonotice; //공지사항으로 이동
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pm_main);

       miniweather = (ImageButton)findViewById(R.id.btn_mini_weather);
       miniweather.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_Notice_Gathering.class);
            startActivity(intent);
       });

      gochatbot =(ImageButton)findViewById(R.id.btn_go_chatbot);
       gochatbot.setOnClickListener(v ->{
         Intent intent = new Intent(getApplicationContext(),MainActivity_ChatBot.class);
         startActivity(intent);
       });

       gonotice = (ImageButton)findViewById(R.id.btn_notice);
       gonotice.setOnClickListener(v ->{
           Intent intent = new Intent(getApplicationContext(), MainActivity_Notice_Gathering.class);
           startActivity(intent);
       });



        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //디스플레이에 적용
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_white_24dp); //툴바에 이미지 선언

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); //activity_main_pm에서 선언한 레이아웃
        navigationView = (NavigationView) findViewById(R.id.navigation_view); //activity_main_pm 메뉴화면을 만든 레이아웃을 가져옴
        //드로어 레이아웃 안에 네비게이션 뷰를 적용한거임

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        int id = item.getItemId();

                        if (id == R.id.LoginandMembership) {
                            Intent membership = new Intent(getApplicationContext(), MainActivity_Membership.class);

                            startActivity(membership); //로그인/회원가입

                        } else if (id == R.id.DatagetShip) {
                            Intent shipdata = new Intent(getApplicationContext(), MainActivity_ShipData.class);

                            startActivity(shipdata); //선별 입출항 현황

                        } else if (id == R.id.DataControlShip){
                            Intent controlData = new Intent(getApplicationContext(), MainActivity_MshipData.class);

                            startActivity(controlData); //선별 관제 현황

                        } else if (id == R.id.WeatherData){
                            Intent weather = new Intent(getApplicationContext(), MainActivity_Weather.class);

                            startActivity(weather);

                        } else if (id == R.id.GoTochatbot){
                            Intent chatbot = new Intent(getApplicationContext(), MainActivity_ChatBot.class);

                            startActivity(chatbot);
                        } else if(id == R.id.informboard){
                            Intent inform = new Intent(getApplicationContext(), MainActivity_message_board.class);

                            startActivity(inform);
                        } else if(id == R.id.Setting){
                            Intent setting = new Intent(getApplicationContext(), MainActivity_Setting.class);

                            startActivity(setting);
                        }

                        return true;
                    }
                });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //메뉴 버튼을 눌렀을 때
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

        @Override
        public void onBackPressed() { //뒤로가기 했을 때
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

    }


