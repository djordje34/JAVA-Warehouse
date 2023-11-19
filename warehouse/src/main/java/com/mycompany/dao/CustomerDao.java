/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class CustomerDao implements CustomerDaoInt{
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
            ps = con.prepareStatement("SELECT * FROM customers where CustomerId=?");
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
            ps = con.prepareStatement("DELETE FROM customers WHERE CustomerId=?");
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
            ps = con.prepareStatement("UPDATE customers SET CustomerName=?, ContactPerson=?, Address=?, City=?, PostCode=?, Country=? WHERE CustomerId=?");
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
            ps = con.prepareStatement("INSERT INTO customers(CustomerName, ContactPerson, Address, City, PostCode, Country) VALUES(?,?,?,?,?,?)");
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
                    ps = con.prepareStatement("SELECT * FROM customers");
                   
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        l.add(new Customer(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"),
                        rs.getString("Address"), rs.getString("City"),rs.getInt("PostCode"),rs.getString("Country")));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
}
