package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vo.HospitalsVO;
import dao.DatabaseConnection;

public class DongDAO {
    private Connection connection;

    public DongDAO() {
        this.connection = DatabaseConnection.getConnection();
        
        if (connection != null) {
            System.out.println("연결 성공."); // 연결 확인 메시지 출력
        } else {
            System.out.println("연결 실패."); // 연결 실패 메시지 출력
        }
    
    }

    // 동 이름을 가져오는 메서드
    public List<String> getDongNames() {
    	// set으로 중복 제거
        Set<String> dongNamesSet = new HashSet<>();
        String query = "SELECT city FROM Hospitals";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
            	// dongNamesSet.add(resultSet.getString("city"));
            	
            	String dongName = resultSet.getString("city");
                dongNamesSet.add(dongName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set을 List로 변환
        List<String> dongNames = new ArrayList<>(dongNamesSet);

        return dongNames;
    }
    
    // 선택한 동에 대한 값만 출력
    public String getSelectedDongName(String selectedDong) {
        String dongName = null;
        String query = "SELECT city FROM Hospitals WHERE city = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedDong);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    dongName = resultSet.getString("city");
                    System.out.println("사용자가 선택한 동은 : " + dongName); // 콘솔에 동 이름 출력
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dongName;
    }

    
 // 동 이름을 가져오는 메서드
	/*
	 * public List<String> getDongNames(String selectedDong) { // set으로 중복 제거
	 * Set<String> dongNamesSet = new HashSet<>(); String query =
	 * "SELECT city FROM Hospitals WHERE city = ?";
	 * 
	 * try (PreparedStatement statement = connection.prepareStatement(query)) {
	 * statement.setString(1, selectedDong); try (ResultSet resultSet =
	 * statement.executeQuery()) { while (resultSet.next()) { String dongName =
	 * resultSet.getString("city"); dongNamesSet.add(dongName);
	 * System.out.println("사용자가 선택한 동은: " + dongName); // 콘솔에 동 이름 출력 } } } catch
	 * (SQLException e) { e.printStackTrace(); }
	 * 
	 * // Set을 List로 변환 List<String> dongNames = new ArrayList<>(dongNamesSet);
	 * 
	 * return dongNames; }
	 */

 
}
