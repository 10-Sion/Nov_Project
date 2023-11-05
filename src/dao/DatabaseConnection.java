package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {    // DB 연결
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");    

                // 데이터베이스 연결 설정 (characterEncoding을 UTF-8로 설정)
                final String DB_URL = "jdbc:mysql://localhost:3306/ChiGwa?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
                final String DB_USERNAME = "last";
                final String DB_PASSWORD = "1111";

                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {    // DB 연결 종료
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

