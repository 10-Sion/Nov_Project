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
    <title>병원 리스트</title>
    <link rel="stylesheet" type="text/css" href="review.css">
</head>
<body>
<jsp:include page= "./mainNavigate.jsp"/>

    <h1 style="font-weight: bold; font-family: 'Song Myung', serif;"  >부산 진구 치아병원 목록</h1>
    <p>"병원 이름" 을 클릭하시면 해당 병원의 리뷰목록으로 이동합니다.</p>
    <table border="1" style="font-family: 'Gowun Dodum', sans-serif;">
        <tr>
            <th class="rounded-left">번호</th>
            <th>병원 이름</th>
            <th>주소</th>
            <th>동</th>
            <th class="rounded-right">전화번호</th>
        </tr>
        <%
            while (resultSet.next()) {
        %>
        <tr>
            <td><%= resultSet.getInt("id") %></td>
            <td><a href="/Nov_Project/review?hospitalId=<%= resultSet.getInt("id") %>"style="text-decoration: none; color: black;"><%= resultSet.getString("name") %></a></td>
            <td><%= resultSet.getString("address") %></td>
            <td><%= resultSet.getString("city") %></td>
            <td><%= resultSet.getString("tel") %></td>
        </tr>
        <%
            }
        %>
    </table>
    
    <!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
</body>
</html>

<%
    // 결과셋과 연결을 닫기
    resultSet.close();
    statement.close();
%>
