<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
<head>
    <title>Bookmarks</title>
</head>
<body>

    <ul>
    <c:forEach items="${bookmarks}" var="bookmark" varStatus="status">
        <li>
            <a href="${bookmark}">${bookmark}</a>
            <form action="${pageContext.request.contextPath}/bookmarks" method="post">
                <input type="hidden" name="deleteBookmark" value="${status.index}">
                <input type="submit" value="Delete">
            </form>
        </li>
    </c:forEach>
</ul>

</body>
</html>
