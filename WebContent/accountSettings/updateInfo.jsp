<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="user_id" value="${sessionScope.user_id}" />
<c:set var="username" value="${sessionScope.username}" />
<c:set var="email" value="${sessionScope.email}" />
<c:set var="password" value="${sessionScope.password}" />


<jsp:include page="../Main/mainNavigate.jsp"/> <!-- 좌측 메뉴 페이지 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정 화면</title>
<style type="text/css">

    body {
        background: #eceef1;
    }

    .inner {
        margin-left: 300px;
        max-width: calc(100% - 300px);
    }

    table.inner {
        width: 800px;
        height: 800px;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: white;
    }

    table.inner td {
        width: 200px;
        height: 80px;
        padding: 30px;
        border: 1px solid #ddd;
        text-align: left; /* 텍스트를 왼쪽 정렬 */
        transition: background-color 0.3s;
        font-size: 30px;
        cursor: pointer; /* 포인터 모양으로 변경하여 클릭 가능함을 나타냄 */
    }

    table.inner td:hover {
        background-color: #f0f0f0;
    }

    table.inner td b {
        margin-left: 50px; /* 여백을 조절할 수 있는 값으로 변경 */          
        color: #007BFF;
    }
    
    table.inner td a {
        text-decoration: none;
        color: black;
        display: inline-block; /* 블록 요소 대신 인라인 블록으로 변경 */
        margin-left: 50px; /* 여백을 조절할 수 있는 값으로 변경 */
    }


    table.inner td a:hover {
        text-decoration: none;
        color: #007BFF;
    }

    .mod{
        color: black;
        font-size: 35px;
        margin-left: 300px;
        font-weight: bold;
    }
    
    p {
     	color: black;
        font-size: 20px;
    }
    
    .input {
        max-width: 300px;
        height: 50px;
        background-color: #05060f0a;
        border-radius: .5rem;
        padding: 0 1rem;
        border: 2px solid transparent;
        font-size: 1rem;
        transition: border-color .3s cubic-bezier(.25,.01,.25,1) 0s, color .3s cubic-bezier(.25,.01,.25,1) 0s,background .2s cubic-bezier(.25,.01,.25,1) 0s;
        margin-left: 30px;
    }
        
    .label {
        display: block;
        margin-bottom: .5rem;
        font-size: 1rem;
        font-weight: bold;
        color: #05060f99;
        transition: color .3s cubic-bezier(.25,.01,.25,1) 0s;
        margin-left: 30px;
    }
        
    .input:hover, .input:focus, .input-group:hover .input {
        outline: none;
        border-color: #05060f;
    }
        
    .input-group:hover .label, .input:focus {
        color: #05060fc2;
    }
    
	   button {
		 display: flex;
		 height: 3em;
		 width: 100px;
		 align-items: center;
		 justify-content: center;
		 background-color: #eeeeee4b;
		 border-radius: 3px;
		 letter-spacing: 1px;
		 transition: all 0.2s linear;
		 cursor: pointer;
		 border: none;
		 background: #fff;
		}
		
		button > svg {
		 margin-right: 5px;
		 margin-left: 5px;
		 font-size: 20px;
		 transition: all 0.4s ease-in;
		}
		
		button:hover > svg {
		 font-size: 1.2em;
		 transform: translateX(-5px);
		}
		
		button:hover {
		 box-shadow: 9px 9px 33px #d1d1d1, -9px -9px 33px #ffffff;
		 transform: translateY(-2px);
		}
		
	    .button-container {
	        display: flex;
	        justify-content: space-between; /* 가로로 버튼을 분산 배치합니다. */
	        margin-top: 10px; /* 원하는 간격으로 조절합니다. */
	    }
	
	    .button-container button {
	        /* 버튼 스타일을 추가할 수 있습니다. */
	          margin-right: 250px; /* 현재는 5px이었던 값을 더 크게 조정합니다. */
	    }
  
    </style>
</head>
<body>
    <p class="mod">개인정보수정</p>
    <form action="${contextPath}/UpdateInfo/UpdateInfo.do" method="post" onsubmit="return validateForm();">
        <table class="inner"> 
            <input type="hidden" name="user_id" value="${user_id}"/>            
            <tr>
                <td>
                    <div class="input-group">
                        <label class="label">이메일</label>
                        <input class="input" autocomplete="off" id="email" name="email" required="required" type="email" value="${email}">
                    </div>
                </td>            
            </tr>
            <tr>
                <td>
                    <div class="input-group">
                        <label class="label">비밀번호</label>
                        <input class="input" autocomplete="off" id="password" name="password" required="required" type="password" class="input" value="${password}">
                    </div>
                </td>                    
            </tr>
            <tr>
                <td>
                    <div class="input-group">
                        <label class="label">이름</label>
                        <input class="input" autocomplete="off" id="username" name="username" required="required" type="text" value="${username}">
                    </div>
                </td>
            </tr>
            <tr>
                <td>                                
				<div class="button-container">
					<!-- 뒤로가기 버튼 -->
					<button type="button" onclick="goBack()">
					    <svg height="24" width="24" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 1024 1024"><path d="M874.690416 495.52477c0 11.2973-9.168824 20.466124-20.466124 20.466124l-604.773963 0 188.083679 188.083679c7.992021 7.992021 7.992021 20.947078 0 28.939099-4.001127 3.990894-9.240455 5.996574-14.46955 5.996574-5.239328 0-10.478655-1.995447-14.479783-5.996574l-223.00912-223.00912c-3.837398-3.837398-5.996574-9.046027-5.996574-14.46955 0-5.433756 2.159176-10.632151 5.996574-14.46955l223.019353-223.029586c7.992021-7.992021 20.957311-7.992021 28.949332 0 7.992021 8.002254 7.992021 20.957311 0 28.949332l-188.073446 188.073446 604.753497 0C865.521592 475.058646 874.690416 484.217237 874.690416 495.52477z"></path></svg>
					    <span style="font-size: 18px;">Back</span>
					</button>				
					<!-- 변경하기 버튼 -->
					<button type="submit">
					    <p>변경하기</p>
					</button>		    
				</div>
                </td>
            </tr>
        </table>        
    </form>
    <!-- Footer -->
    <div id="footer">
        <jsp:include page="/Main/footer.jsp" />
    </div>
	<script>
	
	    function validateForm() {
	        // 비밀번호 유효성 검사
	        var password = document.getElementById("password").value;
	        if (password.length < 6 || password.length > 16) {
	            alert('비밀번호는 6자 이상, 16자 이하로 입력해야 합니다.');
	            return false;
	        }
	
	        // 기존 비밀번호와 동일한 경우 경고 메시지 출력
	        var currentPassword = "${password}"; // 기존 비밀번호를 서버에서 가져와서 설정
	        var submitButton = document.activeElement;
	
	        console.log("submitButton:", submitButton); // 콘솔에 submitButton 정보 출력
	
	        if (submitButton && submitButton.type === "submit") {
	            if (password === currentPassword) {
	                var userResponse = confirm('현재 사용 중인 비밀번호와 동일합니다. 그래도 변경하시겠습니까?');
	                if (!userResponse) {
	                    return false; // 사용자가 취소한 경우 수정을 중지
	                }
	            }
	        }
	
	        // 모든 유효성 검사 통과
	        return true;
	    }
	
	    function goBack() {    
	        window.history.back();
	        
	    }
	</script>

</body>
</html>
