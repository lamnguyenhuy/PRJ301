<%-- 
    Document   : skill
    Created on : 22 thg 9, 2024, 23:37:16
    Author     : lam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dog skill Page</title>
    </head>
    <body>
        Name : ${sessionScope.dog.name} </br>
        Age : ${sessionScope.dog.age} </br>
        Gender : ${sessionScope.dog.gender} </br>

        <table>
            <tr>
                <td>Skill name</td>
                <td>Level</td>
            </tr>
            <c:forEach items="${sessionScope.dog.skill}" var="s">
            <tr>
                <td>${s.name}</td>
                <td>${s.level}</td>
            </tr>
            </c:forEach>
        </table>
        
        <form action="../controller/skill">
            <input type="hidden" name="action" value="back">
            <input type="button" value="back">
        </form>
    </body>
</html>
