package com.example.a2022realproject_pmplusapp;


//날씨 아이템
public class weather_item {
    public String numEf;//발효번호 (발표시간), 날씨 상세정보에 적용시키기
    public String ws1; //풍속1
    public String wh1; //파고 1
    public String wf; //날씨
    public String rnYn; //강수형태

    public void clear(){
        numEf = "";
        ws1 = "";
        wh1 = "";
        wf = "";
        rnYn = "";
    }

    boolean checkRecvAllData(){
        return numEf.length() > 0
                && ws1.length() > 0
                && wh1.length() > 0
                && wf.length() > 0
                && rnYn.length() > 0;
    }

}
