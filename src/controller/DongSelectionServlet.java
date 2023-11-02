package controller;

import dao.DongDAO;
import dao.HospitalDAO;
import dao.ReviewDAO;
import vo.ReviewVO;

import java.io.IOException;
import java.util.List;
import dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dongSelection")
public class DongSelectionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
    protected void doHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.setCharacterEncoding("UTF-8");
        
        // HttpSession 객체를 가져옴
        HttpSession session = request.getSession();
        
        // String action =  request.getPathInfo();
        String action = request.getParameter("action");  //dongSelect
        System.out.println("action : " + action);
        // 인스턴스화
        DongDAO dongDAO = new DongDAO();
        HospitalDAO hospitalDAO = new HospitalDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        
        if("review_first".equals(action)) {
        	 List<String> dongNames = dongDAO.getDongNames();

             // 동 이름 목록을 request에 저장
             request.setAttribute("dongNames", dongNames);

             // 기본페이지로 포워딩
             request.getRequestDispatcher("Review/review_first.jsp").forward(request, response);
             
        }else if("dongSelect".equals(action)) { // 동선택 요청을 받았을때
        	  // 사용자가 선택한 동 가져오기
            String selectedDong = request.getParameter("selectedDong");
            System.out.println("선택한   option : "+selectedDong);

            // 선택한 동에 해당하는 병원 이름 목록을 가져옴
            List<String> hospitalNames = hospitalDAO.getHospitalNamesByDong(selectedDong);

            System.out.println("검색 개수 : "+ hospitalNames.size());
            // 병원 이름 목록을 request에 저장
            request.setAttribute("hospitalNames", hospitalNames);
            request.setAttribute("selectedDong", selectedDong); // 동 정보를 다시 저장

            // JSP 페이지로 포워딩
            request.getRequestDispatcher("Review/review_second.jsp").forward(request, response);
        	
        }else if("review_post".equals(action)) {
        	// 폼에서 전송된 데이터 가져오기 (평점, 리뷰 내용, 병원 이름)
            double rating = Double.parseDouble(request.getParameter("rating"));
            String reviewText = request.getParameter("comment");
            String hospitalName = request.getParameter("selectedHospital");

            // 병원 이름으로 병원 ID 가져오기
            int hospitalId = hospitalDAO.getHospitalIdByName(hospitalName);

            // 세션을 통해 현재 로그인한 사용자의 user_id 값을 가져옴
            Integer userId = (Integer) session.getAttribute("user_id");

            if (userId != null) {
                ReviewVO review = new ReviewVO();
                review.setUserId(userId);
                review.setHospitalId(hospitalId);
                review.setReviewText(reviewText);
                review.setRating(rating);

                String result = reviewDAO.addReview(session, review);

                if ("success".equals(result)) {
                    response.sendRedirect("Review/file.jsp");
                } 
}
}
}
}

        
        
  