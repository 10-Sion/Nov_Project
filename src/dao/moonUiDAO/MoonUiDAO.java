package dao.moonUiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.announcementsVO.AnnouncementsVO;
import vo.suggestionsVO.SuggestionsVO;

public class MoonUiDAO {
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
					
					//건의사항 전체글갯수 조회하는 메소드
					public int listCount() {
						int count = 0;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select count(*) from Suggestions";
							pstmt = con.prepareStatement(sql);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								count = rs.getInt(1);
							}
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 listCount메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return count;
					}
					
					//문의 사항 조회하는 메소드
					public List<SuggestionsVO> moonUiList(int startRow, int pageSize) {
						List<SuggestionsVO> list = new ArrayList<SuggestionsVO>();
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select * from Suggestions order by suggestion_id desc limit ?,? ";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setInt(1, startRow);
							pstmt.setInt(2, pageSize);
							
							rs = pstmt.executeQuery();
							
							while (rs.next()) {
								SuggestionsVO vo = new SuggestionsVO();
								vo.setSuggestion_id(rs.getInt("suggestion_id"));
								vo.setPost_name(rs.getString("post_name"));
								vo.setPost_title(rs.getString("post_title"));
								vo.setPost_date(rs.getTimestamp("Post_date"));
								vo.setView_count(rs.getInt("view_count"));
								list.add(vo);
							}
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 moonUiList메소드 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return list;
					}
					
					//건의사항 추가 메소드
					public void addMoonUiList(String post_name, String post_title, String post_content, String post_user_id) {
						try {
							//DB연결
							con = getConnection();
							//sql문 연결
							String sql = "insert into Suggestions(post_name , post_title, post_content, post_user_id, post_date) values(?,?,?,?,now())";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_name);
							pstmt.setString(2, post_title);
							pstmt.setString(3, post_content);
							pstmt.setString(4, post_user_id);
							
							
							
						pstmt.executeUpdate();
							
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 addGonggiList메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					
					
					//건의사항 삭제 메소드
					public int delMoonUiList(String suggestion_id) {
						int check = -1;
						
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "delete from Suggestions where suggestion_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, suggestion_id);
							
							check = pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 delMoonUiList메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return check;
					}
					
					//건의사항 수정 메소드
					public void modifyMoonUiList(String suggestion_id,String post_title, String post_content) {
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "update Suggestions set post_title = ?, post_content = ? where suggestion_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_title);
							pstmt.setString(2, post_content);
							pstmt.setString(3, suggestion_id);
							
							pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 modifyMoonUiList메소드 sql문 오류" + e );
						}finally {
							freeResource();
						}
						
					}
					
					//건의사항에서 제목을 클릭하였을때 조회해올 메소드
					public SuggestionsVO listOne(String suggestion_id) {
						SuggestionsVO vo = null;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select * from Suggestions where suggestion_id = ?";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, suggestion_id);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								vo = new SuggestionsVO();
								vo.setSuggestion_id(rs.getInt("suggestion_id"));
								vo.setPost_content(rs.getString("Post_content"));
								vo.setPost_title(rs.getString("Post_title"));
								vo.setView_count(rs.getInt("view_count"));
							}
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 listOne메소드의 sql문 오류" +e );
						}finally {
							freeResource();
						}
						return vo;
					}
					
					// 조회수 카운트 올리는 메소드
					public void viewCountUpdate(String suggestion_id) {
					
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "update Suggestions set view_count = view_count + 1 where suggestion_id = ? "; 
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, suggestion_id);
							pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 viewCountUpdate메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					
					//건의사항 검색 조회 해오는 메소드
					
					public List<SuggestionsVO> searchList(String searchField, String searchText,int startRow,int pageSize) {
						List<SuggestionsVO> list = new ArrayList<SuggestionsVO>();
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							
							String sql = "select * from Suggestions where " + searchField.trim();
							
							sql += " like '%" + searchText.trim() + "%' limit ?,?;";
							
							pstmt = con.prepareStatement(sql);
							
							
							pstmt.setInt(1, startRow);
							pstmt.setInt(2, pageSize);
							
							rs = pstmt.executeQuery();
							
							while (rs.next()) {
								SuggestionsVO vo = new SuggestionsVO();
								vo.setSuggestion_id(rs.getInt("suggestion_id"));
								vo.setPost_name(rs.getString("post_name"));
								vo.setPost_title(rs.getString("post_title"));
								vo.setPost_date(rs.getTimestamp("Post_date"));
								vo.setView_count(rs.getInt("view_count"));
								list.add(vo);
							}
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 searchList메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
						return list;
					}
					
					//검색버튼을 눌렀을때 
					public int searchListCount(String searchField, String searchText) {
						int check = -1;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select count(*) from Suggestions where " + searchField.trim();
							
							sql += " like '%" + searchText.trim() + "%' ;";
							 
							
							pstmt = con.prepareStatement(sql);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								check = rs.getInt(1);
							}
							
						} catch (Exception e) {
							System.out.println("MoonUiDAO클래스의 searchListCount메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return check;
					}

				
}
