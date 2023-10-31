<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />  



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainNavStyle.css"> <!-- index 페이지 전용 css -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id="menu">
		<div class="hamburger">
			<div class="line"></div>
			<div class="line"></div>
			<div class="line"></div>
		</div>
		<div class="menu-inner">
			
			<ul class="mainMenu">
				<li> 병원 및 리뷰 
				
					<ul class="subMenu">
			        	<li> 병원 리스트 </li>
			        	<li> 병원 리뷰 </li>
			        </ul>
				</li>
					
				<li> XX병원 지도XX </li>
				<li> 커뮤니티 
					
					<ul class="subMenu">
			        	<li> 자유 게시판 </li>

			        	<li><a href="${path}/gongiBoard/gongiList.do" style=" text-decoration: none">공지사항</a></li>

			     		<li><a href="${path}/moonUiBoard/moonUiList.do" style="text-decoration: none"> 건의 사항 </a></li>

			        </ul>
				</li>
				<li> XX이벤트XX </li>
				<li> 정보 관리 
				
					<ul class="subMenu">
			        	<li> 정보 수정 </li>
			        	<li> 관리자: 가입자 관리 </li>
			        	<li> 관리자: 방문자 관리 </li>
			        	<li> 리뷰 절차 승인 </li>
			        </ul>
				</li>

			</ul>
		</div>
  
  
		
		<svg version="1.1" id="blob"xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
			<path id="blob-path" d="M60,500H0V0h60c0,0,20,172,20,250S60,900,60,500z"/>
		</svg>
	</div>

	
	<script src="<%= path %>/Assets/Script/mainScript/mainNavSc.js"></script>	<!-- 메뉴 애니메이션 처리 -->
</body>
</html>