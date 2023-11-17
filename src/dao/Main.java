package dao;

import java.util.List;

import vo.postsVO.PostsVO; // PostsVO 클래스 import

public class Main {
    public static void main(String[] args) {
        MainBoardDAO mainBoardDAO = new MainBoardDAO();

        try {
            // Ensure a database connection is established
            try {
                mainBoardDAO.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 최신 공지 5개 가져오기
            List<PostsVO> announcementList = mainBoardDAO.getLatestAnnouncement();
            System.out.println("Latest Announcements: " + announcementList);

            // 최신 게시글 5개 가져오기
            List<PostsVO> postList = mainBoardDAO.getLatestPost();
            System.out.println("Latest Posts: " + postList);

            // 최신 리뷰 5개 가져오기
            List<PostsVO> reviewList = mainBoardDAO.getLatestReview();
            System.out.println("Latest Reviews: " + reviewList);
        } finally {
            // Always release resources after usage
            mainBoardDAO.freeResource();
        }
    }
}
