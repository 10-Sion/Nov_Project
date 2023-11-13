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

import dao.DelAccountDAO;
import service.JoinService;
import vo.UsersVO;

@WebServlet("/DelAccount/*")
public class DelAccountController extends HttpServlet{
	
	//서비스 객체
	private JoinService sv;
		
	//정보 객체
	private UsersVO vo;
	
	// 서블릿 초기화 메서드, 서블릿 컨테이너에 의해 서블릿 초기화 시 호출됨
	public void init(ServletConfig config) throws ServletException {
		
		sv = new JoinService();
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
		System.out.println("DelAccountController 2단계 요청주소: " + action);
	    //			/DelPage.do 회원탈퇴 페이지로 이동
		//			/DelAccount.do 회원탈퇴 처리
		
	    try {
	    	
			if (action.equals("/DelPage.do")) {
				
				// 이동할 페이지 설정
				nextPage = "/accountSettings/delAccount.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}else if (action.equals("/DelAccount.do")) {
				
				DelAccountDAO dao = new DelAccountDAO();
				
				String email = request.getParameter("email");
				String password = request.getParameter("password");				
				int result = dao.delAccount(email, password);
				
				 // 계정 삭제 결과에 따라 적절한 메시지를 설정합니다.
	            if (result == 1) {
	                // 회원탈퇴 성공
	                request.setAttribute("successMessage", "회원탈퇴가 되었습니다. 그동안 이용해주셔서 감사합니다.");   
	                
	                // 이동할 페이지 설정
					nextPage = "/Main/index.jsp";
					
					// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
					RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
					dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
	                
	            } else if (result == 0) {
	                // 비밀번호가 일치하지 않아 회원탈퇴 실패
	                request.setAttribute("errorMessage", "비밀번호가 일치하지 않아 회원 탈퇴에 실패했습니다.");	                
		        	out.println("<script>");
					out.println("history.go(-1);");
					out.println("</script>");
					
	            }			
				
			}
						
	
		} catch (Exception e) {
			e.printStackTrace();
			
		}        
	}
}
