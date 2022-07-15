package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;


import io.socket.client.IO;
import io.socket.client.Socket;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

public class MainActivity_ShipData extends AppCompatActivity {

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://192.168.219.150:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    Toolbar  toolbar;
    EditText shipcode;
    EditText shipday1;
    EditText shipday2;
    EditText codecall;

    Button gofind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ship_data);

        toolbar = (Toolbar)findViewById(R.id.toolbar); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //디스플레이에 적용
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before); //툴바에 이미지 선언

        shipcode = (EditText)findViewById(R.id.Text_shipcode); //청코드, 필수
        String Shipcode = shipcode.getText().toString().trim();

        shipday1 = (EditText)findViewById(R.id.Text_shipday1); //시작날짜, 필수
        String Shipday1 = shipday1.getText().toString().trim();

        shipday2 = (EditText)findViewById(R.id.Text_shipday2); //종료날짜, 필수
        String Shipday2 = shipday2.getText().toString().trim();

        codecall = (EditText)findViewById(R.id.Text_call); //호출부호
        String Codecall = codecall.getText().toString().trim();



        gofind = (Button)findViewById(R.id.btn_find);




        gofind.setOnClickListener(v -> { //조회 버튼이 눌렸을 때

            if(TextUtils.isEmpty(Shipcode) || TextUtils.isEmpty(Shipday1) || TextUtils.isEmpty(Shipday2)){

                Toast.makeText(getApplicationContext(),"필수 부분을 모두 입력해주세요.",Toast.LENGTH_SHORT).show();

            } else{
                mSocket.connect();

                JSONObject data = new JSONObject();

                    try {

                        data.put("prtAgCd",Shipcode);
                        data.put("sde",Shipday1);
                        data.put("ede",Shipday2);
                        data.put("clsgn", Codecall);
                        mSocket.emit("PutShipData",data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

            Intent intent = new Intent(getApplicationContext(),MainActivity_ShipdataResult.class);
            startActivity(intent);
        });

    }
}