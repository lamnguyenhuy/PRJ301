package controller.employee;

import controller.auth.BaseRBACController;
import dal.assignment.EmployeeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.assignment.Employee;
import model.auth.User;

public class EmployeeListServlet extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        EmployeeDBContext employeeDB = new EmployeeDBContext();
        ArrayList<Employee> employees = employeeDB.getEmployees();

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("../view/employee/view.jsp").forward(request, response);
    }
}
