package com.example.a2022realproject_pmplusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageButton;




public class MainActivity_Shipdata_Result extends AppCompatActivity {

    ImageButton replay_shipdata;
    ImageButton no_replay_main;

    //detail
    TextView etryptDt_ship, tkoffDt_ship, ibobprtNm_ship, laidupFcltyNm_ship, ldadngFrghtCICd, grtg_ship, satmntEntrpsNm_ship;

    //item
    TextView prtAgCd_ship, prtAgNm_ship, clsgn_ship, vsslNm_ship, vsslNltyNm_ship, vsslKndNm_ship, etryptPurpsNm_ship, dstnPrtNm_ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shipdata_result);

        etryptDt_ship = (TextView)findViewById(R.id.ship_result_etryptDt_data);
        tkoffDt_ship = (TextView)findViewById(R.id.ship_result_tkoffDt_data);
        ibobprtNm_ship = (TextView)findViewById(R.id.ship_result_ibobprtNm_data);
        laidupFcltyNm_ship = (TextView)findViewById(R.id.ship_result_laidupFcltyNm_data);
        ldadngFrghtCICd = (TextView)findViewById(R.id.ship_result_ldadngFrghtCICd_data);
        grtg_ship = (TextView)findViewById(R.id.ship_result_grtg_data);
        satmntEntrpsNm_ship = (TextView)findViewById(R.id.ship_result_satmntEntrpsNm_data);

        prtAgCd_ship = (TextView)findViewById(R.id.et_prtAgCd_ship);
        prtAgNm_ship = (TextView)findViewById(R.id.ship_result_prtAgNm_data);
        clsgn_ship = (TextView)findViewById(R.id.ship_result_clsgn_data);
        vsslNm_ship = (TextView)findViewById(R.id.ship_result_vsslNm_data);
        vsslNltyNm_ship = (TextView)findViewById(R.id.ship_result_vsslNltyNm_data);
        vsslKndNm_ship = (TextView)findViewById(R.id.ship_result_vsslKndNm_data);
        etryptPurpsNm_ship = (TextView)findViewById(R.id.ship_result_etryptPurpsNm_data);
        dstnPrtNm_ship = (TextView)findViewById(R.id.ship_result_dstnPrtNm_data);


        shipdata_to_xml_re result = new shipdata_to_xml_re();
        String[] your_result = new String[result.total().length]; //받아온 배열의 길이만큼 저장해줄건데

        for(int i = 0; i < result.total().length; i++){ //배열에 값을 넣어줌

            your_result[i] = result.total()[i];

        } //받아온 배열값을 순서대로 textview에 넣어주면 끝

        etryptDt_ship.setText(your_result[8]); //text값 변경
        tkoffDt_ship.setText(your_result[14]);
        ibobprtNm_ship.setText(your_result[9]);



        replay_shipdata = (ImageButton)findViewById(R.id.btn_shipdata_replay_go);
        no_replay_main = (ImageButton)findViewById(R.id.btn_shipdata_go_main);

        replay_shipdata.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_ShipData.class);
            startActivity(intent);
        });

        no_replay_main.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),MainActivity_PM_Main.class);
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