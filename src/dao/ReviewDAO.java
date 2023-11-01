package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DatabaseConnection;
import vo.ReviewVO;


public class ReviewDAO {
	private Connection connection;
	
	public ReviewDAO() {
		this.connection = DatabaseConnection.getConnection();
		
		
	}
	// 리뷰 정보를 추가하는 메서드
	public boolean addReview(ReviewVO review) {
	    String query = "INSERT INTO Reviews (user_id, hospital_id, review_text, rating, verification) " +
	                   "VALUES (?, ?, ?, ?, '미인증')"; // '미인증'을 직접 포함

	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, review.getUserId());
	        statement.setInt(2, review.getHospitalId());
	        statement.setString(3, review.getReviewText());
	        statement.setDouble(4, review.getRating());

	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0; // 리뷰가 성공적으로 추가되면 true를 반환
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    }

