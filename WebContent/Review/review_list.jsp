<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>

<%
    // 데이터베이스 연결 가져오기
    Connection connection = DatabaseConnection.getConnection();

    // 데이터베이스에서 데이터 가져오기
    String query = "SELECT * FROM Hospitals";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="review.css">
    <title>병원 리스트</title>
</head>
<body>
    <h1>부산 진구 치아병원 목록</h1>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>병원 이름</th>
            <th>주소</th>
            <th>동</th>
            <th>전화번호</th>
        </tr>
        <%
            while (resultSet.next()) {
        %>
        <tr>
            <td><%= resultSet.getInt("id") %></td>
            <td><%= resultSet.getString("name") %></td>
            <td><%= resultSet.getString("address") %></td>
            <td><%= resultSet.getString("city") %></td>
            <td><%= resultSet.getString("tel") %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

<%
    // 결과셋과 연결을 닫기
    resultSet.close();
    statement.close();
%>
