package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainBoardDAO;
import vo.postsVO.PostsVO;

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
                showAllLists(request, response);
        }
    }

    private void showAllLists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Jau 게시판 정보 가져오는 로직
        List<PostsVO> jauList = mainBoardDAO.getLatestPost();
        request.setAttribute("jauList", jauList);

        // Notice 게시판 정보 가져오는 로직
        List<PostsVO> noticeList = mainBoardDAO.getLatestAnnouncement();
        request.setAttribute("noticeList", noticeList);

        // Review 게시판 정보 가져오는 로직
        List<PostsVO> reviewList = mainBoardDAO.getLatestReview();
        request.setAttribute("reviewList", reviewList);

        // 한 번에 forward
        request.getRequestDispatcher("/Main/mainPage.jsp").forward(request, response);
    }

    private void showJauList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PostsVO> jauList = mainBoardDAO.getLatestPost();
        request.setAttribute("jauList", jauList);
        response.sendRedirect(request.getContextPath() + "/mainBoard/*");
    }

    private void showNoticeList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PostsVO> noticeList = mainBoardDAO.getLatestAnnouncement();
        request.setAttribute("noticeList", noticeList);
        response.sendRedirect(request.getContextPath() + "/mainBoard/*");
    }

    private void showReviewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PostsVO> reviewList = mainBoardDAO.getLatestReview();
        request.setAttribute("reviewList", reviewList);
        response.sendRedirect(request.getContextPath() + "/mainBoard/*");
    }
}
