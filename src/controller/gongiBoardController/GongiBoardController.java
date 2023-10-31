package controller.gongiBoardController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import service.gongiService.GongiService;
import vo.announcementsVO.AnnouncementsVO;



@WebServlet("/gongiBoard/*")
public class GongiBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
	//회원가입 서비스 변수 설정
	private GongiService gongiService;
	
	
	
	//서블릿이 요청을 받았을때.. 가장처음에 JoinController클래스가 톰캣 메모리 로드되는 시점에
	//개발자가 변수의 값을 초기화 해놓을때 사용되는 init메소드 오버라이딩
	@Override
	public void init() throws ServletException {
		gongiService = new GongiService();
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}

	@Override
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
			if (action.equals("/gongiList.do")) {
				int pageSize = 5;
			    int currentPage = 1;

			    String pageNum = request.getParameter("pageNum");
			    if (pageNum != null) {
			        currentPage = Integer.parseInt(pageNum);
			    }

			    int startRow = (currentPage - 1) * pageSize;
				List<AnnouncementsVO> membersList = gongiService.serviceGongiList(startRow,pageSize);
				int count = gongiService.serviceListCount();
				
				//request내장객체 영역에 웹브라우저로 응답할 조회된회원정보들이 저장된 ArrayList배열을 바인딩 합니다.
				request.setAttribute("membersList", membersList);
				request.setAttribute("count", count);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageNum", pageNum);
				nextPage = "/Community/gongiList/gongiList.jsp";
				
				
			}else if(action.equals("/addGongiList.do")){
				
				String post_title = request.getParameter("post_title");
				String post_content = request.getParameter("post_content");
				
				gongiService.addGongiList(post_title,post_content);
				
				request.setAttribute("msg", "addGongi");
				
				nextPage = "/gongiBoard/gongiList.do";
				
			}else if (action.equals("/delGongiList.do")) {
				
				String announcement_id = request.getParameter("announcement_id");
				
				gongiService.delGongiList(announcement_id);
				nextPage = "/gongiBoard/gongiList.do";
			
			}else if (action.equals("/modifyList.do")) {
				
				String announcement_id = request.getParameter("announcement_id");
				
				String post_title = request.getParameter("post_title");
				String post_content = request.getParameter("post_content");
				
				gongiService.modifyGongiList(announcement_id,post_title,post_content);
				
				nextPage = "/gongiBoard/gongiList.do";
				
			}else if (action.equals("/detailList.do")) {
				String announcement_id = request.getParameter("announcement_id");
				
				
				gongiService.serviceViewCountUpdate(announcement_id);
			  AnnouncementsVO vo = gongiService.serviceListOne(announcement_id);
			  
			 
				request.setAttribute("vo", vo);
				
				nextPage = "/Community/gongiList/detailList.jsp";
				
			}else if(action.equals("/addListForm.do")) {
				
				nextPage = "/Community/gongiList/addListForm.jsp";
			}else if(action.equals("/backList.do")) {
				nextPage = "/gongiBoard/gongiList.do";

			}
			
			
			//포워딩 (디스패처 방식)
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}
