<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Train List</title>
    <style>
</style>

</head>
<body>
    <div class="filter-section">
        <form action="TrainController" method="GET">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="departStation">Ga Đi</label>
                    <input type="text" class="form-control" id="departStation" name="departStation" placeholder="Nhập Ga Đi">
                </div>
                <div class="form-group col-md-4">
                    <label for="arriveStation">Ga Đến</label>
                    <input type="text" class="form-control" id="arriveStation" name="arriveStation" placeholder="Nhập Ga Đến">
                </div>
                <div class="form-group col-md-4">
                    <label for="departureDate">Ngày Khởi Hành</label>
                    <input type="date" class="form-control" id="departureDate" name="departureDate">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Lọc</button>
        </form>
    </div>
    <h2>Train List</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="t" items="${trains}">
                <tr>
                    <td>${t.trainID}</td>
                    <td>${t.trainName}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>