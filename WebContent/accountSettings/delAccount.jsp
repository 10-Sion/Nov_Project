<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="user_id" value="${sessionScope.user_id}" />
<c:set var="username" value="${sessionScope.username}" />
<c:set var="email" value="${sessionScope.email}" />

<jsp:include page="../Main/mainNavigate.jsp"/> <!-- 좌측 메뉴 페이지 -->
	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원탈퇴 화면</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" />	   
    <style type="text/css">
        body {
            background: #eceef1;
        }

        .inner {
            margin-left: 300px;
            max-width: calc(100% - 300px);
        }

        table.inner {
            width: 1000px;
            height: 850px;
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
            font-size: 25px;
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

        p {
            color: black;
            font-size: 30px;
            margin-left: 300px;
            font-weight: bold;
        }
        
    </style>
</head>
<body>
    <p>회원탈퇴</p>   
    <div id="container">
	   <form action="${contextPath}/DelAccount/DelAccount.do" method="post">
        <table style="text-align:center; border: 1px solid #ddddddd">
            <thead>
                <tr>
                    <th colspan="3"><h4>회원탈퇴</h4></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td style="width: 110px"><h5>이메일</h5></td>
                    <td>
                        <h5>${email}</h5>
                        <input type="hidden" name="email" value="${email}">
                    </td>
                </tr>
                <tr>
                    <td style="width: 110px"><h5>비밀번호 입력 : </h5></td>
                    <td colspan="3"><input id="userPassword" type="password" name="password" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
  						<input type="submit" value="회원탈퇴"/>&nbsp;&nbsp;						
  						<input type="reset" value="취소" onclick="location.href='${contextPath}/accountSettings/myPage.jsp'"/>
                    </td>
                    <td style="text-align: left;" colspan="3">
                        <!-- 성공 또는 실패 메시지 출력 -->
                        <c:if test="${not empty requestScope.successMessage}">
                            <h5 style="color: green;">${requestScope.successMessage}</h5>
                        </c:if>
                        <c:if test="${not empty requestScope.errorMessage}">
                            <h5 style="color: red;">${requestScope.errorMessage}</h5>
                        </c:if>
                    </td>                       
                </tr>
            </tbody>  
        </table>
        </form>
    </div>
    <!-- Footer -->
    <div id="footer"> 
        <jsp:include page="/Main/footer.jsp" />
    </div>
</body>
</html>
