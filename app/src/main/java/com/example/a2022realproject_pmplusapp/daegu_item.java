package com.example.a2022realproject_pmplusapp;

public class daegu_item {
    public String daegu_numEf;
    public String daegu_ta; //예상기온
    public String daegu_rnSt; //강수확률
    public String daegu_wf; //날씨
    public String daegu_rnYn; //강수형태


    public void clear(){
        daegu_numEf="";
        daegu_ta = "";
        daegu_rnSt = "";
        daegu_wf = "";
        daegu_rnYn = "";

    }

    boolean checkRecvAllData(){
        return daegu_ta.length()  > 0
                && daegu_rnSt.length()  > 0
                && daegu_wf.length()    > 0
                && daegu_rnYn.length()     > 0
                && daegu_numEf.length()>0;

    }
}
