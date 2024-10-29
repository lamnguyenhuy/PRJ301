package controller.assignment.productionplan;

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


public class PlanListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext();
        List<Plan> plans = db.getPlans();
        request.setAttribute("plans", plans);
        request.getRequestDispatcher("../view/productionplan/view.jsp").forward(request, response);
    }
}
