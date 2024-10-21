<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="create" method="POST">
            Title: <input type="text" name="title" ><br/>
            Released Date <input type="date" name="releaseddate"/> <br/>
            Only for Adult: <input type="checkbox" name="adultonly" value="adult"   /> <br/>
            Category: <select name="cid">
                <c:forEach items="${requestScope.category}" var="c">
                    <option value="${c.cid}">${c.cname}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Save">
        </form>
    </body>
</html>