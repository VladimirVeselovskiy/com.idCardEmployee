<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style> <%@include file="../css/styles.css"%></style>
</head>
<h2>CardAccess</h2>
<br>
<body>
<table>
    <tr>
        <th>LevelAccess</th>
        <th>EmployeeId</th>
    </tr>
    <c:forEach var="card" items="${cardAccess}">
        <tr>
            <td>${card.levelAccess}</td>
            <td>${card.employee.id}</td>
        </tr>
    </c:forEach>
</table>
<br>
<input type="button" value="Return"
       onclick="window.location.href = '/id-card-employee/card-access'"/>
</body>
</html>
