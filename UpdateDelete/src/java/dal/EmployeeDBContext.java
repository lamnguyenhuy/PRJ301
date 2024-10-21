/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Employee;
import model.auth.User;

/**
 *
 * @author sonnt-local
 */
public class EmployeeDBContext extends DBContext<Employee> {

    public ArrayList<Employee> search(Integer id, String name, Boolean gender, Date from, Date to, String address, Integer did) {
        String sql = "SELECT e.eid,e.ename,e.gender,e.address,e.dob,d.did,d.dname FROM Employee e \n"
                + "INNER JOIN Department d ON d.did = e.did\n"
                + "WHERE (1=1)";
        ArrayList<Employee> emps = new ArrayList<>();
        ArrayList<Object> paramValues = new ArrayList<>();

        if (id != null) {
            sql += " AND e.eid = ?";
            paramValues.add(id);
        }

        if (name != null) {
            sql += " AND e.ename LIKE '%'+?+'%'";
            paramValues.add(name);
        }

        if (gender != null) {
            sql += " AND e.gender = ?";
            paramValues.add(gender);
        }

        if (address != null) {
            sql += " AND e.address LIKE '%' + ? + '%'";
            paramValues.add(address);
        }

        if (from != null) {
            sql += " AND e.dob >= ? ";
            paramValues.add(from);
        }
        if (to != null) {
            sql += " AND e.dob <= ? ";
            paramValues.add(to);
        }
        if (did != null) {
            sql += " AND e.did = ?";
            paramValues.add(did);
        }

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i + 1), paramValues.get(i));
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getString("address"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                e.setDept(d);
                emps.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return emps;
    }

    @Override
    public void insert(Employee model) {
        String sql_insert = ""
                + "INSERT INTO [Employee]\n"
                + "           ([ename]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[address]\n"
                + "           ,[did]\n"
                + "           ,[createdby])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)\n";
        String sql_getEid = "SELECT @@IDENTITY as eid";

        PreparedStatement stm_insert = null;
        PreparedStatement stm_getEid = null;

        try {
            connection.setAutoCommit(false);
            stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setString(1, model.getName());
            stm_insert.setBoolean(2, model.isGender());
            stm_insert.setDate(3, model.getDob());
            stm_insert.setString(4, model.getAddress());
            stm_insert.setInt(5, model.getDept().getId());
            stm_insert.setString(6, model.getCreatedby().getUsername());
            stm_insert.executeUpdate();

            stm_getEid = connection.prepareStatement(sql_getEid);
            ResultSet rs = stm_getEid.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("eid"));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Employee model) {
        String sql_update = "UPDATE [Employee]\n"
                + "   SET [ename] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[did] = ?\n"
                + "      ,[updatedby] = ?\n"
                + "      ,[updatedtime] = GETDATE()\n"
                + " WHERE eid = ?";
        PreparedStatement stm_update = null;
        try {

            stm_update = connection.prepareStatement(sql_update);
            stm_update.setString(1, model.getName());
            stm_update.setBoolean(2, model.isGender());
            stm_update.setDate(3, model.getDob());
            stm_update.setString(4, model.getAddress());
            stm_update.setInt(5, model.getDept().getId());
            stm_update.setString(6, model.getUpdatedby().getUsername());
             stm_update.setInt(7, model.getId());
            stm_update.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Employee model) {
        String sql_update = "DELETE FROM Employee\n"
                + " WHERE eid = ?";
        PreparedStatement stm_update = null;
        try {

            stm_update = connection.prepareStatement(sql_update);
            stm_update.setInt(1, model.getId());
            stm_update.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Employee> list() {
        ArrayList<Employee> emps = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT [eid]\n"
                    + "      ,[ename]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[address]\n"
                    + "  FROM [Employee]";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getString("address"));
                emps.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return emps;
    }

    @Override
    public Employee get(int id) {
        PreparedStatement stm = null;
        try {
            String sql = "SELECT \n"
                    + "	e.eid,e.ename,e.gender,e.dob,e.[address],\n"
                    + "	e.did,d.dname,\n"
                    + "	c.username as [cusername],c.displayname as [cdisplayname],\n"
                    + "	u.username as [uusername],u.displayname as [udisplayname],\n"
                    + "	e.updatedtime\n"
                    + "FROM Employee e INNER JOIN Department d ON d.did = e.did\n"
                    + "		INNER JOIN [User] c ON c.username = e.createdby\n"
                    + "		LEFT JOIN [User] u ON e.updatedby = u.username\n"
                    + "WHERE e.eid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getString("address"));
                e.setUpdatedtime(rs.getTimestamp("updatedtime"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                e.setDept(d);

                User c = new User();
                c.setUsername(rs.getString("cusername"));
                c.setDisplayname(rs.getString("cdisplayname"));
                e.setCreatedby(c);

                String uusername = rs.getString("uusername");
                if (uusername != null) {
                    User u = new User();
                    u.setUsername(rs.getString("uusername"));
                    u.setDisplayname(rs.getString("udisplayname"));
                    e.setUpdatedby(u);
                }

                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
