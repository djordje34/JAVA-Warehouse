/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;
import com.fink.projectPA.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Djordje
 */
public class CustomerDao{
    private static final CustomerDao instance = new CustomerDao();
    
    private CustomerDao(){
    }
    
    public static CustomerDao getInstance() {
        return instance;
    }
    
    public Customer find(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT"
                    + " * "
                    + "FROM "
                    + "customers "
                    + "WHERE "
                    + "CustomerId=?");
            ps.setInt(1, dataObjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"),
                        rs.getString("Address"), rs.getString("City"),rs.getInt("PostCode"),rs.getString("Country"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }
    
    public void delete(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("DELETE "
                    + "FROM "
                    + "customers "
                    + "WHERE "
                    + "CustomerId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection con, Customer customer) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("UPDATE "
                    + "customers "
                    + "SET CustomerName=?, ContactPerson=?, Address=?, City=?, PostCode=?, Country=? "
                    + "WHERE "
                    + "CustomerId=?");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, (int) customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, customer.getCustomerId());
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void create(Connection con, Customer customer) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement("INSERT INTO "
                    + "customers(CustomerName, ContactPerson, Address, City, PostCode, Country) "
                    + "VALUES(?,?,?,?,?,?)");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, (int) customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(rs, ps);
        }
    }
    
    public List<Customer> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Customer> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT"
                            + " * "
                            + "FROM "
                            + "customers");
                   
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        l.add(new Customer(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"),
                        rs.getString("Address"), rs.getString("City"),rs.getInt("PostCode"),rs.getString("Country")));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
    public List<String> getAllWithOrders(Connection con) throws SQLException {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<String> l = new ArrayList<>();

    try {
        ps = con.prepareStatement("SELECT " +
                "c.CustomerName, " +
                "o.OrderId " +
                "FROM " +
                "customers c " +
                "JOIN " +
                "orders o ON c.CustomerId = o.CustomerId " +
                "ORDER BY " +
                "c.CustomerName");

        rs = ps.executeQuery();
        while (rs.next()) {
            String customerName = rs.getString("CustomerName");
            int orderId = rs.getInt("OrderId");
            String resultString = customerName + " " + orderId;
            l.add(resultString);
        }
    } finally {
        ResourcesManager.closeResources(rs, ps);
    }

    return l;
    }
    
    public List<Customer> getBestCustomers(Connection con) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> l = new ArrayList<>();
        try{
            ps = con.prepareStatement("SELECT " +
                "c.*,  " +
                "SUM(p.PricePerUnit * od.Quantity) AS uk_cena " +
                "FROM " +
                "customers c " +
                "JOIN " +
                "orders o ON c.CustomerId = o.CustomerId " +
                "JOIN " +
                "orderdetails od ON o.OrderId = od.OrderId " +
                "JOIN " +
                "products p ON od.ProductId = p.ProductId " +
                "GROUP BY " +
                "c.CustomerId " +
                "ORDER BY " +
                "uk_cena DESC " +
                "LIMIT 4");
            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("CustomerId: "+ rs.getInt("CustomerId")+ " Total money spent: "+ rs.getInt("uk_cena"));
                l.add(new Customer(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"),
           rs.getString("Address"), rs.getString("City"),rs.getInt("PostCode"),rs.getString("Country")));
            }
        }
        finally{
            ResourcesManager.closeResources(rs,ps);
        }
        return l;
    }
    
}
