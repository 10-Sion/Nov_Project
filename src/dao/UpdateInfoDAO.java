package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.UsersVO;

public class UpdateInfoDAO {
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
	
	//개인정보를 수정하기 위해 기존 정보를 불러오는 메소드
	public UsersVO getUsersById(int user_id) {
		
		sql = "select * from users where user_id = ?";
		UsersVO vo = new UsersVO();
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				vo = new UsersVO();
				
				vo.setEmail(rs.getString("email"));
				vo.setPassword(rs.getString("password"));
				vo.setUsername(rs.getString("username"));
			}
			
			System.out.println("getUsersById메소드 실행 완료");

		} catch (Exception e) {
			System.out.println("getUsersById메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		
		return vo;
		
	}//getUsersById end
	
	
	//개인정보 수정하는 메소드
	public void updateInfo (String email, String password, String username, int user_id) {
		
		sql = "UPDATE Users SET email = ?, password = ?, username = ? WHERE user_id = ? "; 
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, username);
			pstmt.setInt(4, user_id);
					
			
			pstmt.executeUpdate();			
			
			System.out.println("updateInfo메소드 실행 완료");

		} catch (Exception e) {
			System.out.println("updateInfo메소드 예외발생 : " + e);
		} finally {
			freeResource();
		}
		
	}//updateInfo end
		
	//중복 이메일 확인 메소드
    public boolean isEmailAvailable(String email, int user_id) {
        boolean available = true;
        try {
            getConnection();
            String sql = "SELECT COUNT(*) FROM Users WHERE email = ? AND user_id <> ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2, user_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                available = (count == 0); // count가 0이면 이메일이 중복되지 않음
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            freeResource();
        }
        return available;
    }
}
