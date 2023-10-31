<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.HospitalDAO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>두 번째 페이지</title>
</head>
<body>
<h2>병원 리뷰 작성 페이지</h2>
    <form action="dongSelection" method="post">
        <label for="hospitalSelect">병원 선택:</label>
        <select id="hospitalSelect" name="selectedHospital">
            <%
                // 사용자가 선택한 동에 해당하는 병원 이름 목록을 가져옴
                String selectedDong = (String) request.getAttribute("selectedDong");
                HospitalDAO hospitalDAO = new HospitalDAO();
                List<String> hospitalNames = hospitalDAO.getHospitalNamesByDong(selectedDong);
                
                for (String hospital : hospitalNames) {
            %>
                <option value="<%= hospital %>"><%= hospital %></option>
            <%
                }
            %>
        </select>
        <input type="submit" value="병원 선택">
    </form>
</body>
</html>
