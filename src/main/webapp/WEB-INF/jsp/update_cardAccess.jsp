<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style> <%@include file="../css/styles.css"%></style>
</head>

<body>
<div style="text-align: center;">
<h2>Update CardAccess</h2>
<br>

<form:form action="updateCardAccess" modelAttribute="cardAccess">
  <form:hidden path="id"/>

  CardAccessID: <td>${cardAccess.id}</td>
  <br><br>
  LevelAccess <form:select path="levelAccess">
  <form:option value="ONE"/>
  <form:option value="TWO"/>
  <form:option value="THREE"/>
  </form:select>
  <br>
  <br>
  EmployeeID: <td>${cardAccess.employee.id}</td>
  <br>
  <input type="submit" value="OK">
</form:form>
<br><br>
<br>
<input type="button" value="Return"
       onclick="window.location.href = '/id-card-employee/card-access'"/>

</div>
</body>

</html>
