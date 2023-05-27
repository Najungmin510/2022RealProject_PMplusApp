package com.example.a2022realproject_pmplusapp;

public class gwangju_item {
    public String gwangju_numEf;//발효번호 (발표시간), 날씨 상세정보에 적용시키기
    public String gwangju_ta; //예상기온
    public String gwangju_rnSt; //강수확률
    public String gwangju_wf; //날씨
    public String gwangju_rnYn; //강수형태


    public void clear(){
        gwangju_numEf = "";
        gwangju_ta = "";
        gwangju_rnSt = "";
        gwangju_wf = "";
        gwangju_rnYn = "";

    }

    boolean checkRecvAllData(){
        return gwangju_ta.length()  > 0
                && gwangju_rnSt.length()  > 0
                && gwangju_wf.length()    > 0
                && gwangju_rnYn.length()     > 0
                &&gwangju_numEf.length() >0;

    }
}
