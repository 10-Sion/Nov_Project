<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

<c:set  var="contextPath"  value="${pageContext.request.contextPath}"/>
    
<jsp:include page= "/Main/mainNavigate.jsp"/>

<c:set var="currentPage" value="${requestScope.currentPage}" />
<c:set var="nextPage" value="${currentPage + 1}" />
<c:set var="count" value="${requestScope.count}" />
<c:choose>
	<c:when test="${requestScope.msg == 'modified'}">		
		<script>
			window.onload = function(){
				alert("게시판 정보를 수정했습니다.");
			}
		</script>
	</c:when>
	
</c:choose>    


    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Insert title here</title>

	<style type="text/css">
		.cls1{
			font-size: 40px;
			text-align: center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
	</style>

</head>
<body>
	<div class="container">
                   	<div class="row justify-content-center">
                   	
                   	<form class="form-inline w-75" action="${contextPath}/jauBoard/modifyList.do" method="post">
                    <div class="input-group flex-nowrap mt-2 mb-2">
		  <span class="input-group-text" id="addon-wrapping">제목</span>
		  <input type="text" id="LectureName" class="form-control" aria-describedby="addon-wrapping" name="post_title" value="${vo.post_title}">
		</div>
		<div style="display: none">
                   	<input name="post_id" value="${vo.post_id}">
                   	</div>
		<div class="form-floating">
			  <textarea class="form-control" id="mainText" style="height: 300px; resize: none" name="post_content" >${vo.post_content}</textarea>
			  <label for="floatingTextarea2"></label>
		</div>
		
		<div class="col text-center" id="reflectedList">
			<input type="text" value="" id="userName" hidden="">
			<input type="submit" class="btn btn-secondary btn-sm" value="수정하기" id="reflected">
			<a type="button" href="${contextPath}/jauBoard/delMoonUiList.do?post_id=${vo.post_id}" class="btn btn-secondary btn-sm" id="del">삭제하기</a>
			<a type="button" href="${contextPath}/jauBoard/backList.do" class="btn btn-secondary btn-sm" id="cancel">리스트로 돌아가기</a>
		</div>
		<br><br>
		</form>
		
		<!-- 댓글쓰는 칸이얌 -->
		<form method="post"  action="" class="form-inline w-75">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br><br></td>
					<td><input type="text" style="height:100px;" class="form-control" placeholder="상대방을 존중하는 댓글을 남깁시다." name = "commentText"></td>
					<td><br><br><input type="submit" class="btn btn-secondary btn-sm" value="댓글 작성"></td>
				</tr>
				
			</table>
		</form>
		
		<!-- 댓글쓰면 여기로!!!! -->
		<form method="post"  action="" class="form-inline w-75">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;"><br><br></td>
					<td>작성자<br><br>작성일시</td>
			
					<td><input type="text" style="height:100px;" class="form-control" name = "commentText"></td>
					<td><br><br>
						<a type="button" class="btn btn-secondary btn-sm">댓글 수정</a>
						<a type="button" class="btn btn-secondary btn-sm">댓글 삭제</a>
					</td>
				</tr>
			</table>
		</form>
		
		
	</div>
	</div>
	
	
		
	
						
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>	
</body>
</html>









