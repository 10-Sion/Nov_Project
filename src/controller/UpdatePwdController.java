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

import dao.UpdatePwdDAO;


@WebServlet("/UpdatePwd/*")
public class UpdatePwdController extends HttpServlet{
	

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
		System.out.println("UpdatePwdController 2단계 요청주소: " + action);
		//			/UpdatePage.do 비밀번호 수정 화면으로 이동
		//			/Update.do 비밀번호 수정 요청 처리
				
		
	    try {
	    	
	    	if(action.equals("/UpdatePage.do")) {
	    			    			
				// 이동할 페이지 설정
				nextPage = "/accountSettings/updatePwd.jsp";
				
				// 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
	    	
	    	} else if(action.equals("/Update.do")) {
	    		
	            HttpSession session = request.getSession();
	    	    String newPwd = request.getParameter("newPwd");
	    	    String email = (String) session.getAttribute("email");
	    	    String originPwd = (String) session.getAttribute("password");

	    	    if (newPwd.length() >= 6 && newPwd.length() <= 16) {
	    	        if (!newPwd.equals(originPwd)) {
	    	            // 비밀번호가 기존 비밀번호와 다를 경우에만 업데이트
	    	            UpdatePwdDAO dao = new UpdatePwdDAO();
	    	            dao.updatePwd(email, newPwd);  
	    	            
	    	            // 비밀번호 변경 성공
	    	        	out.println("<script>");
	    				out.println("window.alert('비밀번호 변경에 성공했습니다.');");
			            out.println("window.location.href = '" + request.getContextPath() + "/Main/mainPage.jsp';"); // 이동할 페이지 설정
	    				out.println("</script>");
	    				
	    	        } else if(newPwd.equals(originPwd)){
	    	        	    	        	
	    	        	// 비밀번호 변경 실패
			        	out.println("<script>");
						out.println("window.alert('비밀번호 변경에 실패했습니다. 기존 비밀번호와 동일하게 변경할 수 없습니다.');");
						out.println("history.go(-1);");
						out.println("</script>");
						
						System.out.println("비밀번호 변경 실패");	
	    	        }
	    	    } 
	    	    
	    	    
	    	    
	    	    // 이동할 페이지 설정
	    	    //nextPage = "/Main/mainPage.jsp"; 

	    	    // 다음 페이지로 포워드하기 위한 디스패처 객체 생성
				//RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
				//dispatch.forward(request, response); // 다음 페이지로 요청과 응답 객체를 포워드
	    	
	    	}
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    
	}
}
