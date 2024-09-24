package controller;

import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

public class CreateStudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name, id, dob, gender;
        name = request.getParameter("name");
        id = request.getParameter("id");
        dob = request.getParameter("dob");
        gender = request.getParameter("gender");
        
        Student student = new Student(name, id, dob, gender);
        response.getWriter().println("Name: "+ name);
        response.getWriter().println("Id: "+ id);
        response.getWriter().println("Dob: "+ dob);
        response.getWriter().println("Gender: "+ gender);
    }
}
