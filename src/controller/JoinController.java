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

import dao.JoinDAO;
import service.JoinService;
import vo.UsersVO;

@WebServlet("/Join/*")
public class JoinController extends HttpServlet{

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
		System.out.println("JoinController 2단계 요청주소: " + action);
		//			/JoinPage.do 회원가입 페이지 이동
		//			/JoinI.do 회원가입 요청 처리(일반회원)
		//			/JoinM.do 회원가입 요청 처리(관리자)

		try {
			
			if(action.equals("/JoinPage.do")) {
				
				// 이동할 페이지 설정
				nextPage="/login/join.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}else if(action.equals("/JoinI.do")) {
				
	            String username = request.getParameter("username");
	            String email = request.getParameter("email");
	            String password = request.getParameter("password");
           
	            vo.setUsername(username);
	            vo.setEmail(email);
	            vo.setPassword(password);
	            
	            JoinDAO dao = new JoinDAO();
	            dao.insertUsers(vo);
	            
	            // 이동할 페이지 설정
				nextPage="/login/login.jsp";

			    // 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}else if(action.equals("/JoinM.do")) {
				
	            String username = request.getParameter("username");
	            String email = request.getParameter("email");
	            String password = request.getParameter("password");
           
	            vo.setUsername(username);
	            vo.setEmail(email);
	            vo.setPassword(password);
	            
	            JoinDAO dao = new JoinDAO();
	            dao.insertMasters(vo);
	            
	            // 이동할 페이지 설정
				nextPage="/login/login.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 콘솔에 스택 트레이스 출력
		}
	
	}	
	
}
