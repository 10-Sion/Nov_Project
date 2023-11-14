<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.DongDAO" %>
<%@ page import="java.util.List" %>
<% 
	Integer user_id = (Integer)session.getAttribute("user_id");
	System.out.println("로그인된 user_id : " + user_id);
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>동 선택 페이지</title>
        <link rel="stylesheet" type="text/css" href="post.css">
</head>
<body>
<jsp:include page= "./mainNavigate.jsp"/>
<div class="container">
<header>

<h2>병원 리뷰 작성중 (1/3)</h2>

<p>리뷰를 작성하고 싶으신 "부산 진구 치과" 중 방문하셨던 동네를 선택해주세요.</p>
    <form action="<%=request.getContextPath()%>/dongSelection" method="post">
    
    	<input type="hidden" name="action" value="dongSelect" >
       
        <label for="dongSelect">동 선택:</label>
        <select id="dongSelect" name="selectedDong">
        <option value="">동을 선택하세요.</option> <!-- 기본값 추가 -->
            <%
                DongDAO dao = new DongDAO();
                List<String> dongNames = dao.getDongNames();
                for (String dong : dongNames) {
            %>
                <option value="<%= dong %>"><%= dong %></option>
            <%
                }
            %>
        </select>
        <input type="submit" value="다음으로">
        <!-- style="background-color:#BC55EF" -->
    </form>
    </header>
    </div>
    
    <!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
</body>
</html>
