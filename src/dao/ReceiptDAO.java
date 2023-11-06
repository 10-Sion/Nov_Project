package dao;

import java.sql.Connection;
import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DatabaseConnection;
import vo.ReceiptVO;

//영수증
public class ReceiptDAO {
    private Connection connection;

    public ReceiptDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

 // 영수증 정보를 추가하는 메서드
    public String addReceipt(HttpSession session, ReceiptVO receipt) {
        String query = "INSERT INTO receipt (user_id, hospital_id, receipt_image) VALUES (?, ?, ?)";

        // 세션을 통해 현재 로그인한 사용자의 user_id 값을 가져옴
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId != null) {
            receipt.setUserId(userId);  // ReceiptVO 객체에 user_id 설정
            
            // 이미지 파일명을 데이터베이스에 저장
            String receiptFileName = receipt.getReceiptImage();
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, receipt.getUserId());
                statement.setInt(2, receipt.getHospitalId());
                statement.setString(3, receiptFileName); // 이미지 파일명 저장

                int rowsInserted = statement.executeUpdate();

                // 삽입 성공 여부에 따라 반환값을 결정
                if (rowsInserted > 0) {
                    return "success";
                } else {
                    return "failure";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "failure";
            }
        } else {
            return "failure";
        }
    }


        	}



