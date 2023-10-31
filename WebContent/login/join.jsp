<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 페이지</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
	</head>
	<body>
		<h1>회원가입 페이지입니다.</h1>
		
		<form action="<%=path %>/Join/Join.do" method="post">
	        <label for="user_id">사용자 ID:</label>
	        <input type="text" id="user_id" name="user_id" required><br>
	        
	        <label for="username">이름:</label>
	        <input type="text" id="username" name="username" required><br>
	        
	        <label for="email">이메일:</label>
	        <input type="email" id="email" name="email" required><br>
	        
	        <label for="password">비밀번호:</label>
	        <input type="password" id="password" name="password" required><br>
	        
	        <input type="submit" value="가입하기">
    	</form>
	</body>
</html>