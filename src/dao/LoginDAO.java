package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {

	private Connection con;
	private PreparedStatement pstmt;		
	private ResultSet rs;	
	private DataSource ds;
	private String sql;
	
	//커넥션풀 생성 및 커넥션 객체를 얻어 커넥션객체자체를 반환 하는  기능의 메소드 
	private Connection getConnection() throws Exception {
		
		//InitialContext객체 생성
		Context ctx = new InitialContext();
		
		//기본 경로 주소 설정
		Context envContext = (Context) ctx.lookup("java:comp/env");

		//DataSource커넥션풀 객체 찾아 반환
		ds = (DataSource)envContext.lookup("jdbc/ChiGwa");	
		
		//커넥션풀(DataSouce)객체 메모리 에 저장된 Connection객체를 반환받아 사용
		con = ds.getConnection();
		
		return con;
				
	}//생성자 end
	
	
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
	
	//로그인 기능을 하는 메소드
	public int login(String email, String password) {
		
		int check = -1;
		sql = "select email, password from Users where email = ?";
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			//System.out.println("메소드로 오는 email : " + email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//이메일이 존재한다면?
				
				//비밀번호가 존재한다면?
				if(password.equals(rs.getString("password"))) {
					check = 1;
					
				//이메일은 일치하고, 비밀번호는 틀린 경우
				}else {
					check = 0;					
				}
				
			}else {//이메일이 틀림
				check = -1;
			}			
			
			//System.out.println("login메소드 실행 완료");

		} catch (Exception e) {
			System.out.println("login메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		
		return check;
		
	}//login end
	
	public int getUserIDByEmail(String email) {
		
		int user_id = -1;
		sql = "SELECT user_id FROM Users WHERE email = ?";
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
	            // 데이터베이스에서 user_id 값을 검색하여 설정
	            user_id = rs.getInt("user_id");
	        }
			
			//System.out.println("getUserIDByEmail메소드 실행 완료");

		} catch (Exception e) {
			System.out.println("getUserIDByEmail메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		return user_id;
		
	}//getUserIDByEmail end
	
	
	
}
