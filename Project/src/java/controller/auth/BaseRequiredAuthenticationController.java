package controller.auth;

import model.auth.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("account");
        return (user != null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            User user = (User) req.getSession().getAttribute("account");
            doPost(req, resp, user);
        } else {
            resp.sendRedirect("../login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            User user = (User) req.getSession().getAttribute("account");
            doGet(req, resp, user);
        } else {
            resp.sendRedirect("../login");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException;
}
