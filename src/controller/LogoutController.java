package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Logout.do")
public class LogoutController extends HttpServlet{
	
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

		try {
	
			HttpSession session = request.getSession();
			session.invalidate();
			
			// 이동할 페이지 설정
			nextPage = "/Main/index.jsp";
			
			// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
			dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
