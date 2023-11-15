package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainBoardDAO;

@WebServlet("/mainBoard/*")
public class MainBoardController extends HttpServlet {
    MainBoardDAO mainBoardDAO = new MainBoardDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        switch (action) {

        case "/jauList":
            showJauList(request, response);
            break;
        case "/noticeList":
            showNoticeList(request, response);
            break;
        case "/reviewList":
            showReviewList(request, response);
            break;

        default:
            showJauList(request, response);
            showNoticeList(request, response);
            showReviewList(request, response);

        }
    }

    private void showJauList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Jau 게시판 정보 가져오는 로직
        List<String> jauList = mainBoardDAO.getLatestPost();
        request.setAttribute("jauList", jauList);
        request.getRequestDispatcher("/Main/mainPage.jsp").forward(request, response);
    }

    private void showNoticeList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Notice 게시판 정보 가져오는 로직
        List<String> noticeList = mainBoardDAO.getLatestAnnouncement();
        request.setAttribute("noticeList", noticeList);
        request.getRequestDispatcher("/Main/mainPage.jsp").forward(request, response);
    }

    private void showReviewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Review 게시판 정보 가져오는 로직
        List<String> reviewList = mainBoardDAO.getLatestReview();
        request.setAttribute("reviewList", reviewList);
        request.getRequestDispatcher("/Main/mainPage.jsp").forward(request, response);
    }
}
