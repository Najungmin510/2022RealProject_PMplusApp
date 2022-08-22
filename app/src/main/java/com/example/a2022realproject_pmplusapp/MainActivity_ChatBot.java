package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


//챗봇 관련 소스코드 작성하시면 됩니다. 사용자와 대화를 나누는 화면입니다.
/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳

코드 작성자 : 나정민

 */

public class MainActivity_ChatBot extends AppCompatActivity {

    ImageButton send_to_chatbot; //전송 버튼
    TextView today; //날짜 표시
    EditText user_say; //사용자의 입력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat_bot);


        send_to_chatbot = (ImageButton)findViewById(R.id.btn_send_chatbot_message);
        today = (TextView)findViewById(R.id.text_chatbot_today);
        user_say = (EditText)findViewById(R.id.et_chatbot_usersay);

        //오늘날짜 출력
        String chatbot_today = today.getText().toString();
       // chatbot_today= howtoday_yyyymmdd();


        //챗봇 하단, 상단은 고정하고 가운데를 스크롤뷰를 이용해서 채팅화면 구성할 것
    }

    //오늘 날짜를 출력하는 코드
   // public static String howtoday_yyyymmdd(){
     //   Date currentTime = Calendar.getInstance().getTime();
      //  SimpleDateFormat format = new SimpleDateFormat(format_yyyMMdd, Locale.getDefault());
      //  return format.format(currentTime);





   // }
}