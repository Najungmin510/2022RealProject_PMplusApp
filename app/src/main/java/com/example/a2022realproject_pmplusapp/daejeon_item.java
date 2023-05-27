package com.example.a2022realproject_pmplusapp;

public class daejeon_item {
    public String daejeon_numEf;
    public String daejeon_ta; //예상기온
    public String daejeon_rnSt; //강수확률
    public String daejeon_wf; //날씨
    public String daejeon_rnYn; //강수형태


    public void clear(){
        daejeon_numEf = "";
        daejeon_ta = "";
        daejeon_rnSt = "";
        daejeon_wf = "";
        daejeon_rnYn = "";

    }

    boolean checkRecvAllData(){
        return daejeon_ta.length()  > 0
                && daejeon_rnSt.length()  > 0
                && daejeon_wf.length()    > 0
                && daejeon_rnYn.length()     > 0
                && daejeon_numEf.length() > 0;

    }
}
