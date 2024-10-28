/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.assignment;

import java.util.ArrayList;
import model.auth.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.assignment.Department;
import model.assignment.Employee;
import model.assignment.Salary;
import model.auth.Feature;
import model.auth.Role;

public class UserDBContext extends DBContext<User> {

    public ArrayList<Role> getRoles(String username) {
        PreparedStatement stm = null;
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.rid, r.rname, f.fid, f.fname, f.url "
                    + "FROM Users u "
                    + "INNER JOIN UserRoles ur ON u.uid = ur.uid "
                    + "INNER JOIN Roles r ON r.rid = ur.rid "
                    + "INNER JOIN RoleFeatures rf ON rf.rid = r.rid "
                    + "INNER JOIN Features f ON f.fid = rf.fid "
                    + "WHERE u.username = ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            Role crole = new Role();
            crole.setId(-1);
            while (rs.next()) {
                int rid = rs.getInt("rid");
                if (rid != crole.getId()) {
                    crole = new Role();
                    crole.setId(rid);
                    crole.setName(rs.getString("rname"));
                    roles.add(crole);
                }

                Feature f = new Feature();
                f.setId(rs.getInt("fid"));
                f.setName(rs.getString("fname"));
                f.setUrl(rs.getString("url"));

                f.setRoles(roles);
                crole.getFeatures().add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public User get(String username, String password) {
        User user = null;
        PreparedStatement stm = null;
        try {
            String sql = "SELECT [username],[password] FROM [Users]\n"
                    + "WHERE [username] = ? AND [password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(username);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public Employee getEmployeeById(int uid) {
        Employee employee = null;
        try {
            String sql = "SELECT e.eid, e.ename, e.phonenumber, e.address, e.did, e.sid, "
                    + "d.dname AS department_name, s.salary AS salary_amount "
                    + "FROM Employees e "
                    + "INNER JOIN Departments d ON e.did = d.did "
                    + "INNER JOIN Salaries s ON e.sid = s.sid "
                    + "WHERE e.eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("eid"));
                employee.setName(rs.getString("ename"));
                employee.setPhoneNumber(rs.getString("phonenumber"));
                employee.setAddress(rs.getString("address"));

                Department department = new Department();
                department.setId(rs.getInt("did"));
                department.setName(rs.getString("department_name"));
                employee.setDepartment(department);

                Salary salary = new Salary();
                salary.setId(rs.getInt("sid"));
                salary.setAmount((float) rs.getDouble("salary_amount"));
                employee.setSalary(salary);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateEmployee(int uid, String name, String phoneNumber, String address, int departmentId, int salaryId) {
        try {
            String sql = "UPDATE Employees SET ename = ?, phonenumber = ?, address = ?, did = ?, sid = ? "
                    + "WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, phoneNumber);
            stm.setString(3, address);
            stm.setInt(4, departmentId);
            stm.setInt(5, salaryId);
            stm.setInt(6, uid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteEmployee(int uid) {
        try {
            String sql = "DELETE FROM Employees WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
