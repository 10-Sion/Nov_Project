package controller.memberListController;

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

import service.memberListService.MemberListService;
import vo.memberListVO.MemberListVO;


@WebServlet("/memberList/*")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MemberListService memberListService;
	
	@Override
	public void init() throws ServletException {
		memberListService = new MemberListService();
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
			if (action.equals("/searchMemberList.do")) {
				int pageSize = 10;
			    int currentPage = 1;

			    String pageNum = request.getParameter("pageNum");
			    if (pageNum != null) {
			        currentPage = Integer.parseInt(pageNum);
			    }

			    int startRow = (currentPage - 1) * pageSize;
				
				List<MemberListVO> membersList = new ArrayList<MemberListVO>();

				membersList = memberListService.serviceSearchMemberList(startRow,pageSize);
				int count = memberListService.serviceCountMemberList();
				
				request.setAttribute("membersList", membersList);
				request.setAttribute("count", count);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageNum", pageNum);
				
				nextPage = "/Info/memberList.jsp";
			}else if(action.equals("/updateMemberList.do")) {
				
				String user_id = request.getParameter("user_id");
				
				String grade_Name = request.getParameter("grade_Name");
				
				System.out.println(user_id);
				System.out.println(grade_Name);
				memberListService.serviceUpdateMemberList(user_id,grade_Name);
				
				nextPage = "/memberList/searchMemberList.do";
				
			}else if (action.equals("/delMemberList.do")) {
				
				String user_id = request.getParameter("user_id");
				
				memberListService.serviceWithdrawalBoard(user_id);
				
				memberListService.serviceDelMemberList(user_id);
				
//				System.out.println(user_id) -- 확인; 
				
				nextPage = "/memberList/searchMemberList.do";
			}
			
			//포워딩 (디스패처 방식)
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
				
	}
}
