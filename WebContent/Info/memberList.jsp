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
<c:set var="user_id" value="${sessionScope.user_id}" />


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>가입자 관리</title>

	<style type="text/css">
		.cls1{
			font-size: 40px;
			text-align: center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
		#menu {  z-index: 3;  }	.container {  top: 35%;  position: relative; z-index:0;  }
	</style>

</head>

<body>
	
	<div class="container">
	
		<p class="cls1">가입자 관리</p>
		<input type="text" value="${user_id}" id="user_id" hidden= hidden>
		
		
		<table align="center" class="table table-striped inline w-75">
			<tr align="center">
				<td width="7%">회원번호</td>
				<td width="7%"><b>이름</b></td>
				<td width="7%"><b>이메일</b></td>
				<td width="7%"><b>등급</b></td>
				<td width="7%"><b>리뷰게시판 글갯수</b></td>
				<td width="7%"><b>자유게시판 글갯수</b></td>
				<td width="7%"><b>댓글 갯수</b></td>
				<td width="7%"><b>추천수</b></td>
				<td width="7%"><b>등급수정</b></td>
				<td width="7%"><b>회원삭제</b></td>			
			</tr>		
				
		<c:choose>  			  
			 <c:when test="${empty requestScope.membersList}"><%-- request에 바인딩된  ArrayList배열이 없으면?(조회된 정보가 없으면?)  --%>
			 	<tr align="center">
			 		<td colspan="8">
			 			<b>등록된 회원이 없습니다.</b>
			 		</td>
			 	</tr>
			 </c:when>                  
			 <c:when test="${not empty requestScope.membersList}"> <%--request에 바인딩된 ArrayList배열이 있으면?(조회된 정보가 있으면?) --%>
			 	<%-- request에 바인딩된 ArrayList배열을 꺼내오고 MemberVO객체의 갯수만큼 반복해서 얻어 출력 --%>
			 	<c:forEach  var="mem"   items="${requestScope.membersList}"  >
			 		
			 	<tr align="center">
			 			
			 			<td>${mem.user_id}</td>
			 			<td>${mem.username}</td>
			 			<td>${mem.email}</td>
						<td>
						<select id="grade_Name" name="grade_Name">
		                 	<option value="1" <c:if test="${mem.grade_id == 1}">selected</c:if>>잡초</option>
		                 	<option value="2" <c:if test="${mem.grade_id == 2}">selected</c:if>>잔디</option>
		                 	<option value="3" <c:if test="${mem.grade_id == 3}">selected</c:if>>금잔디</option>
		                 	<option value="4" <c:if test="${mem.grade_id == 4}">selected</c:if>>농부</option>
		                </select>
		                </td>
		                <td>${mem.review_count}</td>
						<td>${mem.post_count}</td>
			 			<td>${mem.comments_count}</td> 
			 			<td>${mem.count}</td>
			 			
			 			<td><a type="button" class="btn btn-secondary btn-sm" id="modifyMember">수정</a></td>	
						<td><a class="btn btn-secondary btn-sm" id="deleteMember" style="text-decoration: none">삭제</a></td>
			 		</tr>
			 		
			 	</c:forEach>
			 </c:when>
		</c:choose>		
		</table>
		
			<!-- 	   페이징 컨트롤 부분 -->
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
			                        <a class="page-link" href="${contextPath}/memberList/searchMemberList.do?pageNum=${i}">${i}</a>
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

	<script src="<%=request.getContextPath()%>/js/memberList.js"></script>
	
</body>
</html>









