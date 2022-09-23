package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.Objects;


//게시글 메뉴 클릭 시 보여지는 화면에 관련된 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */
public class MainActivity_message_board extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton go_write_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message_board);

        go_write_button = (ImageButton)findViewById(R.id.btn_go_write_messageboard);

        go_write_button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity_write_in_message_board.class);
            startActivity(intent);
        });


        toolbar = (Toolbar)findViewById(R.id.toolbar_messageboard);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("항만/항구 정보 공유 게시판");


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