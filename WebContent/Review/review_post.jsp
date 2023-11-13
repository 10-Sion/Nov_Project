<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


 <form action="/dongSelection" method="post">
 <input type="hidden" name="action" value="hospitalSelect">
 
        
        <label for="rating">평점:</label>
        <input type="number" id="rating" name="rating" min="1" max="5" required><br><br>

        <label for="comment">리뷰 내용:</label> <br>
        <textarea id="comment" name="comment" rows="10" cols="50" required></textarea><br><br>

        <input type="submit" value="리뷰 제출">
    </form>

</body>
</html>