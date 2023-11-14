package dao.jauDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import vo.commentsVO.CommentsVO;
import vo.postsVO.PostsVO;


public class JauDAO {
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
					
					//자유게시판 전체글갯수 조회하는 메소드
					public int listCount() {
						int count = 0;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select count(*) from Posts";
							pstmt = con.prepareStatement(sql);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								count = rs.getInt(1);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 listCount메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return count;
					}
					
					//자유게시판 조회하는 메소드
					public List<PostsVO> jauList(int startRow, int pageSize) {
						List<PostsVO> list = new ArrayList<PostsVO>();
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select * from Posts order by post_id desc limit ?,? ";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setInt(1, startRow);
							pstmt.setInt(2, pageSize);
							
							rs = pstmt.executeQuery();
							
							while (rs.next()) {
								PostsVO vo = new PostsVO();
								vo.setPost_id(rs.getInt("post_id"));
								vo.setPost_name(rs.getString("post_name"));
								vo.setPost_title(rs.getString("post_title"));
								vo.setPost_date(rs.getTimestamp("Post_date"));
								vo.setView_count(rs.getInt("view_count"));
								vo.setGood(rs.getInt("good"));
								vo.setBad(rs.getInt("bad"));
								list.add(vo);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 jauList메소드 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return list;
					}
					
					//자유게시판 추가 메소드
					public void addJauList(String post_name, String post_title, String post_content, String post_user_id) {
						try {
							//DB연결
							con = getConnection();
							//sql문 연결
							String sql = "insert into Posts(post_name , post_title, post_content, post_user_id, post_date) values(?,?,?,?,now())";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_name);
							pstmt.setString(2, post_title);
							pstmt.setString(3, post_content);
							pstmt.setString(4, post_user_id);
							
							
							
						pstmt.executeUpdate();
							
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 addJauList메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					
					
					//자유게시판 삭제 메소드
					public int delJauList(String suggestion_id) {
						int check = -1;
						
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "delete from Posts where post_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, suggestion_id);
							
							check = pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 delJauList메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return check;
					}
					
					//자유게시판 수정 메소드
					public void modifyJauList(String post_id,String post_title, String post_content) {
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "update posts set post_title = ?, post_content = ? where post_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_title);
							pstmt.setString(2, post_content);
							pstmt.setString(3, post_id);
							
							pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 modifyMoonUiList메소드 sql문 오류" + e );
						}finally {
							freeResource();
						}
						
					}
					
					//자유게시판에서 제목을 클릭하였을때 조회해올 메소드
					public PostsVO listOne(String post_id) {
						PostsVO vo = null;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select * from posts where post_id = ?";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_id);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								vo = new PostsVO();
								vo.setPost_id(rs.getInt("post_id"));
								vo.setPost_user_id(rs.getInt("post_user_id"));
								vo.setPost_name(rs.getString("post_name"));
								vo.setPost_content(rs.getString("Post_content"));
								vo.setPost_title(rs.getString("Post_title"));
								vo.setView_count(rs.getInt("view_count"));
								vo.setGood(rs.getInt("good"));
								vo.setBad(rs.getInt("bad"));
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 listOne메소드의 sql문 오류" +e );
						}finally {
							freeResource();
						}
						return vo;
					}
					
					// 조회수 카운트 올리는 메소드
					public void viewCountUpdate(String post_id) {
					
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "update posts set view_count = view_count + 1 where post_id = ? "; 
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, post_id);
							pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 viewCountUpdate메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					
					//자유게시판 검색 조회 해오는 메소드
					
					public List<PostsVO> searchList(String searchField, String searchText,int startRow,int pageSize) {
						List<PostsVO> list = new ArrayList<PostsVO>();
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							
							String sql = "select * from posts where " + searchField.trim();
							
							sql += " like '%" + searchText.trim() + "%' limit ?,?;";
							
							pstmt = con.prepareStatement(sql);
							
							
							pstmt.setInt(1, startRow);
							pstmt.setInt(2, pageSize);
							
							rs = pstmt.executeQuery();
							
							while (rs.next()) {
								PostsVO vo = new PostsVO();
								vo.setPost_id(rs.getInt("post_id"));
								vo.setPost_name(rs.getString("post_name"));
								vo.setPost_title(rs.getString("post_title"));
								vo.setPost_date(rs.getTimestamp("Post_date"));
								vo.setView_count(rs.getInt("view_count"));
								list.add(vo);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 searchList메소드의 sql문 오류" + e);
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
							String sql = "select count(*) from posts where " + searchField.trim();
							
							sql += " like '%" + searchText.trim() + "%' ;";
							
							
							pstmt = con.prepareStatement(sql);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								check = rs.getInt(1);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 searchListCount메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return check;
					}
					//댓글 작성 메소드
					public void addComments(String user_id, String post_id, String comment_text,String user_name) {
						
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "insert into Comments (user_name,user_id, post_id, comment_text, comment_date) "
									+ " values(?,?,?,?,now())";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, user_name);
							pstmt.setString(2, user_id);
							pstmt.setString(3, post_id);
							pstmt.setString(4, comment_text);
							
							pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 addComments메소드의 sql문 오류 " + e);
						}finally {
							freeResource();
						}
				
					}
					
					//댓글 조회하는 메소드
					public List<CommentsVO> searchComments(String post_id, int startRow, int pageSize) {
						List<CommentsVO> list = new ArrayList<CommentsVO>();
						try {
							//DB연결
							con = getConnection();
							//sql문작성
							String sql = "WITH RECURSIVE CommentCTE AS (\n" + 
									"  SELECT\n" + 
									"    user_id,\n" +
									"    comment_id,\n" + 
									"    user_name,\n" + 
									"    comment_text,\n" + 
									"    comment_date,\n" + 
									"    level,\n" + 
									"    parent_id,\n" + 
									"    CAST(comment_id AS CHAR(200)) AS path\n" + 
									"  FROM Comments\n" + 
									"  WHERE parent_id is null AND post_id = ? \n" + 
									"\n" + 
									"  UNION ALL\n" + 
									"\n" + 
									"  SELECT\n" +
									"    c.user_id,\n" +
									"    c.comment_id,\n" + 
									"    c.user_name,\n" + 
									"    c.comment_text,\n" + 
									"    c.comment_date,\n" + 
									"    c.level,\n" + 
									"    c.parent_id,\n" + 
									"    CONCAT(cte.path, '-', c.comment_id)\n" + 
									"  FROM Comments c\n" + 
									"  INNER JOIN CommentCTE cte ON c.parent_id = cte.comment_id\n" + 
									")\n" + 
									"SELECT * FROM CommentCTE ORDER BY path limit ?,? ";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_id);
							pstmt.setInt(2, startRow);
							pstmt.setInt(3, pageSize);
							
							rs = pstmt.executeQuery();
							
							while (rs.next()) {
								CommentsVO vo = new CommentsVO();
								vo.setComment_text(rs.getString("Comment_text"));
								vo.setComment_date(rs.getTimestamp("Comment_date"));
								vo.setUser_name(rs.getString("user_name"));
								vo.setUser_id(rs.getInt("user_id"));
								vo.setComment_id(rs.getInt("comment_id"));
								vo.setParent_id(rs.getInt("parent_id"));
								vo.setLevel(rs.getInt("level"));
								list.add(vo);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 searchComments메소드의 sql문 오류 " + e);
						}finally {
							freeResource();
						}
						return list;
					}
					//댓글 갯수 알아보는 메소드
					public int commentCount(String post_id) {
						int count = -1;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select count(*) from Comments where post_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_id);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								count = rs.getInt(1);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 comment메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return count;
					}

					public String searchUserId(String user_id) {
						String user_name = null;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select userName from users where user_id = ?";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, user_id);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								user_name = rs.getString(1);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 searchUserId의 메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return user_name;
					}
					//댓글 작성자에 대한 정보 조회하는 메소드
					public CommentsVO commentInfo(String commnet_id) {
						CommentsVO vo = new CommentsVO();
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select * from Comments where comment_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, commnet_id);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								vo.setUser_id(rs.getInt("user_id"));
								vo.setComment_id(rs.getInt("comment_id"));
								vo.setComment_date(rs.getTimestamp("comment_date"));
								vo.setComment_text(rs.getString("comment_text"));
								vo.setUser_name(rs.getString("user_name"));
							}
						} catch (Exception e) {
							System.out.println("jauDAO클래스의 commentInfo메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
						return vo;
					}
					
					//댓글 수정하는 메소드
					public int modifyComment(String comment_id,String comment_text) {
						int check = -1;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "update Comments set comment_text = ? where comment_id = ? ";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, comment_text);
							pstmt.setString(2, comment_id);
							
							check = pstmt.executeUpdate();
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 modifyComment메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return check;
					}
					
					//댓글 삭제하는 메소드
					public int delComment(String comment_id) {
						int check = -1;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "delete from comments where comment_id = ?";
							
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, comment_id);
							pstmt.setString(2, comment_id);
							check = pstmt.executeUpdate();
									
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 delComment메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return check;
					}

					//자유게시판 글을 삭제할때 거기 포함되어 있는 댓글까지 다 삭제하기
					public void delAllComments(String post_id) {
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "delete from comments where post_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_id);
							
							pstmt.executeUpdate();
						} catch (Exception e) {
							System.out.println("jauDAO클래스의 delAllCommnets메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					
					//자유게시판 추천 기능
					public void countUpjauGood(String post_id) {
						try {
							//DB연결
							con = getConnection();
							//SQL문 작성
							String sql = "update posts set good = good + 1 where post_id = ? ";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_id);
							
							pstmt.executeUpdate();
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 countUpjauGood메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					//자유게시판 비추천 기능
					public void countUpJauBad(String post_id) {
						try {
							//DB연결
							con = getConnection();
							//SQL문 작성
							String sql = "update posts set bad = bad + 1 where post_id = ? ";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, post_id);
							
							pstmt.executeUpdate();
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 countUpjauGood메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
					//level값 얻는 메소드
					public int searchCommentLevel(String parent_id) {
						int level = 0;
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "select count(*)-1 as count from comments where comment_id = ? or parent_id = ?";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, parent_id);
							pstmt.setString(2, parent_id);
							
							rs = pstmt.executeQuery();
							
							if (rs.next()) {
								level = rs.getInt(1);
							}
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 searchCommentLevel메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						return level;
					}
					//답글추가기능역할을 하는 메소드	
					public void addReplyComments(String user_name, String user_id, String post_id, String comment_text,
							int level, String parent_id) {
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "INSERT INTO Comments (user_name, user_id, post_id, comment_text, comment_date, level, parent_id) \n" + 
									"VALUES (?, ?, ?, ?, now(), ? + 1 , ?)";
							
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, user_name);
							pstmt.setString(2, user_id);
							pstmt.setString(3, post_id);
							pstmt.setString(4, comment_text);
							pstmt.setInt(5, level);
							pstmt.setString(6, parent_id);
							
							pstmt.executeUpdate();
							
							
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 addReplyComments메소드의 sql문  오류" + e);
						}finally {
							freeResource();
						}
						
					}
					//답글 삭제시키는 메소드
					public void delReply(String comment_id) {
						try {
							//DB연결
							con = getConnection();
							//sql문 작성
							String sql = "delete from comments where parent_id = ?";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, comment_id);
							
							pstmt.executeUpdate();
						} catch (Exception e) {
							System.out.println("JauDAO클래스의 delReply메소드의 sql문 오류" + e);
						}finally {
							freeResource();
						}
						
					}
}
