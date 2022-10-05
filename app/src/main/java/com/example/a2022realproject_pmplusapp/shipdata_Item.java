package com.example.a2022realproject_pmplusapp;

//선박 입출항 현황 아이템들 정렬



public class shipdata_Item {

    String prtAgCd; //항구청코드
    String prtAgNm; //항구청명
    String clsgn; //호출부호
    String vsslNm; //선박명
    String vsslNltyNm; //선박 국가명
    String vsslKndNm; // 선박종류명
    String etryptPurpsNm; //입항목적명
    String dstnPrtNm; //목적지항구명

    String etryptDt; //입항일시
    String tkoffDT; //출항일시
    String ibobprtNm; //내외항구분명
    String laidupFcltyNm; //계선시설명
    String ldadngFrghtClCd; //화물명세
    String grtg; // 총톤수
    String satmntEntrpsNm; //신고업체명

    //xml 데이터는 오름차순으로 파싱되어옴

    public void clear(){

         prtAgCd = ""; //항구청코드 o
         prtAgNm = ""; //항구청명 o
         clsgn = ""; //호출부호 o
         vsslNm = ""; //선박명 o
         vsslNltyNm = ""; //선박 국가명 o
         vsslKndNm = ""; // 선박종류명 o
         etryptPurpsNm = ""; //입항목적명 o
         dstnPrtNm = ""; //목적지항구명 o

         etryptDt = ""; //입항일시 o
         tkoffDT = ""; //출항일시 o
         ibobprtNm = ""; //내외항구분명 o
         laidupFcltyNm = ""; //계선시설명 o
         ldadngFrghtClCd = ""; //화물명세 o
         grtg = ""; // 총톤수 o
         satmntEntrpsNm = ""; //신고업체명



    }

    boolean checkRecvAllData(){
        return prtAgCd.length()  > 0
                && prtAgNm.length()  > 0
                && clsgn.length()    > 0
                && vsslNm.length()     > 0
                && vsslNltyNm.length()     > 0
                && vsslKndNm.length() > 0
                && etryptPurpsNm.length()  > 0
                && dstnPrtNm.length()    > 0
                && etryptDt.length()     > 0
                && tkoffDT.length()     > 0
                && ibobprtNm.length() > 0
                && laidupFcltyNm.length()  > 0
                && ldadngFrghtClCd.length()    > 0
                && grtg.length()     > 0
                && satmntEntrpsNm.length()    > 0;
    }
}
