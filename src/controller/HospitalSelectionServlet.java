package controller;

import dao.DongDAO;
import dao.HospitalDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hospitalSelection")
public class HospitalSelectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 사용자가 선택한 동 가져오기
        String selectedDong = request.getParameter("selectedDong");

        if (selectedDong != null) {
            // hospitalDAO를 사용하여 선택한 동에 해당하는 병원 이름 목록을 가져옴
            HospitalDAO hospitalDAO = new HospitalDAO();
            List<String> hospitalNames = hospitalDAO.getHospitalNamesByDong(selectedDong);

            // 병원 이름 목록을 request에 저장
            request.setAttribute("hospitalNames", hospitalNames);

            // JSP 페이지로 포워딩
            request.getRequestDispatcher("Review/review_second.jsp").forward(request, response);
        }
    }
}
