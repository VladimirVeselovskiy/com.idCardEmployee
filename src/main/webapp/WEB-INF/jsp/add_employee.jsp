<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style> <%@include file="../css/styles.css"%></style>
</head>

<body>
<h2>Add Employee</h2>
<br>

<form:form action="saveEmployee" modelAttribute="employee">

    <form:hidden path="id"/>

    Surname <form:input path="surname"/>
    <br><br>
    Department <form:select path="department">
    <form:option value="IT"/>
    <form:option value="HR"/>
    <form:option value="Financial"/>
    <form:option value="Marketing"/>
    <form:option value="Logistics"/>
    <form:option value="Management"/>
    <form:option value="Security"/>
    </form:select>
    <br><br>
    DateOfEmployment <form:input path="dateOfEmployment"/>
    <br><br>
    CardAccessID: <form:hidden path="card.id"/>
    <td>${employee.card.id}
        <form:hidden path="id"/>
    </td>
    <br>
    LevelAccess <form:select path="card.levelAccess">
    <form:option value="ONE"/>
    <form:option value="TWO"/>
    <form:option value="THREE"/>
    </form:select>
    <br><br>
    <input type="submit" value="OK">

</form:form>
<br>
<input type="button" value="Return"
       onclick="window.location.href = '/id-card-employee/employees'"/>
</body>
</html>
