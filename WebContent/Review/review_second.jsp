<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.HospitalDAO" %>
<%@ page import="java.util.List" %>

<% 
	Integer user_id = (Integer)session.getAttribute("user_id");
	System.out.println("로그인된 user_id : " + user_id);

	
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    
    <title>두 번째 페이지</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Review/post.css">
</head>
<body>

<div class="container">

<header>
<h2>병원 리뷰 작성중 (2/3)</h2>
<p>방문하셨던 병원의 이름은 무엇인가요?</p>
    <form action="<%=request.getContextPath()%>/dongSelection" method="post">
    
    	<input type="hidden" name="action" value="review_post">
      

        <select id="hospitalSelect" name="selectedHospital">
        <option value="">병원을 선택하세요.</option> <!-- 기본값 추가 -->
            <%
                // 사용자가 선택한 동에 해당하는 병원 이름 목록을 가져옴
                String selectedDong = (String) request.getAttribute("selectedDong");
                HospitalDAO hospitalDAO = new HospitalDAO();
                List<String> hospitalNames = hospitalDAO.getHospitalNamesByDong(selectedDong);
       
                
                for (String hospital : hospitalNames) {
            %>
                <option value="<%= hospital %>"><%= hospital %></option>
            <%
                }
            %>
        </select>

        <label for="rating">평 점 (0 ~ 5 까지 자유롭게 기재해주세요.)</label>
        <input type="number" id="rating" name="rating" min="1" max="5" required><br><br>

        <label for="comment">리뷰 내용</label> <br>
        <textarea id="comment" name="comment" rows="10" cols="50" required></textarea><br><br>

        <input type="submit" value="다음으로">
    </form>
    
    
    </header>
    
</div>
    
</body>
</html>
