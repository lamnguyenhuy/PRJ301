/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Dog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author lam
 */
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dog dog = (Dog) req.getSession().getAttribute("dog");

        if (dog == null) {
            dog = new Dog();
        }
        
/*
        dog.setName((String) req.getAttribute("name"));
        dog.setAge(Integer.parseInt((String) req.getAttribute("age")));
        dog.setGender((String) req.getAttribute("gender"));
        HttpSession session = req.getSession();
        session.setAttribute("dog", dog);
        resp.sendRedirect("skill");
*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dog dog = (Dog) req.getSession().getAttribute("dog");
        if (dog != null) {
            req.setAttribute("dog", dog);
        } else {
            req.getRequestDispatcher("view/register.jsp").forward(req, resp);
        }
    }

}
