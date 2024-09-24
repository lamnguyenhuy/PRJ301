<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Rect" %>
<!DOCTYPE html>
<html>
<head>
    <title>Random Rectangles</title>
    
</head>
<body>
    <table border="1px">
        <thead>
            <tr>
                <th>stt</th>
                <th> x</th>
                <th> y</th>
                <th>w</th>
                <th>h</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.rects}" var="r">
                <tr>
                <td>${r.i}</td>
                <td>${r.x}</td>
                <td>${r.y}</td>
                <td>${r.w}</td>
                <td>${r.h}</td>
                <td><canvas id="myCanvas${r.i}" width="300" height="150" style="border:1px solid grey"></canvas>
                <script>

        const c${r.i} = document.getElementById("myCanvas${r.i}");
        const ctx${r.i} = c${r.i}.getContext("2d");
                ctx${r.i}.fillRect(${r.x}, ${r.y}, ${r.w}, ${r.h});
                ctx${r.i}.beginPath();
                ctx${r.i}.rect(${r.x}, ${r.y}, ${r.w}, ${r.h});
                ctx${r.i}.stroke();
    </script>
                </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>