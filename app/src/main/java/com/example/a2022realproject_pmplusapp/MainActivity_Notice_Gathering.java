package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//공지사항 모아보기 화면 관련 소스코드 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */


public class MainActivity_Notice_Gathering extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<NoticeData> list = new ArrayList();

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notice_gathering);


        toolbar = (Toolbar)findViewById(R.id.toolbar_notice_gathering);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("공지사항");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //AsyncTask 작동시킴(파싱)
        new Description().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class Description extends AsyncTask<Void, Void, Void> {

        //진행바표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //진행다일로그 시작
            progressDialog = new ProgressDialog(MainActivity_Notice_Gathering.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("데이터를 가져오는 중입니다.");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            int count = 4637; //2022-01-01부터 파싱을 할거임
           try {
                Document doc = Jsoup.connect("https://www.gicoms.go.kr/know/know_06.do").get();
                Elements mElementDataSize = doc.select("ul[class=list_timeline_style]").select("li"); //기사 부분을 지정해주고, li부분을 특정적으로 파싱할거임
                int mElementSize = mElementDataSize.size(); //목록이 몇개인지 알아내서 반복(출력한다는거임)

                for(Element elem : mElementDataSize){
                    //<li> 에서 다시 원하는 데이터를 추출해 낸다.
                    String my_title = elem.select("li [onclick = javascript:fn_egov_inquire_notice("+count+",''] a").text(); //list 세부사항 중 제목만 가져오기
                    String my_date = elem.select("li div[class=datetime] span").first().text(); //span 태그가 두개여서 그 중 첫번째가 작성자라 문자로 그걸 가져오고
                    String my_writer = elem.select("li div[class=datetime] span").next().first().text(); //나머지 span태그의 내용 중 하나를 마저 가져옴
                    String my_content = elem.select("li div[class=list_detail] p").next().first().attr("href"); //상세보기 링크를 가져오기

                    //ArrayList에 계속 추가한다.
                    list.add(new NoticeData(my_title, my_writer, my_date, my_content));
                }

                //추출한 전체 <li> 출력해 보자.
                Log.d("debug :", "List " + mElementDataSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //ArraList를 인자로 해서 어답터와 연결한다.
            RecyclerViewAdpter myAdapter = new RecyclerViewAdpter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);

            progressDialog.dismiss();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super. onOptionsItemSelected(item);
    }


}