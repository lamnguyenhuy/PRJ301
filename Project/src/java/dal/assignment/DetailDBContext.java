package dal.assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.assignment.PlanDetail;
import model.assignment.Shift;

public class DetailDBContext extends DBContext {

    public List<PlanDetail> getPlanDetailsByPlanId(int planId) {
        List<PlanDetail> details = new ArrayList<>();
        String sql = "SELECT \n"
                + "    pd.date AS \"Date\",\n"
                + "    p.pid AS \"ProductID\",\n"
                + "    p.pname AS \"ProductName\",\n"
                + "    s.sname AS \"Shift\",\n"
                + "    pd.quantity AS \"Quantity\"\n"
                + "\n"
                + "FROM \n"
                + "    PlanDetails pd\n"
                + "JOIN \n"
                + "    PlanHeaders ph ON pd.phid = ph.phid\n"
                + "JOIN \n"
                + "    Products p ON ph.pid = p.pid\n"
                + "JOIN \n"
                + "    Shifts s ON pd.sid = s.sid\n"
                + "join Plans pl on pl.plid = ph.plid\n"
                + "\n"
                + "where pl.plid=?\n"
                + "\n"
                + "ORDER BY \n"
                + "    pd.date ASC,  s.sname ASC;";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, planId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PlanDetail detail = new PlanDetail();

                Date date = rs.getDate("Date");
                if (date != null) {
                    detail.setDate(date);
                }

                detail.setProductId(rs.getInt("ProductID"));
                detail.setProductName(rs.getString("ProductName"));
                detail.setQuantity(rs.getInt("Quantity"));

                Shift shift = new Shift();
                shift.setName(rs.getString("Shift"));
                detail.setShift(shift); 

                details.add(detail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return details;
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
