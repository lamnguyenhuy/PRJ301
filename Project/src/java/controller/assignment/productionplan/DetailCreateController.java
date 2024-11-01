package controller.assignment.productionplan;

import dal.assignment.DetailDBContext;
import dal.assignment.ProductDBContext;
import dal.assignment.ShiftDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.assignment.PlanDetail;
import model.assignment.Product;
import model.assignment.Shift;

public class DetailCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String startDateParam = request.getParameter("startdate");
        String endDateParam = request.getParameter("enddate");

        Date startDate = Date.valueOf(startDateParam);  
        Date endDate = Date.valueOf(endDateParam);


        List<Date> dateList = generateDateList(startDate, endDate);


        ShiftDBContext shiftDB = new ShiftDBContext();
        ProductDBContext productDB = new ProductDBContext();
        List<Shift> shifts = shiftDB.getShifts();     
        List<Product> products = productDB.list(); 


        request.setAttribute("dateList", dateList);
        request.setAttribute("shifts", shifts);
        request.setAttribute("products", products);


        request.getRequestDispatcher("/view/productionplan/detail_create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DetailDBContext detailDB = new DetailDBContext();


        List<Date> dateList = (List<Date>) request.getAttribute("dateList");
        List<Shift> shifts = (List<Shift>) request.getAttribute("shifts");
        List<Product> products = (List<Product>) request.getAttribute("products");

        for (Date date : dateList) {
            for (Shift shift : shifts) {
                for (Product product : products) {
                    String quantityParam = request.getParameter("quantity_" + date + "_" + shift.getId() + "_" + product.getId());
                    int quantity = Integer.parseInt(quantityParam);

                    PlanDetail detail = new PlanDetail();
                    detail.setDate(date);
                    detail.setShift(shift);
                    detail.setProduct(product);
                    detail.setQuantity(quantity);

                    detailDB.insert(detail); 
                }
            }
        }

        response.sendRedirect("view");
    }

    private List<Date> generateDateList(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            dateList.add(new Date(calendar.getTimeInMillis()));  
            calendar.add(Calendar.DATE, 1); 
        }

        return dateList;
    }
}
