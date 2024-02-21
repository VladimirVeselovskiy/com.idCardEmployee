<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

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
        <c:url var="updateButton" value="/id-card-employee/employees/updateEmployee">
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
<br>

<input type="button" value="Add"
    onclick="window.location.href = '/id-card-employee/employees/addNewEmployee'"/>
</body>

</html>
