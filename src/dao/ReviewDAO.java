package dao;

import java.sql.Connection;
import dao.DatabaseConnection;


public class ReviewDAO {
	private Connection connection;
	
	public ReviewDAO() {
		this.connection = DatabaseConnection.getConnection();
	}
	
	// 아래 메서드 추가
}
