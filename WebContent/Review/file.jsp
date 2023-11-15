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
<jsp:include page= "./mainNavigate.jsp"/>

<div class="container">

<header>
<h2>병원 리뷰 작성중 (3/3)</h2>
<p>선택하신 병원은 <%=hospitalName %> 입니다.</p>


<form action="<%=request.getContextPath()%>/addReceipt?action=fileUpload&user_id=<%=user_id%>&hospitalName=<%=hospitalName %>" 
      name="fileUpload" method="post" enctype="multipart/form-data">

<!-- 	<input type="hidden" name="action" value="fileUpload"> -->

	영수증 첨부 <input type="file" name="receiptImage"><br>
	<input type="submit" value="리뷰작성 완료">
	
	<p style="text-align: center;">ps. 사진첨부를 완료하시면 담당자가 확인 후 완료됩니다.</p>
	
</form>
</header>
</div>
<!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
</body>
</html> 