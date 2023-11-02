<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

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

<title>건의사항</title>

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
	
	<p class="cls1">건의 사항</p>
	
	<table class="pagination justify-content-center" style="float: right;">
		<tr>
			<td><a href="${contextPath}/moonUiBoard/addListForm.do" type="button" class="btn btn-primary" id="searchBtn">글쓰기</a></td>
		</tr>
	</table>
	
	<table align="center" class="table table-striped">
		<tr align="center" >
			<td width="7%"><b>글번호</b></td>
			<td width="7%"><b>글제목</b></td>
			<td width="7%"><b>작성자</b></td>
			<td width="7%"><b>작성일자</b></td>
			<td width="7%"><b>조회수</b></td>
					
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
	 			<td>${mem.suggestion_id}</td>
	 			<td><a href="${contextPath}/moonUiBoard/detailList.do?suggestion_id=${mem.suggestion_id}" style="text-decoration: none">${mem.post_title}</a></td>
	 			<td>${mem.post_name}</td>
	 			<td>${mem.post_date}</td>
	 			<td>${mem.view_count}</td>
	 			 			
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
		<c:set var="searchField" value="${searchField}"></c:set>
        <c:set var="searchText" value="${searchText}"></c:set>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="#">${i}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${contextPath}/moonUiBoard/searchList.do?pageNum=${i}&searchField=${searchField}&searchText=${searchText}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	</ul>
</nav>
<form action="${contextPath}/moonUiBoard/searchList.do" method="post">
	<table class="pagination justify-content-center" >
		<tr>
			<td>
			
			<select class="form-control" name="searchField">
			<option value="post_title">글제목</option>
			<option value="post_name">작성자</option>
			
			</select></td>
			<td>
				<input type="text" class="form-control" placeholder="검색어 입력" name="searchText" maxlength="100" id="searchText"></td>
				<td><button type="submit" class="btn btn-primary" id="searchBtn">검색</button></td>
			
				<td><a type="button" href="${contextPath}/moonUiBoard/backList.do" class="btn btn-primary" id="searchBtn">목록으로</a></td>
			
			</tr>
					
	</table>
</form>	
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>	
</body>
</html>









