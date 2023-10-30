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

import dao.LoginDAO;
import service.LoginService;
import vo.UsersVO;

@WebServlet("/login/*")
public class LoginController extends HttpServlet{

	//로그인 서비스 객체
	private LoginService ls;
	
	//유저 정보 객체
	private UsersVO vo;
	
	//서블릿 초기화 메소드, 서블릿 컨테이너에 의해 서블릿 초기화 시 호출됨
	public void init(ServletConfig config) throws ServletException {
	
		ls = new LoginService();
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
		System.out.println("LoginController 2단계 요청주소 : " + action);
		//			/login.do 로그인 요청 처리
	
		try {
					
				if(action.equals("loginPage.do")) {
					
					nextPage = "/Main/login/login.jsp";
					
					// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
					RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
					dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
			
				}else if(action.equals("/login.do")) {
				
				 LoginDAO dao = new LoginDAO();
				 String user_id = request.getParameter("user_id");
		         String password = request.getParameter("password");
		         int check = dao.login(user_id, password);
		         	
		         if (check == 1) {
		            // 일반 회원으로 로그인 성공
		            HttpSession session = request.getSession();
		            session.setAttribute("user_id", user_id);
		            response.sendRedirect("index.jsp"); 
		         } else if (check == 2) {
		            // 관리자로 로그인 성공
		            HttpSession session = request.getSession();
		            session.setAttribute("user_id", user_id);
		            response.sendRedirect("index.jsp"); 
		         } else if (check == 0) {
		            // 아이디는 맞고, 비밀번호 틀림
		        	out.println("<script>");
					out.println("window.alert('비밀번호가 틀렸습니다.');");
					out.println("history.go(-1);");
					out.println("</script>");
					return;
		         } else {
		            // 아이디가 틀림
		        	out.println("<script>");
					out.println("window.alert('아이디가 틀렸습니다.');");
					out.println("history.go(-1);");
					out.println("</script>");
					return;
		         }
		         
		        System.out.println("check 값 : " + check);
				nextPage = "/Main/index.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}
		

		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
