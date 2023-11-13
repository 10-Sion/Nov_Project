package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UpdateInfoDAO;
import service.UpdateInfoService;
import vo.UsersVO;

@WebServlet("/UpdateInfo/*")
public class UpdateInfoController extends HttpServlet{
	
	//서비스 객체
	private UpdateInfoService sv;
		
	//정보 객체
	private UsersVO vo;
	
	// 서블릿 초기화 메서드, 서블릿 컨테이너에 의해 서블릿 초기화 시 호출됨
	public void init(ServletConfig config) throws ServletException {
		
		sv = new UpdateInfoService();
		vo = new UsersVO();
		
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
		
		String nextPage = "";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
	    PrintWriter out = response.getWriter();
	    
	    String action = request.getPathInfo();
		System.out.println("UpdateInfoController 2단계 요청주소: " + action);
	    //			/UpdateInfoPage.do 회원정보수정 페이지로 이동
		//			/UpdateInfo.do 회원정보수정 요청 처리
		
		
		try {
			
			if(action.equals("/UpdateInfoPage.do")) {
				            
				int user_id = Integer.parseInt(request.getParameter("user_id"));
		            
	            UpdateInfoDAO dao = new UpdateInfoDAO();
	            vo = dao.getUsersById(user_id);
	            
	            request.setAttribute("vo", vo);
		            
				// 이동할 페이지 설정
				nextPage = "/accountSettings/updateInfo.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}else if(action.equals("/UpdateInfo.do")) {
				
				HttpSession session = request.getSession();				
				String email = request.getParameter("email");			
                String password = request.getParameter("password");	
                String username = request.getParameter("username");		     
	            int user_id = (int) session.getAttribute("user_id");
                
	            // 중복 이메일 확인
	            if (sv.isEmailAvailable(email, user_id)) {
	                // 중복되지 않는 경우에만 업데이트
	                sv.updateInfo(email, password, username, user_id);

	                session.setAttribute("email", email);
	                session.setAttribute("password", password);
	                session.setAttribute("username", username);
	                
	                // 개인정보수정 성공 메시지 설정
	                out.println("<script>");
	                out.println("window.alert('변경되었습니다!');");
	                out.println("window.location.href = '" + request.getContextPath() + "/accountSettings/myPage.jsp';"); // 이동할 페이지 설정
	                out.println("</script>");
	                
	            } else {
	                // 중복된 경우에는 메시지를 표시하거나 다른 조치를 취할 수 있음
	                out.println("<script>");
	                out.println("window.alert('이미 사용 중인 이메일입니다. 다른 이메일을 사용해주세요.');");
	                out.println("window.location.href = '" + request.getContextPath() + "/accountSettings/updateInfo.jsp';"); // 이동할 페이지 설정
	                out.println("</script>");
	            }           

			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
}
