<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.DongDAO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>데이터베이스 연결 상태 확인</title>
</head>
<body>
    <h1>데이터베이스 연결 상태 확인</h1>
    <%
        DongDAO dao = new DongDAO();
        List<String> dongNames = dao.getDongNames();
    %>
    <p>데이터베이스 연결 상태: 
        <% if (dongNames != null) { %>
            <span style="color: green;">연결 성공</span>
        <% } else { %>
            <span style="color: red;">연결 실패</span>
        <% } %>
    </p>
</body>
</html>
