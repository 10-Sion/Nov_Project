package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccountSettings/*")
public class AccountSettingsController extends HttpServlet{
	
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
		System.out.println("AccountSettingsController 2단계 요청주소: " + action);
	    //			/MyPage.do 계정 관리 화면(마이페이지 화면)으로 이동
		
		try {
		    	
			if(action.equals("/MyPage.do")) {
	    			    			
			// 이동할 페이지 설정
			nextPage = "/accountSettings/myPage.jsp";
			
			// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
			dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
	    	
	    	}
		    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
