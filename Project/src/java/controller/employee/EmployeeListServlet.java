
package controller.employee;

import dal.assignment.EmployeeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.assignment.Employee;

public class EmployeeListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDBContext employeeDB = new EmployeeDBContext();
        ArrayList<Employee> employees = employeeDB.getEmployees();
        
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("../view/employee/view.jsp").forward(request, response);
    }
}

