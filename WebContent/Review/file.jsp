<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.HospitalDAO" %>
<%@ page import="java.util.List" %>
    
    <% 
	int user_id = (Integer)session.getAttribute("user_id");
	System.out.println("로그인된 user_id : ----------- " + user_id);

    String hospitalName = request.getParameter("selectedHospital");
   
%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>영수증 첨부 페이지</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Review/post.css">
</head>
<body>
<h2>영수증 첨부 페이지</h2>
<p>선택한 병원: <%=hospitalName %></p>


<form action="<%=request.getContextPath()%>/addReceipt?action=fileUpload&user_id=<%=user_id%>&hospitalName=<%=hospitalName %>" 
      name="fileUpload" method="post" enctype="multipart/form-data">

<!-- 	<input type="hidden" name="action" value="fileUpload"> -->

	파일명: <input type="file" name="receiptImage"><br>
	<input type="submit" value="파일 업로드">
	
</form>

</body>
</html> 