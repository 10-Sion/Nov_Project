package service.reviewCheckService;

import java.util.List;

import dao.reviewCheckDAO.ReviewCheckDAO;
import vo.reviewCheckVO.ReviewCheckVO;

public class ReviewCheckService {
private ReviewCheckDAO dao;
	
	public ReviewCheckService() {
		dao = new ReviewCheckDAO();
	}
	//단위기능 1. 리뷰 확인화면에 리뷰대기글 조회
	public List<ReviewCheckVO> serviceSearchReviewCheck(int startRow, int pageSize) {
		return dao.searchReviewCheck(startRow,pageSize);
	}
	//단위기능 2. 리뷰 전체 글갯수 조회
	public int serviceCountReviewCheck() {
		return dao.countReviewCheck();
	}
	//단위기능3. 리뷰 승인 기능
	public void serviceReviewCheckOk(String review_id) {
		dao.reviewCheckOk(review_id);
		
	}
	//단위기능4. 리뷰 거절 기능
	//리뷰테이블 삭제
	public void serviceDelReviewCheck(String review_id) {
		dao.delReviewCheck(review_id);
		
	}
	//영수증테이블 삭제
	public void serviceDelReceipt(String review_id) {
		dao.delReceipt(review_id);
		
	}
}
