<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <style> <%@include file="../css/styles.css"%></style>

</head>

<body>
<div style="text-align: center;">

<h2>All employee</h2>
<br>

<table>
    <tr>
        <th>Surname</th>
        <th>Department</th>
        <th>DateOfEmployment</th>
        <th>CardAccess</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="emp" items="${allEmployees}">

        <%--То что выделено красным так и должно быть, по другому не работает--%>
        <c:url var="updateButton" value="/id-card-employee/employees/update-employee">
            <c:param name="empId" value="${emp.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/id-card-employee/employees/deleteEmployee">
            <c:param name="empId" value="${emp.id}"/>
        </c:url>

        <tr>
            <td>${emp.surname}</td>
            <td>${emp.department}</td>
            <td>${emp.dateOfEmployment}</td>
            <td>${emp.card.levelAccess}</td>
            <td>
                <input type="button" value="Update"
                       onclick="window.location.href = '${updateButton}'"/>
            </td>
            <td>
                <input type="button" value="Delete"
                       onclick="window.location.href = '${deleteButton}'"/>
            </td>
        </tr>
    </c:forEach>

</table>

<input type="button" value="Add"
    onclick="window.location.href = '/id-card-employee/employees/addNewEmployee'"/>
<br>

<h2>Search Employee</h2>
<form method="get" action="employees/surname">
    <label>
        <input type="text" name="surname"/>
    </label>
    <input type="submit" value="search">
</form>
<br>

<h2> Go to <input type="button" value="CardAccess"
                  onclick="window.location.href = '/id-card-employee/card-access'"/> </h2>
</div>
</body>
</html>
