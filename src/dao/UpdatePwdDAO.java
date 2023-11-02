package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.UsersVO;

public class UpdatePwdDAO {
	
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
	
	//비밀번호 변경하는 메소드
	public void updatePwd (String email, String newPwd) {
		
		sql = "UPDATE Users SET password = ? WHERE email = ? "; 
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, email);
			
			pstmt.executeUpdate();			
			
			System.out.println("updatePwd메소드 실행 완료");

		} catch (Exception e) {
			System.out.println("updatePwd메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		
	}//updatePwd end
}
