package service.memberListService;

import java.util.List;

import dao.memberListDAO.MemberListDAO;
import vo.memberListVO.MemberListVO;

public class MemberListService {
	
	MemberListDAO dao;
	
	public MemberListService() {
		dao = new MemberListDAO();
	}
	//단위기능1. 가입자 조회
	public List<MemberListVO> serviceSearchMemberList(int startRow, int pageSize) {
		
		return dao.searchMemberList(startRow,pageSize);
	}
	//단위기능2. 가입자 등급수정
	public void serviceUpdateMemberList(String user_id,String grade_Name) {
		dao.updateMemberList(user_id,grade_Name);
	}
	//단위기능3. 가입자 삭제기능
	public void serviceDelMemberList(String user_id) {
		dao.delMemberList(user_id);
	}
	//단위기능4. 가입자 탈퇴시 작성한게시판의 모든 글의 이름 수정
	public void serviceWithdrawalBoard(String user_id) {
		dao.withdrawalSuggestionsBoard(user_id);
		dao.withdrawalReceiptBoard(user_id);
		dao.withdrawalReviewBoard(user_id);
		dao.withdrawalCommentsBoard(user_id);
		dao.withdrawalPostsBoard(user_id);
	}
	//단위기능5. 운영자를 제외한 가입자들의 총 수
	public int serviceCountMemberList() {
		return dao.countMemberList();
	}
	


}
