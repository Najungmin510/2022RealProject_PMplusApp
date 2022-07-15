package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity_PM_Main extends AppCompatActivity {

    Toolbar toolbar; //툴바
    ImageButton notice;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pm_main);

        //notice = (ImageButton)findViewById(R.id.btn_tonotice);
       // notice.setOnClickListener(v -> {
         //   Intent intent = new Intent(getApplicationContext(),MainActivity_Notice.class);
           // startActivity(intent);
      //  });



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


