<%-- 
    Document   : demo
    Created on : 11 thg 9, 2024, 08:30:09
    Author     : lam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP demo Page</title>
    </head>
    <body>
        <h1>Hello K20!</h1>
        <form action="edicius" method="post">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required autofocus>
            <label for="bd">Birth Day</label>
            <input type="date" id="bd" name="bd">
            <label for="id">ID</label>
            <input type="text" id="id" name="id" required>
            <label for="el">English</label>
            <input type="text" id="el" name="el" required>
            <input type="submit" value="Send">
        </form>
    </body>
</html>
