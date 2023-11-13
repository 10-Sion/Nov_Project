package jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PharmacyApiExample {
    public static void main(String[] args) {
        try {
            // 발급받은 API 키를 여기에 입력
            String apiKey = "MdWCJF1FH2COwPbmfr0dmvJzAgHoGGLJOaDtmIKBSqzZWFn9Sj0eiIclx1pnsjf1dBKi6OpeoY6Z%2BarzAgC36g%3D%3D";
            apiKey = URLEncoder.encode(apiKey, "UTF-8");

            // API 엔드포인트
            String apiUrl = "http://apis.data.go.kr/B551182/hospInfoServicev2/getHospBasisList";

            // 요청 파라미터 설정
            String pageNo = "1";
            String numOfRows = "5";
            String sgguCd = "210004";  // 부산 진구 코드, 실제 코드로 변경 필요

            // 요청 URL 생성
            String requestUrl = apiUrl + "?ServiceKey=" + apiKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&sgguCd=" + sgguCd;

            // HTTP 요청 보내기
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // JSON 응답 파싱
            parseJsonResponse(response.toString());

            // 연결 해제
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server returned error: " + e.getMessage());
        }
    }

    private static void parseJsonResponse(String jsonResponse) {
        System.out.println("API 응답: " + jsonResponse);

        try {
            // JSON 응답을 파싱하는 코드
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject body = (JSONObject) jsonObject.get("body");

            // items가 배열이 아닌 경우 예외 처리
            Object itemsObj = body.get("items");
            if (itemsObj instanceof JSONArray) {
                JSONArray items = (JSONArray) itemsObj;

                for (int i = 0; i < items.size(); i++) {
                    JSONObject pharmacy = (JSONObject) items.get(i);

                    String name = (String) pharmacy.get("yadmNm"); // 약국명
                    String address = (String) pharmacy.get("addr"); // 주소

                    // 추출한 정보 활용 (여기에서는 콘솔에 출력)
                    System.out.println("약국명: " + name);
                    System.out.println("주소: " + address);
                    System.out.println("============================");
                }
            } else {
                System.out.println("API 응답에서 'items'가 배열이 아닙니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JSON 파싱 오류: " + e.getMessage());
        }
    }
}
