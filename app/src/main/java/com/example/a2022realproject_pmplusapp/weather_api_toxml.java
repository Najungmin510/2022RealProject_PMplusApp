package com.example.a2022realproject_pmplusapp;

import android.annotation.SuppressLint;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;


//XML 형태로 데이터 받아온 후 필요한 정보만 가공해 mainactivity_weather로 전달
/*
regld : 예보구역코드
tmFc : 발표시간
wd1 : 풍향 1
wdTnd : 풍향연결코드
wd2 : 풍향 2
ws1 : 풍속 1
wf : 날씨
wfCd : 날씨예보코드
rnYn : 강수형태
 */




public class weather_api_toxml {


    TextView main_mini_weather_sky;
    TextView main_mini_weather_rain; //메인화면에 출력 할 내용들


    TextView detail_weather_degree;
    TextView detail_weather_sky;
    TextView detail_weather_wind;
    TextView detail_weather_wave;
    TextView detail_weather_rain; //해상예보조회 화면에 출력 할 내용들




    public static void main(String[] args) throws IOException {


    }


    @SuppressLint("SetTextI18n")
    public weather_api_toxml(String searchdata) throws UnsupportedEncodingException {

        Object findViewById;
        TextView detail_weather_ocean = null;
        TextView detail_weather_degree = null;
        TextView detail_weather_sky = null;
        TextView detail_weather_wind = null;
        TextView detail_weather_wave = null;
        TextView detail_weather_rain = null;



        detail_weather_ocean = (TextView) detail_weather_ocean.findViewById(R.id.ocean_name_weather);
        detail_weather_degree = (TextView) detail_weather_degree.findViewById(R.id.degree_ocean_weather);
        detail_weather_sky = (TextView)detail_weather_sky.findViewById(R.id.sky_weather);
        detail_weather_wave = (TextView)detail_weather_wave.findViewById(R.id.wave_height_detail);
        detail_weather_wind = (TextView)detail_weather_wind.findViewById(R.id.wind_weather_detail);



        boolean initem = false;
        boolean inregld = false, intmFc = false, inwd1 = false, inwdTnd = false, inwd2 = false;
        boolean inws1 = false, inwf = false, inwfCd = false, inrnYn = false;

        String regld = null;
        String tmFc = null;
        String wd1 = null;
        String wdTnd = null;
        String wd2 = null;
        String ws1 = null;
        String wf = null;
        String wfCd = null;
        String rnYn = null;


        String regld_result;


     try{
         URL url = new URL("http://apis.data.go.kr/1360000/VilageFcstMsgService/getSeaFcst?"+
                 "serviceKey=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D&numOfRows" +
                 "=1&pageNo=1&regId="+searchdata); //사용자의 입력값에 맞춰 api 연결

         XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
         XmlPullParser parser = parserCreator.newPullParser();

         parser.setInput(url.openStream(), null);

         int parserEvent = parser.getEventType();
         System.out.println("파싱시작.\n");


         while (parserEvent != XmlPullParser.END_DOCUMENT){
             switch(parserEvent){
                 case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                     if(parser.getName().equals("regld")){
                         inregld = true;
                     }
                     if(parser.getName().equals("rnYn")){
                         inrnYn = true;
                     }
                     if(parser.getName().equals("tmFc")){
                         intmFc = true;
                     }
                     if(parser.getName().equals("wd1")){
                         inwd1 = true;
                     }
                     if(parser.getName().equals("wd2")){
                         inwd2 = true;
                     }
                     if(parser.getName().equals("ws1")){
                         inws1 = true;
                     }
                     if(parser.getName().equals("wf")){
                         inwf = true;
                     }
                     if(parser.getName().equals("wfCd")){
                         inwfCd = true;
                     }
                     if(parser.getName().equals("rnYn")){
                         inrnYn = true;
                     }
                     break;

                 case XmlPullParser.TEXT://parser가 내용에 접근했을때
                     if(inregld){ //isTitle이 true일 때 태그의 내용을 저장.
                         regld = parser.getText();
                         inregld = false;
                     }
                     if(intmFc){ //isAddress이 true일 때 태그의 내용을 저장.
                         tmFc = parser.getText();
                         intmFc = false;
                     }
                     if(inwd1){ //isMapx이 true일 때 태그의 내용을 저장.
                         wd1 = parser.getText();
                         inwd1 = false;
                     }
                     if(inwdTnd){ //isMapy이 true일 때 태그의 내용을 저장.
                         wdTnd = parser.getText();
                         inwdTnd = false;
                     }
                     if(inwd2){ //isMapy이 true일 때 태그의 내용을 저장.
                         wd2 = parser.getText();
                         inwd2 = false;
                     }
                     if(inws1){ //isMapy이 true일 때 태그의 내용을 저장.
                         ws1 = parser.getText();
                         inws1 = false;
                     }
                     if(inwf){ //isMapy이 true일 때 태그의 내용을 저장.
                        wf = parser.getText();
                         inwf = false;
                     }
                     if(inwfCd){ //isMapy이 true일 때 태그의 내용을 저장.
                         wfCd = parser.getText();
                         inwfCd = false;
                     }
                     if(inrnYn){ //isMapy이 true일 때 태그의 내용을 저장.
                         rnYn = parser.getText();
                         inrnYn = false;
                     }

                     break;
                 case XmlPullParser.END_TAG:
                     if(parser.getName().equals("item")){

                         initem = false;
                     }
                     break;
             }
             parserEvent = parser.next();
         }
     }catch(Exception ignored){

        }


    }

}

