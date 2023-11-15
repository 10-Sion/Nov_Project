package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.postsVO.PostsVO;

public class MainBoardDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void freeResource() {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            // Connection should be closed outside this method to allow more flexibility
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws Exception {
        if (con == null || con.isClosed()) {
            con = DBConnector.getConnection();
        }
        return con;
    }

    private String getColumnName(String tableName, boolean isPrimaryKey) {
        switch (tableName) {
            case "Announcements":
                return isPrimaryKey ? "announcement_id" : "post_title";
            case "Posts":
                return isPrimaryKey ? "post_id" : "post_title";
            case "Reviews":
                return isPrimaryKey ? "review_id" : "review_text";
            default:
                throw new IllegalArgumentException("Unsupported table name: " + tableName);
        }
    }

    public List<PostsVO> getTitlesAndPK(String tableName) {
        List<PostsVO> infoList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " ORDER BY " + getColumnName(tableName, true) + " DESC LIMIT 5");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt(getColumnName(tableName, true));
                String title;

                // 테이블에 따라 title 컬럼을 다르게 설정
                if ("Announcements".equals(tableName)) {
                    title = rs.getString("post_title");
                } else if ("Posts".equals(tableName)) {
                    title = rs.getString("post_title");
                } else if ("Reviews".equals(tableName)) {
                    title = rs.getString("review_text");
                } else {
                    throw new IllegalArgumentException("Unsupported table name: " + tableName);
                }

                // PostsVO 객체를 생성하여 값을 설정
                PostsVO post = new PostsVO();
                post.setPost_id(id);
                post.setPost_title(title);

                // 다른 필요한 정보들도 설정
                // post_name 컬럼이 있는 경우에만 설정
                if ("Posts".equals(tableName)) {
                    post.setPost_name(rs.getString("post_name"));
                }

                infoList.add(post);
            }
        } catch (Exception e) {
            System.out.println("MainBoardDAO - Error getting latest titles and PK: " + e.getMessage());
        }

        System.out.println("MainBoardDAO - Titles and PK for " + tableName + ": " + infoList);

        return infoList;
    }


    public List<PostsVO> getLatestAnnouncement() {
        return getTitlesAndPK("Announcements");
    }

    public List<PostsVO> getLatestPost() {
        return getTitlesAndPK("Posts");
    }

    public List<PostsVO> getLatestReview() {
        return getTitlesAndPK("Reviews");
    }

    // Additional method to close the Connection outside this method
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
