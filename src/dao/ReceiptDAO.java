package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.ReceiptVO;

//영수증
public class ReceiptDAO {
    private Connection connection;

    public ReceiptDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // 영수증 정보를 추가하는 메서드
    public boolean addReceipt(ReceiptVO receipt) {
        String query = "INSERT INTO receipt (user_id, hospital_id, receipt_image) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, receipt.getUserId());
            statement.setInt(2, receipt.getHospitalId());
            statement.setBytes(3, receipt.getReceiptImage());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // 영수증이 성공적으로 추가되면 true를 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
