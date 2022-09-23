package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


//챗봇 관련 소스코드 작성하시면 됩니다. 사용자와 대화를 나누는 화면입니다.
/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳
사용자의 입장 -> 챗봇이 안내멘트와 함께 선택지 출력 (버튼형식) -> 사용자가 선택한 버튼 값을 Dialogflow로 전송 ->

코드 작성자 : 나정민

 */

public class MainActivity_ChatBot extends AppCompatActivity {

    private ArrayList<Chatbot_DataItem> ChatdataList;

    ImageButton send_to_chatbot; //전송 버튼
    TextView today; //날짜 표시
    EditText user_say; //사용자의 입력
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat_bot);


        toolbar = (Toolbar)findViewById(R.id.toolbar_chatbot_main);

        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("PM+ 매니저");


        send_to_chatbot = (ImageButton)findViewById(R.id.btn_send_chatbot_message);
        user_say = (EditText)findViewById(R.id.et_chatbot_usersay);

        initData();



        //챗봇 하단, 상단은 고정하고 가운데를 스크롤뷰를 이용해서 채팅화면 구성할 것
    }

    private void initData(){
        ChatdataList = new ArrayList<>();
        ChatdataList.add(new Chatbot_DataItem("안녕하세요. PM+ 매니저입니다.",null,Chatbot_Content_Code.ViewType.LEFT_Content));
        ChatdataList.add(new Chatbot_DataItem("원하시는 서비스를 선택해주세요.",null,Chatbot_Content_Code.ViewType.LEFT_Content));
        ChatdataList.add(new Chatbot_DataItem("사용자 입력값 출력",null,Chatbot_Content_Code.ViewType.RIGHT_Content));
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