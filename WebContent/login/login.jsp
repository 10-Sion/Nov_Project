<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 화면</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		
		<script type="text/javascript">
			//일반회원 로그인 화면 기본적용, 관리자 버튼 클릭 시 관리자 로그인 화면 전환
			$(function() {				
				$('.mLogin').hide();
								
				$('#loginBtn').children().on('click', function(e) {
					if($(this).val() == '관리자') {
						$('.iLogin').hide();
						$('.mLogin').show();
					} else {
						$('.mLogin').hide();
						$('.iLogin').show();
					}
				});
			});			
		</script>		
		
	</head>
	<body>
		
		<div id="loginBtn"> 
			<button class="user_role" value="일반회원">일반회원</button>
			<button class="user_role" value="관리자">관리자</button>
		</div>
		
		<!-- 일반회원 로그인 시 -->
		<form action="<%=path %>/Login/login.do" class="iLogin" method="post">		
			<div>
                <label for="id">아이디</label>
                <input id="user_id" type="text" name="user_id" required/>
            </div>
            <div>
                <label for="pwd">비밀번호</label>
                <input id="password" type="password" name="password" required />
            </div>
            
            <input id="user_role" type="hidden" name="user_role" value="일반회원">
         
           	<input type="submit" value="로그인">
           	<input type="reset" value="다시작성">              
		</form>
		
		<!-- 관리자 로그인 시 -->
		<form action="<%=path %>/Login/login.do" class="mLogin" method="post">		
			<div>
                <label for="id">관리자 아이디</label>
                <input id="user_id" type="text" name="user_id" required/>
            </div>
            <div>
                <label for="pwd">비밀번호</label>
                <input id="password" type="password" name="password" required />
            </div>
            
            <input id="user_role" type="hidden" name="user_role" value="관리자">
         
           	<input type="submit" value="로그인">
           	<input type="reset" value="다시작성">              
		</form>
		
	</body>
</html>