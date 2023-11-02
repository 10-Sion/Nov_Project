package dao;

import java.sql.Connection;
import javax.servlet.http.HttpSession;
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
	public String addReview(HttpSession session, ReviewVO review) {
	    String query = "INSERT INTO Reviews (user_id, hospital_id, review_text, rating, verification) " +
	                   "VALUES (?, ?, ?, ?, '미인증')";

	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        // 세션을 통해 현재 로그인한 사용자의 user_id 값을 가져옴
	        Integer userId = (Integer) session.getAttribute("user_id");

	        // user_id가 null이 아닌지 확인
	        if (userId != null) {
	            statement.setInt(1, userId); // user_id를 설정
	            statement.setInt(2, review.getHospitalId());
	            statement.setString(3, review.getReviewText());
	            statement.setDouble(4, review.getRating());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                return "success"; // 리뷰가 성공적으로 추가되면 "success"를 반환
	            } else {
	                return "fail"; // 리뷰 추가 실패 시 "fail"를 반환
	            }
	        } else {
	            return "notLoggedIn"; // 사용자가 로그인하지 않은 경우 "notLoggedIn"을 반환
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "error"; // 다른 오류 발생 시 "error"를 반환
	    }
	}
}

