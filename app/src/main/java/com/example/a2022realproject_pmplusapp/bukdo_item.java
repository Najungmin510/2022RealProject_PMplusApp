package com.example.a2022realproject_pmplusapp;

public class bukdo_item {
    public String buk_numEf;//발효번호 (발표시간), 날씨 상세정보에 적용시키기
    public String buk_ta; //예상기온
    public String buk_rnSt; //강수확률
    public String buk_wf; //날씨
    public String buk_rnYn; //강수형태


    public void clear(){
        buk_numEf="";
        buk_ta = "";
        buk_rnSt = "";
        buk_wf = "";
        buk_rnYn = "";

    }

    boolean checkRecvAllData(){
        return buk_ta.length()  > 0
                && buk_rnSt.length()  > 0
                && buk_wf.length()    > 0
                && buk_rnYn.length()     > 0
                && buk_numEf.length() > 0;

    }
}
