<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>	<!-- contextPath 변수 -->
<% request.setCharacterEncoding("UTF-8"); %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 메인 페이지 </title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    
	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainPageStyle.css"> <!-- main 페이지 전용 css -->
	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainBuildings.css"> <!-- main 상단 빌딩 영역 -->
	
</head>
<body>
	<div class="topSection">
		<div class="realTop">
			<jsp:include page= "./mainIncludeTop.jsp"/>	<!-- 상단부 로그인 정보 처리 페이지 -->
		</div>
			<div class="fakeTop">
			
				  <div class="wrap">
				    <div class="grid slide">
						<%
						    String[] buildingClasses = {"class1", "class2", "class3", "class4", "class5", "class6", "class7", "class8", "class9", "class10", "class11"};
						
						    for (int i = 0; i < 32; i++) {
						        int buildingIndex = i % buildingClasses.length;
						        String currentClass = buildingClasses[buildingIndex];
						%>
						        <div class="cell <%= currentClass %>"></div>
						<%
						    }
						%>
				
				    </div>
				  </div>
				  
			</div>
		
		<div class="sideNav">
			<jsp:include page= "./mainNavigate.jsp"/>	<!-- 좌측 메뉴 페이지 -->
		</div>
		
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
		
		<!-- 하단부 추천 게시물 -->
	    <div class="notification" id="note-1">
	        <div class="notification__box">
	            <div class="notification__content">
	                <div class="notification__icon">
	                    <svg class="notification__icon-svg" role="img" aria-label="success" width="32px" height="32px">
	                        <use xlink:href="#success"></use>
	                    </svg>
	                </div>
	                <div class="notification__text">
	                    <div class="notification__text-title"> 추천 게시물들을 보여드릴게요! </div>
	                </div>
	            </div>
	            <div class="notification__btns">
	                <button class="notification__btn" type="button" data-dismiss="note-1">
	                    <span class="notification__btn-text"> 아하! </span>
	                </button>
	            </div>
	        </div>
	    </div>
	</div>

	<!-- Footer -->
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
	<script src="<%= path %>/Assets/Script/mainScript/mainPageSc.js"></script>	<!-- 메뉴 애니메이션 처리 -->
	<script src="<%= path %>/Assets/Script/mainScript/mainBuildings.js"></script> <!-- 빌딩용 애니메이션 처리 -->
</body>
</html>
