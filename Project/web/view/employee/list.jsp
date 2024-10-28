<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
    body {
        font-family: Arial, sans-serif;
    }

    h2 {
        text-align: center;
        color: #333;
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
        color: #333;
    }

    tr:hover {
        background-color: #f5f5f5;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
</style>

</head>
<body>
    <h2>Employee List</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Department</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${employees}">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.name}</td>
                    <td>${e.phoneNumber}</td>
                    <td>${e.address}</td>
                    <td>${e.department.name}</td>
                    <td>${e.salary.amount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>