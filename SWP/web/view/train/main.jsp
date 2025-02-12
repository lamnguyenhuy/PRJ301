<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        /* Định dạng chung cho trang */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
    text-align: center;
}

/* Tiêu đề chính */
h1 {
    color: #333;
    margin-bottom: 20px;
}

/* Bảng hiển thị chuyến tàu */
table {
    width: 80%;
    margin: 0 auto;
    border-collapse: collapse;
    background-color: #fff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

/* Định dạng tiêu đề cột */
th {
    background-color: #007BFF;
    color: white;
    padding: 10px;
    text-align: left;
    font-size: 16px;
}

/* Định dạng ô dữ liệu */
td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}

/* Hiệu ứng hover khi rê chuột vào hàng */
tr:hover {
    background-color: #f1f1f1;
}

/* Định dạng hàng chẵn để dễ nhìn hơn */
tr:nth-child(even) {
    background-color: #f9f9f9;
}

/* Làm cho bảng không quá sát nhau trên mobile */
@media screen and (max-width: 768px) {
    table {
        width: 100%;
    }

    th, td {
        font-size: 14px;
        padding: 8px;
    }
}

/* Bộ lọc chuyến tàu */
.filter-section {
    background: #ffffff;
    padding: 20px;
    margin: 20px auto;
    width: 80%;
    border-radius: 10px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    text-align: center;
}

/* Hàng chứa các input */
.form-row {
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    margin-bottom: 10px;
}

/* Nhóm input */
.form-group {
    flex: 1;
    margin: 0 10px;
    text-align: left;
}

/* Nhãn cho input */
.form-group label {
    font-weight: bold;
    color: #333;
    display: block;
    margin-bottom: 5px;
}

/* Ô nhập dữ liệu */
.form-control {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
}

/* Nút lọc */
.btn-primary {
    background: #007BFF;
    color: white;
    border: none;
    padding: 12px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: 0.3s;
    margin-top: 10px;
}

.btn-primary:hover {
    background: #0056b3;
}

/* Responsive cho màn hình nhỏ */
@media screen and (max-width: 768px) {
    .form-row {
        flex-direction: column;
    }

    .form-group {
        margin: 10px 0;
    }
}

    </style>
    <title>Danh sách chuyến tàu</title>
</head>
<body>
    <h1>Danh sách chuyến tàu</h1>
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
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên tàu</th>
            <th>Tổng toa</th>
            <th>Tổng ghế</th>
            <th>Ga đi</th>
            <th>Ga đến</th>
            <th>Xuất phát</th>
            <th>Đến nơi</th>
            <th>Giá vé</th>
        </tr>

        <tr>
                 <c:forEach var="t" items="${trains}">
                <tr>
                    <td>${t.trainID}</td>
                    <td>${t.trainName}</td>
                    <td>${t.totalCarriages}</td>
                    <td>${t.totalSeats}</td>
                    <td>${t.departureStation}</td>
                    <td>${t.arrivalStation}</td>
                    <td>${t.departureTime}</td>
                    <td>${t.arrivalTime}</td>
                    <td>${t.price}</td>
                </tr>
            </c:forEach>
        </tr>

    </table>
</body>
</html>