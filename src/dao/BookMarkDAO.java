// BookMarkDAO.java (DAO)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMarkDAO {
    private List<String> bookmarks = new ArrayList<>();
    
    // 기존 북마크 목록을 가져옴
    public List<String> getBookmarks() {
        List<String> bookmarks = new ArrayList<>();
        
        try {
            // 데이터베이스 연결을 얻어옴
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT page_url FROM Favorites");
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String bookmark = resultSet.getString("url");
                bookmarks.add(bookmark);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            DatabaseConnection.closeConnection();
            
        }
        
        return bookmarks;
    }

    // 새 북마크를 추가하는 메서드
    public void addBookmark(String url) {
    	
        try {
            // 데이터베이스 연결을 얻어옴
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Favorites (page_url) VALUES (?)");
            preparedStatement.setString(1, url);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            DatabaseConnection.closeConnection();
            
        }
    }
    
    // 북마크 목록에서 삭제하는 메서드
    public void deleteBookmark(int index) {

        if (index >= 0 && index < bookmarks.size()) {
            bookmarks.remove(index);
            
        }

    }

}
