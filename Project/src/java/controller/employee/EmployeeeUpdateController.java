
package controller.employee;

import dal.assignment.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.assignment.Employee;

public class EmployeeeUpdateController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        

        Employee employee = new UserDBContext().getEmployeeById(uid);
        request.setAttribute("employee", employee);
        

        request.getRequestDispatcher("../view/employee/edit.jsp").forward(request, response);; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int uid = Integer.parseInt(request.getParameter("uid"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        int salaryId = Integer.parseInt(request.getParameter("salaryId"));
        

        UserDBContext db = new UserDBContext();
        db.updateEmployee(uid, name, phoneNumber, address, departmentId, salaryId);
        

        response.sendRedirect("../view/employee/view.jsp");
    }
    
    
}
