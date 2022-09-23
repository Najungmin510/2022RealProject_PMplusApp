import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;



public class testcode {
    public static void main(String[] args) throws IOException {

        String urlBuilder = "http://apis.data.go.kr/1192000/VsslEtrynd2/Info" + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=NYQp85bV4GjxauduBdSwaoZb3uT9jcgbECXr1WNuzKbPSx5%2Fdv7m%2B5gV6xRZk3yYt5M4dzOuspvMzSwrPgtd7g%3D%3D" + /*Service Key*/
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*페이지번호*/
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8") + /*조회 결과 데이터 최대 개수(최대 50)*/
                "&" + URLEncoder.encode("prtAgCd", "UTF-8") + "=" + URLEncoder.encode("020", "UTF-8") + /*검색하고자 하는 항만청 코드*/
                "&" + URLEncoder.encode("sde", "UTF-8") + "=" + URLEncoder.encode("20170209", "UTF-8") + /*검색을 원하는 조회 시작 날짜*/
                "&" + URLEncoder.encode("ede", "UTF-8") + "=" + URLEncoder.encode("20170209", "UTF-8") + /*검색을 원하는 조회 종료 날짜*/
                "&" + URLEncoder.encode("clsgn", "UTF-8") + "=" + URLEncoder.encode("9VHZ8", "UTF-8");/*URL*//*검색을 원하는 조회 호출부호(민감정보이므로 예제에서는 실제값을 ABCDEFG로 값을 대체함)*/
        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString()+"\n");

    }
}
