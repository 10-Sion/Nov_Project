package controller;

import dao.DongDAO;
import dao.HospitalDAO;
import dao.ReceiptDAO;
import dao.ReviewDAO;
import vo.ReceiptVO;

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
        if("fileUpload".equals(action)) {
        
        String saveFolder = "C:/Users/User/OneDrive/Desktop/test"; // 업로드 파일 저장 경로

        File directory = new File(saveFolder);

        if (!directory.exists()) {
            directory.mkdirs(); // 폴더가 없으면 생성
        }

        String encType = "utf-8";
        int maxSize = 10 * 1024 * 1024; // 최대 업로드 10MB

        try {
            MultipartRequest multi = new MultipartRequest(request, saveFolder, maxSize, encType, new DefaultFileRenamePolicy());
       

    	    Enumeration params = multi.getParameterNames();

    	    while(params.hasMoreElements()) {
    	        String name = (String) params.nextElement();
    	        String value = multi.getParameter(name);
    	        System.out.println(name + " = " + value + "<br>");
    	    }

    	    Enumeration files = multi.getFileNames();

    	    while(files.hasMoreElements()) {
    	        String name = (String)files.nextElement();
    	        String filename = multi.getFilesystemName(name);
    	        String original = multi.getOriginalFileName(name);
    	        String type = multi.getContentType(name);
    	        File file = multi.getFile(name);

    		    
    	    }

        } catch (IOException ioe) {
           System.out.println("오류 발생: " + ioe.getMessage() + "<br>");
        } catch (Exception ex) {
        	System.out.println("오류 발생: " + ex.getMessage() + "<br>");
        }
 
     
        // 폼에서 전송된 데이터 가져오기
        int userId = Integer.parseInt(request.getParameter("userId")); // 사용자 ID
        int hospitalId = Integer.parseInt(request.getParameter("hospitalId")); // 병원 ID
        Part receiptPart = request.getPart("receiptImage"); // 업로드된 영수증 이미지 Part

        InputStream receiptInputStream = receiptPart.getInputStream();
        byte[] receiptImageBytes = receiptInputStream.readAllBytes();
        
        // 영수증 정보를 생성
        ReceiptVO receipt = new ReceiptVO();
        receipt.setUserId(userId);
        receipt.setHospitalId(hospitalId);
        receipt.setReceiptImage(receiptImageBytes);
        
        // ReceiptDAO를 사용하여 영수증 정보를 데이터베이스에 추가

        String result = receiptDAO.addReceipt(session, receipt);
        
        if ("success".equals(result)) {
            // 성공한 경우
            response.sendRedirect("Review/file.jsp"); // 영수증 추가 성공 페이지로 리다이렉트
        } 
    }
}
}
