package dao.reviewCheckDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.reviewCheckVO.ReviewCheckVO;

public class ReviewCheckDAO {
	//데이터베이스 작업관련 객체들을 저장할 변수들
	DataSource ds;//커넥션풀 역할을 하는 DataSouce객체의 주소를 저장할 변수 
	Connection con; //커넥션풀에 미리 만들어 놓고 DB와의 접속이 필요하면 빌려와서 사용할 DB접속정보를 가지고 있는 Connection객체의 주소를 저장할 변수 
	PreparedStatement pstmt;//생성한 SQL문을 DB에 전송해서 실행할 역할을하는 PreparedStatement실행객체의 주소를 저장할 변수 
	ResultSet rs;//DB의 테이블에 저장된 정보를 조회한 결과를 임시로 얻기 위한 ResultSet객체 메모리의 주소를 저장할 변수 
	
	//커넥션풀 생성 및 커넥션 객체를 얻어 커넥션객체자체를 반환 하는  기능의 메소드 
	private Connection getConnection() throws Exception {
		
		//1. InitialContext객체 생성
		//생성하는 이유는  자바의 네이밍 서비스(JNDI)에서 이름과 실제 객체를 연결해주는 개념이 Context이며,
		//InitialContext객체는 네이밍 서비스를 이용하기위한 시작점입니다.
		Context initCtx = new InitialContext();
		//2. "java:comp/env"라는 주소를 전달하여  Context객체를 얻었습니다.
		//"java:comp/env" 주소는 현재 웹 애플리케이션의 루트 디렉터리 라고 생각 하면됩니다.
		//즉! 현재 웹애플리케이션이 사용할수 있는 모든 자원은 "java:comp/env"아래에 위치합니다.(<Context></Context/>이위치를 말합니다.)
		Context ctx = (Context)initCtx.lookup("java:comp/env");
		//3. "java:comp/env 경로 아래에 위치한  "jdbc/studyplannerdb" Recource태그의  DataSource커넥션풀을 얻는다
		ds = (DataSource)ctx.lookup("jdbc/ChiGwa");		 
		//4. 마지막으로 커넥션풀(DataSouce)객체 메모리 에 저장된 Connection객체를 반환받아 사용
		con = ds.getConnection();
		return con;
	}
	
	//DB작업관련 객체 메모리들 자원해제 하는 메소드 
			public void freeResource() {	
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(rs != null) {
						rs.close();
					}
					if(con != null) {
						con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//관리자 리뷰 확인 화면에서 조회해서 보여 주는 기능
			public List<ReviewCheckVO> searchReviewCheck(int startRow,int pageSize) {
				List<ReviewCheckVO> list = new ArrayList<ReviewCheckVO>();
				try {
					//DB연결
					con = getConnection();
					//sql문 작성
					String sql = "SELECT\r\n" + 
							"	reviews.user_id,\r\n" + 
							"	reviews.review_id,\r\n" +
							"	reviews.review_text,\r\n" + 
							"    reviews.rating,\r\n" + 
							"    reviews.verification,\r\n" + 
							"	users.username,\r\n" + 
							"    users.email,\r\n" + 
							"	hospitals.name,\r\n" + 
							"	receipt.receipt_image\r\n" + 
							"FROM\r\n" + 
							"    reviews\r\n" + 
							"INNER JOIN\r\n" + 
							"    users\r\n" + 
							"    ON reviews.user_id = users.user_id\r\n" + 
							"INNER JOIN\r\n" + 
							"    hospitals\r\n" + 
							"    ON reviews.hospital_id = hospitals.id\r\n" + 
							"INNER JOIN\r\n" + 
							"    receipt\r\n" + 
							"    ON reviews.review_id = receipt.receipt_id\r\n" + 
							"WHERE\r\n" + 
							"    reviews.verification = '미인증' limit ?,?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, pageSize);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						ReviewCheckVO vo = new ReviewCheckVO();
						vo.setUser_id(rs.getInt("user_id"));
						vo.setReview_id(rs.getInt("review_id"));
						vo.setReview_text(rs.getString("review_text"));
						vo.setRating(rs.getDouble("rating"));
						vo.setVerification(rs.getString("verification"));
						vo.setUsername(rs.getString("userName"));
						vo.setEmail(rs.getString("email"));
						vo.setName(rs.getString("name"));
						vo.setReceipt_image(rs.getString("receipt_image"));
						list.add(vo);
					}
					
				} catch (Exception e) {
					System.out.println("ReviewCheckDAO클래스의 searchReviewCheck메소드의 sql문 오류");
				}finally {
					freeResource();
				}
				return list;
			}
			//전체 리뷰대기글갯수 조회
			public int countReviewCheck() {
				int check = -1;
				try {
					//DB연결
					con = getConnection();
					//sql문작성
					String sql = "SELECT\r\n" + 
							"	count(*)\r\n" + 
							"FROM\r\n" + 
							"    reviews\r\n" + 
							"INNER JOIN\r\n" + 
							"    users\r\n" + 
							"    ON reviews.user_id = users.user_id\r\n" + 
							"INNER JOIN\r\n" + 
							"    hospitals\r\n" + 
							"    ON reviews.hospital_id = hospitals.id\r\n" + 
							"INNER JOIN\r\n" + 
							"    receipt\r\n" + 
							"    ON reviews.review_id = receipt.receipt_id\r\n" + 
							"WHERE\r\n" + 
							"    reviews.verification = '미인증' ";
					pstmt = con.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						check = rs.getInt(1);
					}
				} catch (Exception e) {
					System.out.println("ReviewCheckDAO클래스의 countReviewCheck메소드의 sql문 오류" + e);
				}finally {
					freeResource();
				}
				return check;
			}
			
			//리뷰확인 화면에서 리뷰승인하는 메소드
			public void reviewCheckOk(String review_id) {
				try {
					//DB연결
					con = getConnection();
					//sql문 작성
					String sql = "update reviews set verification = '인증완료' where review_id = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, review_id);
					
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("ReviewCheckDAO클래스에서 reviewCheckOk메소드 sql문 오류 " + e);
				}finally {
					freeResource();
				}
				
			}
			//리뷰테이블 삭제 기능
			public void delReviewCheck(String review_id) {
				try {
					//DB연결
					con = getConnection();
					//sql문 작성
					String sql = "delete from reviews where review_id = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, review_id);
					
					pstmt.executeUpdate();
				} catch (Exception e) {
					System.out.println("ReviewCheckDAO클래스에서 reviewCheckOk메소드 sql문 오류" + e);
				}finally {
					freeResource();
				}
				
			}
			
			//영수증 테이블 삭제 기능
			public void delReceipt(String review_id) {
				try {
					//DB연결
					con = getConnection();
					//sql문작성
					String sql = "delete from receipt where receipt_id = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, review_id);
					
					pstmt.executeUpdate();
				} catch (Exception e) {
					System.out.println("ReviewCheckDAO클래스의 delReceipt메소드의 sql문 오류" + e);
				}finally {
					freeResource();
				}
				
			}
			
			
}
