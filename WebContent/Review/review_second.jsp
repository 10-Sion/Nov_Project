<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.DongDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.HospitalDAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>병원 선택 페이지</title>
</head>
<body>
    <%
        String selectedDong = request.getParameter("selectedDong"); // 사용자가 선택한 동 가져오기

        if (selectedDong != null) {
        	HospitalDAO hospitalDAO = new HospitalDAO();
            List<String> hospitalNames = hospitalDAO.getHospitalNamesByDong(selectedDong);

    %>
    <form action="hospitalSelection" method="get">
        <label for="hospitalSelect">병원 선택:</label>
        <select id="hospitalSelect" name="selectedHospital">
            <%
                for (String hospital : hospitalNames) {
            %>
                <option value="<%= hospital %>"><%= hospital %></option>
            <%
                }
            %>
        </select>
        <input type="submit" value="선택">
    </form>
    <%
        }
    %>
</body>
</html>
