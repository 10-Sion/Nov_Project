<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    



<c:set  var="contextPath"  value="${pageContext.request.contextPath}"/>
<c:set var="user_id" value="${sessionScope.user_id}"/>    
<jsp:include page= "/Main/mainNavigate.jsp"/>
<c:set var="currentPage" value="${requestScope.currentPage}" />
<c:set var="nextPage" value="${currentPage + 1}" />
<c:set var="count" value="${requestScope.count}" />

<c:choose>
	<c:when test="${requestScope.msg == 'delCommentOk'}">		
		<script>
			window.onload = function(){
				alert("댓글을 삭제했습니다.");
				
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
	<input id="user_id" name="user_id" value="${user_id}" hidden="">
	<div class="container">
                   	<div class="row justify-content-center">
                   	
                   <form class="form-inline w-75" action="${contextPath}/jauBoard/modifyList.do" method="post">
                   	<input id="post_user_id" name="post_user_id" value="${vo.post_user_id}" hidden="">
                   	
                    <div class="input-group flex-nowrap mt-2 mb-2">
		  <span class="input-group-text" id="addon-wrapping">제목</span>

		  <input type="text" id="post_title" class="form-control" aria-describedby="addon-wrapping" name="post_title" value="${vo.post_title}" required="required">

		</div>
		<div style="display: none">
                   	<input name="post_id" value="${vo.post_id}">
                   	</div>
		<div class="form-floating">
			<c:choose>
              	<c:when test="${user_id eq vo.post_user_id}">
                    <!-- 스마트 에디터 부분 -->
                    <textarea class="form-control" id="post_content" style="height: 500px; resize: none;"
                        name="post_content">${vo.post_content}</textarea>
                </c:when>
                <c:otherwise>
                    <!-- 읽기 전용 텍스트 부분 -->
                    <div class="form-control" style="height: 500px; resize: none;" readonly>${vo.post_content}</div>
                </c:otherwise>
            </c:choose>
			<label for="floatingTextarea2"></label>
		</div>
		
		<div class="col text-center" id="reflectedList">
			<input type="text" value="" id="userName" hidden="">
			<input type="submit" class="btn btn-secondary btn-sm" value="수정하기" id="reflected">
			<a type="button" href="${contextPath}/jauBoard/delJauList.do?post_id=${vo.post_id}" class="btn btn-secondary btn-sm" id="del">삭제하기</a>
			<a type="button" href="${contextPath}/jauBoard/backList.do" class="btn btn-secondary btn-sm" id="cancel">리스트로 돌아가기</a>
		</div>
		<br><br>
		</form>
		
		<!-- 추천 & 비추천 -->
		<div class="row justify-content-center inline w-75">
			<div class="input-group flex-nowrap mt-2 mb-2">
				<a type="button" class="btn btn-secondary btn-sm" href="${contextPath}/jauBoard/jauGood.do?&post_id=${vo.post_id}&user_id=${user_id}">추천${vo.good}</a>&nbsp;&nbsp;&nbsp;
				<a type="button" class="btn btn-secondary btn-sm" href="${contextPath}/jauBoard/jauBad.do?&post_id=${vo.post_id}&user_id=${user_id}">비추천${vo.bad}</a>
			</div>
		</div>	
		
		<!-- 댓글쓰는 칸이야 -->
		
		
		<form method="post"  action="${contextPath}/jauBoard/addComments.do" class="form-inline w-75">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br><br></td>
					<td><input type="text" value="${vo.post_id}" name="post_id" hidden=""></td>
					<td><input type="text" value="${user_id}" name="user_id" hidden=""></td>
					<td><input type="text" value="${vo.post_name}" name="user_name" hidden=""></td>
					<td><input type="text" style="height:100px;" class="form-control" placeholder="상대방을 존중하는 댓글을 남깁시다." name = "comment_text"></td>
					<td><br><br><input type="submit" class="btn btn-secondary btn-sm" value="댓글 작성"></td>
				</tr>
				
			</table>
		</form>
		
		<!-- 댓글쓰면 여기로!!!! -->
		<hr class="inline w-75">
		
		<c:choose>  			  
	 <c:when test="${empty requestScope.membersList}">
	 	<table class="form-inline w-75">
	 	 	<tr align="center">
	 		<td colspan="5">
	 			<b>등록된 댓글이 없습니다.</b>
	 		</td>
	 	</tr>
	 	</table>
	 	</c:when>
	 	<c:when test="${not empty requestScope.membersList}"> <%--request에 바인딩된 ArrayList배열이 있으면?(조회된 정보가 있으면?) --%>
	 	<c:forEach  var="mem"   items="${requestScope.membersList}"  >
	 	<div class=" inline w-75">
		<c:choose>
			<c:when test="${mem.level == 0 }">	
			<table>
				<tr>
					<td><b>${mem.user_name}</b>  (${mem.comment_date}) 
					<c:if test="${mem.user_id == user_id}">
					<a style="margin-left: 500px" type="button" class="btn btn-secondary btn-sm" id="modifyComment">댓글 수정</a>
					<a type="button" class="btn btn-secondary btn-sm" id="delComment" href = "${contextPath}/jauBoard/delComment.do?comment_id=${mem.comment_id}&post_id=${vo.post_id}">댓글 삭제</a>
					</c:if>
					
					<input type="text" style="height:70px; width: 400px; border: none" class="form-control" id = "comment_text" name = "comment_text" value="${mem.comment_text}" readonly="readonly">
					<td><input type="text" value="${mem.level}" id = "comment_id" hidden=""></td>
					<td><input type="text" value="${mem.comment_id}" hidden="" id = "comment_id" ></td>
					<td><input type="text" value="${mem.user_name}" hidden="" id="user_name"></td>
					<td><input type="text" value="${mem.comment_date}" hidden="" id="comment_date"></td>
					<td><input type="text" value="${mem.user_id}" hidden=""></td>
					<td><input type="text" value="${user_id}" hidden=""></td>
					
				</tr>
				<tr>
				<td><a type="button" id="replyComment">답글</a></td>
				</tr>
			</table>
			</c:when>
			<%-- 답글시작지점 --%>
			<c:otherwise>
			<span style="font-size: 12px; margin-bottom : 50px">[답변]</span>
			<table style="margin-left: 50px;" class="table table-striped">
			
				<tr>
					<td><b>${mem.user_name}</b>  (${mem.comment_date}) 
					<c:if test="${mem.user_id == user_id}">
					
					<a type="button" class="btn btn-secondary btn-sm" id="delComment" href = "${contextPath}/jauBoard/delComment.do?comment_id=${mem.comment_id}&post_id=${vo.post_id}">답글 삭제</a>
					</c:if>
					
					<input type="text" style="height:70px; width: 400px; border: none; background-color: rgba(0, 0, 0, 0.001);" class="form-control -striped" id = "comment_text" name = "comment_text" value="${mem.comment_text}" readonly="readonly">
					<td><input type="text" value="${mem.level}" id = "comment_id" hidden=""></td>
					<td><input type="text" value="${mem.comment_id}" hidden="" id = "comment_id" ></td>
					<td><input type="text" value="${mem.user_name}" hidden="" id="user_name"></td>
					<td><input type="text" value="${mem.comment_date}" hidden="" id="comment_date"></td>
					<td><input type="text" value="${mem.user_id}" hidden=""></td>
					<td><input type="text" value="${user_id}" hidden=""></td>
					
				</tr>
			</table>	
			</c:otherwise>
		</c:choose>
			<hr>
			<!-- 답글 작성 폼 -->
			<form method="post"  action="${contextPath}/jauBoard/addReply.do" id="replyCommentForm" hidden="">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br><br></td>
					<td><input type="text" value="${mem.comment_id}" name="comment_id" hidden=""></td>
					<td><input type="text" value="${vo.post_id}" name="post_id" hidden=""></td>
					<td><input type="text" value="${user_id}" name="user_id" hidden=""></td>
					<td><input type="text" value="${vo.post_name}" name="user_name" hidden=""></td>
					<td><input type="text" style="height:100px;" class="form-control" placeholder="상대방을 존중하는 댓글을 남깁시다." name = "comment_text"></td>
					<td><br><br><input type="submit" class="btn btn-secondary btn-sm" value="답글 작성"></td>
				</tr>
				
			</table>
			<hr>
		</form>
		</div>
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
                        <a class="page-link" href="${contextPath}/jauBoard/detailList.do?pageNum=${i}&post_id=${vo.post_id}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	</ul>
</nav>
		
	</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>	

	<!-- 댓글 수정하기 눌렀을때 처리하는 javaScript -->
	<script src="<%=request.getContextPath()%>/js/comments.js"></script>
	
	 <!-- 스마트 에디터 스크립트 추가 -->
    <script type="text/javascript" src="${contextPath}/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
    <script type="text/javascript">
    
        var oEditors = [];
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "post_content", // textarea의 id와 일치해야 합니다.
            sSkinURI: "${contextPath}/smarteditor2/SmartEditor2Skin.html",
            htParams: {
                bUseToolbar: true,
                bUseVerticalResizer: true,
                bUseModeChanger: true,
                fOnBeforeUnload: function () {}
            },
            fOnAppLoad: function () {
            	
            },
            fCreator: "createSEditor2"
        });
        
        // 저장 버튼 클릭 시 스마트 에디터의 내용을 업데이트하고 폼을 서버로 제출
        $("#reflected").click(function () {
            oEditors.getById["post_content"].exec("UPDATE_CONTENTS_FIELD", []);
            $("#frm").submit();
        });
        
    </script>
</body>
</html>









