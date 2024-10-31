package dal.assignment;

import model.assignment.Plan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanDBContext extends DBContext {

    public List<Plan> getPlans() {
        List<Plan> plans = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "p.plid, "
                    + "p.plname, "
                    + "p.startdate, "
                    + "p.enddate, "
                    + "d.dname AS department_name, "
                    + "ph.quantity AS total_amount, "
                    + "COALESCE(ph.quantity - SUM(pd.quantity), ph.quantity) AS remained_amount, "
                    + "pr.pname AS product_name, "
                    + "pr.estimation AS product_estimation, "
                    + "s.statusname AS status_name "
                    + "FROM Plans p "
                    + "JOIN Departments d ON p.did = d.did "
                    + "LEFT JOIN Status s ON p.statusid = s.statusid "
                    + "JOIN PlanHeaders ph ON p.plid = ph.plid "
                    + "JOIN Products pr ON ph.pid = pr.pid "
                    + "LEFT JOIN PlanDetails pd ON ph.phid = pd.phid "
                    + "GROUP BY p.plid, p.plname, p.startdate, p.enddate, d.dname, ph.quantity, pr.pname, pr.estimation, s.statusname";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("plid"));
                plan.setName(rs.getString("plname"));
                plan.setStartDate(rs.getDate("startdate"));
                plan.setEndDate(rs.getDate("enddate"));
                plan.setTotalAmount(rs.getInt("total_amount"));
                plan.setRemainedAmount(rs.getInt("remained_amount"));
                plan.setProduct(rs.getString("product_name"));
                plan.setEstimation(rs.getFloat("product_estimation"));
                plan.setStatusName(rs.getString("status_name"));
                plans.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plans;
    }

    public void deletePlan(int id) {
        try {
            String sql = "delete from PlanDetails where pdid = ?\n"
                    + "delete from PlanHeaders where plid = ?\n"
                    + "delete from Plans where plid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, id);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
