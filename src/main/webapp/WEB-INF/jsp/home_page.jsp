<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style> <%@include file="../css/styles.css"%></style>
    <style>
        .header {
        background-color: darkgrey;
        text-align: center;
        padding: 20px;
        }
        .left_column {
            text-align: center;
            float: left;
            width: 50%;
        }
        .right_column {
            text-align: center;
            float: right;
            width: 50%;
        }
        .row:after{
            content: "";
            display: table;
            clear: both;
        }
        @media screen and (max-width: 800px){
            .left_column, .right_column {
                width: 100%;
                padding: 0;
            }
        }

    </style>
</head>
<body>
<div class="header">
    <h1>Id-Card-Employee</h1>
</div>

<div class="row">
    <div class="left_column">
        <h1>  <input type="button" value="CardAccess" style="height: 100px; width:  500px;"
                     onclick="window.location.href = '/id-card-employee/card-access'"/> </h1>
    </div>
    <div class="right_column">
        <h1> <input type="button" value="Employees" style="height: 100px; width:  500px"
                    onclick="window.location.href = '/id-card-employee/employees'"/></h1>
    </div>
</div>
<div class="row">
    <div class="left_column">
        <h2>Search CardAccess</h2>
        <form method="get" action="id-card-employee/card-access/levelAccess">
            <label>
                <input type="text" name="levelAccess">
            </label>
            <input type="submit" value="search">
        </form>
    </div>
    <div class="right_column">
        <h2>Search Employee</h2>
        <form method="get" action="id-card-employee/employees/surname">
            <label>
                <input type="text" name="surname"/>
            </label>
            <input type="submit" value="search">
        </form>
    </div>
</div>
<div>
</div>
</body>
</html>
