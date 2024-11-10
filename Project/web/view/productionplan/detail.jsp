<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/newtag_library.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Plan Details</title>
        <style>
            body {
                font-family: 'Poppins', sans-serif;
                background-color: #f0f8ff;
                margin: 0;
                padding: 0;
            }

            h2 {
                text-align: center;
                color: #333;
                margin-top: 20px;
                font-size: 2em;
            }

            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 12px 15px;
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

            tr:nth-child(odd) {
                background-color: #e6f7ff;
            }

            tr:hover {
                background-color: #d1e7dd;
            }
        </style>

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
                        <td> <mytag:ToVietnameseDate value="${detail.date}"/></td>
                        <td>${detail.productId}</td>
                        <td>${detail.productName}</td>
                        <td>${detail.shift.name}</td> 

                        <td>${detail.quantity}</td>
                    </tr>
                </c:forEach>
            <form action="../plandetails/view" method="get" style="display: inline;">
                <c:forEach var="detail" items="${planDetails}">
                        <input type="hidden" name="pdid" value="${detail.id}">
                        </c:forEach>
                        <button type="submit">Create</button>
                    </form>
        </tbody>
    </table>
</body>
</html>
