package controller;

import dao.DongDAO;
import java.io.IOException;
import java.util.List;
import dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dongSelection")
public class DongSelectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 사용자가 선택한 동 가져오기
        String selectedDong = request.getParameter("selectedDong");

        if (selectedDong != null) {
            // 동 이름을 request에 저장
            request.setAttribute("selectedDong", selectedDong);

            // 리다이렉트할 페이지 경로 (review_second.jsp)
            String nextPage = "Review/review_second.jsp";

            // 리다이렉트
            response.sendRedirect(nextPage);
        } else {
            // 기본 페이지로 포워딩 (사용자가 동을 선택하지 않은 경우)
            // DongDAO를 사용하여 동 이름 목록을 가져옴
            DongDAO dongDAO = new DongDAO();
            List<String> dongNames = dongDAO.getDongNames();

            // 동 이름 목록을 request에 저장
            request.setAttribute("dongNames", dongNames);

            // 기본페이지로 포워딩
            request.getRequestDispatcher("Review/review_first.jsp").forward(request, response);
        }
    }
}
