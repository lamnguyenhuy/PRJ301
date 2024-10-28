package controller.employee;

import dal.assignment.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EmployeeDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));

        UserDBContext db = new UserDBContext();
        db.deleteEmployee(uid);


        response.sendRedirect("../view/employee/view.jsp");
    }
}
