<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="dao.ReviewDAO" %>
<%@ page import="dao.DatabaseConnection" %>
<%@ page import="java.util.List" %>
<% String path = request.getContextPath(); %>	<!-- contextPath 변수 -->
<% 
	Integer user_id = (Integer)session.getAttribute("user_id");
	System.out.println("로그인된 user_id : " + user_id);
%>

	    
	<!DOCTYPE html>
	<html>
	<head>
	    <title>인증완료 리뷰 목록</title>
	    <link rel="stylesheet" type="text/css" href="<%= path %>/Review/review.css">
	       
	</head>
	<body>
	<jsp:include page= "./mainNavigate.jsp"/>

	    <h1>인증완료 리뷰 목록</h1>
	<p>담당자가 확인완료한 리뷰리스트들만 출력됩니다.</p>
	    <table>
	        <thead>
	            <tr>
	                <th class="rounded-left">번호</th>
	                <th>사용자 ID</th>
	                <th>병원 이름</th>
	                <th>리뷰 내용</th>
	                <th class="rounded-right">평점</th>

	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="review" items="${verifiedReviews}" >
	                <tr>
	                    <td>${review.reviewId}</td>
						<td>${review.userId}</td>
						<td>${review.hospitalName}</td>
						<td>${review.reviewText}</td>
						<td>${review.rating}</td>

	 
	                </tr>
	            </c:forEach>	
	        </tbody>
	    </table>
	    
	     <!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
	</body>
	</html>
