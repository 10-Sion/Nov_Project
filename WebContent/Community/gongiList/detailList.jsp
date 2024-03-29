<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="/Main/mainNavigate.jsp" />
<c:set var="grade_id" value="${sessionScope.grade_id}" />



<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>스마트 에디터 예제</title>

    <style type="text/css">
        .cls1 {
            font-size: 40px;
            text-align: center;
        }

        .cls2 {
            font-size: 20px;
            text-align: center;
        }
        .board {  top: 15%;  position: relative; z-index:0;  } body {   background-color:#fff;  }
        #menu {  z-index: 3;  }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <form class="form-inline w-75" action="${contextPath}/gongiBoard/modifyList.do" method="post">
                <input id="grade_id" name="grade_id" value="${grade_id}" hidden="">
                
                <div class="input-group flex-nowrap mt-2 mb-2">
                    <span class="input-group-text" id="addon-wrapping">제목</span>
                    <input type="text" id="post_title" class="form-control" aria-describedby="addon-wrapping" name="post_title" value="${vo.post_title}" required="required">
                </div>
                <div style="display: none">
                    <input id="announcement_id" name="announcement_id" value="${vo.announcement_id}">
                </div>
                <!-- 스마트 에디터에 해당하는 textarea -->
				<div class="form-floating">
				    <c:choose>
			  		<c:when test="${sessionScope.grade_id == 4}">
				            <!-- 스마트 에디터 부분 -->
				            <textarea class="form-control" id="mainText" style="height: 500px; resize: none;" name="post_content" required="required">${vo.post_content}</textarea>
				        </c:when>
				        <c:otherwise>
				            <!-- 읽기 전용 텍스트 부분 -->
				            <div class="form-control" style="height: 500px; resize: none;" readonly>${vo.post_content}</div>
				        </c:otherwise>
				    </c:choose>
				</div>
				<div class="col text-center" id="reflectedList">
				    <input type="text" value="" id="userName" hidden="">               
				    <c:choose>
			  			<c:when test="${sessionScope.grade_id == 4}">
				            <input type="submit" class="btn btn-primary btn-sm" value="수정하기" id="reflected">
				        </c:when>
				    </c:choose>               
				    <a type="button" href="${contextPath}/gongiBoard/backList.do" class="btn btn-primary btn-sm" id="cancel">리스트로 돌아가기</a>
				</div>

            </form>
        </div>
    </div>
	<script src="<%=request.getContextPath()%>/js/gonggiList.js"></script>
    <!-- 스마트 에디터 스크립트 추가 -->
    <script type="text/javascript" src="${contextPath}/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
    <script type="text/javascript">
    
        var oEditors = [];
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "mainText", // textarea의 id와 일치해야 합니다.
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
            oEditors.getById["mainText"].exec("UPDATE_CONTENTS_FIELD", []);
            $("#frm").submit();
        });
        
    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
