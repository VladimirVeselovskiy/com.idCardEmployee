<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style> <%@include file="../css/styles.css"%></style>
</head>

<body>
<div style="text-align: center;">

    <div class="header">
        <h2>All CardAccess</h2>

        <div class="row">
            <div class="home_page_id_card_employee">
                <h1>  <input type="button" value="Id-Card-Employee" style="height: 50px; width:  200px;"
                             onclick="window.location.href = '/id-card-employee'"/> </h1>
            </div>
        </div>
    </div>

    <table>

        <tr>
            <th>LevelAccess</th>
            <th>EmployeeId</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="card" items="${allCardAccess}">

            <%--То что выделено красным так и должно быть, по другому не работает--%>
            <c:url var="updateButton" value="/id-card-employee/card-access/update-cardAccess">
                <c:param name="cardId" value="${card.id}"/>
            </c:url>
            <c:url var="deleteButton" value="/id-card-employee/card-access/deleteCardAccess">
                <c:param name="cardId" value="${card.id}"/>
            </c:url>

            <tr>
                <td>${card.levelAccess}</td>
                <td>${card.employee.id}</td>
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

    <input type="button" value="Add CardAccess" style="height: 30px; width: 110px;"
           onclick="window.location.href = '/id-card-employee/card-access/addNewCardAccess'"/>
    <br>

    <h2>Search CardAccess</h2>
    <form method="get" action="card-access/levelAccess">
        <label>
            <input type="text" name="levelAccess">
        </label>
        <input type="submit" value="search">
    </form>
    <br>

    <h2> Go to <input type="button" value="Employees" style="height: 30px; width: 100px;"
                      onclick="window.location.href = '/id-card-employee/employees'"/></h2>

</div>
</body>
</html>
