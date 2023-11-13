package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;
import vo.ReviewVO;
import dao.DatabaseConnection;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ReviewDAO 객체 생성
        ReviewDAO reviewDAO = new ReviewDAO();

        // "인증완료" 상태의 리뷰 가져오기
        List<ReviewVO> verifiedReviews = reviewDAO.getVerifiedReviews();

        // 가져온 리뷰를 웹 페이지에 표시하거나 다른 작업 수행
        request.setAttribute("verifiedReviews", verifiedReviews);
        request.getRequestDispatcher("Review/check.jsp").forward(request, response);
    }
}
