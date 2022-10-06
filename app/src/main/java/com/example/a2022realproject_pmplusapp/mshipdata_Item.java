package com.example.a2022realproject_pmplusapp;
//선박 관제 현황 아이템들 정렬


public class mshipdata_Item {

    String m_prtAgCd; //항구청코드
    String m_prtAgNm; //항만명
    String m_clsgn; //호출부호
    String m_vsslNm; //선박한글명
    String m_vsslNltyNm; //선박국적명
    String m_vsslKndNm; // 선박종류명
    String m_vsslGrtg; //선박총톤수
    String m_aprtfEtryptDt; //기항지입항일시
    String m_harborEntrpsNm; //항만업체명

    String m_cntrlNm; //관제구분명
    String m_cntrlOpertDt; //관제작업일시
    String m_fcltyCd; //시설코드
    String m_fcltySubCd; //시설서브코드
    String m_fcltyNm; //시설한글명


    //xml 데이터는 오름차순으로 파싱되어옴

    public void m_clear(){

        m_prtAgCd = ""; //항구청코드 o
        m_prtAgNm = ""; //항구청명 o
        m_clsgn = ""; //호출부호 o
        m_vsslNm = ""; //선박명 o
        m_vsslNltyNm = ""; //선박 국가명 o
        m_vsslKndNm = ""; // 선박종류명 o
        m_vsslGrtg = ""; //입항목적명 o
        m_aprtfEtryptDt = ""; //목적지항구명 o
        m_harborEntrpsNm = ""; //목적지항구명 o

        m_cntrlNm = ""; //목적지항구명 o
        m_cntrlOpertDt = ""; //목적지항구명 o
        m_fcltyCd = ""; //목적지항구명 o
        m_fcltySubCd = ""; //목적지항구명 o
        m_fcltyNm = ""; //목적지항구명 o

    }

    boolean m_checkRecvAllData(){

        return m_prtAgCd.length()  > 0
                && m_prtAgNm.length()  > 0
                && m_clsgn.length()    > 0
                && m_vsslNm.length()     > 0
                && m_vsslNltyNm.length()     > 0
                && m_vsslKndNm.length() > 0
                && m_vsslGrtg.length()  > 0
                && m_aprtfEtryptDt.length()    > 0
                && m_harborEntrpsNm.length()     > 0
                && m_cntrlNm.length()     > 0
                && m_cntrlOpertDt.length() > 0
                && m_fcltyCd.length()  > 0
                && m_fcltySubCd.length()    > 0
                && m_fcltyNm.length()     > 0;
    }
}
