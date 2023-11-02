<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 페이지</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
		<script type="text/javascript">		
			//일반회원 회원가입 화면 기본적용, 관리자 버튼 클릭 시 관리자 회원가입 화면 전환됩니다.
			$(function() {				
				$('.mJoin').hide();
								
				$('#joinBtn').children().on('click', function(e) {
					if($(this).val() == '관리자') {
						$('.iJoin').hide();
						$('.mJoin').show();
					} else {
						$('.mJoin').hide();
						$('.iJoin').show();
					}
				});
			});			
		</script>
			
		<script type="text/javascript">		
		    // 비밀번호, 비밀번호 확인 칸의 글자가 일치하는지 확인하는 함수
		    function check() {
		        var password = document.getElementById('password').value;
		        var password_check = document.getElementById('password_check').value;
		        var message = document.querySelector('.message'); // 'message' 클래스를 가진 엘리먼트 선택
		        
		        if (password.length < 6 || password.length > 16) {
		            message.textContent = '비밀번호는 6자 이상, 16자 이하만 가능합니다.';
		            message.style.color = 'red'; // 빨간색으로 표시

		        } else if (password !== password_check) {
		            message.textContent = '비밀번호가 일치하지 않습니다.';
		            message.style.color = 'red'; // 빨간색으로 표시

		        } else {		        	
		            message.textContent = '가입하기를 눌러 커뮤니티를 이용해보세요.';		            
		            message.style.color = 'green'; // 녹색으로 표시
		        }
		    }
		        
			// 가입하기 전 제출하는 폼의 양식이 올바른지 검증해보는 함수
		    function validateForm() {				 
		        var password = document.getElementById('password').value;
		        var password_check = document.getElementById('password_check').value;

		        if (password.length < 6 || password.length > 16) {
		            alert('비밀번호는 6자 이상, 16자 이하로 입력해야 합니다.');
		            return false; // 폼 제출을 막음
		        } else if (password !== password_check) {
		            alert('비밀번호가 일치하지 않습니다.');
		            return false; // 폼 제출을 막음
		        } // else if ~ return false; 구문을 추가해서 검증할 부분을 더 넣을 수 있음		        
		        
		        return true; // 폼 제출을 허용
		    }
		</script>

		
		<style>
			body {
			  display: flex;
			  justify-content: center;
			  align-items: center;
			  height: 100vh;
			  margin: 0;
			}
			 
			.iJoin, .mJoin {
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
			
			.iJoin label, .mJoin label{
			  position: relative;
			}
			
			.iJoin label .input, .mJoin label .input{
			  width: 100%;
			  padding: 10px 10px 20px 10px;
			  outline: 0;
			  border: 1px solid rgba(105, 105, 105, 0.397);
			  border-radius: 10px;
			}
			
			.iJoin label .input + span, .mJoin label .input + span{
			  position: absolute;
			  left: 10px;
			  top: 15px;
			  color: grey;
			  font-size: 0.9em;
			  cursor: text;
			  transition: 0.3s ease;
			}
			
			.iJoin label .input:placeholder-shown + span, .mJoin label .input:placeholder-shown + span{
			  top: 15px;
			  font-size: 0.9em;
			}
			
			.iJoin label .input:focus + span,.iJoin label .input:valid + span, .mJoin label .input:focus + span, mJoin label .input:valid + span{
			  top: 30px;
			  font-size: 0.7em;
			  font-weight: 600;
			}
			
			.iJoin label .input:valid + span, .mJoin label .input:valid + span{
			  color: green;
			}
			
			.submit {
			  border: none;
			  outline: none;
			  background-color: royalblue;
			  padding: 10px;
			  border-radius: 10px;
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
<!-- 		<div id="joinBtn">  -->
<!-- 			<button class="user_role" value="일반회원">일반회원</button> -->
<!-- 			<button class="user_role" value="관리자">관리자</button> -->
<!-- 		</div> -->
    	<div onclick="check()">
    		<!-- 일반회원 회원가입 폼 -->
	    	<form action="<%=path %>/Join/JoinI.do" class="iJoin" method="post" onsubmit="return validateForm();">
			    <p class="title">회원가입 </p>
			    <p class="message">지금 가입하고 커뮤니티에 참여해보세요. </p>        
		        <label>
		            <input id="username" name="username" required="" placeholder="" type="text" class="input">
		            <span>이름</span>
		        </label>
			       
			    <label>
			        <input id="email" name="email" required="" placeholder="" type="email" class="input">
			        <span>이메일</span>
			    </label> 
			        
			    <label>
			        <input id="password" name="password" required="" placeholder="" type="password" class="input">
			        <span>비밀번호</span>
			    </label>
			    <label>
			        <input id="password_check" required="" placeholder="" type="password" class="input">
			        <span>비밀번호 확인</span>
			    </label>
			    <button class="submit">가입하기</button>
			    <p class="signin">이미 가입한 계정이 있으신가요? <a href="<%=path%>/Login/LoginPage.do">로그인하기</a> </p>
			</form>
		</div>
		
		
		<!-- 관리자 회원가입 폼 -->
    	<form action="<%=path %>/Join/JoinM.do" class="mJoin" method="post">
		    <p class="title">회원가입 </p>
		    <p class="message">관리자 회원가입 폼 </p>
	        <label>
	            <input id="username" name="username" required="" placeholder="" type="text" class="input">
	            <span>이름</span>
	        </label>
		       
		    <label>
		        <input id="email" name="email" required="" placeholder="" type="email" class="input">
		        <span>이메일</span>
		    </label> 
		        
		    <label>
		        <input id="password" name="password" required="" placeholder="" type="password" class="input">
		        <span>비밀번호</span>
		    </label>
		    <label>
		        <input id="password_check"required="" placeholder="" type="password" class="input">
		        <span>비밀번호 확인</span>
		    </label>
		    <button class="submit">가입하기</button>
		    <p class="signin">이미 가입한 계정이 있으신가요? <a href="<%=path%>/Login/LoginPage.do">로그인하기</a> </p>
		</form>
    	 
	</body>
</html>