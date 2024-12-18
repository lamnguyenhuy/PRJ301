<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Production Plan List</title>
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
        text-align: center; /* Center-align text in columns */
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

    .delete-button {
        background-color: #f44336;
        color: white;
        border: none;
        padding: 5px 10px;
        border-radius: 5px;
        cursor: pointer;
    }

    .delete-button, .view-button:hover {
        opacity: 0.8;
    }
    
    .view-button {
        background-color: green;
        color: white;
        border: none;
        padding: 5px 10px;
        border-radius: 5px;
        cursor: pointer;
    }


</style>



</head>
<body>

<h2>Production Plan List</h2>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>StartDate</th>
            <th>EndDate</th>
            <th>Remained Amount</th>
            <th>Total Amount</th>
            <th>Product</th>
            <th>Estimation</th>
            <th></th>
             <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="plan" items="${plans}">
            <tr>
                <td>${plan.id}</td>
                <td style="color: green;"><strong>${plan.name}</strong></td>
                <td>${plan.startDate}</td>
                <td>${plan.endDate}</td>
                <td>${plan.remainedAmount}</td>
                <td>${plan.totalAmount}</td>
                <td>${plan.product}</td>
                <td>${plan.estimation}</td>
                 <td>
                    <form action="../plandetails/view" method="get" style="display: inline;">
                        <input type="hidden" name="id" value="${plan.id}">
                        <button type="submit" class="view-button">View Details</button>
                    </form>
                </td>
                <td>
                    <form action="delete" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="${plan.id}">
                        <button type="submit" class="delete-button" onclick="return confirm('Are you sure you want to delete this plan?')">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const table = document.querySelector("table");
        const rows = table.querySelectorAll("tbody tr");

        function mergeCells(columnIndex) {
            let previousCell = null;
            let rowspan = 1;

            rows.forEach(row => {
                const cell = row.cells[columnIndex];
                if (previousCell && cell.innerHTML === previousCell.innerHTML) {
                    rowspan++;
                    cell.style.display = "none";
                    previousCell.rowSpan = rowspan;
                } else {
                    previousCell = cell;
                    rowspan = 1;
                }
            });
        }

        mergeCells(0);
        mergeCells(1);
        mergeCells(2); 
        mergeCells(3); 
        mergeCells(8); 
        mergeCells(9); 
    });
</script>

</body>
</html>
