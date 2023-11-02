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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Review/post.css">
</head>
<body>
<header>
<h2>동 선택 페이지</h2>
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
        <input type="submit" value="선택">
    </form>
    </header>
</body>
</html>
