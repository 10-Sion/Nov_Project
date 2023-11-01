package service.jauService;

import java.util.List;

import dao.jauDAO.JauDAO;
import vo.postsVO.PostsVO;



public class JauService {
private JauDAO dao;
	
	public JauService() {
		dao = new JauDAO();
	}
	//단위기능1. 문의사항 게시판 조회
	public List<PostsVO> serviceJauList(int startRow, int pageSize) {
		return dao.jauList(startRow,pageSize);
	}
	//단위기능2. 문의사항 게시판 추가
	public void addJauList(String post_name, String post_title, String post_content, String post_user_id) {
		dao.addJauList(post_name,post_title,post_content,post_user_id);
	}
	//단위기능3. 문의사항 게시판 삭제
	public void delJauList(String suggestion_id) {
		dao.delJauList(suggestion_id);
		
	}
	//단위기능4. 문의사항 게시판 수정
	public void modifyJauList(String post_id, String post_title, String post_content) {
		dao.modifyJauList(post_id, post_title, post_content);
		
	}
	//단위기능5. 문의사항 전체글 개수 조회
	public int serviceListCount() {
		return dao.listCount();
		
	}
	//단위기능6. 공지사항 클릭한 글 한개 조회
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
	
	
	
	
}
