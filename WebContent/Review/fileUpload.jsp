<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%
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
	        out.println(name + " = " + value + "<br>");
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
        out.println("오류 발생: " + ioe.getMessage() + "<br>");
    } catch (Exception ex) {
        out.println("오류 발생: " + ex.getMessage() + "<br>");
    }
%>