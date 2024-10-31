<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Plan Details</title>
</head>
<body>
    <h2>Plan Details</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Date</th>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Shift</th>
                <th>Quantity (unit: piece)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="detail" items="${planDetails}">
                <tr>
                    <td>${detail.date}</td>
                    <td>${detail.productId}</td>
                    <td>${detail.productName}</td>
                    <td>${detail.shift}</td>
                    <td>${detail.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
