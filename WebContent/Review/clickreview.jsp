<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>

<%
    // 병원 ID 받아오기
    int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));

    // 데이터베이스 연결 가져오기
    Connection connection = DatabaseConnection.getConnection();

    // 해당 병원의 리뷰 데이터 가져오기
    String query = "SELECT * FROM Reviews WHERE hospital_id = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, hospitalId);
    ResultSet resultSet = preparedStatement.executeQuery();
    
 	// 해당 병원의 이름 가져오기
    String queryHospitalName = "SELECT name FROM Hospitals WHERE id = ?";
    PreparedStatement preparedStatementHospitalName = connection.prepareStatement(queryHospitalName);
    preparedStatementHospitalName.setInt(1, hospitalId);
    ResultSet resultSetHospitalName = preparedStatementHospitalName.executeQuery();

    String hospitalName = "";
    if (resultSetHospitalName.next()) {
        hospitalName = resultSetHospitalName.getString("name");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>리뷰 리스트</title>
   <link rel="stylesheet" type="text/css" href="review.css">
</head>
<body>
<jsp:include page= "./mainNavigate.jsp"/>
    <h1>해당 병원 리뷰 리스트</h1>
    <p>선택하신 병원은 ➕<span class="highlight-text"><%= hospitalName %></span>➕ 입니다.</p>
    <table border="1">
        <tr>
            <th class="rounded-left">리뷰 번호</th>
            <th>리뷰 내용</th>
            <th class="rounded-right">평점</th>
           
        </tr>
        <% while (resultSet.next()) { %>
            <tr>
                <td><%= resultSet.getInt("review_id") %></td>
                <td><%= resultSet.getString("review_text") %></td>
                <td><%= resultSet.getDouble("rating") %></td>
                <!-- 필요한 다른 열 추가 -->
            </tr>
        <% } %>
    </table>

   <!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>

</body>
</html>
