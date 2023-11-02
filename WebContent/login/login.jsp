<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 페이지</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			//일반회원 로그인 화면 기본적용, 관리자 버튼 클릭 시 관리자 로그인 화면 전환됩니다.
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
		
		<style>
		
			body {
			  display: flex;
			  justify-content: center;
			  align-items: center;
			  height: 100vh;
			  margin: 0;
			}		
		
			.iLogin, .mLogin {
			  display: flex;
			  flex-direction: column;
			  gap: 10px;
			  max-width: 350px;
			  background-color: #fff;
			  padding: 20px;
			  border-radius: 20px;
			  position: relative;
			}
			
			.title {
			  font-size: 28px;
			  color: royalblue;
			  font-weight: 600;
			  letter-spacing: -1px;
			  position: relative;
			  display: flex;
			  align-items: center;
			  padding-left: 30px;
			}
			
			.title::before,.title::after {
			  position: absolute;
			  content: "";
			  height: 16px;
			  width: 16px;
			  border-radius: 50%;
			  left: 0px;
			  background-color: royalblue;
			}
			
			.title::before {
			  width: 18px;
			  height: 18px;
			  background-color: royalblue;
			}
			
			.title::after {
			  width: 18px;
			  height: 18px;
			  animation: pulse 1s linear infinite;
			}
			
			.message, .signin {
			  color: rgba(88, 87, 87, 0.822);
			  font-size: 14px;
			}
			
			.signin {
			  text-align: center;
			}
			
			.signin a {
			  color: royalblue;
			}
			
			.signin a:hover {
			  text-decoration: underline royalblue;
			}						
			
			.iLogin label, .mLogin label{
			  position: relative;
			}
			
			.iLogin label .input, .mLogin label .input{
			  width: 100%;
			  padding: 10px 10px 20px 10px;
			  outline: 0;
			  border: 1px solid rgba(105, 105, 105, 0.397);
			  border-radius: 10px;
			}
			
			.iLogin label .input + span, .mLogin label .input + span{
			  position: absolute;
			  left: 10px;
			  top: 15px;
			  color: grey;
			  font-size: 0.9em;
			  cursor: text;
			  transition: 0.3s ease;
			}
			
			.iLogin label .input:placeholder-shown + span, .mLogin label .input:placeholder-shown + span{
			  top: 15px;
			  font-size: 0.9em;
			}
			
			.iLogin label .input:focus + span, .iLogin label .input:valid + span, .mLogin label .input:focus + span, mLogin label .input:valid + span{
			  top: 30px;
			  font-size: 0.7em;
			  font-weight: 600;
			}
			
			.iLogin label .input:valid + span, .mLogin label .input:valid + span{
			  color: green;
			}
			
			.submit {
			  border: none;
			  outline: none;
			  background-color: royalblue;
			  padding: 12px;
			  border-radius: 15px;
			  color: #fff;
			  font-size: 16px;
			  transform: .3s ease;
			}
			
			.submit:hover {
			  background-color: rgb(56, 90, 194);
			}
			
			@keyframes pulse {
			  from {
			    transform: scale(0.9);
			    opacity: 1;
			  }
			
			  to {
			    transform: scale(1.8);
			    opacity: 0;
			  }
			}			
		</style>
		
	</head>
	<body>
		
		<!-- 회원가입, 로그인 폼 둘 다 관리자버전도 만들어 두었으나 큰 필요성을 못 느껴 버튼 주석처리해둠 -->
<!-- 		<div id="loginBtn">  -->
<!-- 			<button class="user_role" value="일반회원">일반회원</button> -->
<!-- 			<button class="user_role" value="관리자">관리자</button> -->
<!-- 		</div>		 -->
		
		<!-- 일반회원 로그인 폼 -->
    	<form action="<%=path %>/Login/Login.do" class="iLogin" method="post">
		    <p class="title">로그인 </p>		   
		    <p class="message">지금 로그인하고 커뮤니티에 참여해보세요. </p>   
		    <label>
		        <input id="email" name="email" required="" placeholder="" type="email" class="input">
		        <span>이메일</span>
		    </label> 	        
		    <label>
		        <input id="password" name="password" required="" placeholder="" type="password" class="input">
		        <span>비밀번호</span>
		    </label>
			<button class="submit">로그인하기</button>			    
		    <p class="signin">가입된 계정이 없으신가요? <a href="<%=path%>/Join/JoinPage.do">회원가입하기</a> </p>
		</form>
			
			
				
		<!-- 관리자 로그인 폼 -->
    	<form action="<%=path %>/Login/Login.do" class="mLogin" method="post">
		    <p class="title">로그인 </p>		   
		    <p class="message">관리자 로그인 폼. </p>  
		    <label>
		        <input id="email" name="email" required="" placeholder="" type="email" class="input">
		        <span>이메일</span>
		    </label> 		        
		    <label>
		        <input id="password" name="password" required="" placeholder="" type="password" class="input">
		        <span>비밀번호</span>
		    </label>		    
			<button class="submit">로그인하기</button>			 		   
		    <p class="signin">가입된 계정이 없으신가요? <a href="<%=path%>/Join/JoinPage.do">회원가입하기</a> </p>
		</form>	
		
	</body>
</html>