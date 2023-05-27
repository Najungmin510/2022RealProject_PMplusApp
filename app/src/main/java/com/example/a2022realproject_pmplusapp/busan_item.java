package com.example.a2022realproject_pmplusapp;

public class busan_item {
    public String busan_numEf;
    public String busan_ta; //예상기온
    public String busan_rnSt; //강수확률
    public String busan_wf; //날씨
    public String busan_rnYn; //강수형태


    public void clear(){
        busan_numEf = "";
        busan_ta = "";
        busan_rnSt = "";
        busan_wf = "";
        busan_rnYn = "";

    }

    boolean checkRecvAllData(){
        return busan_ta.length()  > 0
                && busan_rnSt.length()  > 0
                && busan_wf.length()    > 0
                && busan_rnYn.length()     > 0
                && busan_numEf.length() > 0;

    }
}
