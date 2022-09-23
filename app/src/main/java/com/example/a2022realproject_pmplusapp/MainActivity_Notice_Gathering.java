package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

//공지사항 모아보기 화면 관련 소스코드 작성하시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */


public class MainActivity_Notice_Gathering extends AppCompatActivity {

    RecyclerViewAdpter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notice_gathering);

        init();
        getData();

        toolbar = (Toolbar)findViewById(R.id.toolbar_notice_gathering);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("공지사항");
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerViewAdpter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        NoticeData data = new NoticeData("보령, 낚시어선 - 레저보트 간 접촉", "상황관리실", "2022-09-18", R.drawable.notice_detail_safe_with_text);
        adapter.addItem(data);
        data = new NoticeData("진도, 연안여객선 추진기 장애", "상황관리실","2022-09-16",R.drawable.notice_detail_safe_with_text );
        adapter.addItem(data);
        data = new NoticeData("군산, 외국적 화물선 국적 어선 간 접촉", "상황관리실","2022-09-16", R.drawable.notice_detail_safe_with_text);
        adapter.addItem(data);
        data = new NoticeData("보령, 모터보트 침수 ", "상황관리실","2022-09-16", R.drawable.notice_detail_safe_with_text);
        adapter.addItem(data);
        data = new NoticeData("군산, 연안여객선 기관 고장", "상황관리실","2022-09-10", R.drawable.notice_detail_safe_with_text);
        adapter.addItem(data);
        data = new NoticeData("강릉, 연안여객선 기관 고장", "상황관리실","2022-09-08", R.drawable.notice_detail_safe_with_text);
        adapter.addItem(data);
        data = new NoticeData("강릉, 연안여객선 추진기 장애발생","상황관리실","2022-09-08", R.drawable.notice_detail_safe_with_text);
        adapter.addItem(data);
    } //임의로 데이터를 넣어줘서 테스트

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super. onOptionsItemSelected(item);
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}