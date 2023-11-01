package service.moonUiService;

import java.util.List;

import dao.moonUiDAO.MoonUiDAO;
import vo.announcementsVO.AnnouncementsVO;
import vo.suggestionsVO.SuggestionsVO;

public class MoonUiService {
private MoonUiDAO dao;
	
	public MoonUiService() {
		dao = new MoonUiDAO();
	}
	//단위기능1. 문의사항 게시판 조회
	public List<SuggestionsVO> serviceMoonUiList(int startRow, int pageSize) {
		return dao.moonUiList(startRow,pageSize);
	}
	//단위기능2. 문의사항 게시판 추가
	public void addMoonUiList(String post_name, String post_title, String post_content, String post_user_id) {
		dao.addMoonUiList(post_name,post_title,post_content,post_user_id);
	}
	//단위기능3. 문의사항 게시판 삭제
	public void delGongiList(String suggestion_id) {
		dao.delMoonUiList(suggestion_id);
		
	}
	//단위기능4. 문의사항 게시판 수정
	public void modifyMoonUiList(String suggestion_id, String post_title, String post_content) {
		dao.modifyMoonUiList(suggestion_id, post_title, post_content);
		
	}
	//단위기능5. 문의사항 전체글 개수 조회
	public int serviceListCount() {
		return dao.listCount();
		
	}
	//단위기능6. 공지사항 클릭한 글 한개 조회
	public SuggestionsVO serviceListOne(String suggestion_id) {
		return dao.listOne(suggestion_id);
	}
	
	//단위기능 7. 조회수 증가 
	public void serviceViewCountUpdate(String suggestion_id) {
		 dao.viewCountUpdate(suggestion_id);
	}
	
	//단위기능 8. 게시판 검색기능
	public List<SuggestionsVO> serviceSearchList(String searchField, String searchText,int startRow,int pageSize) {
		return dao.searchList(searchField,searchText,startRow,pageSize);
		
	}
	//단위기능9. 게시판 검색한 글의 전체 갯수
	public int serviceSearchListCount(String searchField, String searchText) {
		return dao.searchListCount(searchField,searchText);
	}
	
	
	
	
}
