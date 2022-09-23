package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.lang.ref.WeakReference;

//GICOMS에서 데이터 파싱하는 코드들 파싱할 데이터들 : 제목, 날짜, 작성자, 본문

public class MainActivity_notice_content_when_click extends AppCompatActivity {


    private String URL = "https://www.gicoms.go.kr/know/know_06_view.do";
    String msg;
    TextView title;
    TextView content_one;
    TextView content_two;
    TextView content_three; //본문의 text


    TextView one;
    TextView two;
    TextView three; //main화면에는 제목만 띄워둘 것


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notice_content_when_click);

        title = (TextView) findViewById(R.id.text_notice_contect_title);
        content_one = (TextView) findViewById(R.id.text_notice_contect_one);
        content_two = (TextView) findViewById(R.id.text_notice_contect_two);
        content_three = (TextView) findViewById(R.id.text_notice_contect_three);


        one = (TextView)findViewById(R.id.text_notice_one_main);
        two = (TextView)findViewById(R.id.text_notice_two_main);
        three = (TextView)findViewById(R.id.text_notice_three_main);



        new Thread() { //세부 공지사항 스레드를 하나 생성
            @Override
            public void run() {
                try{

                    Document doc = Jsoup.connect(URL).get(); //HTML코드를 전부 가져오고

                    Elements content_title = doc.select(".title");	// 제목인 TITLE클래스를 가지고옴
                    String title_str = content_title.text(); //태그는 제외하고 가져오기, 공지사항 주요 화면으로 보낼 것
                    String one_str = content_title.text(); //메인화면으로 보낼 것
                    //bundle.putString("ti",title_str);

                    Elements content_writer = doc.select(".writer"); //작성자
                    String writer_str = content_writer.text();

                    Elements content_date = doc.select(".date"); //날짜
                    String date_str = content_date.text();

                    Elements content = doc.select(".table_con"); // 본문
                    String content_str = content.text();

                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }.start();


    }

    Handler handelr = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg){
            Bundle bundle = msg.getData();	//new Thread에서 작업한 결과물 받기
            title.setText(bundle.getString("ti"));
            content_one.setText(bundle.getString(""));
        }
    };

}