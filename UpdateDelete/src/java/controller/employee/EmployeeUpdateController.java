/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.employee;

import controller.auth.BaseRBACController;
import controller.auth.BaseRequiredAuthenticationController;
import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import model.auth.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Employee;

/**
 *
 * @author sonnt-local hand-some
 */
public class EmployeeUpdateController extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
    
    //read parameters
        String raw_id =req.getParameter("id");
        String raw_name = req.getParameter("name");
        String raw_dob = req.getParameter("dob");
        String raw_gender = req.getParameter("gender");
        String raw_address = req.getParameter("address");
        String raw_did = req.getParameter("did");
        
        //validate data
        
        //object binding
        Employee e = new Employee();
        e.setId(Integer.parseInt(raw_id));
        e.setName(raw_name);
        e.setGender(raw_gender.equals("male"));
        e.setDob(Date.valueOf(raw_dob));
        e.setAddress(raw_address);
        
        Department d = new Department();
        d.setId(Integer.parseInt(raw_did));
        e.setDept(d);
        
        e.setUpdatedby(account);
        //save data
        EmployeeDBContext db = new EmployeeDBContext();
        db.update(e);
        //response to user
        resp.getWriter().println("Done");
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        EmployeeDBContext dbEmp = new EmployeeDBContext();
        Employee e = dbEmp.get(id);
        if(e!=null)
        {
            DepartmentDBContext dbDept = new DepartmentDBContext();
            ArrayList<Department> depts = dbDept.list();
            req.setAttribute("e", e);
            req.setAttribute("depts", depts);
            req.getRequestDispatcher("../view/employee/update.jsp").forward(req, resp);
        }
        else
        {
            resp.sendError(404, "employee does not exist!");
        }
    
    }
   
   
    

}
