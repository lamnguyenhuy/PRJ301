<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Plan Detail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
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
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .input-section {
            width: 80%;
            margin: 20px auto;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>

<h2>Create Plan Detail</h2>

<!-- Bảng hiển thị tạm thời dữ liệu đã nhập -->
<table>
    <thead>
        <tr>
            <th>Date</th>
            <th>Shift</th>
            <th>Product</th>
            <th>Quantity</th>
        </tr>
    </thead>
    <tbody id="detailsTable">
        <!-- Dòng sẽ được thêm vào đây bằng JavaScript -->
    </tbody>
</table>

<div class="input-section">
    <form id="detailForm">
        <label>Date:
            <select id="dateSelect">
                <c:forEach var="day" items="${dateRange}">
                    <option value="${day}">${day}</option>
                </c:forEach>
            </select>
        </label>

        <label>Shift:
            <input type="radio" name="shift" value="K1"> K1
            <input type="radio" name="shift" value="K2"> K2
            <input type="radio" name="shift" value="K3"> K3
        </label>

        <label>Product:
            <c:forEach var="product" items="${products}">
                <input type="radio" name="product" value="${product}"> ${product}
            </c:forEach>
        </label>

        <label>Quantity:
            <input type="number" id="quantityInput" min="1">
        </label>

        <input type="submit" value="add">
    </form>
</div>




</body>
</html>
