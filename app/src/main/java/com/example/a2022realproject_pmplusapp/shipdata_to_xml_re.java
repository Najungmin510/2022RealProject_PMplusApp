package com.example.a2022realproject_pmplusapp;

//선박 입출항 현황 api 추출 및 데이터 정제 클래스


import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

class shipdata_to_xml_re {

     String code;
     String day1;
     String day2;
     String call;

     //근데 이거 너무 비효율적인듯...
     String[] total_array = new String[15]; //배열에 값을 저장해서 return 해줄거임
     String[] total_array_2 = new String[15];
     String[] total_array_3 = new String[15];
     String[] total_array_4 = new String[15];
     String[] total_array_5 = new String[15];

     String[] total_array_6 = new String[15]; //배열에 값을 저장해서 return 해줄거임
     String[] total_array_7 = new String[15];
     String[] total_array_8 = new String[15];
     String[] total_array_9 = new String[15];
     String[] total_array_10 = new String[15];

     String[] total_array_11 = new String[15]; //배열에 값을 저장해서 return 해줄거임
     String[] total_array_12 = new String[15];
     String[] total_array_13 = new String[15];
     String[] total_array_14 = new String[15];
     String[] total_array_15 = new String[15];

     String[] total_array_16 = new String[15]; //배열에 값을 저장해서 return 해줄거임
     String[] total_array_17 = new String[15];
     String[] total_array_18 = new String[15];
     String[] total_array_19 = new String[15];
     String[] total_array_20 = new String[15];

    public shipdata_to_xml_re(String rs_sc, String rs_day1, String rs_day2, String rs_call) {
        this.code = rs_sc; //청코드
        this.day1 = rs_day1; //입항날짜
        this.day2 = rs_day2; //출항날짜
        this.call = rs_call; //호출부호
    }

      public String[] shipdata_to_xml_re() { // 청코드. 입항, 출항, 호출부호

         //출력할 때 조회된 결과 수는 1개 입니다 식으로 몇개 조회되었는지 알려주어야 할 듯
         //int count 이용해서 결과수 개수 센 후 이를 return 해줘야 할 것 같다... 이것도 배열 맨 마지막에 넣어서 출력해줘야 할 것 같음
         //count는 전역변수로 두고, for 문 이용해서 계산할 것

         // 여기서 1~50번째 까지의 값을 전부 받아서 return 할거임
         String datatag = "parsing";

            String a_d2 = this.day2;
            String a_d1 = this.day1;
            String a_s = this.code;
            String a_c = this.call; //호출부호

        String ship_url = "http://apis.data.go.kr/1192000/VsslEtrynd2/Info?" +
                "serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" +
                "&sde="+a_d1+"&ede="+a_d2+"&prtAgCd="+a_s+"&clsgn="+a_c+"&pageNo=1&numOfRows=1";


        try {
            URL url = new URL(ship_url); //url 연결
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));


            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName(); //태그의 이름을 얻어옴

                        if (tag.equals("item")) {
                            System.out.println("item목록 파싱입니다.");

                        } else if (tag.equals("prtAgCd")) {
                            String result_s_prtAgCd = xpp.getText(); //항구청코드 값 가져오기
                            total_array[0] = result_s_prtAgCd;
                            Log.i(datatag,"data : "+ total_array[0]);

                        } else if (tag.equals("prtAgNm")) { //
                           String result_s_prtAgNm = xpp.getText();
                            total_array[1] = result_s_prtAgNm;
                            Log.i(datatag,"data : "+ total_array[1]);

                        } else if (tag.equals("clsgn")) { //
                            String result_s_clsgn = xpp.getText();
                            total_array[2] = result_s_clsgn;
                            Log.i(datatag,"data : "+ total_array[2]);

                        } else if (tag.equals("vsslNm")) { //
                            String result_s_vsslNm = xpp.getText();
                            total_array[3] = result_s_vsslNm;

                        } else if (tag.equals("vsslNltyNm")) { //
                            String result_s_vsslNltyNm = xpp.getText();
                            total_array[4] = result_s_vsslNltyNm;

                        } else if (tag.equals("vsslKndNm")) { //선박 종류명
                           String result_s_vsslKndNm = xpp.getText();
                            total_array[5] = result_s_vsslKndNm;

                        } else if (tag.equals("etryptPurpsNm")) { //입항 목적명
                           String result_s_etryptPurpsNm = xpp.getText();
                            total_array[6] = result_s_etryptPurpsNm;

                        } else if (tag.equals("dstnPrtNm")) { //목적지 항구명
                           String result_s_dstnPrtNm = xpp.getText();
                            total_array[7] = result_s_dstnPrtNm;

                        }else if (tag.equals("details")) {
                            System.out.println("detail목록 파싱입니다.");

                            } else if (tag.equals("etryptDt")) { //입항 날짜
                                String result_s_etryptDt = xpp.getText();
                                total_array[8] = result_s_etryptDt;

                            } else if (tag.equals("ibobprtNm")) { //내외항구분명
                                String result_s_ibobprtNm = xpp.getText();
                                total_array[9] = result_s_ibobprtNm;

                            } else if (tag.equals("laidupFcltyNm")) { // 계선시설명
                                String result_s_laidupFcltyNm = xpp.getText();
                                total_array[10] = result_s_laidupFcltyNm;

                            } else if (tag.equals("ldadngFrghtClCd")) { //화물명세
                                String result_s_ldadngFrghtClCd = xpp.getText();
                                total_array[11] = result_s_ldadngFrghtClCd;

                            } else if (tag.equals("grtg")) {//총톤수
                                String result_s_grtg = xpp.getText();
                                total_array[12] = result_s_grtg;

                            } else if(tag.equals("satmntEntrpsNm")) { //신고업체명
                                String result_s_satmntEntrpsNm = xpp.getText();
                                total_array[13] = result_s_satmntEntrpsNm;

                            } else if(tag.equals("tkoffDt")) {//출항일시
                                String result_s_tkoffDt = xpp.getText();
                                total_array[14] = result_s_tkoffDt;

                            }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                             tag = xpp.getText();
                             if (tag.equals("item")){ //만약 세부사항 중 "입항" 부분이 끝이라면 밑의 detail을 분석
                               break;
                             }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total_array;
    }


    public shipdata_to_xml_re(){ }

    public String[] total(){
        return total_array;
    } //첫번째 페이지 결과의 값을 넘겨줌

    public String[] total2(){
         return total_array_2;
    } //두번째 페이지 결과


}
