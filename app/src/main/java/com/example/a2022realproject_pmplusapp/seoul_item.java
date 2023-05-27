package com.example.a2022realproject_pmplusapp;

public class seoul_item {
    public String s_numEf;//발효번호 (발표시간), 날씨 상세정보에 적용시키기
    public String s_ta; //예상기온
    public String s_rnSt; //강수확률
    public String s_wf; //날씨
    public String s_rnYn; //강수형태


    public void clear(){
        s_numEf = "";
        s_ta = "";
        s_rnSt = "";
        s_wf = "";
        s_rnYn = "";

    }

    boolean checkRecvAllData(){
        return s_ta.length()  > 0
                && s_rnSt.length()  > 0
                && s_wf.length()    > 0
                && s_rnYn.length()     > 0
                && s_numEf.length() > 0;

    }
}
