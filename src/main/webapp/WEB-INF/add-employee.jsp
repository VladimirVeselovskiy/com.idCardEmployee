<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<h2>Add Employee</h2>
<br>

<form:form action="saveEmployee" modelAttribute="employee">

    <form:hidden path="id"/>

    Surname <form:input path="surname"/>
    <br><br>
    department <form:input path="department"/>
    <br><br>
    DateOfEmployment <form:input path="dateOfEmployment"/>
    <br><br>
    Card <form:input path="card"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>
</html>
