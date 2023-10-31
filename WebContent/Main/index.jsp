<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />  

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>커뮤니티 시작 페이지</title>
	
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/MainIndexStyle.css"> <!-- index 페이지 전용 css -->
	
	</head>
	
	<body>
			<!-- 처음 보여지는 부분임 -->
			<div class="page">
			  <span class="page__name">맨 첨에 보여질 화면단임</span>
			  <span class="page__hint">
			    해당 div 수정해서 첫 화면 보여주면됨
			  </span>
			</div>
		
		<!-- 메뉴 클릭 따라 바뀌는 부분임 -->
		<div class="menu">
		  <!-- active 클래스 추가로 붙을 경우 띄워지는 화면단임 -->
		  <nav id="main-menu" class="menu__nav">
		    <ul class="menu__list r-list">
		      <!-- 1번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="./mainPage.jsp" class="menu__link r-link"> 메인 페이지 </a>
		      </li>
		      <!-- 2번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="#0" class="menu__link r-link"> 병원 및 리뷰 </a>
		        
		        <ul class="menu_subGroup">
		        	<li> 병원 리스트 </li>
		        	<li> 병원 리뷰 </li>
		        </ul>
		      </li>
		      <!-- 3번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="#0" class="menu__link r-link"> 병원 지도 </a>		<!-- 후순위로 구현 -->
		      </li>
		      <!-- 4번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="#0" class="menu__link r-link"> 커뮤니티 </a>
		        
		        <ul class="menu_subGroup">
		        	<li> 자유 게시판 </li>
		        	<li> 공지 사항 </li>
		        	<li> 건의 사항 </li>
		        </ul>
		      </li>
		      <!-- 5번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="#0" class="menu__link r-link"> 이벤트 </a>		<!-- 후순위로 구현 -->
		      </li>
		      <!-- 6번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="#0" class="menu__link r-link"> 정보 관리 </a>
		        
		        <ul class="menu_subGroup">
		        	<li> 정보 수정 </li>
		        	<li> 관리자: 가입자 관리 </li>
		        	<li> 관리자: 방문자 관리 </li>
		        	<li> 리뷰 절차 승인 </li>
		        </ul>
		      </li>
		      
		    </ul>
		  </nav>
		  
		  <button class="menu__toggle r-button" type="button" aria-controls="main-menu" aria-expanded="false">
		    <span class="menu__hamburger m-hamburger">
		      <span class="m-hamburger__label">
		        <span class="menu__toggle-hint screen-reader">Open menu</span>
		      </span>
		    </span>
		  </button>
		</div>
		
		<script src="<%= path %>/Assets/Script/mainScript/mainIndexSc.js"></script>	<!-- 화면 전환 애니메이션 처리 -->
	</body>
</html>