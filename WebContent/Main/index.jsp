<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<% request.setCharacterEncoding("UTF-8"); %>

<% String path = request.getContextPath(); %>

<% 
	Integer user_id = (Integer)session.getAttribute("user_id"); 
	String email = (String)session.getAttribute("email");
	Integer grade_id = (Integer)session.getAttribute("grade_id");
	System.out.println("로그인된 user_id : " + user_id);
	System.out.println("로그인된 사용자 등급 : " + grade_id);	// 0 - 3 까지는 일반회원, 4 는 관리자	표시입니다.
	//System.out.println("로그인된 email : " + email);	//
%>
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
			  	<div id=container>
				  2조
				  <div id=flip>
				    <div><div> 병원 리뷰 </div></div>
				    <div><div> 우리 </div></div>
				    <div><div> 커뮤니티다!! </div></div>
				  </div>
				  레츠고!
				</div>
			</div>
		
		<!-- 메뉴 클릭 따라 바뀌는 부분임 -->
		<div class="menu">
		  <!-- active 클래스 추가로 붙을 경우 띄워지는 화면단임 -->
		  <nav id="main-menu" class="menu__nav">
		    <ul class="menu__list r-list">
		      <!-- 로그인 -->
		      <% 
				
				if (email != null) {
				    // 세션에 로그인한 사용자 정보가 있을 때
				%>
				    <li class="menu__group" role="none">	
				        <a href="<%=path %>/Logout.do" class="menu__link r-link"> 로그아웃 </a>
				    </li>
				<% 
				} else {
				    // 세션에 로그인한 사용자 정보가 없을 때
				%>
				    <li class="menu__group" role="none">	
				        <a href="<%=path%>/Login/LoginPage.do" class="menu__link r-link"> 로그인 </a>
				    </li>
				<% 
				}
			  %>
		      <!-- 1번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="<%=path %>/Main/mainPage.jsp" class="menu__link r-link"> 메인 페이지 </a>
		      </li>
		      <!-- 2번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="<%=path %>/H-start/NewFile.jsp" class="menu__link r-link"> 병원 및 리뷰 </a>     
		      </li>
		      <!-- 3번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="<%=path %>/Map/road.jsp" class="menu__link r-link"> 병원 지도 </a>		<!-- 후순위로 구현 -->
		      </li>
		      <!-- 4번 메뉴 -->
		      <li class="menu__group" role="none">	
		        <a href="#0" class="menu__link r-link"> 커뮤니티 </a>
		        
		        <ul class="menu_subGroup">
		        	<li><a href="${path}/jauBoard/jauList.do" style=" text-decoration: none"> 자유게시판</a></li>

			        <li><a href="${path}/gongiBoard/gongiList.do" style=" text-decoration: none"> 공지사항</a></li>

			     	<li><a href="${path}/moonUiBoard/moonUiList.do" style="text-decoration: none"> 건의 사항 </a></li>
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
		        	<li><a href="${path}/memberList/searchMemberList.do" style="text-decoration: none"> 가입자 관리</a></li>
		        	<li> 관리자: 방문자 관리 </li>
		        	<li>
		        	<a href="${path}/review/reviewCheckList.do" style="text-decoration: none"> 리뷰 절차 승인</a></li>
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