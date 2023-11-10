<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>	<!-- contextPath 변수 -->
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:include page= "./mainNavigate.jsp"/>	<!-- 좌측 메뉴 페이지 -->



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 메인 페이지 </title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
    
    
	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainPageStyle.css"> <!-- main 페이지 전용 css -->
</head>
<body>
	<div class="topSection">
		<jsp:include page= "./mainIncludeTop.jsp"/>	<!-- 상단부 로그인 정보 처리 페이지 -->
	</div>
	
	<div class="mainSection">
		<!-- 하단부 카드 영역 -->
	    <div class="cardSection">
			  <div class="header">
			    <h2 class="header__subtitle"> 최근 게시물 </h2>
			  </div>
			
			  <div class="cards">
			
			    <div class=" card [ is-collapsed ] ">
			      <div class="card__inner [ js-expander ]">
			        <span> 리뷰 </span>
		
			      </div>
			      <div class="card__expander">
		
			        	<ul>
							<li> 글 1 </li>
							<li> 글 2 </li>
							<li> 글 3 </li>
							<li> 글 4 </li>
							<li> 글 5 </li>
						</ul>
			      </div>
			    </div>
			
			    <div class=" card [ is-collapsed ] ">
			      <div class="card__inner [ js-expander ]">
			        <span> 자유 게시판 </span>
		
			      </div>
			      <div class="card__expander">
		
			        	<ul>
							<li> 글 1 </li>
							<li> 글 2 </li>
							<li> 글 3 </li>
							<li> 글 4 </li>
							<li> 글 5 </li>
						</ul>
			      </div>
			    </div>
			
			    <div class=" card [ is-collapsed ] ">
			      <div class="card__inner [ js-expander ]">
			        <span> 공지사항 </span>
		
			      </div>
			      <div class="card__expander">
		
			        	<ul>
							<li> 글 1 </li>
							<li> 글 2 </li>
							<li> 글 3 </li>
							<li> 글 4 </li>
							<li> 글 5 </li>
						</ul>
			      </div>
			    </div>
			
			  </div>
		
		</div>
		
		<!-- 하단부 슬라이드 영역 -->
		<div class="slider-wrap" id="card-slider">
	        <div class="slider-item">
	
	            <div class="card_content">
	                <h4 class="card_board title-2"> 게시물 1 </h4>
	                <p class="card_title p-2"> 인기 게시물 제목 정도 </p>
	                <p class="card_author"> 작성자 </p>
	            </div>
	        </div>
	        <div class="slider-item">
	
	            <div class="card_content">
	                <h4 class="card_board title-2"> 게시물 2 </h4>
	                <p class="card_title p-2"> 인기 게시물 제목 정도 </p>
	                <p class="card_author"> 작성자 </p>
	            </div>
	        </div>
	        <div class="slider-item">
	
	            <div class="card_content">
	                <h4 class="card_board title-2"> 게시물 3 </h4>
	                <p class="card_title p-2"> 인기 게시물 제목 정도 </p>
	                <p class="card_author"> 작성자 </p>
	            </div>
	        </div>
	        <div class="slider-item">
	
	            <div class="card_content">
	                <h4 class="card_board title-2"> 게시물 4 </h4>
	                <p class="card_title p-2"> 인기 게시물 제목 정도 </p>
	                <p class="card_author"> 작성자 </p>
	            </div>
	        </div>
	        <div class="slider-item">
	
	            <div class="card_content">
	                <h4 class="card_board title-2"> 게시물 5 </h4>
	                <p class="card_title p-2"> 인기 게시물 제목 정도 </p>
	                <p class="card_author"> 작성자 </p>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
	<script src="<%= path %>/Assets/Script/mainScript/mainPageSc.js"></script>	<!-- 메뉴 애니메이션 처리 -->
</body>
</html>
