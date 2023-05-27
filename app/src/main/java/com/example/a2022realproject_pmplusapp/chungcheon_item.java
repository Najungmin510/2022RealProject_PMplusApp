package com.example.a2022realproject_pmplusapp;

public class chungcheon_item {
    public String ch_numEf;
    public String ch_ta; //예상기온
    public String ch_rnSt; //강수확률
    public String ch_wf; //날씨
    public String ch_rnYn; //강수형태


    public void clear(){
        ch_numEf = "";
        ch_ta = "";
        ch_rnSt = "";
        ch_wf = "";
        ch_rnYn = "";

    }

    boolean checkRecvAllData(){
        return ch_ta.length()  > 0
                && ch_rnSt.length()  > 0
                && ch_wf.length()    > 0
                && ch_rnYn.length()     > 0
                && ch_numEf.length() > 0;

    }
}
