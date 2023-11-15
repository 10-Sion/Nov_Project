package service;

import dao.MainBoardDAO;
import vo.postsVO.PostsVO;

import java.util.ArrayList;
import java.util.List;

public class MainBoardService {
    private MainBoardDAO mainBoardDAO = new MainBoardDAO();

    public List<PostsVO> serviceGetPostsForMultipleBoards() {
        List<PostsVO> freeBoardPosts = mainBoardDAO.getLatestPost("Posts", 0, 5);
        List<PostsVO> noticeBoardPosts = mainBoardDAO.getLatestAnnouncement("Announcements", 0, 5);
        List<PostsVO> reviewBoardPosts = mainBoardDAO.getLatestReview("Reviews", 0, 5);

        List<PostsVO> allPosts = new ArrayList<>();
        allPosts.addAll(freeBoardPosts);
        allPosts.addAll(noticeBoardPosts);
        allPosts.addAll(reviewBoardPosts);

        return allPosts;
    }
}
