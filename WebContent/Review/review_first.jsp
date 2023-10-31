<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.DongDAO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>동 선택 페이지</title>
</head>
<body>
    <form action="dongSelection" method="post">
        <label for="dongSelect">동 선택:</label>
        <select id="dongSelect" name="selectedDong">
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
</body>
</html>
