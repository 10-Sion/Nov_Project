package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {

	//DB작업에 쓰일 객체들을 저장할 변수들
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public LoginDAO() {
		
		try {
			
			//InitialContext객체 생성
			Context ctx = new InitialContext();
			
			//기본 경로 주소 설정
			Context envContext = (Context) ctx.lookup("java:comp/env");

			//DataSource커넥션풀 객체 찾아 반환
			ds = (DataSource) envContext.lookup("jdbc/ChiGwa");
			
		} catch (Exception e) {
			System.out.println("LoginDAO의 생성자 내부에서 커넥션풀 얻기 실패 : " + e.toString());
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
	
	//일반, 관리자 계정 구분해서 로그인하는 메소드
	public int login(String user_id, String password) {
		int check = -1;
		String userRole = null;
		
		try {
			
			//DB연결
			con = ds.getConnection();
			
			//sql문 작성
	        String sql = "select username, password, user_role, user_id from Users where user_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				userRole = rs.getString("user_role"); // 사용자 역할을 가져옴

	            if (password.equals(rs.getString("password"))) {
	                if ("일반회원".equals(userRole)) {
	                    check = 1; // 일반 회원 로그인 성공
	                } else if ("관리자".equals(userRole)) {
	                    check = 2; // 관리자 로그인 성공
	                }
	            } else {
	                check = 0; // 아이디는 맞고, 비밀번호 틀림
	            }
	        } else {
	            check = -1; // 아이디가 틀림
	        }
	    } catch (Exception e) {
	        System.out.println("iLogin 메소드 SQL 문 오류 발생: " + e);
	    } finally {
	        freeResource();
	    }
		    
		return check;
	}
	
	

	
	
	
	
}
