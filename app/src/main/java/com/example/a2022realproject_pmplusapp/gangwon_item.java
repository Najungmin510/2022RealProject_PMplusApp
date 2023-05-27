package com.example.a2022realproject_pmplusapp;

public class gangwon_item {
    public String gangwon_numEf;
    public String gangwon_ta; //예상기온
    public String gangwon_rnSt; //강수확률
    public String gangwon_wf; //날씨
    public String gangwon_rnYn; //강수형태


    public void clear(){
        gangwon_numEf = "";
        gangwon_ta = "";
        gangwon_rnSt = "";
        gangwon_wf = "";
        gangwon_rnYn = "";

    }

    boolean checkRecvAllData(){
        return gangwon_ta.length()  > 0
                && gangwon_rnSt.length()  > 0
                && gangwon_wf.length()    > 0
                && gangwon_rnYn.length()     > 0
                && gangwon_numEf.length() > 0;


    }
}
