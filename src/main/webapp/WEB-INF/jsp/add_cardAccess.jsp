<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style> <%@include file="../css/styles.css"%></style>
</head>

<body>
<div style="text-align: center;">
<h2>Add CardAccess</h2>
<br>

<form:form action="saveCardAccess" modelAttribute="cardAccess">

  <form:hidden path="id"/>

  LevelAccess <form:select path="levelAccess">
  <form:option value="ONE"/>
  <form:option value="TWO"/>
  <form:option value="THREE"/>
  </form:select>
  <br><br>
  <input type="submit" value="OK">

</form:form>
<br>
<input type="button" value="Return"
       onclick="window.location.href = '/id-card-employee/card-access'"/>
</div>
</body>
</html>