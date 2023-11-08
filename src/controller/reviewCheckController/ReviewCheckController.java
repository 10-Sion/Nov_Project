package controller.reviewCheckController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.reviewCheckService.ReviewCheckService;
import vo.reviewCheckVO.ReviewCheckVO;

@WebServlet("/review/*")
public class ReviewCheckController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		//건의사항 게시판 서비스 변수 설정
		private ReviewCheckService reviewCheckService;
		
		
		
		//서블릿이 요청을 받았을때.. 가장처음에 JoinController클래스가 톰캣 메모리 로드되는 시점에
		//개발자가 변수의 값을 초기화 해놓을때 사용되는 init메소드 오버라이딩
		@Override
		public void init() throws ServletException {
			reviewCheckService = new ReviewCheckService();
			
		}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글처리
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=utf8");
				//request객체의 getPathInfo()메소드를 호출하여 클라이언트가 요청한 2단계 요청주소 URL을 가져 옵니다.
				String action = request.getPathInfo();
				System.out.println("클라이언트가 요청한 주소 :" + action);
				
				//서블릿에서 재요청할 뷰 주소를 저장할 변수 선언
				String nextPage = null;
				
				//웹브라우저와 연결된 출력 스트림 통로 만들기
				PrintWriter out = response.getWriter();
				
				String path = request.getPathInfo();
		try {
			if(action.equals("/reviewCheckOk.do")) {
				
				String review_id = request.getParameter("review_id");
				
				reviewCheckService.serviceReviewCheckOk(review_id);
				
				nextPage = "/review/reviewCheckList.do";
				
			}else if (action.equals("/reviewCheckNotOk.do")) {
				
				String review_id = request.getParameter("review_id");
				reviewCheckService.serviceDelReceipt(review_id);
				reviewCheckService.serviceDelReviewCheck(review_id);
				
				nextPage = "/review/reviewCheckList.do";
				
			}else if (action.equals("/reviewCheckList.do")) {
				int pageSize = 4;
			    int currentPage = 1;

			    String pageNum = request.getParameter("pageNum");
			    if (pageNum != null) {
			        currentPage = Integer.parseInt(pageNum);
			    }

			    int startRow = (currentPage - 1) * pageSize;
				
				List<ReviewCheckVO> reviewList = new ArrayList<ReviewCheckVO>();
				int count = reviewCheckService.serviceCountReviewCheck();
				reviewList = reviewCheckService.serviceSearchReviewCheck(startRow,pageSize);
				
				request.setAttribute("reviewList",reviewList);
				request.setAttribute("count", count);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageNum", pageNum);
				
				nextPage = "/Info/reviewCheck.jsp";
				
			
			}	
			
			
			//포워딩 (디스패처 방식)
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
