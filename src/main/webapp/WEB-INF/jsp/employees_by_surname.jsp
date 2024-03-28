<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style> <%@include file="../css/styles.css"%></style>
</head>

<body>
<div style="text-align: center;">

<h2>Employees</h2>
<br>

<table>
  <tr>
    <th>Surname</th>
    <th>Department</th>
    <th>DateOfEmployment</th>
    <th>CardAccess</th>
  </tr>
  <c:forEach var="emp" items="${employee}">
    <tr>
      <td>${emp.surname}</td>
      <td>${emp.department}</td>
      <td>${emp.dateOfEmployment}</td>
      <td>${emp.card.levelAccess}</td>
    </tr>
  </c:forEach>
</table>
<br>
<input type="button" value="Return"
       onclick="window.location.href = '/id-card-employee/employees'"/>

</div>
</body>
</html>
