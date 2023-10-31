package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import vo.UsersVO;

public class JoinDAO {

	private Connection con;
	private PreparedStatement pstmt;		
	private ResultSet rs;		
	private String sql;
	
	//DB연결
	public JoinDAO() {
		this.con = DatabaseConnection.getConnection();
	    
	    if (con != null) {
	        System.out.println("연결 성공."); // 연결 확인 메시지 출력
	    } else {
	        System.out.println("연결 실패."); // 연결 실패 메시지 출력
	    }
	}
	
	//DB연결 후 작업하는 객체들 사용한 뒤에 자원해제 할 때 공통으로 쓰이는 메소드
	public void freeResource() {
		try {
			
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//자원해제 end
	
	//회원가입 메소드
	public void insertUsers(UsersVO vo) {
		
		sql = "insert into Users(user_id, username, email, password, grade_id, count) " + 
			  "values(?, ?, ?, ?, 2, 0)" ;
		
		try {			
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getUser_id());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
	
			pstmt.executeUpdate();
			
			System.out.println("insertUsers메소드 실행 완료");

		} catch (Exception e) {
			System.out.println("insertUsers메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		
	}//insertUsers end
	
}
