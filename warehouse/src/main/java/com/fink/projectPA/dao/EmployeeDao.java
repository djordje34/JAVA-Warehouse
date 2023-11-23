/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;
import com.fink.projectPA.data.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Djordje
 */
public class EmployeeDao{
    private static final EmployeeDao instance = new EmployeeDao();
    
    private EmployeeDao(){
    
}
    public static EmployeeDao getInstance(){
        return instance;
    }
    
    public Employee find(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            ps = con.prepareStatement("SELECT"
                    + " * "
                    + "FROM "
                    + "employees "
                    + "WHERE "
                    + "EmployeeId=?");
            ps.setInt(1, dataObjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee(rs.getInt("EmployeeId"), rs.getString("LastName"), rs.getString("FirstName"),
                        rs.getDate("BirthDate"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employee;
    }
    
    public void delete(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("DELETE "
                    + "FROM "
                    + "employees "
                    + "WHERE "
                    + "EmployeeId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection con, Employee employee) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("UPDATE "
                    + "employees "
                    + "SET LastName=?, FirstName=?, BirthDate=? "
                    + "WHERE "
                    + "EmployeeId=?");
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setDate(3, (Date) employee.getBirthDate());
            ps.setInt(4, employee.getEmployeeId());
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    
    public void create(Connection con, Employee employee) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement("INSERT INTO "
                    + "employees(LastName, FirstName, BirthDate) "
                    + "VALUES(?,?,?)");
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setDate(3, (Date) employee.getBirthDate());
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(rs, ps);
        }
    }
    
    
    public List<Employee> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Employee> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT"
                            + " * "
                            + "FROM "
                            + "employees");
                   
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        l.add(new Employee(rs.getInt("EmployeeId"), rs.getString("LastName"), 
                                rs.getString("FirstName"), rs.getDate("BirthDate")));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
    
        public Employee getBestSelling(Connection con) throws SQLException {
        PreparedStatement ps =null;
        ResultSet rs = null;
        Employee emp = null;
        try{
            ps = con.prepareStatement("SELECT " +
                        "e.EmployeeId, " +
                        "SUM(p.PricePerUnit * od.Quantity) AS uk_cena " +
                        "FROM " +
                        "employees e " +
                        "JOIN " +
                        "orders o ON e.EmployeeId = o.EmployeeId " +
                        "JOIN " +
                        "orderdetails od ON o.OrderId = od.OrderId " +
                        "JOIN " +
                        "products p ON od.ProductId = p.ProductId " +
                        "GROUP BY " +
                        "e.EmployeeId, e.LastName, e.FirstName " +
                        "ORDER BY " +
                        "uk_cena DESC " +
                        "LIMIT 1;");
            rs = ps.executeQuery();
            if(rs.next()){
                int employeeId = rs.getInt("EmployeeId");
               emp = instance.find(con, employeeId);
            }
        }
        finally{
            ResourcesManager.closeResources(rs, ps);
        } 
        return emp;
    }
    
}
