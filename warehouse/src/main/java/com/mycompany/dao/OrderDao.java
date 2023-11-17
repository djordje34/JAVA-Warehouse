/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.data.Customer;
import com.mycompany.data.Employee;
import com.mycompany.data.Order;
import com.mycompany.data.Product;
import com.mycompany.data.Shipper;
import com.mycompany.data.Supplier;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class OrderDao {
    private static OrderDao instance = new OrderDao();
    
    private OrderDao(){
}
    public static OrderDao getInstance(){
        return instance;
    }
    
    
    public Order find(Connection con, int dataObjectId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = con.prepareStatement("SELECT * FROM orders WHERE OrderId=?");
            ps.setInt(1, dataObjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(con,rs.getInt("CustomerId"));
                Shipper shipper = ShipperDao.getInstance().find(con, rs.getInt("ShipperId"));
                Employee employee = EmployeeDao.getInstance().find(con, rs.getInt("EmployeeId"));
                order = new Order(rs.getInt("OrderId"),rs.getDate("OrderDate"),shipper,customer,employee);
                
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return order;
    }
    
    
    public void delete(Connection con, int dataObjectId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM orders WHERE OrderId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection con, Order order) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE orders SET OrderDate=?, ShipperId=?, CustomerId=?, EmployeeId=? WHERE OrderId=?");
            ps.setDate(1, (Date) order.getOrderDate());
            ps.setInt(2, order.getShipper().getShipperId());
            ps.setInt(3, order.getCustomer().getCustomerId());
            ps.setInt(4, order.getEmployee().getEmployeeId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    
    public int create(Connection con, Order order) throws SQLException, WarehouseException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO orders(OrderDate, ShipperId, CustomerId, EmployeeId) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (Date) order.getOrderDate());
            
            Shipper shipper = ShipperDao.getInstance().find(con, order.getShipper().getShipperId());
            Customer customer = CustomerDao.getInstance().find(con, order.getCustomer().getCustomerId());
            Employee employee = EmployeeDao.getInstance().find(con, order.getEmployee().getEmployeeId());
            if (shipper == null) {
                throw new WarehouseException("Shipper " + order.getShipper() + " doesn't exist in database.");
            }
            else if(customer == null){
                throw new WarehouseException("Customer " + order.getCustomer() + " doesn't exist in database.");
            }
            else if(employee == null){
                throw new WarehouseException("Employee " + order.getEmployee() + " doesn't exist in database.");
            }
            
            ps.setInt(2, shipper.getShipperId());
            ps.setInt(3, customer.getCustomerId());
            ps.setInt(4, employee.getEmployeeId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    
    public List<Order> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Order> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT * FROM orders");
                   
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        Shipper shipper = ShipperDao.getInstance().find(con, rs.getInt("ShipperId"));
                        Customer customer = CustomerDao.getInstance().find(con, rs.getInt("CustomerId"));
                        Employee employee = EmployeeDao.getInstance().find(con, rs.getInt("EmployeeId"));
            
                        l.add(new Order(rs.getInt("OrderId"), rs.getDate("OrderDate"), 
                                shipper, customer, employee));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
}
