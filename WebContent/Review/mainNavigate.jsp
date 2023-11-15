<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />  



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" href="<%= path %>/Review/mainNavStyle.css"> <!-- index 페이지 전용 css -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" />	
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
				<!-- 홈화면 바로가기 추가 -->
				<li><i class="fa-solid fa-house" style="color: #ffffff;"></i><a href="<%=path %>/mainBoard/" style=" text-decoration: none; color:#fff;"> HOME </a></li>
				<li><i class="fa-solid fa-hospital" style="color: #ffffff;"></i> 병원 홈페이지
					<ul class="subMenu">
					<li><i class="fa-regular fa-keyboard" style="color: #ffffff;"></i><a href="<%=path %>/Review/review_list.jsp" style=" text-decoration: none"> 병원 리스트</a></li>
					<li><i class="fa-solid fa-bullhorn" style="color: #ffffff;"></i><a href="<%=path %>/Review/review_first.jsp" style=" text-decoration: none"> 리뷰 작성 하기</a></li>
					<li><i class="fa-regular fa-comment-dots" style="color: #ffffff;"></i><a href="/Nov_Project/review" style="text-decoration: none"> 리뷰 전체 목록 </a></li>
					<li><i class="fa-regular fa-comment-dots" style="color: #ffffff;"></i><a href="<%=path %>/Map/road.jsp" style="text-decoration: none"> 병원지도 및 길찾기</a></li>
					</ul>
				
				</li>
					
				<li><i class="fa-solid fa-map" style="color: #ffffff;"></i><a href="<%=path %>/Map/road.jsp" style=" text-decoration: none; color:#fff;"> 병원 지도 </a></li>
				<li><i class="fa-solid fa-comments" style="color: #ffffff;"></i> 커뮤니티 
					
					<ul class="subMenu">
			        	<li><i class="fa-regular fa-keyboard" style="color: #ffffff;"></i><a href="${path}/jauBoard/jauList.do" style=" text-decoration: none"> 자유게시판</a></li>

			        	<li><i class="fa-solid fa-bullhorn" style="color: #ffffff;"></i><a href="${path}/gongiBoard/gongiList.do" style=" text-decoration: none"> 공지사항</a></li>

			     		<li><i class="fa-regular fa-comment-dots" style="color: #ffffff;"></i><a href="${path}/moonUiBoard/moonUiList.do" style="text-decoration: none"> 건의 사항 </a></li>

			        </ul>
				</li>
				<li><i class="fa-regular fa-calendar-check" style="color: #ffffff;"></i> 이벤트 
				
										<ul class="subMenu">
			        	<li><i class="fa-solid fa-calendar-days" style="color: #ffffff;"></i><a href="#" style=" text-decoration: none"> 출석체크(ㄱㄷ) </a></li>

			        	<li><i class="fa-solid fa-question" style="color: #ffffff;"></i><a href="${path}/Event/Quiz/QuizTest.jsp" style=" text-decoration: none"> 매일매일 건강 퀴즈 </a></li>


			        </ul>
				</li>
				<li><i class="fa-solid fa-user-shield" style="color: #fafafa;"></i> 정보 관리 
				
					<ul class="subMenu">

			        	<li><i class="fa-solid fa-gear" style="color: #ffffff;"></i><a href="#" style="text-decoration: none"> 정보 수정</a></li>
			        	<li><i class="fa-solid fa-user-gear" style="color: #ffffff;"></i><a href="${path}/memberList/searchMemberList.do" style="text-decoration: none"> 가입자 관리</a></li>
			        	<li><i class="fa-solid fa-user-plus" style="color: #ffffff;"></i><a href="#" style="text-decoration: none"> 방문자 관리</a></li>
			        	<li><i class="fa-solid fa-receipt" style="color: #ffffff;"></i><a href="${path}/review/reviewCheckList.do" style="text-decoration: none"> 리뷰 절차 승인</a></li>

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