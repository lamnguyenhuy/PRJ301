package controller.assignment.productionplan;

import controller.auth.BaseRBACController;
import dal.assignment.PlanDBContext;
import model.assignment.Plan;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.auth.User;

public class PlanListController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account)
            throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext();
        List<Plan> plans = db.getPlans();
        request.setAttribute("plans", plans);
        request.getRequestDispatcher("../view/productionplan/view.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
