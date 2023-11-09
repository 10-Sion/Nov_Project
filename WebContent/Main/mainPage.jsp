<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>	<!-- contextPath 변수 -->
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:include page= "./mainNavigate.jsp"/>	<!-- 좌측 메뉴 페이지 -->
<jsp:include page= "./mainIncludeTop.jsp"/>	<!-- 상단부 로그인 정보 처리 페이지 -->


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 메인 페이지 </title>
    
	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainPageStyle.css"> <!-- main 페이지 전용 css -->
</head>
<body>
    <div class="cardSection">

	  <div class="header">
	    <h2 class="header__subtitle"> 뭐로 하지 </h2>
	  </div>
	 
	  <div class="cards">
	 
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
	      </div>
	    </div>
	
	    <div class=" card [ is-collapsed ] ">
	      <div class="card__inner [ js-expander ]">
	        <span>Card</span>

	      </div>
	      <div class="card__expander">

	        Expander
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
