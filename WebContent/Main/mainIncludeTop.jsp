<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<% String path = request.getContextPath(); %>

<% 
	Integer user_id = (Integer)session.getAttribute("user_id");
	String email = (String)session.getAttribute("email");
	//System.out.println("로그인된 user_id : " + user_id);
	//System.out.println("로그인된 email : " + email);	
%> 
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Settings</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainTopStyle.css"> <!-- top 페이지 전용 css -->
    
    
</head>
<body>
    <div class="account-container">	<!-- 로그인 중일 때 띄울 것 -->
        <button class="account-btn">
            <span>Account Settings</span>
            <i class="material-icons">public</i> 
            
        <% 			
				if (email != null) {
				    // 세션에 로그인한 사용자 정보가 있을 때
			%>          
	           	<ul class="account-dropdown">
				    <li class="active"><a href="<%=path %>/AccountSettings/MyPage.do">계정 정보</a></li>
				    <li><a href="<%=path %>/UpdatePwd/UpdatePage.do">비밀번호 변경</a></li>
				    <li><a href="#">문의</a></li>
				    <li><a href="<%=path %>/Logout.do">로그아웃</a></li>
				</ul>		
			<% 
				} else {
				    // 세션에 로그인한 사용자 정보가 없을 때
			%>		
			
				<ul class="account-dropdown">
				    <li class="active"><a href="<%=path %>/Login/LoginPage.do">로그인 후 이용이 가능합니다</a></li>
				</ul>
			<% 
				}
		%>	
        </button>
    </div>
    
    <div class="search-container">
        <input type="text" placeholder="search" class="search-input">
        <a class="search-button">
            <i class="fa fa-search"></i>
        </a>
    </div>
    
    <!-- google Custom Search API -->
	<script async src="https://cse.google.com/cse.js?cx=565cf740fc5334d62">
	</script>
	<div class="gcse-search"></div>
    
    <script src="<%= path %>/Assets/Script/mainScript/mainTopSc.js"></script>	<!-- 검색창 위치 이동 애니메이션 처리 -->
</body>
</html>
