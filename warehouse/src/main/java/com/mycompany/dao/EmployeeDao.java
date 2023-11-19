/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.data.Employee;
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
public class EmployeeDao implements EmployeeDaoInt{
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
            ps = con.prepareStatement("SELECT * FROM employees where EmployeeId=?");
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
            ps = con.prepareStatement("DELETE FROM employees WHERE EmployeeId=?");
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
            ps = con.prepareStatement("UPDATE customers SET LastName=?, FirstName=?, BirthDate=? WHERE EmployeeId=?");
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
            ps = con.prepareStatement("INSERT INTO employees(LastName, FirstName, BirthDate) VALUES(?,?,?)");
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
                    ps = con.prepareStatement("SELECT * FROM employees");
                   
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        l.add(new Employee(rs.getInt("EmployeeId"), rs.getString("LastName"), 
                                rs.getString("FirstName"), rs.getDate("BirthDate")));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
}
