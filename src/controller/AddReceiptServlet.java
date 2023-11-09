package controller;

import dao.DongDAO;
import dao.HospitalDAO;
import dao.ReceiptDAO;
import dao.ReviewDAO;
import vo.ReceiptVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/addReceipt")
public class AddReceiptServlet extends HttpServlet {
	
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
	    String action = request.getParameter("action");  //addReceipt
	    System.out.println("action : " + action);

	    // 인스턴스화
	    DongDAO dongDAO = new DongDAO();
	    HospitalDAO hospitalDAO = new HospitalDAO();
	    ReviewDAO reviewDAO = new ReviewDAO();
	    ReceiptDAO receiptDAO = new ReceiptDAO();
	    
	    // 여기부터즤
	    if ("fileUpload".equals(action)) {

	        // Review/review_second.jsp에서 선택한 병원 이름을 받아옴
	        String hospitalName = request.getParameter("hospitalName");

	        // 파일업로드 구문
	        String saveFolder = "C:/Users/User/OneDrive/Desktop/test"; // 업로드 파일 저장 경로
	        File directory = new File(saveFolder);
	        if (!directory.exists()) {
	            directory.mkdirs(); // 폴더가 없으면 생성
	        }
	        String encType = "utf-8";
	        int maxSize = 10 * 1024 * 1024; // 최대 업로드 10MB

	        String receiptImageFileName = null; // 이미지 파일명을 초기화
	        
	        try {
	            MultipartRequest multi = new MultipartRequest(request, saveFolder, maxSize, encType, new DefaultFileRenamePolicy());

	            Enumeration<String> params = multi.getParameterNames();

	            while (params.hasMoreElements()) {
	                String name = params.nextElement();
	                String value = multi.getParameter(name);
	                response.getWriter().println(name + " = " + value + "<br>");
	            }

	            Enumeration<String> files = multi.getFileNames();

	            while (files.hasMoreElements()) {
	                String name = files.nextElement();
	                String filename = multi.getFilesystemName(name);
	                String original = multi.getOriginalFileName(name);
	                String type = multi.getContentType(name);
	                File file = multi.getFile(name);

	                if (name.equals("receiptImage")) {
	                    receiptImageFileName = original; // 이미지 파일명 저장
	                }
	            }

	        } catch (IOException ioe) {
	            response.getWriter().println("오류 발생: " + ioe.getMessage() + "<br>");
	        } catch (Exception ex) {
	            response.getWriter().println("오류 발생: " + ex.getMessage() + "<br>");
	        } // 여기까지 파일업로드 구문

	        // 폼에서 전송된 데이터 가져오기
	        int user_id = (Integer) session.getAttribute("user_id"); // 사용자 ID
	        System.out.println("user_id : " + user_id);

	        // 병원 이름으로 병원 ID 가져오기
	        int hospitalId = hospitalDAO.getHospitalIdByName(hospitalName);
	        System.out.println("hospitalId : " + hospitalId);

	        try {

	            // 여기부터 잘못됨. 확인
	            Part receiptPart = request.getPart("receiptImage"); // 업로드된 영수증 이미지 Part

	            // 영수증 정보를 생성
	            ReceiptVO receipt = new ReceiptVO();
	            receipt.setUserId(user_id);
	            receipt.setHospitalId(hospitalId);
	            receipt.setReceiptImage(receiptImageFileName); // 이미지 파일명 저장

	            // ReceiptDAO를 사용하여 영수증 정보를 데이터베이스에 추가
	            String result = receiptDAO.addReceipt(session, receipt);

	            if ("success".equals(result)) {
	            	
	                // JSP 페이지로 포워딩
	            	response.sendRedirect("Main/index.jsp");
	            }

	        } catch (Exception e) {
	            System.out.println("  파일 처리  : " + e);
	            
	            e.printStackTrace();
	        }
	    }
	}

}

