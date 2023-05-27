package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


//선별 관제 현황 데이터를 서버로부터 가져오는 소스코드를 작성하시면 됩니다.
//Node.js 서버와 연결되는 소스코드를 포함하여 작성해주세요.


/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

작성하는 곳


코드 작성자 : 나정민
 */
public class MainActivity_MshipData extends AppCompatActivity {

    Toolbar  toolbar;
    TextView mshipcode;
    EditText mshipday1;
    EditText mshipday2;
    EditText mcodecall;

    String mShipcode;
    String mShipday1;
    String mShipday2;
    String mCodecall;

    ImageButton gofind;
    ImageButton gofind_date_start;
    ImageButton gofind_date_end;

    String[] items = {"000 해양수산부","010 해양수산부-해양수산부","020 부산", "021 감천", "022 부산신항","030 인천","031 평택",
    "032 인천연안", "034 용기포", "035 연평도", "040 서울", "050경인","090 불개항-불개항청","200 동해","201 삼척", "202 묵호",
    "203 속초","204 옥계","206 호산","300 대산","301 보령","302 태안","303 당진화력","304 보령출장소","500 군산", "501 장항", "502 고정",
    "503 상왕등도", "601 흑산도","602 가거항리", "610 목포", "611 완도","616 대불분실", "617 북항분실", "620 여수", "621 여천",
    "622 광양","626 월내","627 중흥", "629 중흥", "630 여수", "631 거문도", "700 포항", "701 포항신항", "702 영일만항", "703 후포",
            "704 울릉도동", "705 울릉사동", "810 마산", "811 삼천포", "812 옥포", "813 장승포", "814 진해", "815 통영",
            "816 고현", "817 하동", "818 국도", "820 울산", "821 온산", "822 마포", "900 제주", "901 서귀포", "902 추자",
            "903 화순", "951 기타항-북한", "998 기타-통계기타항", "BO1 수산물품질관리원", "F99 수산물품질관리원", "FMC 수산물품질관리원",
            "G13 수산물품질관리원","I02 수산물품질관리원","IO3 수산물품질관리원","J06 수산물품질관리원","J10 수산물품질관리원","J14 수산물품질관리원",
            "M07 수산물품질관리원","P05 수산물품질관리원","P12 수산물품질관리원","S04 수산물품질관리원","T11 수산물품질관리원","WO8 수산물품질관리원",
            "Y09 수산물품질관리원"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mship_data);

        toolbar = (Toolbar)findViewById(R.id.toolbar_mshipdata); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before);
        getSupportActionBar().setTitle("선박 관제현황 조회");

       // boolean Itsture = false; //클래스로부터 데이터가 잘 받아져왔는지 신호를 받기 위한 변수, 조회된데이터가 없다면 false 아니면 true

        //mshipcode = (EditText)findViewById(R.id.et_prtAgCd_mship); //청코드, 필수
        //mShipcode = mshipcode.getText().toString().trim();

        Spinner spinner = findViewById(R.id.mspinner);
        mshipcode = findViewById(R.id.et_prtAgCd_mship);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item, items
        );

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mshipcode.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gofind_date_start = (ImageButton)findViewById(R.id.btn_date_start_mship);
        gofind_date_end = (ImageButton)findViewById(R.id.btn_date_end_mship);

        mshipday1 = (EditText) findViewById(R.id.et_sde_mship); //시작날짜
        mShipday1 = mshipday1.getText().toString().trim();

        mshipday2 = (EditText) findViewById(R.id.et_ede_mship); //종료날짜, 필수
        mShipday2 = mshipday2.getText().toString().trim();

/*
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mshipday1.setText( year+"/" + (month+1) + "/" + dayOfMonth); //현재 출력 결과 : 2022/1/1 원하는 형태 : 20220101 문자열 바꿔주기
            }
        }, mYear, mMonth, mDay);

        DatePickerDialog datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mshipday2.setText( year+"/" +  (month+1) + "/" + dayOfMonth ); //일단 2022/01/01 형식으로 바꿔준다음 9보다 높으면 그대로 하면 되고, 낮으면 앞에 0을 붙여주면됨

            }
        }, mYear, mMonth, mDay);

        gofind_date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gofind_date_start.isClickable()) {
                    datePickerDialog1.show();
                }
            }
        });

        gofind_date_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gofind_date_end.isClickable()) {
                    datePickerDialog2.show();
                }
            }
        });
        이부분 뭘 어떻게 할수가없음 ㅠㅠ...
*/

        mcodecall = (EditText)findViewById(R.id.et_clsgn_mship); //호출부호
        mCodecall = mcodecall.getText().toString().trim();


        gofind = (ImageButton)findViewById(R.id.btn_data_search);
        gofind.setOnClickListener(v -> { //조회 버튼이 눌렸을 때

                Intent intent = new Intent(MainActivity_MshipData.this, MainActivity_MshipData_Result.class);

                String data = mshipcode.getText().toString();
                String r_mShipcode = data.substring(0,3);
/*
                String day_data1 = mshipday1.getText().toString();
                String year1 = day_data1.substring(0,4); //년도는 4글자니까 이대로 가면 되고
                String month1 = day_data1.substring(6,2);
                String day1 = day_data1.substring(9,2);
                mShipday1 = year1 + month1 + day1;

                String day_data2 = mshipday2.getText().toString();
                String year2 = day_data2.substring(0,4);
                String month2 = day_data2.substring(6,2);
                String day2 = day_data2.substring(9,2);
                mShipday2 = year2 + month2 + day2;

                mCodecall = mcodecall.getText().toString().trim();


*/

                String mShipday1 = mshipday1.getText().toString().trim();
                String mShipday2 = mshipday2.getText().toString().trim();
                String mCodecall = mcodecall.getText().toString().trim(); //호출부호

                intent.putExtra("prt",r_mShipcode); // 020
                intent.putExtra("sde",mShipday1);
                intent.putExtra("ede",mShipday2);
                intent.putExtra("clsgnll",mCodecall);

                startActivity(intent);

        });

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