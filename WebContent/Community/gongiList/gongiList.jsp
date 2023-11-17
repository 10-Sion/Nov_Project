<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %> 

<c:set var="user_id" value="${sessionScope.user_id}" />
<c:set var="grade_id" value="${sessionScope.grade_id}" />
<c:set var="email" value="${sessionScope.email}" />

<c:set  var="contextPath"  value="${pageContext.request.contextPath}"/>
    
<jsp:include page= "/Main/mainNavigate.jsp"/>
<jsp:include page= "/Main/mainIncludeTop.jsp"/>	<!-- 상단부 로그인 정보 처리 페이지 -->

<c:set var="currentPage" value="${requestScope.currentPage}" />
<c:set var="nextPage" value="${currentPage + 1}" />
<c:set var="count" value="${requestScope.count}" />
<c:choose>	
	<c:when test="${requestScope.msg == 'deleted'}">		
		<script>
			window.onload = function(){
				alert("게시판 정보를 삭제했습니다.");
			}
		</script>
	</c:when>	
</c:choose>    


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>공지사항</title>

	<style type="text/css">
		.cls1{
			font-size: 40px;
			text-align: center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
		#menu {  z-index: 3;  }	.board {  top: 35%;  position: relative; z-index:0;  }
	</style>

</head>

<body>
	<div class="board">
	
		<p class="cls1">공지 사항</p>
		
		<table class="pagination justify-content-center" style="float: right;">
			<tr>
				<c:choose>
				  	<c:when test="${sessionScope.grade_id == 4}">
				    	<td><a href="${contextPath}/gongiBoard/addListForm.do" type="button" class="btn btn-primary" id="searchBtn">공지작성</a></td>
				  	</c:when>
				</c:choose>
			</tr>
		</table>
		
		<table align="center" class="table table-striped">
			<tr align="center" >
				<td width="7%"><b>글번호</b></td>
				<td width="7%"><b>글제목</b></td>
				<td width="7%"><b>작성자</b></td>
				<td width="7%"><b>조회수</b></td>
				<c:choose>
				  	<c:when test="${sessionScope.grade_id == 4}">
				    	<td width="7%"><b>삭제</b></td>
				  	</c:when>
				</c:choose>					
			</tr>			
		<c:choose>  			  
			 <c:when test="${empty requestScope.membersList}"><%-- request에 바인딩된  ArrayList배열이 없으면?(조회된 정보가 없으면?)  --%>
			 	<tr align="center">
			 		<td colspan="5">
			 			<b>등록된 글이 없습니다.</b>
			 		</td>
			 	</tr>
			 </c:when>                  
			 <c:when test="${not empty requestScope.membersList}"> <%--request에 바인딩된 ArrayList배열이 있으면?(조회된 정보가 있으면?) --%>
			 	<%-- request에 바인딩된 ArrayList배열을 꺼내오고 MemberVO객체의 갯수만큼 반복해서 얻어 출력 --%>
			 	<c:forEach  var="mem"   items="${requestScope.membersList}"  >
			 		<tr align="center">
			 			<td>${mem.announcement_id}</td>
			 			<td><a href="${contextPath}/gongiBoard/detailList.do?announcement_id=${mem.announcement_id}">${mem.post_title}</a></td>
			 			<td>관리자</td>
			 			<td>${mem.view_count}</td>	 			
						<c:choose>
						  	<c:when test="${sessionScope.grade_id == 4}">
						    	<td><a href="${contextPath}/gongiBoard/delGongiList.do?announcement_id=${mem.announcement_id}">삭제</a></td>
						  	</c:when>
						</c:choose>	 		
					</tr>
			 	</c:forEach>
			 </c:when>
		</c:choose>		
		</table>
		
		   <!-- 페이징 컨트롤 부분 -->
			  <nav aria-label="Page navigation example">
			     <ul class="pagination justify-content-center">
			        <c:set var="pageSize" value="${pageSize}" />
			        <c:set var="currentPage" value="${pageNum}" />
			        <c:set var="totalPages" value="${(count + pageSize - 1) / pageSize}" />
			
			        
			
			        <c:forEach var="i" begin="1" end="${totalPages}">
			            <c:choose>
			                <c:when test="${i == currentPage}">
			                    <li class="page-item active" aria-current="page">
			                        <a class="page-link" href="#">${i}</a>
			                    </li>
			                </c:when>
			                <c:otherwise>
			                    <li class="page-item">
			                        <a class="page-link" href="${contextPath}/gongiBoard/gongiList.do?pageNum=${i}">${i}</a>
			                    </li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
				</ul>
			</nav>
		
		<!-- Footer -->
		<div id="footer">
			<jsp:include page="/Main/footer.jsp" />
		</div>
	
	</div>
	
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>	
</body>
</html>









