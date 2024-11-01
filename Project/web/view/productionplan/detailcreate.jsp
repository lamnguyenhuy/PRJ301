<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create Production Plan Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>

        <h2>Create Production Plan Details</h2>

        <form action="createPlanDetail" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Shift</th>
                        <th>Product</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="day" items="${days}">

                        <c:forEach var="shift" items="${shifts}">

                            <c:forEach var="product" items="${products}">
                                <tr>

                                    <c:if test="${shift eq shifts[0] && product eq products[0]}">
                                        <td rowspan="${shifts.size() * products.size()}">${day}</td>
                                    </c:if>

                                    <c:if test="${product eq products[0]}">
                                        <td rowspan="${products.size()}">${shift.name}</td>
                                    </c:if>

                                    <td>${product.name}</td>
                                    <td>
                                        <input type="number" name="quantity_${day}_${shift.id}_${product.id}" min="0" value="0" required>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <button type="submit">Submit</button>
        </form>

    </body>
</html>
