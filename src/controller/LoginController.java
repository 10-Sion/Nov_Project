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

@WebServlet("/Login/*")
public class LoginController extends HttpServlet{
	
	//서비스 객체
	private LoginService sv;
	
	//정보 객체
	private UsersVO vo;
	
	// 서블릿 초기화 메서드, 서블릿 컨테이너에 의해 서블릿 초기화 시 호출됨
	public void init(ServletConfig config) throws ServletException {
	
		sv = new LoginService();
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
		System.out.println("LoginController 2단계 요청주소: " + action);
		//			/LoginPage.do 로그인 페이지 이동
		//			/Login.do 로그인 요청 처리
					
		
		try {
			
			if(action.equals("/LoginPage.do")) {
				
				// 이동할 페이지 설정
				nextPage="/login/login.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}else if(action.equals("/Login.do")) {
				
				int loginResult = -1;
				String email = request.getParameter("email");
		        String password = request.getParameter("password");
        
		        // 로그인 메소드를 사용하여 로그인 시도
		        loginResult = sv.login(email, password);
		        		        	        
		        //System.out.println("로그인 시도하는 email : " + email);
		        //System.out.println("로그인 시도하는 password : " + password);
		        
		        if (loginResult == 1) {
		        	
		            // 로그인 성공
		            // 세션을 설정하여 로그인 상태 유지
		            HttpSession session = request.getSession();
		            session.setAttribute("email", email);
		            
		            // 사용자 ID (user_id)도 세션에 저장
		            int user_id = sv.getUserIDByEmail(email); 
		            session.setAttribute("user_id", user_id);
		            
		            // 로그인 성공 메시지 설정
		            out.println("<script>");
					out.println("window.alert('로그인에 성공했습니다!');");
					out.println("</script>");
					
					// 이동할 페이지 설정
		            nextPage = "/Main/index.jsp";
		            
		            // 다음 페이지로 포워드하기 위한 디스패처 객체 생성
					RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
					dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
		           
		        } else if(loginResult == -1){ 
		        	
		            // 로그인 실패(이메일이 틀림)
		        	out.println("<script>");
					out.println("window.alert('이메일이 틀렸습니다. 다시 시도해주세요.');");
					out.println("history.go(-1);");
					out.println("</script>");
					return;
		            
		        } else if(loginResult == 0) {
		        	
		            // 로그인 실패(비밀번호가 틀림)
		        	out.println("<script>");
					out.println("window.alert('비밀번호가 틀렸습니다. 다시 시도해주세요.');");
					out.println("history.go(-1);");
					out.println("</script>");
					return;

		        }	        
   
			}
								
			
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 콘솔에 스택 트레이스 출력
		}
		
		
	}	
}
