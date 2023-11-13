package jun;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

            // 클라이언트에 응답
            response.getWriter().write("Coordinates received: xPos=" + xPos + ", yPos=" + yPos);
        } catch (NumberFormatException e) {
            // 좌표값이 올바르지 않은 경우 처리
            response.getWriter().write("Invalid coordinates");
        }
    }
}
