<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
<jsp:include page="/Main/mainNavigate.jsp"/>

<c:set var="currentPage" value="${requestScope.currentPage}" />
<c:set var="nextPage" value="${currentPage + 1}" />
<c:set var="count" value="${requestScope.count}" />
<c:set var="userName" value="${sessionScope.username}" />
<c:set var="user_id" value="${sessionScope.user_id}" />

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
<title>건의 하기</title>

<style type="text/css">
    .cls1 {
        font-size: 40px;
        text-align: center;
    }

    .cls2 {
        font-size: 20px;
        text-align: center;
    }
    #menu {  z-index: 3;  }
</style>

<script type="text/javascript" src="${contextPath}/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
    var oEditors = [];
    $(function () {
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
                // 기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할 때 사용
                oEditors.getById["post_content"].exec("PASTE_HTML", ["건의할 내용을 작성해주세요."]);
            },
            fCreator: "createSEditor2"
        });

        // 저장 버튼 클릭 시 form 전송
        $("#reflected").click(function () {
            oEditors.getById["post_content"].exec("UPDATE_CONTENTS_FIELD", []);
            // form 데이터를 서버로 전송
            $("#frm").submit();
        });
    });
</script>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <form class="form-inline w-75" id="frm" action="${contextPath}/moonUiBoard/addMoonUiList.do" method="post">
            <div class="input-group flex-nowrap mt-2 mb-2">
                <span class="input-group-text" id="addon-wrapping">제목</span>
                
                <input type="text" id="post_title" class="form-control" aria-describedby="addon-wrapping" name="post_title" value="" required="required">
            	<input id="post_user_id" name="post_user_id" value="${user_id}" hidden="" >
            </div>

            <div class="input-group flex-nowrap mt-2 mb-2">
                <span class="input-group-text" id="addon-wrapping">작성자</span>
                <input type="text" id="userName" class="form-control" aria-describedby="addon-wrapping" name="post_name" value="${userName}" readonly="readonly" required="required">
            </div>

            <div class="form-floating">
                <textarea class="form-control" id="post_content" style="height: 600px" name="post_content" required="required"></textarea>
                <label for="floatingTextarea2"></label>
            </div>

            <div class="col text-center" id="reflectedList">
                <input type="text" value="${userName}" id="userName" name="post_name" hidden="">
                <input type="submit" class="btn btn-primary btn-sm" value="글쓰기" id="reflected" required="required">
                <a type="button" href="${contextPath}/moonUiBoard/backList.do" class="btn btn-primary btn-sm" id="cancel">리스트로 돌아가기</a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
