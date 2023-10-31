package service.moonUiService;

import java.util.List;

import dao.moonUiDAO.MoonUiDAO;
import vo.announcementsVO.AnnouncementsVO;

public class MoonUiService {
private MoonUiDAO dao;
	
	public MoonUiService() {
		dao = new MoonUiDAO();
	}
	//단위기능1. 공지사항 게시판 조회
//	public List<AnnouncementsVO> serviceGongiList(int startRow,int pageSize) {
//		return dao.gongiList(startRow,pageSize);
//	}
	//단위기능2. 공지사항 게시판 추가
	public void addGongiList(String post_title, String post_content) {
		dao.addGongiList(post_title,post_content);
	}
	//단위기능3. 공지사항 게시판 삭제
	public void delGongiList(String announcement_id) {
		dao.delGongiList(announcement_id);
		
	}
	//단위기능4. 공지사항 게시판 수정
	public void modifyGongiList(String announcement_id, String post_title, String post_content) {
		dao.modifyGongiList(announcement_id, post_title, post_content);
		
	}
	//단위기능5. 공지사항 전체글 개수 조회
	public int serviceListCount() {
		return dao.listCount();
		
	}
	//단위기능6. 공지사항 클릭한 글 한개 조회
	public AnnouncementsVO serviceListOne(String announcement_id) {
		return dao.listOne(announcement_id);
	}
	
	//단위기능 7. 조회수 증가 
	public void serviceViewCountUpdate(String announcement_id) {
		 dao.viewCountUpdate(announcement_id);
	}
}
