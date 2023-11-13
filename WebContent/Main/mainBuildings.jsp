<% String path = request.getContextPath(); %>	<!-- contextPath 변수 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="<%= path %>/Assets/Style/mainStyle/mainBuildings.css"> <!-- main 상단 빌딩 영역 -->
  
  <title>Animation</title>
</head>
<body>
  <div class="wrap">
    <div class="grid slide">
		<%
		    String[] buildingClasses = {"class1", "class2", "class3", "class4", "class5", "class6", "class7", "class8", "class9", "class10", "class11"};
		
		    for (int i = 0; i < 32; i++) {
		        int buildingIndex = i % buildingClasses.length;
		        String currentClass = buildingClasses[buildingIndex];
		%>
		        <div class="cell <%= currentClass %>"></div>
		<%
		    }
		%>

    </div>
  </div>
</body>
</html>
