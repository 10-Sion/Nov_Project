<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <title>Add Bookmark</title>
</head>
<body>
    <h1>Add Bookmark</h1>
    <form action="${pageContext.request.contextPath}/bookmarks" method="post">
        <input type="text" name="newBookmark" placeholder="Enter URL">
        <input type="submit" value="Add Bookmark">
    </form>
</body>
</html>
