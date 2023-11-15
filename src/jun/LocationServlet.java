package jun;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 좌표값 받기
            double xPos = Double.parseDouble(request.getParameter("xPos"));
            double yPos = Double.parseDouble(request.getParameter("yPos"));

            // 받은 좌표값을 이용한 처리 수행
            System.out.println("Received coordinates: xPos=" + xPos + ", yPos=" + yPos);

            // 약국 리스트 가져오기
            List<Pharmacy> pharmacyList = PharmacyApi.getNearbyPharmacies(xPos, yPos);
            System.out.println("Pharmacy List: " + pharmacyList);

            // 약국 리스트를 JSON 형식으로 변환
            String jsonPharmacyList = buildJsonPharmacyList(pharmacyList);

            // JSON 형식으로 응답
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonPharmacyList);

        } catch (NumberFormatException e) {
            // 좌표값이 올바르지 않은 경우 처리
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Invalid coordinates");
            e.printStackTrace();
        }
    }

    private String buildJsonPharmacyList(List<Pharmacy> pharmacyList) {
        JSONArray jsonArray = new JSONArray();
        for (Pharmacy pharmacy : pharmacyList) {
            JSONObject pharmacyObject = new JSONObject();
            pharmacyObject.put("name", pharmacy.getName());
            pharmacyObject.put("address", pharmacy.getAddress());
            jsonArray.add(pharmacyObject);
        }
        return jsonArray.toJSONString();
    }
}
