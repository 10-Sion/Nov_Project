package jun;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PharmacyApiExample {
    public static void main(String[] args) {
        try {
            // API 엔드포인트 URL
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList");

            // URL 파라미터 설정
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") +
                    "=MdWCJF1FH2COwPbmfr0dmvJzAgHoGGLJOaDtmIKBSqzZWFn9Sj0eiIclx1pnsjf1dBKi6OpeoY6Z%2BarzAgC36g%3D%3D"); // 서비스 키

            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지 번호
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); // 한 페이지 결과 수

            urlBuilder.append("&" + URLEncoder.encode("sgguCd", "UTF-8") + "=" + URLEncoder.encode("210004", "UTF-8")); // 시군구코드
            urlBuilder.append("&" + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode("3000", "UTF-8")); // 반경

            // URL 객체 생성
            URL url = new URL(urlBuilder.toString());

            // HTTP 연결 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            // HTTP 응답 코드 확인
            System.out.println("Response code: " + conn.getResponseCode());

            // 응답 내용 읽기
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            // 응답 내용을 StringBuilder에 저장
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // XML 파싱
            parseXmlResponse(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류: " + e.getMessage());
        }
    }

    private static void parseXmlResponse(String xmlResponse) {
        try {
            // XML 파싱을 위한 DocumentBuilder 생성
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 문자열에서 XML 파싱
            Document doc = builder.parse(new InputSource(new java.io.StringReader(xmlResponse)));
            doc.getDocumentElement().normalize();

            // "item" 엘리먼트들을 NodeList로 가져오기
            NodeList itemList = doc.getElementsByTagName("item");

            // 각 "item"에 대해 정보 출력
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);

                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;

                    String pharmacyName = getValue("yadmNm", itemElement);
                    String pharmacyAddress = getValue("addr", itemElement);

                    System.out.println("약국명: " + pharmacyName);
                    System.out.println("주소: " + pharmacyAddress);
                    System.out.println("============================");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("XML 파싱 오류: " + e.getMessage());
        }
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
