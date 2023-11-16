<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

<c:set  var="contextPath"  value="${pageContext.request.contextPath}"/>
<%    
	String comment_id =request.getParameter("comment_id");
	String user_name = request.getParameter("user_name");
	String comment_date =request.getParameter("comment_date");
	String comment_text = request.getParameter("comment_text");
%>


<c:choose>
	<c:when test="${requestScope.msg == 'modifyOk'}">		
		<script>
			window.onload = function(){
				alert("댓글을 수정했습니다.");
				opener.document.location.reload();
				window.close();
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
<title>댓글 수정</title>

	<style type="text/css">
		.cls1{
			font-size: 40px;
			text-align: center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
		#menu {  z-index: 3;  }
	</style>

</head>
<body>
	<div class="container">
                   	<div class="row justify-content-center">
                   	<h2>댓글 수정</h2>
                   	<hr>
   	 <form class="form-inline w-75" action="${contextPath}/jauBoard/modifyComment.do" method="post">
          <div class="input-group flex-nowrap mt-2 mb-2">
 	      <input type="text" id="comment_id" class="form-control" aria-describedby="addon-wrapping" name="comment_id" value="<%=comment_id%>">
          
		  <span class="input-group-text" id="addon-wrapping">작성자</span>
		  <input type="text" id="user_name" class="form-control" aria-describedby="addon-wrapping" name="user_name" value="<%=user_name%>" readonly="readonly">
		   
		 
		</div>
		
		<div class="input-group flex-nowrap mt-2 mb-2">
		<span class="input-group-text" id="addon-wrapping">작성일시</span>
		  <input type="text" id="comment_date" class="form-control" aria-describedby="addon-wrapping" name="comment_date" value="<%=comment_date%>" readonly="readonly">
		</div>
		
		<div class="form-floating">
			  <textarea class="form-control" id="comment_text" style="height: 200px; resize: none" name="comment_text" ><%=comment_text%></textarea>
		</div>
		
		<div class="col text-center" id="reflectedList">
			<input type="text" value="" id="userName" hidden="">
			<input type="submit" class="btn btn-primary btn-sm" value="수정하기" id="reflected">
			<a type="button" class="btn btn-primary btn-sm" id="cancel" onclick="window.close();">창닫기</a>
		</div>
	</form>
	</div>
	</div>
						
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>	
	
	
</body>
</html>









