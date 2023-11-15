package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws Exception {
        if (con == null || con.isClosed()) {
            con = DBConnector.getConnection();
        }
        
            con = DBConnector.getConnection();

           
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

    public List<String> getTitlesAndPK(String tableName) {
        List<String> infoList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " ORDER BY " + getColumnName(tableName, true) + " DESC LIMIT 5");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt(getColumnName(tableName, true));
                String title = rs.getString(getColumnName(tableName, false));
                infoList.add(tableName + " ID: " + id + ", Title: " + title);
            }
        } catch (Exception e) {
            System.out.println("MainBoardDAO - Error getting latest titles and PK: " + e.getMessage());
        }

        System.out.println("MainBoardDAO - Titles and PK for " + tableName + ": " + infoList);

        return infoList;
    }

    public List<String> getLatestAnnouncement() {
        return getTitlesAndPK("Announcements");
    }

    public List<String> getLatestPost() {
        return getTitlesAndPK("Posts");
    }

    public List<String> getLatestReview() {
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
