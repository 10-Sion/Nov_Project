package controller;

import dao.DongDAO;
import dao.HospitalDAO;
import dao.ReviewDAO;

import java.io.IOException;
import java.util.List;
import dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        	
        }
        
        
        
        
        
/*        
        if (action == null) {
            // 사용자가 action을 지정하지 않은 경우
            if (selectedDong != null) {
                // 동 이름을 가져오는 메서드 호출
                String dongName = dongDAO.getSelectedDongName(selectedDong);

                // 동 이름을 request에 저장
                request.setAttribute("selectedDong", dongName);
                
                // 리다이렉트할 페이지 경로 (review_second.jsp)
                String nextPage = "Review/review_second.jsp";

                // 리다이렉트
                response.sendRedirect(nextPage);
            } else {
                // 기본 페이지로 포워딩 (사용자가 동을 선택하지 않은 경우)
                List<String> dongNames = dongDAO.getDongNames();

                // 동 이름 목록을 request에 저장
                request.setAttribute("dongNames", dongNames);

                // 기본페이지로 포워딩
                request.getRequestDispatcher("Review/review_first.jsp").forward(request, response);
            }
        } else if (action.equals("hospitalSelect")) {
           
                // hospitalDAO를 사용하여 선택한 동에 해당하는 병원 이름 목록을 가져옴
                HospitalDAO hospitalDAO = new HospitalDAO();
                List<String> hospitalNames = hospitalDAO.getHospitalNamesByDong(selectedDong);

                
                System.out.println(hospitalNames.size());
                // 병원 이름 목록을 request에 저장
                request.setAttribute("hospitalNames", hospitalNames);
                request.setAttribute("selectedDong", selectedDong); // 동 정보를 다시 저장

                // JSP 페이지로 포워딩
                request.getRequestDispatcher("Review/review_second.jsp").forward(request, response);
            }
*/
        }
    }
