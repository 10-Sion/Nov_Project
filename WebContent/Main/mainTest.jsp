<!-- /Main/mainTest.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Test Page</title>
</head>
<body>
    <h1>Pharmacy List</h1>
    
    <c:if test="${not empty pharmacyList}">
        <ul>
            <c:forEach var="pharmacy" items="${pharmacyList}">
                <li>
                    <strong>${pharmacy.name}</strong> - ${pharmacy.address}
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty pharmacyList}">
        <p>No nearby pharmacies found.</p>
    </c:if>
</body>
</html>
