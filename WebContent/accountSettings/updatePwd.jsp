<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>

<% String path = request.getContextPath(); %>

<% 
	Integer user_id = (Integer)session.getAttribute("user_id");
	String email = (String)session.getAttribute("email");
	String originPwd = (String) session.getAttribute("password"); // 현재 사용자의 비밀번호 가져오기
	//System.out.println("로그인된 user_id : " + user_id);
	//System.out.println("로그인된 email : " + email);	
	
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 변경 화면</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">		
		    // 새 비밀번호, 새 비밀번호 확인 칸의 글자가 일치하는지 확인하는 함수
		    function check() {
		        var newPwd = document.getElementById('newPwd').value;
		        var newPwd_check = document.getElementById('newPwd_check').value;
		        var message = document.querySelector('.message'); // 'message' 클래스를 가진 엘리먼트 선택
		        var originPwd = "<%= originPwd %>"; // 서버로부터 받은 originPwd 값 사용

		        if (newPwd.length < 6 || newPwd.length > 16) {
		            message.textContent = '새 비밀번호는 6자 이상, 16자 이하만 가능합니다.';
		            message.style.color = 'red'; 

		        } else if (newPwd !== newPwd_check) {
		            message.textContent = '비밀번호가 일치하지 않습니다.';
		            message.style.color = 'red'; 

		        } else if (newPwd === originPwd) {
		        	message.textContent = '기존 비밀번호와 동일합니다.';
		            message.style.color = 'red'; 
		        	
		        } else {		        	
		            message.textContent = '비밀번호 변경이 가능합니다.';		            
		            message.style.color = 'green'; 
		        }
		    }
		        
			// 가입하기 전 제출하는 폼의 양식이 올바른지 검증해보는 함수
		    function validateForm() {				 
		        var newPwd = document.getElementById('newPwd').value;
		        var newPwd_check = document.getElementById('newPwd_check').value;

		        if (newPwd.length < 6 || newPwd.length > 16) {
		            alert('새 비밀번호는 6자 이상, 16자 이하로 입력해야 합니다.');
		            return false; 
		        } else if (newPwd !== newPwd_check) {
		            alert('비밀번호가 일치하지 않습니다.');
		            return false; 
		        } else if (newPwd === originPwd) {
		        	alert('기존 비밀번호로 변경하실 수 없습니다.');
		        	return false; 
		        }// else if ~ return false; 구문을 추가해서 검증할 부분을 더 넣을 수 있음		        
		        
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
		
			.updatePwd, .mLogin {
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
			
			.updatePwd label{
			  position: relative;
			}
			
			.updatePwd label .input{
			  width: 100%;
			  padding: 10px 10px 20px 10px;
			  outline: 0;
			  border: 1px solid rgba(105, 105, 105, 0.397);
			  border-radius: 10px;
			}
			
			.updatePwd label .input:focus-within{
			  border: 1.5px solid #2d79f3;
			}
			
			.updatePwd label .input + span{
			  position: absolute;
			  left: 10px;
			  top: 15px;
			  color: grey;
			  font-size: 0.9em;
			  cursor: text;
			  transition: 0.3s ease;
			}
			
			.updatePwd label .input:placeholder-shown + span{
			  top: 15px;
			  font-size: 0.9em;
			}
			
			.updatePwd label .input:focus + span, .updatePwd label .input:valid + span{
			  top: 30px;
			  font-size: 0.7em;
			  font-weight: 600;
			}
			
			.updatePwd label .input:valid + span{
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
				
			.input:focus-within {
			  border: 1.5px solid #2d79f3;
			}	
		</style>
	</head>
	<body>		
	<div onclick="check()">
		<form action="<%=path %>/UpdatePwd/Update.do" class="updatePwd" method="post" onsubmit="return validateForm();">
		    <p class="title">비밀번호 변경 </p>		   
		    <p class="message">새 비밀번호를 입력해주세요. </p> 
		    <label>
		        <input id="newPwd" name="newPwd" required="" placeholder="" type="password" class="input">
		   		<span>새 비밀번호</span>		   
		    </label>  		         
		    <label>	
			    <input id="newPwd_check" name="newPwd_check" required="" placeholder="" type="password" class="input">			       
		   		<span>새 비밀번호 확인</span>
		    </label>	  
			<button class="submit">변경하기</button>			    
		    <p class="signin">변경을 원하지 않으세요? <a href="javascript:history.back();">뒤로 가기</a> </p>
		</form>
	</div>	
	</body>
</html>