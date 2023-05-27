package com.example.a2022realproject_pmplusapp;

public class jeju_item {
    public String jeju_numEf;//발효번호 (발표시간), 날씨 상세정보에 적용시키기
    public String jeju_ta; //예상기온
    public String jeju_rnSt; //강수확률
    public String jeju_wf; //날씨
    public String jeju_rnYn; //강수형태


    public void clear(){
        jeju_numEf = "";
        jeju_ta = "";
        jeju_rnSt = "";
        jeju_wf = "";
        jeju_rnYn = "";

    }

    boolean checkRecvAllData(){
        return jeju_ta.length()  > 0
                && jeju_rnSt.length()  > 0
                && jeju_wf.length()    > 0
                && jeju_rnYn.length()     > 0
                && jeju_numEf.length() >0;

    }
}
