<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: #89CFF0;
        margin: 0;
        padding: 0;
    }

    h1 {
        color: var(--color);
  font-weight: 500;
  text-align: center;
  letter-spacing: 0.1em;
    }

    .button-container {
        width: 60%;
        margin: 30px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 15px;
        background-color: #ffffff;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        text-align: center;
    }

    .button-container form {
        margin: 15px 0;
    }

    button {
        padding: 12px 20px;
        margin: 10px;
        border: none;
        border-radius: 25px;
        background-color: #4CAF50;
        color: white;
        font-size: 1em;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }

    button:hover {
        background-color: #45a049;
        transform: scale(1.05);
    }

    .logout-button {
        position: absolute;
        top: 10px;
        right: 10px;
        background-color: #f44336;
        padding: 10px 20px;
        border-radius: 25px;
    }

    .logout-button:hover {
        background-color: #d32f2f;
    }
</style>



</head>
<body>
    <h1>Welcome to the Main Page</h1>
    <div class="button-container">
        <form action="${pageContext.request.contextPath}/employee/create" method="get">
            <button type="submit">Create Employee</button>
        </form>
        <form action="${pageContext.request.contextPath}/employee/view" method="get">
            <button type="submit">View Employees</button>
        </form>
        <form action="${pageContext.request.contextPath}/plan/create" method="get">
            <button type="submit">Create Plan</button>
        </form>
        <form action="${pageContext.request.contextPath}/plan/view" method="get">
            <button type="submit">View Plans</button>
        </form>
        <c:if test="${sessionScope.account eq null}">
            <form action="${pageContext.request.contextPath}/login" method="get">
            <button type="submit" class="logout-button">Login</button>
        </c:if>
        <c:if test="${sessionScope.account ne null}">
            <form action="${pageContext.request.contextPath}/logout" method="get">
            <button type="submit" class="logout-button">Logout</button>
        </c:if>
            
        
        </form>
    </div>
</body>
</html>
