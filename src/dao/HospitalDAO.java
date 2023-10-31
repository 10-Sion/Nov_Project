package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    private Connection connection;

    public HospitalDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // 선택한 동에 해당하는 병원 이름 목록을 가져오는 메서드
    public List<String> getHospitalNamesByDong(String selectedDong) {
        List<String> hospitalNames = new ArrayList<>();
        String query = "SELECT name FROM Hospitals WHERE city = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedDong);
            
            System.out.println("SQL Query: " + statement.toString()); // 쿼리를 콘솔에 출력
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    hospitalNames.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospitalNames;
    }
}
