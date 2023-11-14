package dao.memberListDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import vo.memberListVO.MemberListVO;

public class MemberListDAO {
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
			
			
		public List<MemberListVO> searchMemberList(int startRow,int pageSize) {
			List<MemberListVO> list = new ArrayList<MemberListVO>();
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "SELECT\r\n" +
						"  u.grade_id,\r\n" + 
						"  u.user_id,\r\n" + 
						"  u.username,\r\n" + 
						"  u.email,\r\n" + 
						"  u.password,\r\n" + 
						"  u.count,\r\n" + 
						"  g.grade_name,\r\n" + 
						"  COUNT(DISTINCT p.post_id) AS post_count,\r\n" + 
						"  COUNT(DISTINCT r.review_id) AS review_count,\r\n" + 
						"  COUNT(DISTINCT c.comment_id) AS comments_count\r\n" + 
						"FROM\r\n" + 
						"  Users AS u\r\n" + 
						"INNER JOIN\r\n" + 
						"  grades AS g\r\n" + 
						"ON g.grade_id = u.grade_id\r\n" + 
						"LEFT JOIN\r\n" + 
						"  posts AS p\r\n" + 
						"ON p.post_user_id = u.user_id\r\n" + 
						"LEFT JOIN\r\n" + 
						"  reviews AS r\r\n" + 
						"ON r.user_id = u.user_id\r\n" + 
						"LEFT JOIN\r\n" + 
						"  comments AS c\r\n" + 
						"ON c.user_id = u.user_id\r\n" + 
						"WHERE\r\n" + 
						"  g.grade_name != '농부'\r\n" + 
						"GROUP BY\r\n" + 
						"  u.user_id limit ? , ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberListVO vo = new MemberListVO();
					vo.setGrade_id(rs.getInt("grade_id"));
					vo.setUser_id(rs.getInt("user_id"));
					vo.setUsername(rs.getString("userName"));
					vo.setEmail(rs.getString("email"));
					vo.setCount(rs.getInt("count"));
					vo.setGrade_name(rs.getString("grade_name"));
					vo.setPost_count(rs.getInt("post_count"));
					vo.setReview_count(rs.getInt("review_count"));
					vo.setComments_count(rs.getInt("comments_count"));
					list.add(vo);
				}
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 searchMemberList메소드의 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
			return list;
		}
		//가입자 등급수정하는 메소드
		public void updateMemberList(String user_id,String grade_Name) {
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "update users set grade_id = ? where user_id = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, grade_Name);
				pstmt.setString(2, user_id);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 updateMemberList메소드의 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
		}

		public void delMemberList(String user_id) {
			try {
				//DB연결
				con = getConnection();
				//sql문작성
				String sql = "delete from users where user_id =  ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user_id);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 delMemberList메소드의 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
		}
		// 작성자의 리뷰글 삭제
		public void withdrawalReviewBoard(String user_id) {
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "delete from reviews where user_id = ? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user_id);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 withdrawalReviewBoard메소드의 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
		}
		//작성자의 건의사항글 삭제
		public void withdrawalSuggestionsBoard(String user_id) {
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "delete from Suggestions where post_user_id = ? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user_id);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 withdrawalSuggestionsBoard메소드 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
		}
		//작성자의 영수증글 삭제
		public void withdrawalReceiptBoard(String user_id) {
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "delete from receipt where user_id = ? ";
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 withdrawalReceiptBoard메소드 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
		}

		public void withdrawalCommentsBoard(String user_id) {
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "delete from Comments where user_id = ? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user_id);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 withdrawalCommentsBoard메소드 sql문 오류" +e);
			}finally {
				freeResource();
			}
			
		}
		//작성자의 탈퇴시 자유게시판 삭제 기능
		public void withdrawalPostsBoard(String user_id) {
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "delete from posts where post_user_id = ? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user_id);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 withdrawalPostsBoard메소드의 sql문 오류" + e);
			}finally {
				freeResource();
			}
			
		}
		//운영자를 제외한 가입자의 총수를 알아보는 메소드
		public int countMemberList() {
			int check = -1;
			try {
				//DB연결
				con = getConnection();
				//sql문 작성
				String sql = "select count(*) from users where grade_id != 4";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					check = rs.getInt(1);
				}
				
			} catch (Exception e) {
				System.out.println("MemberListDAO클래스의 countMemberList메소드의 sql문 오류" +e);
			}finally {
				freeResource();
			}
			return check;
		}
		

}
