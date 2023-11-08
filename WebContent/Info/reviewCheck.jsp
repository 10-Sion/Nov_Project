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

<title>리뷰 확인</title>

	<style type="text/css">
		.cls1{
			font-size: 40px;
			text-align: center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
		.wrapper style2 {
		  width: 40%; /* Adjust the width of the wrapper div */
		}
		
		.container {
		  width: 80%; /* Adjust the width of the container div */
		}
		
		.input-group {
		  width: 90%; /* Adjust the width of the input group div */
		}
		
		#tdImg {
		  width: 200px; /* Adjust the width of the image */
		  height: auto; /* Maintain the aspect ratio of the image */
		}
		</style>

</head>

<body>
	
	<p class="cls1">리뷰 확인</p>
	<h4 style="margin-left: 300px">대기리뷰 갯수 ${count}</h4>
	<c:choose>
	<c:when test="${not empty requestScope.reviewList}">
	<c:forEach  var="review"   items="${requestScope.reviewList}"  >
	<div class="wrapper style2">
					
              			
                        <div class="container">
                        <form action="${contextPath}/memberInfo/addRegister.me" method="post" enctype="multipart/form-data">
                        
                        	<div class="row justify-content-center">
                        <div class="input-group flex-nowrap mt-2 mb-2">
                        
                        	
						<img src="${contextPath}/upload/${review.receipt_image}" class="img-thumbnail" alt="사진없음" height="150px" width="200px" id="tdImg" name="imgview" onclick="window.open(this.src,'_blank','width=500,height=400')">
					<div class="col justify-content-center">
					<span class="input-group-text" id="addon-wrapping">
					<label for="userName">이름</label>
					<input type="text" id="userName" class="form-control" aria-describedby="addon-wrapping" name="userName" value="${review.username}" readonly="readonly">
					<label for="ssn">병원</label>
					<input type="text" id="hospital_name" class="form-control" aria-describedby="addon-wrapping" name="hospital_name" value="${review.name}" readonly="readonly">
					
					</span>
					
				
					<span class="input-group-text" id="addon-wrapping">
					<label for="tel">인증상태</label>
					<input style="width: 300px" type="text" id="verification" class="form-control" aria-describedby="addon-wrapping" name="verification" value="${review.verification}" readonly="readonly" >
					&nbsp;&nbsp;&nbsp;&nbsp;
					
					</span>
		
					<span class="input-group-text" id="addon-wrapping">
						<label>리뷰글</label>
						<textarea rows="4" cols="100" name="" readonly="readonly" style="resize: none">${review.review_text}</textarea>
						&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" href="${contextPath}/review/reviewCheckOk.do?review_id=${review.review_id}" style="text-decoration: none">승인</a>
						&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" href="${contextPath}/review/reviewCheckNotOk.do?review_id=${review.review_id}" style="text-decoration: none">거절</a>
					</span>
				</div>		  
           		</div>
				</div>
				</form>
				</div>
				</div>
				<br>
				</c:forEach>
			</c:when>	
		</c:choose>		
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
                        <a class="page-link" href="${contextPath}/review/reviewCheckList.do?pageNum=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	</ul>
</nav>


	
	<div id="footer">
		<jsp:include page="/Main/footer.jsp" />
	</div>
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>	

	<script src="<%=request.getContextPath()%>/js/comments.js"></script>
</body>
</html>









