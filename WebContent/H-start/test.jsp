<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String path = request.getContextPath(); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="test.css">
	
</head>
<header>


<div class="menu-icon">
	<span class="menu-icon__line menu-icon__line-left"></span>
	<span class="menu-icon__line"></span>
	<span class="menu-icon__line menu-icon__line-right"></span>
</div>

<div class="nav">
	<div class="nav__content">
		<ul class="nav__list">
			<li class="nav__list-item"><a href="<%=path %>/Review/review_list.jsp" style=" text-decoration: none">병원 리스트</a></li>
			<li class="nav__list-item"><a href="<%=path %>/Review/review_first.jsp" style=" text-decoration: none">병원 리뷰 작성</a></li>
			<li class="nav__list-item"><a href="#" style=" text-decoration: none">리뷰 리스트</a></li>
			<li class="nav__list-item"><a href="<%=path %>/Map/road.jsp"" style=" text-decoration: none">병원 지도 및 길찾기</a></li>
		</ul>
	</div>
</div>

<!-- <div class="site-content">
	<h1 class="site-content__headline">Another menu concept</h1>
</div> -->


</header>
<script src="test.js"></script>
</html>