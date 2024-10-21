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
        
        if(id != null)
        {
            sql += " AND e.eid = ?";
            paramValues.add(id);
        }
        
        if(name != null)
        {
            sql+=" AND e.ename LIKE '%'+?+'%'"; 
            paramValues.add(name);
        }
        
        if(gender != null)
        {
            sql+=" AND e.gender = ?";
            paramValues.add(gender);
        }
        
        if(address != null)
        {
            sql += " AND e.address LIKE '%' + ? + '%'";
            paramValues.add(address);
        }
        
        if(from != null)
        {
            sql += " AND e.dob >= ? ";
            paramValues.add(from);
        }
        if(to != null)
        {
            sql += " AND e.dob <= ? ";
            paramValues.add(to);
        }
        if(did != null)
        {
            sql += " AND e.did = ?";
            paramValues.add(did);
        }
        
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i+1), paramValues.get(i));
            }
            
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
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
        }
        finally
        {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
