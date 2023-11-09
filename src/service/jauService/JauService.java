package service.jauService;

import java.util.List;

import dao.jauDAO.JauDAO;
import vo.commentsVO.CommentsVO;
import vo.postsVO.PostsVO;



public class JauService {
private JauDAO dao;
	
	public JauService() {
		dao = new JauDAO();
	}
	//단위기능1. 자유 게시판 조회
	public List<PostsVO> serviceJauList(int startRow, int pageSize) {
		return dao.jauList(startRow,pageSize);
	}
	//단위기능2. 자유 게시판 추가
	public void addJauList(String post_name, String post_title, String post_content, String post_user_id) {
		dao.addJauList(post_name,post_title,post_content,post_user_id);
	}
	//단위기능3. 자유 게시판 삭제
	public void delJauList(String suggestion_id) {
		dao.delJauList(suggestion_id);
		
	}
	//단위기능4. 자유 게시판 수정
	public void modifyJauList(String post_id, String post_title, String post_content) {
		dao.modifyJauList(post_id, post_title, post_content);
		
	}
	//단위기능5. 자유게시판 전체글 개수 조회
	public int serviceListCount() {
		return dao.listCount();
		
	}
	//단위기능6. 자유게시판 클릭한 글 한개 조회
	public PostsVO serviceListOne(String post_id) {
		return dao.listOne(post_id);
	}
	
	//단위기능 7. 조회수 증가 
	public void serviceViewCountUpdate(String suggestion_id) {
		 dao.viewCountUpdate(suggestion_id);
	}
	
	//단위기능 8. 게시판 검색기능
	public List<PostsVO> serviceSearchList(String searchField, String searchText,int startRow,int pageSize) {
		return dao.searchList(searchField,searchText,startRow,pageSize);
		
	}
	//단위기능9. 게시판 검색한 글의 전체 갯수
	public int serviceSearchListCount(String searchField, String searchText) {
		return dao.searchListCount(searchField,searchText);
	}
	//단위기능 10. 자유게시판 댓글추가 기능
	public void serviceAddCommets(String user_id, String post_id, String comment_text,String user_name) {
		dao.addComments(user_id,post_id,comment_text,user_name);
	}
	
	//단위기능 11. 댓글 조회 기능
	public List<CommentsVO> serviceSearchComments(String post_id, int startRow, int pageSize) {
		return dao.searchComments(post_id,startRow,pageSize);
	}
	//단위기능 12. 댓글 총갯수 가져오기
	public int serviceCommentCount(String post_id) {
		return dao.commentCount(post_id);
	}
	//단위기능 13. 자유게시판 글 삭제 할때 댓글까지 삭제하기
		public void servicedelAllComments(String post_id) {
			dao.delAllComments(post_id);
		}
	//단위기능 14. 댓글 작성자 이름 조회하기
	public String serviceSearchUserId(String user_id) {
		return dao.searchUserId(user_id);
	}
	//단위기능 15. 댓글 작성자 정보 얻기
	public CommentsVO serviceCommentInfo(String comment_id) {
		return dao.commentInfo(comment_id);
	}
	//단위기능16. 댓글 수정기능
	public int serviceModifyComment(String comment_id,String comment_text) {
		return dao.modifyComment(comment_id,comment_text);
		
	}
	//단위기능17. 댓글 삭제기능
	public int serviceDelComment(String comment_id) {
		return dao.delComment(comment_id);
	}
	//단위기능 18. 자유게시판 추천기능
	public void serviceCountUpjauGood(String post_id) {
	dao.countUpjauGood(post_id);
	
	}
	//단위기능 19. 자유게시판 비추천 기능
	public void serviceCountUpJauBad(String post_id) {
	dao.countUpJauBad(post_id);
	
}
	
	
	
	}
	
	
	
	
	

