<%-- 
    Document   : register
    Created on : 22 thg 9, 2024, 22:28:07
    Author     : lam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dog Register</title>
    </head>
    <body>
        <h1>Dog register</h1>
        <form action="register" method="post">
            Name : <input type="text" name="name" > <br>
            Age : <input type="text" name="age"> <br>
            Gender: <input type="radio" name="gender" value="male"> Male 
            <input type="radio" name="gender" value="female"> female <br>
            <input type="submit" value="Next">
        </form>
    </body>
</html>
