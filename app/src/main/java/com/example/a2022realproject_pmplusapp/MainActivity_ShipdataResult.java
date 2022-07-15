package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import io.socket.client.IO;
import io.socket.client.Socket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.net.URISyntaxException;

public class MainActivity_ShipdataResult extends AppCompatActivity {

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://192.168.219.150:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shipdata_result);

        Button button = (Button)findViewById(R.id.btn_return);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_ShipData.class);

            startActivity(intent);

        }); //재조회 버튼 클릭 시 화면 전환

    }
}