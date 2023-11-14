package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.gongjiDAO.GongiDAO;
import dao.jauDAO.JauDAO;
import vo.announcementsVO.AnnouncementsVO;
import vo.postsVO.PostsVO;

@WebServlet("/mainBoard/*")
public class MainBoardController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        switch (action) {
            case "/jauList":
                showJauList(request, response);
                break;
            case "/noticeList":
                showNoticeList(request, response);
                break;
        }
    }

    private void showJauList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Jau 게시판 정보 가져오는 로직
        List<PostsVO> jauList = getJauBoardData(); // 메소드 호출로 실제 데이터 가져오기
        request.setAttribute("jauList", jauList);
        request.getRequestDispatcher("/Community/Board/boardList.jsp").forward(request, response);
    }

    private List<PostsVO> getJauBoardData() {
        // 여기에서 데이터베이스 조회 및 jauList에 데이터 추가
        JauDAO jauDAO = new JauDAO();
        return jauDAO.jauList(0, 5);
    }

    private void showNoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Notice 게시판 정보 가져오는 로직
        List<AnnouncementsVO> noticeList = getNoticeBoardData(); // 메소드 호출로 실제 데이터 가져오기
        request.setAttribute("noticeList", noticeList);
        request.getRequestDispatcher("/Community/Board/boardList.jsp").forward(request, response);
    }

    private List<AnnouncementsVO> getNoticeBoardData() {
        // 여기에서 데이터베이스 조회 및 noticeList에 데이터 추가
        GongiDAO gongiDAO = new GongiDAO();
        return gongiDAO.gongiList(0, 5);
    }
}
