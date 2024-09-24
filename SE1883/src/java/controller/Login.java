 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author lam
 */
public class Login extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       response.getWriter().print("helpp");
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String account = request.getParameter("tk");    //input
        String password = request.getParameter("mk");
        String acc = getInitParameter("tk");
        String pass = getInitParameter("mk");
        
        if(account.equalsIgnoreCase(acc) && password.equals(pass)){
           out.append("<h1>Dang nhap thanh cong </h1>");
        }else{
            out.append("<h1>Sai tai khoan hoac mat khau</h1>");
        }
    }
}
