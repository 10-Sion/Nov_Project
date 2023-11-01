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

    // 선택한 동에 해당하는 병원 리스트를 가져오는 메서드
    public List<String> getHospitalNamesByDong(String selectedDong) {
        List<String> hospitalNames = new ArrayList<>();
        String query = "SELECT name FROM Hospitals WHERE city = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedDong);
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
    
    // 병원 이름으로 ID 값을 가져오는 메서드
    public int getHospitalIdByName(String hospitalName) {
        int hospitalId = -1; // 기본적으로 -1 또는 다른 알려진 값으로 초기화
        
        String query = "SELECT id FROM Hospitals WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hospitalName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    hospitalId = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospitalId;
    }


}
