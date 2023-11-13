package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DelAccountDAO {
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
	
	//회원탈퇴시키는 메소드
	public int delAccount(String email, String password) {
		
		String dbPass = ""; //DB에 저장된 비밀번호
		int result = -1;
		sql = "select password from users where email=? ";
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dbPass = rs.getString("password");
				
				//비밀번호가 일치한다면 삭제
				if(dbPass.equals(password)) {
					
					pstmt = con.prepareStatement("delete from users where email=?");
					pstmt.setString(1, email);
					pstmt.executeUpdate();
					result = 1; //회원탈퇴 성공
				
				//일치하지 않으면(틀리면) 삭제 안됨
				}else {				
					result = 0;
				}
				
				System.out.println("delAccount메소드 실행 완료");

			}		
			
		} catch (Exception e) {
			System.out.println("delAccount메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		
		
		return result;
	}
	
	
	
}
