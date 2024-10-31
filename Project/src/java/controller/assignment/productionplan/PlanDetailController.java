package controller.assignment.productionplan;

import dal.assignment.DetailDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.assignment.PlanDetail;

public class PlanDetailController extends HttpServlet {

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String planIdParam = request.getParameter("planId");
    int planId;

    try {
        if (planIdParam != null) {
            planId = Integer.parseInt(planIdParam);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing planId parameter");
            return;
        }
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid planId format");
        return;
    }

    DetailDBContext db = new DetailDBContext();
    List<PlanDetail> planDetails = db.getPlanDetailsByPlanId(planId);

    request.setAttribute("planDetails", planDetails);
    request.getRequestDispatcher("../view/productionplan/detail.jsp").forward(request, response);
}
}
